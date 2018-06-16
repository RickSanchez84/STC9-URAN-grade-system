<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../../header.jsp" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">URAN-TEAM presentation</a>
        </div>
    </div>

</nav>

<h2>Регистрация</h2>
<div id="navbar" class="navbar-collapse collapse">
    <form action="${pageContext.request.contextPath}/registration" method="post">

        <div class="form-group">
            <input type='text' name='login' placeholder="login" class="form-control" required autofocus><br>
        </div>

        <div class="form-group">
            <input type='text' name='password' placeholder="password" class="form-control" required><br>
        </div>

        <div class="form-group">
            <input type='text' name='role' placeholder="role" class="form-control" required><br>
        </div>
        <button type="submit">Сохранить</button>

    </form>
</div>


<%@include file="../../footer.jsp" %>



