package com.sm4991.springbootcrud.repository;

import com.sm4991.springbootcrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    public User findByEmailOrMobile(String email, String mobile);
}