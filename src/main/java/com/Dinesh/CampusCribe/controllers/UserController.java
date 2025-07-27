package com.Dinesh.CampusCribe.controllers;

import com.Dinesh.CampusCribe.Dtos.RegisterRequest;
import com.Dinesh.CampusCribe.Exceptions.UserNotFoundException;
import com.Dinesh.CampusCribe.Reposiories.UserRepo;
import com.Dinesh.CampusCribe.Util.JwtUtil;
import com.Dinesh.CampusCribe.models.AuthRequest;
import com.Dinesh.CampusCribe.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepo userRepo;
   @Autowired
   private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            return "User already exists with this email";
        }

        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setUserName(request.getUserName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepo.save(user);
        return "User registered successfully";
    }


    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody AuthRequest authRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUserName(),
                            authRequest.getPassword()
                    )
            );
            Users user=userRepo.findByUserName(authRequest.getUserName());
            String token=jwtUtil.generateToken(authRequest.getUserName());
            Map<String, Object> response = new HashMap<>();
            response.put("success",true);
            response.put("token", token);
            response.put("user", user);


            return response;
        } catch (AuthenticationException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success",false);
            response.put("message", "invalid username or password");
            return response;
        }
    }
}
