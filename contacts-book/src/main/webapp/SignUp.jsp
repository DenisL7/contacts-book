<%--
  Created by IntelliJ IDEA.
  User: denislupach
  Date: 21.02.2025
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignUp</title>
</head>
<body>
<H1>New user registration form</H1>
<form method="post" action="signUp">
  <p>
    login:<br>
    <input type="text" name="login" style="color: darkred">

  </p>
  <p>
    password:<br>
    <input type="password" name="password" style="color: darkred">
  </p>
  <input type="submit" style="color: darkred">
</form>

</body>
</html>
