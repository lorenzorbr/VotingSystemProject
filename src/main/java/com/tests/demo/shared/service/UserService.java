package com.tests.demo.shared.service;

import com.tests.demo.SessionApplication;
import com.tests.demo.shared.constants.Constants;
import com.tests.demo.shared.exception.MainException;
import com.tests.demo.shared.model.User;
import com.tests.demo.shared.repository.UserRepository;
import com.tests.demo.shared.validate.ValidateCPF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository userRepository){
        this.repository = userRepository;
    }

    private static Logger logger = LoggerFactory.getLogger(SessionApplication.class);

    public User createUser(User user){
        ValidateCPF validateCPF = new ValidateCPF();
        if((user.getName() != null) && validateCPF.isCPF(user.getCpf())) {
            logger.info(Constants.USER.USER_CREATED);
            return repository.save(user);
        }
        logger.info(Constants.USER.USER_CANNOT_BE_CREATED);
        throw new MainException(Constants.USER.USER_CANNOT_BE_CREATED);

    }

    public User getUser(Long id){
        Optional<User> user = repository.findById(id);
        if(user.isPresent()){
            logger.info(Constants.USER.USER_RETURNED);
            return user.get();
        }
        throw new MainException(Constants.USER.ERROR_NOT_FOUND);
    }

}
