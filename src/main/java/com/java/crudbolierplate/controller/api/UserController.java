package com.java.crudbolierplate.controller.api;

import com.java.crudbolierplate.dto.UserDto;
import com.java.crudbolierplate.entity.User;
import com.java.crudbolierplate.repository.UserRepository;
import com.java.crudbolierplate.response.ResponseWrapper;
import com.java.crudbolierplate.service.UserService;
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
    @GetMapping(value = "/")
    public ResponseWrapper getAll(HttpServletRequest request) {
        return ResponseWrapper.success(request, "User List", userService.getAll());
    }

    /**
     * Api to fetch user by id
     * @param request
     * @param id
     * @return
     */
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
     * @param userDto
     * @return
     */
    @PostMapping(value = "/")
    public ResponseWrapper create(HttpServletRequest request, @RequestBody UserDto userDto) {
        Pair<Boolean, Object> result = userService.createUser(userDto);
        if(result.getFirst() == true) {
            return ResponseWrapper.success(request, "User created successfully.", result.getSecond());
        } else {
            return ResponseWrapper.error(request, HttpStatus.BAD_REQUEST, result.getSecond().toString(), null);
        }
    }

    /**
     * Api to create a new user
     * @param request
     * @param userDto
     * @return
     */
    @PutMapping(value = "/{id}")
    public ResponseWrapper update(HttpServletRequest request, @PathVariable UUID id, @RequestBody UserDto userDto) {
        Optional<User> user = userService.getById(id);
        if(user.isPresent()) {
            Pair<Boolean, Object> result = userService.updateUser(user.get(), userDto);
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
