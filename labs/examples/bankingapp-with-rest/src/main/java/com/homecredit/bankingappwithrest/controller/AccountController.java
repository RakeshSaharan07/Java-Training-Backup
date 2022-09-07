package com.homecredit.bankingappwithrest.controller;


import com.homecredit.bankingappwithrest.dao.AccountDao;
import com.homecredit.bankingappwithrest.model.Account;
import com.homecredit.bankingappwithrest.model.ResponseMessage;
import com.homecredit.bankingappwithrest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountException;
import javax.validation.Valid;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/accounts")
public class AccountController {


    @Autowired
    AccountDao service  ;

    @GetMapping(path = "/")
    public ResponseEntity<HashMap<Integer, Account>> getAccounts(){
        return  ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable int id){
        return ResponseEntity.ok().body(service.get(id));
    }

    @PostMapping
    public ResponseMessage create(@RequestBody @Valid  Account account) throws AccountException, com.homecredit.bankingappwithrest.exception.AccountException {
        ResponseMessage response = new ResponseMessage();
        service.create(account);
        response.setStatus("Status...");
        response.setMessage("message...");
        return response;
    }

    @DeleteMapping("/{id}")
    public  Boolean delete(@PathVariable int id) throws AccountException, com.homecredit.bankingappwithrest.exception.AccountException {
        service.delete(id);
        return true;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseMessage handleValidationError(MethodArgumentNotValidException e){
        ResponseMessage response = new ResponseMessage();
        response.setStatus("Error");
        response.setMessage("Validation Error: "+e.getMessage());
        return response;

    }

    @ExceptionHandler(Exception.class)
        public ResponseMessage handlerGenericException(Exception e){
            ResponseMessage response = new ResponseMessage();
            response.setStatus("Failure");
            response.setMessage("Validation Errror : "+e.getMessage());

             return response;
        }

    

}
