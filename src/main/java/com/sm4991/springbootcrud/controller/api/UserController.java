package com.sm4991.springbootcrud.controller.api;

import com.sm4991.springbootcrud.entity.User;
import com.sm4991.springbootcrud.response.ResponseWrapper;
import com.sm4991.springbootcrud.service.UserService;
import com.sm4991.springbootcrud.dto.CreateUserDto;
import com.sm4991.springbootcrud.dto.UpdateUserDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        return ResponseWrapper.success(request, "User List", userService.getAll());
    }

    /**
     * Api to fetch user by id
     * @param request
     * @param id
     * @return
     */
    @ApiOperation(value = "Find a user by id", response = ResponseWrapper.class)
    @GetMapping(value = "/{id}")
    public ResponseWrapper getOne(HttpServletRequest request, @PathVariable UUID id) {
        Optional<User> user = userService.getById(id);
        if(user.isPresent()) {
            return ResponseWrapper.success(request, "User Data", user.get());
        } else {
            return ResponseWrapper.error(request, HttpStatus.NOT_FOUND, "User not found.", null);
        }
    }

    /**
     * Api to create a new user
     * @param request
     * @param createUserDto
     * @return
     */
    @ApiOperation(value = "Add a new user", response = ResponseWrapper.class)
    @PostMapping(value = "/")
    public ResponseWrapper create(HttpServletRequest request, @RequestBody CreateUserDto createUserDto) {
        Pair<Boolean, Object> result = userService.createUser(createUserDto);
        if(result.getFirst() == true) {
            return ResponseWrapper.success(request, "User created successfully.", result.getSecond());
        } else {
            return ResponseWrapper.error(request, HttpStatus.BAD_REQUEST, result.getSecond().toString(), null);
        }
    }

    /**
     * Api to create a new user
     * @param request
     * @param createUserDto
     * @return
     */
    @ApiOperation(value = "Update an existing user by id", response = ResponseWrapper.class)
    @PutMapping(value = "/{id}")
    public ResponseWrapper update(HttpServletRequest request, @PathVariable UUID id, @RequestBody UpdateUserDto updateUserDto) {
        Optional<User> user = userService.getById(id);
        if(user.isPresent()) {
            Pair<Boolean, Object> result = userService.updateUser(user.get(), updateUserDto);
            if(result.getFirst() == true) {
                return ResponseWrapper.success(request, "User updated successfully.", result.getSecond());
            } else {
                return ResponseWrapper.error(request, HttpStatus.BAD_REQUEST, result.getSecond().toString(), null);
            }
        } else {
            return ResponseWrapper.error(request, HttpStatus.NOT_FOUND, "User not found.", null);
        }
    }

    /**
     * Delete a user by id
     * @param request
     * @param id
     * @return
     */
    @ApiOperation(value = "Delete a user by id", response = ResponseWrapper.class)
    @DeleteMapping(value = "/{id}")
    public ResponseWrapper delete(HttpServletRequest request, @PathVariable UUID id) {
        Boolean result = userService.deleteById(id);
        if(result == true) {
            return ResponseWrapper.success(request, "User deleted successfully", null);
        } else {
            return ResponseWrapper.error(request, HttpStatus.BAD_REQUEST, "User could not be deleted.", null);
        }
    }
}
