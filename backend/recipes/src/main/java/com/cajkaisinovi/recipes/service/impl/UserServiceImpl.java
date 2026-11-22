package com.cajkaisinovi.recipes.service.impl;

import com.cajkaisinovi.recipes.domain.User;
import com.cajkaisinovi.recipes.dto.UserDto;
import com.cajkaisinovi.recipes.repository.RecipeRepository;
import com.cajkaisinovi.recipes.repository.UserRepository;
import com.cajkaisinovi.recipes.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public UserDto findById(Long userId) {

        Optional<User> found = this.userRepository.findById(userId);

        // NOT_FOUND
        if (found.isEmpty()) {
            return null;
        }

        // OK
        long recipeCount = this.recipeRepository.countByAuthorId(userId);
        return new UserDto(found.get(), recipeCount);
    }
}