package com.batuhan.credit.services;

import com.batuhan.credit.model.dto.UserDTO;
import com.batuhan.credit.model.entity.Credit;
import com.batuhan.credit.model.entity.User;
import com.batuhan.credit.model.mapper.dtoMapper;
import com.batuhan.credit.repository.CreditRepository;
import com.batuhan.credit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditServices {

    private final CreditRepository creditRepository;

    private  final UserRepository userRepository;

    public Credit recourse(Long id){
        User findById = userRepository.getById(id);
        Credit credit = new Credit();
        credit.setUserid(findById.getId());
        credit.setStatus("idle");
        credit.setCreditLimit(0L);
        return creditRepository.save(credit);
    }

    public Boolean resultRecourse(Long id) {
        Credit creditById = creditRepository.getById(id);
        User findById = userRepository.getById(creditById.getUserid());
        if (findById.getCreditScore() <= 500) {
            creditById.setStatus("false");
            creditRepository.save(creditById);
            return false;
        }
        else if(500 < findById.getCreditScore() &&  findById.getCreditScore() <=1000){
            if(findById.getIncome() < 5000){
                creditById.setCreditLimit(10000L);
                creditById.setStatus("true");
                creditRepository.save(creditById);
            }
            else{
                creditById.setCreditLimit(20000L);
                creditById.setStatus("true");
                creditRepository.save(creditById);
            }
            return  true;
        } else {
            creditById.setCreditLimit((long) (findById.getCreditScore()* findById.getIncome()));
            creditById.setStatus("true");
            creditRepository.save(creditById);
            return true;
        }

    }
    public Credit getCreditById(Long id){
        Credit credit = creditRepository.getById(id);
        return credit;
    }


}
