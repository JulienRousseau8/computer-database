package com.excilys.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyDTOMapper;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@WebServlet("/EditComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ComputerService computerService = new ComputerService();
	CompanyService companyService = new CompanyService();
	List<CompanyDTO> listCompanyDTO = new ArrayList<CompanyDTO>();
	ComputerDTO computerDto;

	public static final String EDITCOMPUTER = "/WEB-INF/views/editComputer.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listCompanyDTO = CompanyDTOMapper.listCompanyToDto(companyService.getAllCompanies());
		request.setAttribute("listCompanyDTO", listCompanyDTO);
		
		String computerId = request.getParameter("computerId");
		computerDto = ComputerDTOMapper.computerToDto(computerService.getComputerById(computerId).get());
		
		request.setAttribute("computerDto", computerDto);
		request.getRequestDispatcher(EDITCOMPUTER).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String computerId = request.getParameter("computerId");
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		CompanyDTO companyDto = CompanyDTOMapper.companyToDto(companyService.getCompanyById(companyId).get());
		String companyName = companyDto.getName();
		
		computerDto = new ComputerDTO.ComputerDTOBuilder()
				.setId(computerId)
				.setName(computerName)
				.setIntroduced(introduced)
				.setDiscontinued(discontinued)
				.setCompanyId(companyId)
				.setCompanyName(companyName)
				.build();
		computerService.updateComputer(computerDto);
		
		response.sendRedirect("Dashboard");
	}

}
