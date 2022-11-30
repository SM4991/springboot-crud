package com.java.crudbolierplate.controller.api;

import com.java.crudbolierplate.dto.UserDto;
import com.java.crudbolierplate.entity.User;
import com.java.crudbolierplate.repository.UserRepository;
import com.java.crudbolierplate.response.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/")
    public ResponseWrapper getAll(HttpServletRequest request) {
        return ResponseWrapper.success(request, "User List", userRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseWrapper getOne(HttpServletRequest request, @PathVariable UUID id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseWrapper.success(request, "User Data", user.get());
        } else {
            return ResponseWrapper.error(request, HttpStatus.NOT_FOUND, "User not found.", null);
        }
    }

//    @PostMapping(value = "/")
//    public ResponseWrapper create(HttpServletRequest request, @RequestBody UserDto userDto) {
//        return ResponseWrapper.error(request, HttpStatus.NOT_FOUND, "User not found.", null);
//    }
}
