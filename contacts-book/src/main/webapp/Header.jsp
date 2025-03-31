<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="org.contacts.book.dao.contacts.ContactsDAOImpl" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="org.contacts.book.dao.contacts.ContactsDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.contacts.book.model.Contact" %>
<%@ page import="java.util.Base64" %><%--
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
<center>
  <h1>Contacts of <%=request.getRemoteUser() %></h1>

  <table>
 <tr>



<td>
  <a href="index.jsp">Home</a>
</td>
<td>
  <a href="CreateContact.jsp">Create Contact</a>
</td>
<td>
  <a href="impServ">Export Contacts</a>
</td>
<td>
  <a href="ImportContacts.jsp">Import Contacts</a>
</td>
<td>
  <a href="Statistics.jsp">Statistics</a>
</td>
<td>
  <a href="logOut">Log out</a>
  </td>
    </tr>
  </table>
</center>
<br>
<br>
<br>