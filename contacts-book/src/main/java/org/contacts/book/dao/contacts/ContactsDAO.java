package org.contacts.book.dao.contacts;

import org.contacts.book.model.Contact;

import java.util.List;
import java.util.Map;

public interface ContactsDAO {
    public void createContact(String name,String mail,String number,String user);
    public List<Contact> getUserContacts(String user);
    public void deleteContact(String name,String mail,int number,String user);
    public void updateContact(String name,String mail,int number,String user,String oldName,String oldMail,int oldNumber);
    public List<Contact> getUserContacts(String user,int pageNumber,int pageSize);
    public int getAmountOfContactsByUser(String user);
    public List<Contact> getUserContactsFiltered(String user,String search);
    public Map<String,Integer> createdContactsByMonth(String user);
    public void createdContacts(List<Contact> contacts,String user);



}
