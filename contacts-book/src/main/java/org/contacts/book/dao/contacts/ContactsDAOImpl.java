package org.contacts.book.dao.contacts;

import com.mysql.cj.jdbc.Driver;
import org.contacts.book.model.Contact;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsDAOImpl implements ContactsDAO {
    static{
        try{
            DriverManager.registerDriver(new Driver());
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private DataSource dataSource;

    public ContactsDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createContact(String name, String mail, String number, String user, byte[] img) {
        try(Connection connection = dataSource.getConnection()){
            String sql="insert into Contacts (number,name,mail,img,user_id) values(?,?,?,?,(select id from Users where login=?));";
           PreparedStatement preparedStatement=connection.prepareStatement(sql);
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
        try(Connection connection = dataSource.getConnection()){
            List<Contact> contacts=new ArrayList<>();
            String sql="select * from Contacts where user_id=(select id from Users where login=?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            ResultSet set=preparedStatement.executeQuery();
            while(set.next()){
                Contact contact=new Contact();
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
        ;
    }

