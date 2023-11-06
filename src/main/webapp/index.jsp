<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<body>
<h2>Login</h2>
<form action = "login" method = "POST">
    <p>username : <input type="text" name="username"/></p>
    <p>Password : <input type="text" name="password"/></p>
    <a href="register.jsp">register!!!</a>
     <%
         if(request.getAttribute("error") != null){
              out.print("<p>Invalid Credential</p>");
            }
         %>
    <input type="submit" value="Login"/>
</form>
</body>
</html>
