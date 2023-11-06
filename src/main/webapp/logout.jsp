<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<body>
<h2></h2>
<%
    session.invalidate();
    response.sendRedirect(request.getContextPath());
%>

</body>
</html>