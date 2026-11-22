package com.cajkaisinovi.recipes.repository;

import com.cajkaisinovi.recipes.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
