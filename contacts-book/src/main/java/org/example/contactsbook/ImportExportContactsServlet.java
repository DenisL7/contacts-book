package org.example.contactsbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.contacts.book.dao.contacts.ContactsDAO;
import org.contacts.book.dao.contacts.ContactsDAOImpl;
import org.contacts.book.model.Contact;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet(name = "Import",value = "/impServ")
public class ImportExportContactsServlet extends HttpServlet {
    private DataTransformer dataTransformer=new DataTransformer();
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user=req.getRemoteUser();
        Part part=req.getPart("file");
        List<Contact> contacts=dataTransformer.readFromXls(part.getInputStream());
        contactsDAO.createdContacts(contacts,user);
        resp.sendRedirect("MyContacts.jsp");



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Content-Disposition", "attachment; filename="+req.getRemoteUser()+".xls");
        List<Contact> contacts=contactsDAO.getUserContacts(req.getRemoteUser());
        dataTransformer.writeToXls(contacts,resp.getOutputStream());
        //resp.sendRedirect("MyContacts.jsp");

    }
}
