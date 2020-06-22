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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyDTOMapper;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;

@WebServlet("/EditComputer")
@Controller
@ComponentScan({"com.excilys.DAO", "com.excilys.service", "com.excilys.servlets", "com.excilys.persistence", "com.excilys.mapper"})
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;
	@Autowired
	ComputerDTOMapper computerMapper;
	@Autowired
	CompanyDTOMapper companyMapper;
	List<CompanyDTO> listCompanyDTO = new ArrayList<CompanyDTO>();
	ComputerDTO computerDto;
	
	public EditComputerServlet() {
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);	
	}
	
	public static final String EDITCOMPUTER = "/WEB-INF/views/editComputer.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listCompanyDTO = companyMapper.listCompanyToDto(companyService.getAllCompanies());
		request.setAttribute("listCompanyDTO", listCompanyDTO);
		
		String computerId = request.getParameter("computerId");
		computerDto = computerMapper.computerToDto(computerService.getComputerById(computerId).get());
		
		request.setAttribute("computerDto", computerDto);
		request.getRequestDispatcher(EDITCOMPUTER).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String companyId = request.getParameter("companyId");
		CompanyDTO companyDto = companyMapper.companyToDto(companyService.getCompanyById(companyId).get());
		String companyName = companyDto.getName();
		
		computerDto = new ComputerDTO.ComputerDTOBuilder()
				.setId(request.getParameter("computerId"))
				.setName(request.getParameter("computerName"))
				.setIntroduced(request.getParameter("introduced"))
				.setDiscontinued(request.getParameter("discontinued"))
				.setCompanyId(companyId)
				.setCompanyName(companyName)
				.build();
		
		computerService.updateComputer(computerDto);
		response.sendRedirect("Dashboard");
	}

}
