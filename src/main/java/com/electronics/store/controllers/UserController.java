package com.electronics.store.controllers;

import com.electronics.store.config.AppConstants;
import com.electronics.store.dtos.PageableResponse;
import com.electronics.store.dtos.UserDto;
import com.electronics.store.fileservice.FileService;
import com.electronics.store.payloads.ApiResponse;
import com.electronics.store.payloads.ImageResponse;
import com.electronics.store.userservice.UserService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;

    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/createuser")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        logger.info("Initiated request for creating user");
        UserDto createdUser = userService.createUser(userDto);
        logger.info("Initiated request for creating user");
        return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);//201
    }

    // Get all users
    @GetMapping("/")
    public ResponseEntity<PageableResponse<UserDto>> getallUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        logger.info("Initiated request to get all users");
        PageableResponse<UserDto> allUsers = userService.getAllUsers(pageNumber, pageSize, sortBy, sortDir);
        logger.info("Completed request to get all users");
        return new ResponseEntity<PageableResponse<UserDto>>(allUsers, HttpStatus.OK);//200
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer id) {
        logger.info("Initiated request to get single user");
        UserDto userdtowithid = userService.getSingleUser(id);
        logger.info("Completed request to get single user");
        return new ResponseEntity<UserDto>(userdtowithid, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
        logger.info("Initiated request to update single user");
        UserDto userDto1 = userService.updatesUer(userDto, id);
        logger.info("Completed request to update single user");
        return new ResponseEntity<UserDto>(userDto1, HttpStatus.OK);

    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        logger.info("Initiated request to get user by email");
        UserDto userdtowithemail = userService.getUserByEmail(email);
        logger.info("Completed request to get user by email");
        return new ResponseEntity<UserDto>(userdtowithemail, HttpStatus.OK);

    }

    @GetMapping("/email/{email}/password/{password}")
    public ResponseEntity<UserDto> getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        logger.info("Initiated request to get user by email and password");
        UserDto userdtoByEmailAndPassword = userService.getUserByEmailAndPassword(email, password);
        logger.info("Completed request to get user by email and password");
        return new ResponseEntity<UserDto>(userdtoByEmailAndPassword, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteuser(@PathVariable Integer id) {

        logger.info("Intiated request to delete user");

        this.userService.deleteUser(id);

        logger.info("Completed request to delete user");

        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstants.User_Deleted, true, new Date()),
                HttpStatus.OK);


    }

    // Search
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchposts(@PathVariable String keyword) {
        logger.info("Intiated request to search user");
        List<UserDto> userDtos = this.userService.searchUser(keyword);
        logger.info("Completed request to search user");
        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);

    }

    //Upload Image
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadImage(
            @RequestParam("userImage") MultipartFile image,
            @PathVariable Integer userId) throws IOException {

        logger.info("Intiated request to upload userimage");

        //Upload image
        String imageName = fileService.uploadFile(image, imageUploadPath);

        //Get user
        UserDto user = this.userService.getSingleUser(userId);

        //update image name
        user.setImageName(imageName);

        //update user
        UserDto userDto = this.userService.updatesUer(user, userId);

        ImageResponse imageResponse = ImageResponse.builder().
                imageName(imageName).
                success(true).
                message(AppConstants.IMAGE_UPLOADED).
                date(new Date())
                .build();
        logger.info("Completed request to upload userimage");
        return new ResponseEntity<ImageResponse>(imageResponse, HttpStatus.OK);


    }

    // Method to Serve or upload file

     @GetMapping(value="image/{userId}")
    public void serveUserImage(@PathVariable Integer userId, HttpServletResponse response) throws IOException {
        UserDto user = userService.getSingleUser(userId);
        logger.info("Userimagename : {} ",user.getImageName());
         InputStream resource = fileService.getResource(imageUploadPath, user.getImageName());
         response.setContentType(MediaType.IMAGE_JPEG_VALUE);
         StreamUtils.copy(resource,response.getOutputStream());

     }

}
