package com.ibcs.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T>{
//
//    private String responseStatus;
//
//    private String resMessage;
//
//    private T payload;
public enum ResponseStatus{
    OK, ERROR, CREATED, UPDATED, DELETED,SUCCESS
}

    ResponseStatus status;

    String message;

    T payload;
}
