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
    <title>CreateContact</title>
</head>
<body>
<form method="post" action="createContact" >

    <p>name:<br>
        <input type="text" name="name">
    </p>
    <p>
        number:<br>
        <input type="text" name="number">
    </p>
    <p>
        mail:<br>
        <input type="text" name="mail">
    </p>
    <p>
        <input type="submit">
    </p>

</form>
</body>
</html>
