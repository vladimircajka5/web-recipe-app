package com.cajkaisinovi.recipes.service;

import com.cajkaisinovi.recipes.dto.UpdateUserProfileDto;
import com.cajkaisinovi.recipes.dto.UserDto;

public interface IUserService {

    UserDto findById(Long userId);

    UserDto updateProfile(Long userId, UpdateUserProfileDto dto);
}