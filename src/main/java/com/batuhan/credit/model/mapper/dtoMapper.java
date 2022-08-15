package com.batuhan.credit.model.mapper;

import com.batuhan.credit.model.dto.UserDTO;
import com.batuhan.credit.model.dto.UserDataDTO;
import com.batuhan.credit.model.entity.User;

public class dtoMapper {

    public static User toEntity(UserDataDTO userDataDTO)
    {
        User user = new User();
        user.setName(userDataDTO.getName());
        user.setSurname(userDataDTO.getSurname());
        user.setPhone(userDataDTO.getPhone());
        user.setPassword(userDataDTO.getPassword());
        user.setIdcard(userDataDTO.getIdcard());
        user.setIncome(userDataDTO.getIncome());
        return user;
    }

    public static  UserDTO toDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setCreditScore(user.getCreditScore());
        userDTO.setIdcard(user.getIdcard());
        userDTO.setIncome(user.getIncome());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setPhone(user.getPhone());
        return  userDTO;
    }

}
