package com.developer.photoapp.api.users.service;

import com.developer.photoapp.api.users.data.UserEntity;
import com.developer.photoapp.api.users.data.UsersRepository;
import com.developer.photoapp.api.users.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;

    @Autowired //constructor based dependency injection
    public UsersServiceImpl(UsersRepository usersRepository){
        this.usersRepository=usersRepository;

    }

    @Override
    public UserDto createUser(UserDto userDetails) {

        userDetails.setUserId(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); //match fields from source to destination object

        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class); //map fields from source object to destination object

        userEntity.setEncryptedPassword("test");

        usersRepository.save(userEntity); //Persistance

        UserDto returnValue = modelMapper.map(userEntity,UserDto.class);

        return returnValue;
    }
}
