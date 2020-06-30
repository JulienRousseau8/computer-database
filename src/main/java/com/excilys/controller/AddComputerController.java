package com.excilys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyDTOMapper;
import com.excilys.service.CompanyService;
import com.excilys.service.DashboardService;

@Controller
@RequestMapping(value = "/AddComputer")
public class AddComputerController {

	DashboardService dashboardService;
	CompanyService companyService;
	CompanyDTOMapper companyMapper;

	List<CompanyDTO> listCompanyDTO = new ArrayList<CompanyDTO>();
	ComputerDTO computerDto;
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
	public ModelAndView addComputer(@RequestParam(required = false, value = "companyId") String companyId,
			@RequestParam(required = false, value = "computerId") String computerId,
			@RequestParam(required = false, value = "computerName") String computerName,
			@RequestParam(required = false, value = "introduced") String introduced,
			@RequestParam(required = false, value = "discontinued") String discontinued) {
		
		ModelAndView modelAndView = new ModelAndView("redirect:/Dashboard");

		computerDto = new ComputerDTO.ComputerDTOBuilder()
				.setName(computerName)
				.setIntroduced(introduced)
				.setDiscontinued(discontinued)
				.setCompanyId(companyId)
				.setCompanyName(companyService.getCompanyById(companyId).get().getName().toString())
				.build();

		dashboardService.createComputer(computerDto);
		return modelAndView;
	}
	
}