package com.example.applogin;

public class Usuario {
    private int id;
    private String user;
    private String password;
    private int edad;
    private String direccion;

    public Usuario() {
    }

    public Usuario(String user, String password, int edad, String direccion) {
        this.user = user;
        this.password = password;
        this.edad = edad;
        this.direccion = direccion;
    }
    public Usuario(int id,String user, String password, int edad, String direccion) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.edad = edad;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
