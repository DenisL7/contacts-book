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
    <title>EditContact</title>
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

<form method="post" action="editServ">
  <p>
    name:<br>
    <input name="name" type="text" value="<%=request.getParameter("name")%>">
  </p>
  <p>
    mail:<br>
    <input name="mail" type="text" value="<%=request.getParameter("mail")%>">
  </p>
  <p>
    number:<br>
    <input name="number" type="text" value="<%=request.getParameter("number")%>">
  </p>
  <input type="submit">

  <input type="hidden" name="oldName" value="<%=request.getParameter("name")%>">
  <input type="hidden" name="oldMail" value="<%=request.getParameter("mail")%>">
  <input type="hidden" name="oldNumber" value="<%=request.getParameter("number")%>">


</form>
  </center>
  </body>
</html>
