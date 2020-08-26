package com.excilys.controller;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Pagination;
import com.excilys.service.ComputerService;
import com.excilys.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {
	List<ComputerDTO> listComputerDTO = new ArrayList<ComputerDTO>();
	long nbComputers;

	DashboardService dashboardService;
	ComputerService computerService;

	public DashboardController(DashboardService dashboardService, ComputerService computerService) {
		this.dashboardService = dashboardService;
		this.computerService = computerService;
	}

	@GetMapping(value = "")
	public ModelAndView dashboard(
			@RequestParam(required = false, value = "pageNum") String pageNum,
			@RequestParam(required = false, value = "pageTaille") String pageTaille,
			@RequestParam(required = false, value = "search") String search,
			@RequestParam(required = false, value = "orderBy") String orderBy,
			@RequestParam(required = false, value = "direction") Integer direction) {

		ModelAndView modelAndView = new ModelAndView("dashboard");
		nbComputers = dashboardService.getNbComputers(search, orderBy);
		Pagination page = new Pagination(nbComputers, Integer.parseInt(pageTaille == null ? pageTaille="10" : pageTaille));

		dashboardService.setPageTaille(pageTaille, page);
		dashboardService.setPageNum(pageNum, page);
		listComputerDTO = dashboardService.viewChoice(search, orderBy, direction, page);
		dashboardService.setAttributes(search, orderBy, direction, modelAndView, page, nbComputers, listComputerDTO);

		return modelAndView;
	}

	@PostMapping(value = "/deleteComputer")
	public ModelAndView deleteComputer(@RequestParam(value = "selection") String selection) {
		ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");
		dashboardService.deleteListComputers(selection);
		return modelAndView;
	}
}
