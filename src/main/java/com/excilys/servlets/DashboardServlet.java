package com.excilys.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Pagination;
import com.excilys.service.ComputerService;
import com.excilys.service.DashboardService;

@WebServlet("/Dashboard")
@Controller
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	ComputerService computerService;
	@Autowired
	DashboardService dashboardService;
	@Autowired
	ComputerDTOMapper computerMapper;
	List<ComputerDTO> listComputerDTO = new ArrayList<ComputerDTO>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);	
	}
	
	private int pageTaille =10;
	private int pageNum;
	private int nbComputers;
	private int pageMax;
	Pagination page = new Pagination(nbComputers, pageTaille);
	int direction = 1;

    public static final String DASHBOARD = "/WEB-INF/views/dashboard.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		nbComputers = dashboardService.countAllComputer();
		String search = null;
		String orderBy = null;
		
		getPageTailleFromJsp(request);
		getPageNumFromJsp(request);
		search = request.getParameter("search");
		orderBy = request.getParameter("orderBy");
		
		viewChoice(request, search, orderBy);
		setAttributes(request, page, search, orderBy, direction, nbComputers, listComputerDTO);
		request.getRequestDispatcher(DASHBOARD).forward(request, response);	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] idaSupprimer = request.getParameter("selection").split(",");
		for(String id : idaSupprimer) {
			computerService.deleteComputer(Integer.parseInt(id));
		}
		response.sendRedirect("Dashboard");
	}
	
	
	private void setAttributes(HttpServletRequest request, 
	        Pagination page, String search, String orderBy, int direction, int nbComputers, List<ComputerDTO> listComputerDTO) {
		request.setAttribute("search", search);
		request.setAttribute("pageMax", pageMax);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("orderBy", orderBy);
		request.setAttribute("direction", direction);
		request.setAttribute("nbComputers", nbComputers);
		request.setAttribute("computerList", listComputerDTO);
	}
	
	private List<ComputerDTO> viewChoice(HttpServletRequest request, String search, String orderBy){
		if(search!=null && (orderBy==null || orderBy.isEmpty())) {
			listComputerDTO = computerMapper.listComputerToDto(dashboardService.getSearchComputersPage(search, page));
			nbComputers = computerMapper.listComputerToDto(dashboardService.getSearchComputers(search)).size();
		}
		else if(orderBy!=null && (search==null || search.isEmpty())) {
			direction = Integer.parseInt(request.getParameter("direction"))%2;
			listComputerDTO = computerMapper.listComputerToDto(dashboardService.getComputersOrdered(page, orderBy, direction));
		}
		else {
			listComputerDTO = computerMapper.listComputerToDto(dashboardService.getPageComputer(page));
		}
		return listComputerDTO;
	}
	

	private void getPageTailleFromJsp(HttpServletRequest request) {
		if(request.getParameter("pageTaille")!=null) {
			pageTaille = Integer.parseInt(request.getParameter("pageTaille"));
			page.setPageTaille(pageTaille);
			pageMax = nbComputers / pageTaille;
		}
	}

	private void getPageNumFromJsp(HttpServletRequest request) {
		if(request.getParameter("pageNum")!=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			page.setPageNum(pageNum);
		}
	}

}
