package com.batuhan.credit.services;


import com.batuhan.credit.model.dto.UserDTO;
import com.batuhan.credit.model.dto.UserDataDTO;
import com.batuhan.credit.model.dto.UserUpdateDto;
import com.batuhan.credit.model.entity.User;
import com.batuhan.credit.model.mapper.dtoMapper;
import com.batuhan.credit.repository.UserRepository;
import com.batuhan.credit.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    public User create(UserDataDTO userDataDTO){
        userDataDTO.setPassword(passwordEncoder.encode(userDataDTO.getPassword()));
        Integer max = 1500;
        Integer min = 0;
        int  randomWithMathRandom = (int) ((Math.random() * (max-min)) + min);
        User user = dtoMapper.toEntity(userDataDTO);
        user.setCreditScore(randomWithMathRandom);
        return  userRepository.save(user);
    }

    public String login(String idcard, String password){
        try {
            User user = userRepository.findByIdcard(idcard);
            Boolean passwordMatch = passwordEncoder.matches(password,user.getPassword());;
            return jwtTokenProvider.createToken(idcard);
        } catch (Exception e) {
            return ("Invalid username/password supplied");
        }
    }

    public UserDTO getUser(String idCard){
        User userFindBYIdCard = userRepository.findByIdcard(idCard);
        UserDTO userDTO = dtoMapper.toDto(userFindBYIdCard);
        return userDTO;
    }

    public Boolean deleteUserById(Long id){
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public User update(Long id, UserUpdateDto userUpdateDto){
        Optional<User> userById = userRepository.findById(id);

        User updateUser = userById.get();

        if (!StringUtils.isEmpty(userUpdateDto.getIncome())) {
            updateUser.setIncome(userUpdateDto.getIncome());
        }
        if (!StringUtils.isEmpty(userUpdateDto.getPhone())) {
            updateUser.setPhone(userUpdateDto.getPhone());
        }

        return userRepository.save(updateUser);
    }

}
