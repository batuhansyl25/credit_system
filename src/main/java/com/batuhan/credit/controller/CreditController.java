package com.batuhan.credit.controller;


import com.batuhan.credit.model.dto.UserDTO;
import com.batuhan.credit.model.entity.Credit;
import com.batuhan.credit.repository.CreditRepository;
import com.batuhan.credit.services.CreditServices;
import com.batuhan.credit.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credits")
public class CreditController {
    @Autowired
    private CreditServices creditServices;

    private CreditRepository creditRepository;

    @PostMapping("/{userid}")
    public ResponseEntity recourseByIdCard(@PathVariable("userid") Long userid) {
        Credit credit = creditServices.recourse(userid);
        if (credit == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USER NOT FOUND");
        }
        return ResponseEntity.status(HttpStatus.OK).body(credit);
    }

    @GetMapping("/result/{id}")
    public ResponseEntity resultResourceById(@PathVariable("id") Long id) {
        Boolean result = creditServices.resultRecourse(id);
        if (result == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Your loan application has not been approved");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserByIdCard(@PathVariable("id") Long id) {
        Credit credit = creditServices.getCreditById(id);
            return ResponseEntity.status(HttpStatus.OK).body(credit);
        }

}
