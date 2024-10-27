package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import controlador.ConexionBase;

public class Main {
    public static void main(String[] args) {

        EnergiaRenovable e2 = new EnergiaRenovable();
        EnergiaRenovable e3 = new EnergiaRenovable();

        EnergiaHidroelectrica eh1 = new EnergiaHidroelectrica();
        
       /* ConexionBase cone= new ConexionBase();
        
        String url = "jdbc:mysql://localhost:3306/energia";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("Conexión exitosa!");
            } else {
                System.out.println("Fallo en la conexión.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }
}