/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.ProcesaEliminar;
import controlador.ProcesaInsertar;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebastian
 */
public class Acciones {

    public Acciones() {
    }

    
    public int Insertar(String rut, int edad, int sexo){
        int n=0;
        try {
            Class.forName(ConexionBD.getDriver()); //Declara el driver a usar
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcesaInsertar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection con = DriverManager.getConnection(ConexionBD.getUrl(),
                    ConexionBD.getUser(),
                    ConexionBD.getPass()); //Crea el objeto de conexión
            Statement st = con.createStatement(); //Crear el objeto para proceso
            String SQL = " insert into cliente (rut,edad,sexo) values ('"
                    + rut + "',"
                    + edad + ","
                    + sexo + ");"; //Crear la SQL para insertar el registro
            n = st.executeUpdate(SQL); //Ejecuta el proceso de insertar el registro
            st.close(); //cierra el objeto
            con.close(); //cierra el objeto
        } catch (SQLException ex) {
            Logger.getLogger(ProcesaInsertar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Vector Listar() {
        Vector<Cliente> dat = new Vector<Cliente>();
        try {
            Class.forName(ConexionBD.getDriver());
        } catch (ClassNotFoundException ex) {
        }
        try {
            Connection con = DriverManager.getConnection(ConexionBD.getUrl(),
                    ConexionBD.getUser(),
                    ConexionBD.getPass());
            Statement st = con.createStatement();
            String SQL = " select rut, edad, sexo from cliente order by 1;";
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                String rutRecupera = rs.getString(1);
                int edadRecupera = rs.getInt(2);
                int sexoRecupera = rs.getInt(3);
                dat.add(new Cliente(rutRecupera, edadRecupera, sexoRecupera));
            }
            st.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
        }
        return dat;
    }

    public int Eliminar(String rut) {
        int n = 0;
        try {
            Class.forName(ConexionBD.getDriver());
        } catch (ClassNotFoundException ex) {
        }
        try {
            Connection con = DriverManager.getConnection(ConexionBD.getUrl(),
                    ConexionBD.getUser(),
                    ConexionBD.getPass()); //Crea el objeto de conexión
            Statement st = con.createStatement(); //Crear el objeto para proceso
            String SQL = " delete from cliente where rut= \"" + rut + "\";"; //Crear la SQL para insertar el registro
            n = st.executeUpdate(SQL); //Ejecuta el proceso de insertar el registro
            st.close(); //cierra el objeto
            con.close(); //cierra el objeto
        } catch (SQLException ex) {
            Logger.getLogger(ProcesaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int actualizar(String rut, int edad, int sexo) {
        int n = 0;
        try {
            Class.forName(ConexionBD.getDriver());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProcesaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Connection con = DriverManager.getConnection(ConexionBD.getUrl(),
                    ConexionBD.getUser(),
                    ConexionBD.getPass());
            Statement st = con.createStatement();
            String SQL = " update cliente "
                    + " set edad =" + edad + ","
                    + " sexo=" + sexo
                    + " where rut='" + rut + "';";
            n = st.executeUpdate(SQL);
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProcesaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
