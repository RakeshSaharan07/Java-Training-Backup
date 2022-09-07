package com.homecredit.bankingappwithrest.exception;

public class AccountException extends  Exception {

    public AccountException(int no) {

    }

    public AccountException(String errorMessge) {
        super(errorMessge);
    }

    public AccountException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}



