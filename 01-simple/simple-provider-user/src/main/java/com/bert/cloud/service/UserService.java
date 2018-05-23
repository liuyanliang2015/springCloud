package com.bert.cloud.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bert.cloud.entity.User;

@Repository
public interface UserService extends JpaRepository<User, Long> {

}
