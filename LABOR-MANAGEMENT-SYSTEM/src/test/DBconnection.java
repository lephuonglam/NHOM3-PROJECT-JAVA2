/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBconnection {
    public static Connection getConnect()  {
        Connection con = null;
        try {    
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DBurl = "jdbc:sqlserver://localhost:1433;databaseName=LABOR-MANAGEMENT;integratedSecurity=true";      
            con  = DriverManager.getConnection(DBurl);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con ;
    }
}
