package org.contacts.book.dao.contacts;

import org.contacts.book.model.Contact;

import java.util.List;

public interface ContactsDAO {
    public void createContact(String name,String mail,String number,String user,byte[] img);
    public List<Contact> getUserContacts(String user);

}
