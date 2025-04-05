<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contacts Book Application</title>
</head>
<body>
<center>
    <h1 style="color: blue">Welcome to Contacts Book Application
    </h1>
    <br/>
    <table>
        <tr>
            <td>
                <!--<a href="MyContacts.jsp" style="color: blue"><img size="40" src="img/home.jpeg"></a>-->
                <input type="image"
                       src="img/login1.png"
                       onmouseover="src='img/login2.png'"
                       onmouseout="src='img/login1.png'"
                       onclick="location.href='MyContacts.jsp'"/>





            </td>
            <td>
                <!--<a href="SignUp.jsp" style="color: blue" ><img size="40" src="img/signup.jpg"></a>-->
                <input type="image"
                       src="img/new2.png"
                       onmouseover="src='img/new1.png'"
                       onmouseout="src='img/new2.png'"
                       onclick="location.href='SignUp.jsp'"/>

            </td>

        </tr>
    </table>
</center>
</body>
</html>