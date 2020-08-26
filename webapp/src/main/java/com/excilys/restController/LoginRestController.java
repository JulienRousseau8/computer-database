package com.excilys.restController;

import java.util.Objects;

import javax.naming.AuthenticationException;

import com.excilys.dto.MyUserDTO;
import com.excilys.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class LoginRestController {

    //private JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginRestController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        //this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

//    @PostMapping(value = "/login")
//    public ResponseEntity<?> login(@RequestBody MyUserDTO authentificationRequest) throws AuthenticationException {
//
//        authenticate(authentificationRequest.getUsername(), authentificationRequest.getPassword());
//        final UserDetails userDetails = userService.loadUserByUsername(authentificationRequest.getUsername());
//        final String token = jwtTokenUtil.generateToken(userDetails);
//        return ResponseEntity.ok(token);
//    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) throws AuthenticationException {

        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            UserDetails userDetails = userService.loadUserByUsername(username);
            if (!(bCryptPasswordEncoder.matches(password, userDetails.getPassword()))) {
                throw new AuthenticationException("INVALID_CREDENTIALS");
            }
        } catch (UsernameNotFoundException usernameNotFoundException) {
            System.out.println(("invalid"));
            throw new UsernameNotFoundException("USER_DISABLED");
        }
    }
}
