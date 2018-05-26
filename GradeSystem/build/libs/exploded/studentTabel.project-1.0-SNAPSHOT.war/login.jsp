<%@ page import="java.time.Instant" %><%--
  Created by IntelliJ IDEA.
  User: Rikki
  Date: 07.05.2018
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Логин/пароль</title>
</head>
<body>
<%=request.getAttribute("message") + "<br>"%>
<%
    Instant currentTime = Instant.now();
    String timeMessage = "Current Time: " + currentTime;
%>
<%=timeMessage%>
<%=("authErr".equals(request.getParameter("errorMsg"))) ? "Неверное имя пользователя/пароль" : ""%><BR>
<%=("noAuth".equals(request.getParameter("errorMsg"))) ? "Наверное, стоит сначала пройти авторизацию" : ""%><BR>

<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" value="user" name="userName"><BR>
    <input type="text" value="password" name="userPassword"><BR>
    <input type="submit" value="OK">
</form>

</body>
</html>
