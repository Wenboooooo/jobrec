package com.example.jobrec.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;


public class LoginRequestBody {
    @JsonProperty("user_id")
    public String userId;
    public String password;
}
