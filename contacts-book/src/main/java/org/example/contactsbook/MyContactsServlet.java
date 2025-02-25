package org.example.contactsbook;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.contacts.book.dao.UserDAO;

@WebServlet(name = "myContactsServlet", value = "/contacts")
public class MyContactsServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer=response.getWriter();
        String user=request.getRemoteUser();
        writer.println("Contacts of "+user);
        String html= """
                <p>
                <a href="index.jsp">Home</a>
                </p>
                <p>
                <a href="logOut">Log out</a>
                """;
        writer.println(html);
        response.setContentType("text/html");



    }
}