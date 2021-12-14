package com.example.demo.web.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException  extends RuntimeException{

    public ObjectNotFoundException()  {
    }
}
