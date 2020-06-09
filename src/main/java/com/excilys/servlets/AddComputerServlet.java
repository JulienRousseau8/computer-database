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
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@WebServlet("/AddComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ComputerService computerService = new ComputerService();
	CompanyService companyService = new CompanyService();

    public AddComputerServlet() {
        super();
    }
    
    public static final String ADDCOMPUTER = "/WEB-INF/views/addComputer.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CompanyDTO> listCompanyDTO = new ArrayList<CompanyDTO>();
		listCompanyDTO = CompanyDTOMapper.listCompanyToDto(companyService.getAllCompanies());
		
		request.setAttribute("listCompanyDTO", listCompanyDTO);
		request.getRequestDispatcher(ADDCOMPUTER).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerDTO computerDto;
		
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		
		String companyName = companyService.getCompanyById(companyId).get().toString();
		
		computerDto = new ComputerDTO.ComputerDTOBuilder()
				.setName(computerName)
				.setIntroduced(introduced)
				.setDiscontinued(discontinued)
				.setCompanyId(companyId)
				.setCompanyName(companyName)
				.build();
		
		computerService.createComputer(computerDto);
		
		response.sendRedirect("Dashboard");
	}

}
