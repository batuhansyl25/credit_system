package com.batuhan.credit.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String idcard;
    private String name;
    private String surname;
    private double income;
    private String phone;
    private int creditScore;
}
