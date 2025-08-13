package com.movieflix.Exceptions;


public class UsernameOrPasswordInvalidException extends RuntimeException{

    public UsernameOrPasswordInvalidException(String message){ super(message); }
}
