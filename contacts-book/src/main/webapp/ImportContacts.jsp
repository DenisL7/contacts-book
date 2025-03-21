<%--
  Created by IntelliJ IDEA.
  User: denislupach
  Date: 27.02.2025
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Import Contacts</title>
</head>
<body>
<form method="post" action="impServ" enctype="multipart/form-data" >


    <p>
        Please choose xls file:<br>
        <input type="file" name="file">
    </p>
    <p>
        <input type="submit">
    </p>

</form>
</body>
</html>
