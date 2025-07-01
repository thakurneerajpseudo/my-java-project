package com.my.personal.MyProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.personal.MyProject.beans.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

	Users findByEmail(String email);

}
              