/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author sebastian
 */
public class Cliente {
    private String rut;
    private int edad;
    private int sexo;

    public Cliente() {
    }

    public Cliente(String rut, int edad, int sexo) {
        this.rut = rut;
        this.edad = edad;
        this.sexo = sexo;
    }


    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getRut() {
        return rut;
    }

    public int getEdad() {
        return edad;
    }

    public int getSexo() {
        return sexo;
    }
}
