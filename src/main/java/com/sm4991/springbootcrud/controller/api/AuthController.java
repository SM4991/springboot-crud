package com.sm4991.springbootcrud.controller.api;

import com.sm4991.springbootcrud.entity.User;
import com.sm4991.springbootcrud.response.ResponseWrapper;
import com.sm4991.springbootcrud.service.UserService;
import com.sm4991.springbootcrud.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    /**
     * Api to create a new user
     * @param request
     * @param userDto
     * @return
     */
    @ApiOperation(value = "Login", response = ResponseWrapper.class)
    @PostMapping(value = "/login/")
    public ResponseWrapper create(HttpServletRequest request, @RequestParam String email, @RequestParam String password) {
        try{
            if(email == null || email.isEmpty()) {
                return ResponseWrapper.error(request, HttpStatus.BAD_REQUEST, "Email cannot be empty.", null);
            } else if(password == null || password.isEmpty()) {
                return ResponseWrapper.error(request, HttpStatus.BAD_REQUEST, "Password cannot be empty.", null);
            }
            Optional<User> user = userService.getUser(email, password);
            if(user.isPresent()) {
                return ResponseWrapper.success(request, "User loggedin successfully.", UUID.randomUUID().toString());
            } else {
                return ResponseWrapper.error(request, HttpStatus.BAD_REQUEST, "Invalid email or password", null);
            }
        } catch (DataIntegrityViolationException ex) {
            return ResponseWrapper.error(request, HttpStatus.BAD_REQUEST, ex.getMessage(), null);
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseWrapper.error(request, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }

    /**
     * Api to register a new user
     * @param request
     * @param userDto
     * @return
     */
    @ApiOperation(value = "Register a new user", response = ResponseWrapper.class)
    @PostMapping(value = "/register/")
    public ResponseWrapper create(HttpServletRequest request, @RequestBody UserDto userDto) {
        try{
            Pair<Boolean, Object> result = userService.createUser(userDto);
            if(result.getFirst() == true) {
                return ResponseWrapper.success(request, "User created successfully.", result.getSecond());
            } else {
                return ResponseWrapper.error(request, HttpStatus.BAD_REQUEST, result.getSecond().toString(), null);
            }
        } catch (DataIntegrityViolationException ex) {
            return ResponseWrapper.error(request, HttpStatus.BAD_REQUEST, ex.getMessage(), null);
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseWrapper.error(request, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }
}
