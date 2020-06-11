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

	private int pageTaille = 10;
	private int pageNum;
	
    public static final String DASHBOARD = "/WEB-INF/views/dashboard.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connexion.getDbCon();

		int nbComputers = computerService.countAllComputer();
		Pagination page = new Pagination(nbComputers, pageTaille);
		
		
		if(request.getParameter("pageTaille")!=null) {
			pageTaille = Integer.parseInt(request.getParameter("pageTaille"));
			page.setPageTaille(pageTaille);
		}

		if(request.getParameter("pageNum")!=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			page.setPageNum(pageNum);
		}
		
		listComputerDTO = ComputerDTOMapper.listComputerToDto(computerService.getPageComputer(page));
		
		request.setAttribute("pageMax", page.getPageMax());
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("nbComputers", nbComputers);
		request.setAttribute("computerList", listComputerDTO);
		
		request.getRequestDispatcher(DASHBOARD).forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getParameter("selection");
		System.out.println(request.getParameter("selection"));

		//computerService.deleteComputer(computerId);
		response.sendRedirect("Dashboard");
	}
}
