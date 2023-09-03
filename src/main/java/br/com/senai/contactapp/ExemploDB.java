package br.com.senai.contactapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExemploDB {
	
	public static void main(String[] args) {
		try {
			Class.forName("org.h2.Driver");
			String url = "jdbc:h2:./data/database";
			String user = "sa";
			String password = "web123";
			Connection connection = DriverManager.getConnection(url, user, password); 
			
			String sql = "select * from person";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				
				System.out.println("Nome : "+name + " id: "+id);
			}
			
			String sqlInsert = "insert into person (name) values(?)";
			statement = connection.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS );
			statement.setString(1, "Ana Ferreira");
			statement.execute();
			
			
			resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				System.out.println("O id da Ana é "+resultSet.getInt("id"));
			}
			
			sql = "select * from person";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				System.out.println("Nome : "+name + " id: "+id);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
