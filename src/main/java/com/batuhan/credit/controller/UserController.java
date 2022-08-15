package com.batuhan.credit.controller;


import com.batuhan.credit.model.dto.UserDTO;
import com.batuhan.credit.model.dto.UserDataDTO;
import com.batuhan.credit.model.dto.UserLoginDTO;
import com.batuhan.credit.model.dto.UserUpdateDto;
import com.batuhan.credit.model.entity.User;
import com.batuhan.credit.model.mapper.dtoMapper;
import com.batuhan.credit.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody UserDataDTO userDataDTO){
        User user = userServices.create(userDataDTO);
        UserDTO userDTO = dtoMapper.toDto(user);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USER NOT CREATED");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserLoginDTO userLogindto){
        return userServices.login(userLogindto.getIdcard(), userLogindto.getPassword());
    }


    @GetMapping("/{idcard}")
    public ResponseEntity getUserByIdCard(@PathVariable("idcard") String idcard) {
        UserDTO userGetId = userServices.getUser(idcard);
        if (userGetId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USER NOT FOUND");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userGetId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") Long id) {
        Boolean delete = userServices.deleteUserById(id);
        if (delete == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USER NOT FOUND");
        }
        return ResponseEntity.status(HttpStatus.OK).body("USER SUCCESSFULLY DELETED");
    }
    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto){
        User updateAd = userServices.update(id, userUpdateDto);
        if(updateAd == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("PRODUCT NOT UPDATED");
        }
        UserDTO userDTO = dtoMapper.toDto(updateAd);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

}
