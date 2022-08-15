package com.batuhan.credit.model.entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;


@Data

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String idcard;
    private String password;
    private String name;
    private String surname;
    private double income;
    private String phone;
    private int creditScore;



}