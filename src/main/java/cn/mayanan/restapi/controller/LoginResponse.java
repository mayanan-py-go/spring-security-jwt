package cn.mayanan.restapi.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String status;
    private String token;

    public LoginResponse(String status, String token) {
        this.status = status;
        this.token = token;
    }
}
