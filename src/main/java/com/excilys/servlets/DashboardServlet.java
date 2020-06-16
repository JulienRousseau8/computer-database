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

@WebServlet("/Dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ComputerService computerService = new ComputerService();
	List<ComputerDTO> listComputerDTO = new ArrayList<ComputerDTO>();
	List<ComputerDTO> searchComputerList = new ArrayList<ComputerDTO>();
	List<ComputerDTO> orderComputerList = new ArrayList<ComputerDTO>();

	private int pageTaille = 10;
	private int pageNum;
	
	
    public static final String DASHBOARD = "/WEB-INF/views/dashboard.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connexion.getDbCon();

		int nbComputers = computerService.countAllComputer();
		Pagination page = new Pagination(nbComputers, pageTaille);
		String search = null;
		String orderBy = null;
		
		
		if(request.getParameter("pageTaille")!=null) {
			pageTaille = Integer.parseInt(request.getParameter("pageTaille"));
			page.setPageTaille(pageTaille);
		}

		if(request.getParameter("pageNum")!=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			page.setPageNum(pageNum);

		}

		search = request.getParameter("search");
		orderBy = request.getParameter("orderBy");
		
		if(search!=null && (orderBy==null || orderBy.isEmpty())) {
			listComputerDTO = ComputerDTOMapper.listComputerToDto(computerService.getSearchComputersPage(search, page));
			nbComputers = ComputerDTOMapper.listComputerToDto(computerService.getSearchComputers(search)).size();
		}
		else if(orderBy!=null && (search==null || search.isEmpty())) {
			listComputerDTO = ComputerDTOMapper.listComputerToDto(computerService.getComputersOrdered(page, orderBy));
			System.out.println(listComputerDTO.toString());
		}
		else {
			listComputerDTO = ComputerDTOMapper.listComputerToDto(computerService.getPageComputer(page));
		}
		
		request.setAttribute("search", search);
		request.setAttribute("pageMax", page.getPageMax());
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("orderBy", orderBy);
		request.setAttribute("nbComputers", nbComputers);
		request.setAttribute("computerList", listComputerDTO);
		
		request.getRequestDispatcher(DASHBOARD).forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] idaSupprimer = request.getParameter("selection").split(",");
		
		for(String id : idaSupprimer) {
			computerService.deleteComputer(Integer.parseInt(id));
		}

		response.sendRedirect("Dashboard");
	}
}
