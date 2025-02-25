<%--
  Created by IntelliJ IDEA.
  User: denislupach
  Date: 25.02.2025
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="j_security_check">
  <p>
    login:<br>
    <input type="text" name="j_username">

  </p>
  <p>
    password:<br>
    <input type="password" name="j_password">
  </p>
  <input type="submit">

</form>
</body>
</html>
