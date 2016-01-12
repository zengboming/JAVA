/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grade.book;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cogallen
 */
public class MySQLConn {
    public static Connection MySQLConn(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/gradeBook";
        String usr = "root";
        String password = "toor";
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MySQLConn.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection conn;
            try {
                conn = DriverManager.getConnection(url, usr, password);
                return conn;
            } catch (SQLException ex) {
                Logger.getLogger(MySQLConn.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
}
