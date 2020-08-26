package com.excilys.restController;

import javax.validation.Valid;

import com.excilys.dto.MyUserDTO;
import com.excilys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/register")
public class RegisterRestController {

    UserService userService;

    @Autowired
    public RegisterRestController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@Valid MyUserDTO user, BindingResult errors) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "GET, POST");
        if (errors.hasErrors()) {
            String message = Objects.requireNonNull(errors.getFieldError()).getDefaultMessage();

            return new ResponseEntity<>(message, headers, HttpStatus.METHOD_NOT_ALLOWED);
        }

        try {
            userService.createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), headers, HttpStatus.METHOD_NOT_ALLOWED);
        }

        return new ResponseEntity<>("New user added", headers, HttpStatus.OK);
    }
}