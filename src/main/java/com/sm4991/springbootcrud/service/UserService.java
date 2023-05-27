package com.sm4991.springbootcrud.service;

import com.sm4991.springbootcrud.dto.UserDto;
import com.sm4991.springbootcrud.entity.User;
import com.sm4991.springbootcrud.enums.UserRole;
import com.sm4991.springbootcrud.repository.UserRepository;
import com.sm4991.springbootcrud.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Create a new user
     * @param userDto
     * @return
     */
    public Pair<Boolean, Object> createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());

        if(getByEmail(userDto.getEmail()).isPresent()) {
            return Pair.of(false, "User with email id already exists.");
        }

        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCreatedAt(DateUtil.getCurrentTimestamp());
        user.setUpdatedAt(DateUtil.getCurrentTimestamp());
        user = userRepository.save(user);
        if(user.getId() != null) {
            return Pair.of(true, user);
        } else {
            return Pair.of(false, "User could not be saved");
        }
    }

    /**
     * Update existing user
     * @param userDto
     * @return
     */
    public Pair<Boolean, Object> updateUser(User user, UserDto userDto) {
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUpdatedAt(DateUtil.getCurrentTimestamp());
        user = userRepository.save(user);
        return Pair.of(true, user);
    }

    /**
     * Fetch all user entities
     * @return
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Fetch user entity by id
     * @param id
     * @return
     */
    public Optional<User> getById(UUID id) {
        return userRepository.findById(id);
    }

    /**
     * Fetch user entity by email
     * @param id
     * @return
     */
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Fetch user entity by email
     * @param id
     * @return
     */
    public Optional<User> getUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }


}
