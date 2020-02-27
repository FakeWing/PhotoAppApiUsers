package com.developer.photoapp.api.users.service;

import com.developer.photoapp.api.users.shared.UserDto;

public interface UsersService {

    UserDto createUser(UserDto userDetails);
}
