package com.cashrich.coins.controller;

import com.cashrich.coins.entity.User;
import com.cashrich.coins.response.Response;
import com.cashrich.coins.request.UserLoginRequest;
import com.cashrich.coins.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Response> registerUser(@RequestBody User user) {
        try{
            userService.registerUser(user);
            Response response = new Response(true, user, "User registration done", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            Response response = new Response(false, null, "Failed to register User", exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Response> login(@RequestBody UserLoginRequest user) {
        try {
            User loggedInUser = userService.loginUser(user.getUserName(), user.getPassword());
            Response response = new Response(true, loggedInUser, "Login successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Response response = new Response(false, null, "Failed to login", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Response> getUser(@PathVariable("id") Long id) {
        try{
            User user = userService.getUser(id);
            Response response = new Response(true, user, "User fetched successfully", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception exception) {
            Response response = new Response(false, null, "Failed to fetch user", exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
