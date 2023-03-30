package com.electronics.store.userservice;


import com.electronics.store.config.AppConstants;
import com.electronics.store.dtos.PageableResponse;
import com.electronics.store.dtos.UserDto;
import com.electronics.store.entities.User;
import com.electronics.store.exceptions.ResourceNotFoundException;
import com.electronics.store.helper.Helper;
import com.electronics.store.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepo userrepo;

    @Autowired
    private ModelMapper modelmapper;

    @Override
    public UserDto createUser(UserDto userdto) {

        //DTO TO ENTITY
        log.info("Intiating dao call to save user data");
        User user = dtoToEntity(userdto);
        user.setIsactive(AppConstants.YES);
        User saveduser = userrepo.save(user);
        //ENTITY TO DTO
        UserDto usedto = entityToDto(saveduser);
        log.info("Completed dao call to save user data");
        return usedto;
    }

    private UserDto entityToDto(User user) {
        UserDto userdto = UserDto.builder()
                .userId(user.getUserId())
                .about(user.getAbout())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .imageName(user.getImageName())
                .gender(user.getGender()).build();

        return userdto;
    }


    private User dtoToEntity(UserDto userDto) {
        User user = User.builder()
                .userId(userDto.getUserId())
                .about(userDto.getAbout())
                .name(userDto.getName())
                .gender(userDto.getGender())
                .password(userDto.getPassword())
                .imageName(userDto.getImageName())
                .build();

        return user;
    }


    @Override
    public UserDto updatesUer(UserDto userDto, Integer id) {
        log.info("Intiating dao call to update user data" + id);
        //Get
        User user = userrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND));


        //Set
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setGender(userDto.getGender());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        //Update
        User updateduser = this.userrepo.save(user);
        UserDto userdto = this.modelmapper.map(updateduser, UserDto.class);
        log.info("Complete dao call to update user data" + id);
        return userdto;


    }


    @Override
    public UserDto getSingleUser(Integer id) {
        log.info("Intiating dao call to get single user data");
        User user = userrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND));
        log.info("Completed dao call to get single user data");
        return this.modelmapper.map(user, UserDto.class);

    }

    @Override
    public UserDto getUserByEmail(String email) {
        log.info("Intiating dao call to get single user by email");
        User user = this.userrepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(AppConstants.Email_NOT_FOUND));
        log.info("Completed dao call to get single user by email");
        return this.modelmapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByEmailAndPassword(String email, String password) {
        log.info("Intiating dao call to get single user by email and password");
        User user = this.userrepo.findByEmailAndPassword(email, password);
        log.info("Completed dao call to get single user by email and password");
        return this.modelmapper.map(user, UserDto.class);
    }

    @Override
    public PageableResponse<UserDto> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        log.info("Intiating dao call to get all users");

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        //pageNumber by default starts from zero
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> page = this.userrepo.findAll(pageable);

        PageableResponse<UserDto> pageableResponse = Helper.getPageableResponse(page, UserDto.class);
        log.info("Completed dao call to get all users");
        return pageableResponse;

    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = this.userrepo.findByNameContaining(keyword);
        List<UserDto> userdtos = users.stream().map((user) -> this.entityToDto(user)).collect(Collectors.toList());
        return userdtos;
    }

    @Override
    public void deleteUser(Integer id) {
        log.info("Intiating dao call to delete user data");
        User user = this.userrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppConstants.NOT_FOUND));
        user.setIsactive(AppConstants.NO);
        userrepo.delete(user);
        log.info("Completed dao call to delete user data" + id);

    }
}
