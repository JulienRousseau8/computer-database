package com.excilys.restController;

import com.excilys.dto.ComputerDTO;
import com.excilys.model.Computer;
import com.excilys.model.Pagination;
import com.excilys.service.ComputerService;
import com.excilys.service.DashboardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/computers")
public class ComputerRestController {

    ObjectMapper obj = new ObjectMapper();
    private final ComputerService computerService;
    private final DashboardService dashboardService;

    @Autowired
    public ComputerRestController(ComputerService computerService, DashboardService dashboardService) {
        this.computerService = computerService;
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<String> getComputers() {

        List<Computer> listComputers = computerService.getAllComputers();
        try {

            return ResponseEntity.ok(obj.writeValueAsString(listComputers));
        } catch (JsonProcessingException jsonExc) {
            jsonExc.printStackTrace();

            return new ResponseEntity<>("Cannot get computers", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/page_computers") // modify
    public ResponseEntity<String> getComputersPage(@RequestParam(defaultValue = "10") String lines) {

        long nbComputers = dashboardService.countAllComputer();
        Pagination page = new Pagination(nbComputers, Integer.parseInt(lines));
        List<Computer> list = dashboardService.getPageComputer(page);
        try {

            return ResponseEntity.ok(obj.writeValueAsString(list));
        } catch (JsonProcessingException jsonExc) {
            jsonExc.printStackTrace();

            return new ResponseEntity<>("Cannot get computers", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getComputer(@PathVariable("id") String id) {

        Optional<Computer> computer = computerService.getComputerById(id);
        if (computer.isPresent()) {
            try {

                return ResponseEntity.ok(obj.writeValueAsString(computer.get().toString()));
            } catch (JsonProcessingException jsonExc) {
                jsonExc.printStackTrace();


            }
        }

        return new ResponseEntity<>("Id " + id + " not found", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteComputer(@PathVariable("id") String id) {
        try {
            computerService.deleteComputer(Long.parseLong(id));
            return new ResponseEntity<>("Computer deleted", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("cannot delete computer", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> editComputer(@RequestBody ComputerDTO dtoComputer) {

        try{
            computerService.updateComputer(dtoComputer);
            return new ResponseEntity<>("Computer edited", HttpStatus.OK);
        } catch(Exception e){

            return new ResponseEntity<>("Cannot edit computer", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<String> addComputer(@RequestBody ComputerDTO dtoComputerAdd) {
        try{
            computerService.createComputer(dtoComputerAdd);
            return new ResponseEntity<>("Computer added", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("Cannot add computer", HttpStatus.BAD_REQUEST);
        }
    }
}