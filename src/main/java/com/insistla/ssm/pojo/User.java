package com.insistla.ssm.pojo;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;

public class User implements Serializable {

    private Integer userId;
    private String userName;
    private String userPassword;

}
