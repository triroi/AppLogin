package com.example.applogin;
// se crea clase Usuario

public class Usuario {
    // atributos
    private int id;
    private String user;
    private String password;
    private int edad;
    private String direccion;
    // cosntructor sin parámetros

    public Usuario() {
    }
    // constructor sin id, para no tener que especificarlo al crearlo
    public Usuario(String user, String password, int edad, String direccion) {
        this.user = user;
        this.password = password;
        this.edad = edad;
        this.direccion = direccion;
    }
    // cosntructor con todos los parámetros para recibir toda la información
    public Usuario(int id,String user, String password, int edad, String direccion) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.edad = edad;
        this.direccion = direccion;
    }
    // getters y setters
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
