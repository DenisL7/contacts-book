package org.example.contactsbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.contacts.book.dao.contacts.ContactsDAO;
import org.contacts.book.dao.contacts.ContactsDAOImpl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name="editContacts",value="/editServ")
public class EditContactsServlet extends HttpServlet {
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
       String user= req.getRemoteUser();
       String name=req.getParameter("name");
        String mail=req.getParameter("mail");
        int number=Integer.parseInt(req.getParameter("number"));
        String oldName=req.getParameter("oldName");
        String oldMail=req.getParameter("oldMail");
        int oldNumber=Integer.parseInt(req.getParameter("oldNumber"));
        contactsDAO.updateContact(name, mail,number,user,oldName,oldMail,oldNumber);
        resp.sendRedirect("MyContacts.jsp");

    }
}
