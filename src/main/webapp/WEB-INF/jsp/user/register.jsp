<%@ page import="org.apache.log4j.Logger" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! static Logger logger = Logger.getLogger(register_jsp.class); %>

<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<% logger.info("JSP: register.jsp starts");%>
<form class="editForm" action="/user/register" method="post" name="editForm">
    <h5>Регистрация! ура!</h5>

    <%--<c:if test="${action=='update'}">--%>
    <%--<input type="hidden" name="id" value="${user.id}">--%>
    <%--</c:if>--%>
    <c:if test="${action=='add'}">
        <%--<input type="hidden" name="id" value="0">--%>
    </c:if>
    <input type="hidden" name="action" value="${action}">

    <%--<c:if test="${action=='update'}">--%>
    <%--<h2>Редактирование</h2>--%>
    <%--</c:if>--%>
    <c:if test="${action=='add'}">
        <h2>Регистрация нового пользователя</h2>
    </c:if>
    <ul>
        <li>
            <label for="name">Имя:</label>
            <input type="text" placeholder="Иван Иванов" required/>
            <%--value="<c:if test="${action=='update'}">${user.name}</c:if>" name="name"/>--%>
        </li>

        <li>
            <label for="birthday">День рождения:</label>
            <input type="text" placeholder="1970-01-01" required/>
            <%--value="<c:if test="${action=='update'}">${user.birthday}</c:if>" name="birthday"/>--%>
        </li>

        <li>
            <label for="email">Email:</label>
            <input type="email" placeholder="ivan@example.ru" required/>
            <%--value="<c:if test="${action=='update'}">${user.email}</c:if>" name="email"/>--%>
            <span class="form_hint">Proper format "name@something.com"</span>
        </li>
        <li>
            <label for="login">Логин:</label>
            <input type="text" placeholder="Логин" required/>
            <%--required value="<c:if test="${action=='update'}">${user.role}</c:if>"--%>
            <%--name="role"/>--%>
        </li>
        <li>
            <label for="password">Пароль:</label>
            <input type="password" placeholder="Пароль " + required/>
            <%--value="<c:if test="${action=='update'}">${user.role}</c:if>"--%>
            <%--name="role"/>--%>
        </li>
        <li>
            <label for="password2">Пароль:</label>
            <input type="password" placeholder="Повторите пароль " + required/>
            <%--<input type="password" required value="<c:if test="${action=='update'}">${user.role}</c:if>"--%>
            <%--name="role"/>--%>
        </li>
        <li>
            <button class="submit" type="submit">Зарегистрироваться</button>
        </li>
    </ul>
</form>
<% logger.info("JSP: addOrUpdateUser.jsp stops");%>
<%@ include file="../../../footer.jsp" %>