package com.excilys.controller;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyDTOMapper;
import com.excilys.service.CompanyService;
import com.excilys.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/addComputer")
public class AddComputerController {
	
	List<CompanyDTO> listCompanyDTO = new ArrayList<CompanyDTO>();

	private final DashboardService dashboardService;
	private final CompanyService companyService;
	private final CompanyDTOMapper companyMapper;

	public AddComputerController(DashboardService dashboardService,
			CompanyService companyService, CompanyDTOMapper companyMapper) {
		this.dashboardService = dashboardService;
		this.companyService = companyService;
		this.companyMapper = companyMapper;
	}

	@GetMapping(value = "")
	public ModelAndView afficherCompanies() {
		ModelAndView modelAndView = new ModelAndView("addComputer");
		listCompanyDTO = companyMapper.listCompanyToDto(companyService.getAllCompanies());
		modelAndView.addObject("listCompanyDTO", listCompanyDTO);
		return modelAndView;
	}
	
	@PostMapping(value = "/add")
	public ModelAndView addComputer(ComputerDTO computerDto) {
		ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");
		dashboardService.createComputer(computerDto);
		return modelAndView;
	}
	
}