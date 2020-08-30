package com.excilys.restController;

import java.util.List;

import com.excilys.model.MyUser;
import com.excilys.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/users")
public class UserRestController {

    ObjectMapper obj = new ObjectMapper();
    UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<String> getUsers(@PathVariable("username") String username) {

        try {
            UserDetails user = userService.loadUserByUsername(username);
            return ResponseEntity.ok(obj.writeValueAsString(user));
        } catch (JsonProcessingException jsonExc) {
            jsonExc.printStackTrace();

            return new ResponseEntity<>("Cannot get user", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<String> getAllUsers(){

        List<MyUser> listUsers = userService.getUsers();
        try {

            return ResponseEntity.ok(obj.writeValueAsString(listUsers));
        } catch (JsonProcessingException jsonExc) {
            jsonExc.printStackTrace();

            return new ResponseEntity<>("Cannot get users", HttpStatus.BAD_REQUEST);
        }
    }
}
