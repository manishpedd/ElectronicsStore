package com.electronics.store.userservice;

import com.electronics.store.dtos.PageableResponse;
import com.electronics.store.dtos.UserDto;

import java.util.List;

public interface UserService {

    //create user
    public UserDto createUser(UserDto userdto);

    //Update user
    public UserDto updatesUer(UserDto userDto, Integer id);

    //Get single user by id
    public UserDto getSingleUser(Integer id);

    //Get user by email
    public UserDto getUserByEmail(String email);

    public UserDto getUserByEmailAndPassword(String email,String password);


    //get all users
    public PageableResponse<UserDto> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //Search user
    List<UserDto> searchUser(String keyword);

    //Delete user
    public void deleteUser(Integer id);


}
