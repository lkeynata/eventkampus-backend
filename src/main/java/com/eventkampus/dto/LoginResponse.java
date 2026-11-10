package com.eventkampus.dto;

public class LoginResponse {

    private Long id;
    private String nama;
    private String email;
    private String role;
    private String message;

    public LoginResponse(Long id, String nama, String email, String role) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.role = role;
        this.message = "Login berhasil";
    }

    public Long getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getMessage() {
        return message;
    }
}