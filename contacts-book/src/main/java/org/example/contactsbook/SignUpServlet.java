package org.example.contactsbook;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.contacts.book.dao.UserDAO;
import org.contacts.book.dao.UserDAOImpl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name ="signUpServlet",value ="/signUp")
public class SignUpServlet extends HttpServlet {
    private UserDAO userDAO;
    public void init() {
        try {
            InitialContext ctx=new InitialContext();
            DataSource dataSource=(DataSource) ctx.lookup("java:comp/env/myContacts");
            userDAO=new UserDAOImpl(dataSource);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String login=req.getParameter("login");
       String password=req.getParameter("password");
       userDAO.createUser(login, password);
       resp.sendRedirect("index.jsp");

    }
}
