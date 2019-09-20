/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexãoBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aless
 */
public class ODBC {
    public Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:postgresql://devops:xabituca@192.168.91.254/xabitucaDB");
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
