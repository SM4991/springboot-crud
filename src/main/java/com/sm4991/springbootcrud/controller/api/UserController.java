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
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Api to fetch all users
     * @param request
     * @return
     */
    @ApiOperation(value = "List of users", response = ResponseWrapper.class)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseWrapper getAll(HttpServletRequest request) {
        try {
            return ResponseWrapper.success(request, "User List", userService.getAll());
        } catch (HttpServerErrorException.InternalServerError ex) {
            return ResponseWrapper.error(request, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        }
    }
}
