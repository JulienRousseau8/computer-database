package com.excilys.controller;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.CompanyDTOMapper;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.service.CompanyService;
import com.excilys.service.ComputerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/editComputer")
public class EditComputerController {

	List<CompanyDTO> listCompanyDTO = new ArrayList<CompanyDTO>();
	ComputerDTO computerDto;

	private final ComputerService computerService;
	private final CompanyService companyService;
	private final ComputerDTOMapper computerMapper;
	private final CompanyDTOMapper companyMapper;
	
	public EditComputerController(ComputerService computerService,
	        CompanyService companyService, ComputerDTOMapper computerMapper,
	        CompanyDTOMapper companyMapper) {
		this.computerService = computerService;
		this.companyService = companyService;
		this.computerMapper = computerMapper;
		this.companyMapper = companyMapper;
	}
	
	@GetMapping(value = "")
	public ModelAndView afficherComputer(@RequestParam(required = false, value = "computerId") String computerId) {
		ModelAndView modelAndView = new ModelAndView("editComputer");
		
		listCompanyDTO = companyMapper.listCompanyToDto(companyService.getAllCompanies());
		computerDto = computerMapper.computerToDto(computerService.getComputerById(computerId).get());
		modelAndView.addObject("listCompanyDTO", listCompanyDTO);
		modelAndView.addObject("computerDto", computerDto);
		
		return modelAndView;
	}

	@PostMapping(value = "/edit")
	public ModelAndView editComputer(ComputerDTO computerDto){	
		ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");		
		computerService.updateComputer(computerDto);
		return modelAndView;
	}
	
}
