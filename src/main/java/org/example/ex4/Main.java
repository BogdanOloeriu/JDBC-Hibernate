package org.example.ex4;

import org.example.DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {


        try{
            Statement statement = DataBaseConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from Client;");
            while(resultSet.next()){
                System.out.println("ID: " + resultSet.getInt(1) + " Name: " + resultSet.getString(2) +
                        " Phone: " + resultSet.getString(3) + " email: " + resultSet.getString(4)+
                        " Birthdate: " + resultSet.getString(5) + " CNP: " + resultSet.getString(6) );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
