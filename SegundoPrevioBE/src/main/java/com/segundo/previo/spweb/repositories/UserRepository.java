package com.segundo.previo.spweb.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.segundo.previo.spweb.entities.User;

import java.util.Optional;

/*
 * Repository that define the methods to access to User's data 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  

}