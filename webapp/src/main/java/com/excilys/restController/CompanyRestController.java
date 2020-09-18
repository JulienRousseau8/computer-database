package com.excilys.restController;

import com.excilys.model.Company;
import com.excilys.service.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/companies")
public class CompanyRestController {

    ObjectMapper obj = new ObjectMapper();

    private final CompanyService companyService;

    public CompanyRestController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<String> getCompanies() {
        List<Company> listCompanies = companyService.getAllCompanies();
        try {

            return ResponseEntity.ok(obj.writeValueAsString(listCompanies));
        } catch (JsonProcessingException jsonExc) {
            jsonExc.printStackTrace();

            return new ResponseEntity<>("Cannot get companies", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getCompany(@PathVariable("id") String id) {

        Optional<Company> company = companyService.getCompanyById(id);
        if (company.isPresent()) {
            try {

                return ResponseEntity.ok(obj.writeValueAsString(company.get().toString()));
            } catch (JsonProcessingException jsonExc) {
                jsonExc.printStackTrace();

                return new ResponseEntity<>("Id " + id + " not found", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("Id " + id + " not found", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") String id) {
        try{
            companyService.deleteCompany(Integer.parseInt(id));
            return ResponseEntity.ok("Company deleted");
        }catch (Exception e){
            return new ResponseEntity<>("can't delete company", HttpStatus.BAD_REQUEST);
        }
    }

//    @PutMapping
//    public ResponseEntity<String> editCompany(@RequestBody DTOCompany dtoCompany) {
//
//        if (companyService.e) {
//
//            return new ResponseEntity<String>("Company edited", HttpStatus.OK);
//        }
//
//        return new ResponseEntity<String>("Cannot edit company", HttpStatus.BAD_REQUEST);
//    }

//    @PostMapping
//    public ResponseEntity<String> addCompany(@RequestBody DTOCompany dtoCompany) {
//
//        if (companyService.) {
//
//            return new ResponseEntity<String>("Company added", HttpStatus.OK);
//        }
//
//        return new ResponseEntity<String>("Cannot add company", HttpStatus.BAD_REQUEST);
//    }
}
