<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="org.contacts.book.dao.contacts.ContactsDAOImpl" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="org.contacts.book.dao.contacts.ContactsDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.contacts.book.model.Contact" %><%--
  Created by IntelliJ IDEA.
  User: denislupach
  Date: 27.02.2025
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>MyContacts</title>
  </head>
  <body>
  <h1>Contacts of <%=request.getRemoteUser() %></h1>
  <p>
    <a href="index.jsp">Home</a>
  </p>
  <p>
    <a href="CreateContact.jsp">Create Contact</a>
  </p>
  <p>
    <a href="logOut">Log out</a>
<center>
    <%
      ContactsDAO contactsDAO=null;
      try {
        InitialContext ctx=new InitialContext();
        DataSource dataSource=(DataSource) ctx.lookup("java:comp/env/myContacts");
        contactsDAO=new ContactsDAOImpl(dataSource);
      } catch (NamingException e) {
        throw new RuntimeException(e);
      }
      String user=request.getRemoteUser();
      List<Contact> contacts=contactsDAO.getUserContacts(user);
    %>
    <table>
      <tr>
        <td>
          name:
        </td>
        <td>
          number:
        </td>
        <td>
          email:
        </td>
        <td>
          img:
        </td>
      </tr>
      <%
        for (Contact c:contacts){
          %>
      <tr>
        <td>
          <%=c.getName()  %>
        </td>
        <td>
          <%=c.getNumber()  %>
        </td>
        <td>
          <%=c.getEmail()  %>
        </td>
        <td>
          img:
        </td>
        <td>
          <a href="EditContact.jsp?name=<%=c.getName()%>&mail=<%=c.getEmail()%>&number=<%=c.getNumber()%>">Edit</a>
        </td>
        <td>
          <a href="deleteServ?name=<%=c.getName()%>&mail=<%=c.getEmail()%>&number=<%=c.getNumber()%>">Delete</a>
        </td>
      </tr>
      <%
        }
      %>
    </table>
  </center>
  </body>
</html>
