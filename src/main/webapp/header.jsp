<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Система учета успеваемости</title>
    <!-- Bootstrap core CSS -->
    <link href="/resources/styles/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/styles/jquery.bootstrap-touchspin.css" rel="stylesheet">
    <script src="/resources/styles/jquery.min.js"></script>
    <script src="/resources/styles/bootstrap.min.js"></script>
    <script src="/resources/styles/jquery.bootstrap-touchspin.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/resources/styles/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/styles/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="/resources/styles/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/resources/styles/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->



    <link href="/resources/styles/style_forms.css" rel="stylesheet">

    </head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">URAN</a>
        </div>
        <%--<div id="navbar" class="navbar-collapse collapse">--%>
        <%--<div class="navbar-form navbar-right">--%>
        <%--<%if (request.getSession().getAttribute("login") != null) {%>--%>
        <%--<div class="logout">--%>
        <%--<%=(String) request.getSession().getAttribute("login")%>&nbsp;--%>
        <%--<a href="${pageContext.request.contextPath}/login?action=logout">Выйти</a>--%>
        <%--</div></div>--%>
        <%--<%} else {%>--%>

        <%--<form class="navbar-form navbar-right">--%>
        <%--<div class="form-group">--%>
        <%--<input type="text" placeholder="Email" class="form-control">--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
        <%--<input type="password" placeholder="Password" class="form-control">--%>
        <%--</div>--%>
        <%--<button type="submit" class="btn btn-success">Войти</button>--%>
        <%--</form>--%>
        <%--<% } %>--%>
        <%--</div>--%>
    </div>
</nav>


<div class="container-fluid">
    <div class="row">