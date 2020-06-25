package com.excilys.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.DAO.DAOcomputer;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;

@Service
public class DashboardService {
	private static Logger logger = LoggerFactory.getLogger(DashboardService.class);
	
	DAOcomputer daoComputer;
	CompanyService companyService;
	ComputerDTOMapper computerDTOMapper;
	Validators validators = new Validators(companyService);

	
	public DashboardService(DAOcomputer daoComputer, ComputerDTOMapper computerDTOMapper) {
		this.daoComputer = daoComputer;
		this.computerDTOMapper = computerDTOMapper;
	}

	public int countAllComputer() {
		return daoComputer.countAllComputer();
	}

	public List<Computer> getPageComputer(Pagination page) {
		return daoComputer.getPageComputers(page);
	}
	
	public List<Computer> getSearchComputersPage(String recherche, Pagination page){
		return daoComputer.getSearchComputersPage(recherche, page);
	}
	
	public void deleteListComputers(String selection) {
		List<String> idaSupprimer = Arrays.asList(selection.split(","));
		for(String id : idaSupprimer) {
			daoComputer.deleteComputer(Integer.parseInt(id));
		}
	}
	
	public List<Computer> getSearchComputers(String recherche){
		return daoComputer.getSearchComputers(recherche);
	}
	
	public void createComputer(ComputerDTO computerDto){
		boolean name = validators.verifierNom(computerDto);
		boolean date = validators.verifierDate(computerDto);
		if (name && date) {
			Computer computer = computerDTOMapper.dtoToComputer(computerDto);
			daoComputer.createComputer(computer);
			logger.info(computer.toString());
		}
	}
	
	public List<Computer> getComputersOrdered(Pagination page, String orderBy, int direction){
		if(orderBy.equals("company")) {
			orderBy = "company.name";
			return daoComputer.getPageComputersOrdered(page, orderBy, direction);
		} else {
			orderBy = "computer." + orderBy;
			return daoComputer.getPageComputersOrdered(page, orderBy, direction);
		}
	}
	
	public void setAttributes(String search, String orderBy, Integer direction,
	        ModelAndView modelAndView, Pagination page, int nbComputers, List<ComputerDTO> listComputerDTO) {
		modelAndView.addObject("search", search);
		modelAndView.addObject("pageMax", page.getPageMax());
		modelAndView.addObject("pageNum", page.getPageNum());
		modelAndView.addObject("pageTaille", page.getPageTaille());
		modelAndView.addObject("orderBy", orderBy);
		modelAndView.addObject("direction", direction);
		modelAndView.addObject("nbComputers", nbComputers);
		modelAndView.addObject("computerList", listComputerDTO);
	}
	
	public void setPageTaille(String pageTaille, Pagination page) {
		if(pageTaille != null) {
			int pageTailleInt = Integer.parseInt(pageTaille);
			page.setPageTaille(pageTailleInt);
		}
	}
	
	public void setPageNum(String pageNum, Pagination page) {
		if(pageNum != null) {
			page.setPageNum(Integer.parseInt(pageNum));
		}
	}
	
	public List<ComputerDTO> viewChoice(String search, String orderBy, Integer direction,
	        Pagination page) {
		List<ComputerDTO> listComputerDTO;
		if(search!=null && (orderBy==null || orderBy.isEmpty())) {
			listComputerDTO = computerDTOMapper.listComputerToDto(getSearchComputersPage(search, page));
			
		}
		else if(orderBy!=null && (search==null || search.isEmpty())) {
			direction = direction%2;
			listComputerDTO = computerDTOMapper.listComputerToDto(getComputersOrdered(page, orderBy, direction));
		}
		else {
			listComputerDTO = computerDTOMapper.listComputerToDto(getPageComputer(page));
		}
		return listComputerDTO;
	}

	public int getNbComputers(String search, String orderBy) {
		if(search!=null && (orderBy==null || orderBy.isEmpty())) {
			return computerDTOMapper.listComputerToDto(getSearchComputers(search)).size();
		}
		return countAllComputer();
	}
}
