package com.sushmita.github.repository;

import com.sushmita.github.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    @Query("select s from User s where s.userName = ?1")
    User findByUserName(String userName);
}
