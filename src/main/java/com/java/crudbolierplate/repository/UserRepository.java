package com.java.crudbolierplate.repository;

import com.java.crudbolierplate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    public User findByEmailOrMobile(String email, String mobile);
}
