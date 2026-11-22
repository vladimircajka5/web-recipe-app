package com.cajkaisinovi.recipes.service;

import com.cajkaisinovi.recipes.dto.UserDto;

public interface IUserService {

    UserDto findById(Long userId);
}