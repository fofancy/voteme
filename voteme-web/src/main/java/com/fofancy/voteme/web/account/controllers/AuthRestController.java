package com.fofancy.voteme.web.account.controllers;

import com.fofancy.auth.model.ErrorMessage;
import com.fofancy.auth.model.UserSignupDto;
import com.fofancy.auth.model.ValidationResponse;
import com.fofancy.auth.services.UserSignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by User on 12.07.2017.
 */
@RestController
@RequestMapping()
public class AuthRestController {

    @Autowired
    UserSignupService signupService;

    public AuthRestController() {
    }

    public UserSignupService getSignupService() {
        return signupService;
    }

    public void setSignupService(UserSignupService signupService) {
        this.signupService = signupService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/account/auth/login")
    public ResponseEntity<?> doLogin() {
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/account/auth/signup", consumes="application/json")
    public ValidationResponse doSignup(@RequestBody @Valid UserSignupDto userSignupDto, BindingResult result) {
        ValidationResponse response = new ValidationResponse();
        System.out.println(userSignupDto.getUsername());
        if(result.hasErrors()) {
            response.setStatus(HttpStatus.BAD_GATEWAY);
            for (FieldError error : result.getFieldErrors()) {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setFieldName(error.getField());
                errorMessage.setMessage(error.getDefaultMessage());
                response.addMessage(errorMessage);
            }
            return response;
        }

        signupService.signupUser(userSignupDto);

        response.setStatus(HttpStatus.OK);

        return response;
    }
}
