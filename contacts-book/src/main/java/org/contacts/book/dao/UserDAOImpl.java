package org.contacts.book.dao;

import com.mysql.cj.jdbc.Driver;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserDAOImpl implements UserDAO {
    static{
        try{
            DriverManager.registerDriver(new Driver());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private DataSource dataSource;

    public UserDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createUser(String login, String password) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "insert into Users(login,password) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
