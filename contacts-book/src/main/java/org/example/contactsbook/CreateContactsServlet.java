package org.example.contactsbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.contacts.book.dao.contacts.ContactsDAO;
import org.contacts.book.dao.contacts.ContactsDAOImpl;
import org.contacts.book.dao.user.UserDAOImpl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name ="ContactsServlet",value ="/createContact")
public class CreateContactsServlet extends HttpServlet {
    private ContactsDAO contactsDAO;
    public void init() {
        try {
            InitialContext ctx=new InitialContext();
            DataSource dataSource=(DataSource) ctx.lookup("java:comp/env/myContacts");
            contactsDAO=new ContactsDAOImpl(dataSource);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String email=req.getParameter("mail");
        String number=req.getParameter("number");
        String user=req.getRemoteUser();
        contactsDAO.createContact(name,email,number,user,null);
        resp.sendRedirect("MyContacts.jsp");

    }

}
