package com.excilys.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Pagination;
import com.excilys.persistence.Connexion;
import com.excilys.service.ComputerService;
import com.excilys.service.DashboardService;

@WebServlet("/Dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ComputerService computerService = new ComputerService();
	DashboardService dashboardService = new DashboardService();
	List<ComputerDTO> listComputerDTO = new ArrayList<ComputerDTO>();

	private int pageTaille =10;
	private int pageNum;
	private int nbComputers;
	private int pageMax;
	Pagination page = new Pagination(nbComputers, pageTaille);
	int direction = 1;

    public static final String DASHBOARD = "/WEB-INF/views/dashboard.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connexion.getDbCon();
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
			nbComputers = ComputerDTOMapper.listComputerToDto(dashboardService.getSearchComputers(search)).size();
			listComputerDTO = ComputerDTOMapper.listComputerToDto(dashboardService.getSearchComputersPage(search, page));
		}
		else if(orderBy!=null && (search==null || search.isEmpty())) {
			direction = Integer.parseInt(request.getParameter("direction"))%2;
			listComputerDTO = ComputerDTOMapper.listComputerToDto(dashboardService.getComputersOrdered(page, orderBy, direction));
		}
		else {
			listComputerDTO = ComputerDTOMapper.listComputerToDto(dashboardService.getPageComputer(page));
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
