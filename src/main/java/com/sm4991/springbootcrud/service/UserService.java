package com.sm4991.springbootcrud.service;

import com.sm4991.springbootcrud.dto.CreateUserDto;
import com.sm4991.springbootcrud.dto.UpdateUserDto;
import com.sm4991.springbootcrud.entity.User;
import com.sm4991.springbootcrud.enums.UserRole;
import com.sm4991.springbootcrud.repository.UserRepository;
import com.sm4991.springbootcrud.util.DateUtil;
import org.apache.tomcat.util.net.openssl.ciphers.Encryption;
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
     * @param createUserDto
     * @return
     */
    public Pair<Boolean, Object> createUser(CreateUserDto createUserDto) {
        User existingUser = userRepository.findByEmailOrMobile(createUserDto.getEmail(), createUserDto.getMobile());
        if(existingUser == null) {
            User user = new User();
            user.setEmail(createUserDto.getEmail());
            user.setMobile(createUserDto.getMobile());
            user.setName(createUserDto.getName());
            user.setPassword(createUserDto.getPassword());
            if(DateUtil.isValidDateFormat(DateUtil.getGlobalDateFormat(), createUserDto.getDob())) {
                user.setDob(DateUtil.convertDateStringToSqlDate(DateUtil.getGlobalDateFormat(), createUserDto.getDob()));
            } else {
                return Pair.of(false, "Invalid date, Please enter date in format: "+DateUtil.getGlobalDateFormat());
            }
            user.setRole(UserRole.CANDIDATE.toString());
            user.setCreatedAt(DateUtil.getCurrentTimestamp());
            user.setUpdatedAt(DateUtil.getCurrentTimestamp());
            user = userRepository.save(user);
            if(user.getId() != null) {
                return Pair.of(true, user);
            } else {
                return Pair.of(false, "User could not be saved");
            }
        } else {
            return Pair.of(false, "User with email or mobile already exists.");
        }
    }

    /**
     * Update existing user
     * @param updateUserDto
     * @return
     */
    public Pair<Boolean, Object> updateUser(User user, UpdateUserDto updateUserDto) {
        user.setName(updateUserDto.getName());
        if(DateUtil.isValidDateFormat(DateUtil.getGlobalDateFormat(), updateUserDto.getDob())) {
            user.setDob(DateUtil.convertDateStringToSqlDate(DateUtil.getGlobalDateFormat(), updateUserDto.getDob()));
        } else {
            return Pair.of(false, "Invalid date, Please enter date in format: "+DateUtil.getGlobalDateFormat());
        }
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
     * Fetch user entity by id
     * @param id
     * @return
     */
    public Boolean deleteById(UUID id) {
        userRepository.deleteById(id);
        return true;
    }
}
