package com.my.personal.MyProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.personal.MyProject.beans.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{
 
}

