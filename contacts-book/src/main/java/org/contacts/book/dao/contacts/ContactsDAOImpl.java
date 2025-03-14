package org.contacts.book.dao.contacts;

import com.mysql.cj.jdbc.Driver;
import org.contacts.book.model.Contact;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsDAOImpl implements ContactsDAO {
    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private DataSource dataSource;

    public ContactsDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createContact(String name, String mail, String number, String user, byte[] img) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "insert into Contacts (number,name,mail,img,user_id) values(?,?,?,?,(select id from Users where login=?));";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, number);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, mail);
            preparedStatement.setBytes(4, img);
            preparedStatement.setString(5, user);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contact> getUserContacts(String user) {
        try (Connection connection = dataSource.getConnection()) {
            List<Contact> contacts = new ArrayList<>();
            String sql = "select * from Contacts where user_id=(select id from Users where login=?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                Contact contact = new Contact();
                contact.setEmail(set.getString("mail"));
                contact.setName(set.getString("name"));
                contact.setNumber(set.getString("number"));
                contacts.add(contact);

            }
            return contacts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteContact(String name, String mail, int number, String user) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "delete from Contacts where name=? and mail=? and number=? and user_id=(select id from Users where login=?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, mail);
            preparedStatement.setInt(3, number);
            preparedStatement.setString(4, user);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateContact(String name, String mail, int number, String user, String oldName, String oldMail, int oldNumber) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "update Contacts set name=?,mail=?,number=? where user_id=((select id from Users where login=?)) and name=? and mail=? and number=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, mail);
            preparedStatement.setInt(3, number);
            preparedStatement.setString(4, user);
            preparedStatement.setString(5, oldName);
            preparedStatement.setString(6, oldMail);
            preparedStatement.setInt(7, oldNumber);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Contact> getUserContacts(String user, int pageNumber, int pageSize) {
        try (Connection connection = dataSource.getConnection()) {
            List<Contact> contacts = new ArrayList<>();
            String sql = "select * from Contacts where user_id=(select id from Users where login=?) limit ? offset ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setInt(2, pageSize);
            preparedStatement.setInt(3, pageNumber * pageSize);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                Contact contact = new Contact();
                contact.setEmail(set.getString("mail"));
                contact.setName(set.getString("name"));
                contact.setNumber(set.getString("number"));
                contact.setImage(set.getBytes("img"));
                contacts.add(contact);

            }
            return contacts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getAmountOfContactsByUser(String user) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "select count(*) from Contacts where user_id=(select id from Users where login=?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            ResultSet set = preparedStatement.executeQuery();
            set.next();
            return set.getInt(1);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Contact> getUserContactsFiltered(String user, String search) {
        try (Connection connection = dataSource.getConnection()) {
            List<Contact> contacts = new ArrayList<>();
            String sql = "select * from Contacts where user_id=(select id from Users where login=?) and name like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2,"%"+ search +"%");
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                Contact contact = new Contact();
                contact.setEmail(set.getString("mail"));
                contact.setName(set.getString("name"));
                contact.setNumber(set.getString("number"));
                contacts.add(contact);

            }
            return contacts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    }





