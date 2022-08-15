package com.batuhan.credit.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataDTO {
    private String idcard;
    private String password;
    private String name;
    private String surname;
    private double income;
    private String phone;
}
