package com.ibcs.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T>implements Serializable {

    public enum ResponseStatus{
        OK, ERROR, CREATED, UPDATED, DELETED,SUCCESS
    }
    ResponseStatus status;

    String message;

    T payload;

}
