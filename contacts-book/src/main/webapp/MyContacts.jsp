<%@ include file = "Header.jsp" %>
<center>
    <form action="#" method="post">
      <input type="text" name="search">
      <input type="submit">

    </form>
    <%
      ContactsDAO contactsDAO=null;
      try {
        InitialContext ctx=new InitialContext();
        DataSource dataSource=(DataSource) ctx.lookup("java:comp/env/myContacts");
        contactsDAO=new ContactsDAOImpl(dataSource);
      } catch (NamingException e) {
        throw new RuntimeException(e);
      }
      int pageSize=10;
      int pageNumber=0;
      String pageSizeParam=request.getParameter("pageSize");
      if (pageSizeParam!=null){
        pageSize=Integer.parseInt(pageSizeParam);

      }
      String user=request.getRemoteUser();
      String pageNumberParam=request.getParameter("pageNumber");
      String prev=request.getParameter("prev");
      String next=request.getParameter("next");
      List<Contact> contacts=null;
      String search=request.getParameter("search");
      if (search!=null){
        contacts=contactsDAO.getUserContactsFiltered(user,search);

      }else {


        if (pageNumberParam != null) {
          pageNumber = Integer.parseInt(pageNumberParam);
          if (prev != null) {
            pageNumber--;
            if (pageNumber < 0) {
              pageNumber = 0;
            }
          }
          if (next != null) {
            pageNumber++;
            int amountOfContacts = contactsDAO.getAmountOfContactsByUser(user);
            if (pageNumber * pageSize > amountOfContacts) {
              pageNumber--;
            }

          }
        }


       contacts = contactsDAO.getUserContacts(user, pageNumber, pageSize);
      }
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
          creation date:
        </td>
        <td>
          modification date:
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
          <%=c.getCreationDate()  %>
        </td>
        <td>
          <%=c.getModificationDate()  %>
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
    <p>
    <form method="get" action="#">
    <input type="hidden" value="<%=pageNumber%>" name="pageNumber">
      <input type="submit" name="prev" value="<<">
      <input type="text" value="<%=pageSize%>" name="pageSize">
      <input type="submit" name="next" value=">>" >
    </form>
  </p>
  </center>
  </body>
</html>
