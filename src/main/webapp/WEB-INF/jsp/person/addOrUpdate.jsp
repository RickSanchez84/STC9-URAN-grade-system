<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<div class="container">
    <main class="content">
        <c:if test="${action=='update'}">
            <h1>Редактирование</h1>
        </c:if>
        <c:if test="${action=='add'}">
            <h1>Добавление нового студента</h1>
        </c:if>
        <form action="/person/addOrUpdate" method="post">
            <c:if test="${action=='update'}">
                <input type="hidden" name="id" value="${person.id}">
            </c:if>
            <c:if test="${action=='add'}">
                <input type="hidden" name="id" value="0">
            </c:if>
            <input type="hidden" name="action" value="${action}">
            <label>Имя: </label><input type="text" value="<c:if test="${action=='update'}">${person.name}</c:if>"
                                       name="name"><BR>
            <label>День рождения: </label><input type="text"
                                                 value="<c:if test="${action=='update'}">${person.birthday}</c:if>"
                                                 name="birthday"><BR>
            <label>Адрес: </label><input type="text" value="<c:if test="${action=='update'}">${person.address}</c:if>"
                                         name="address"><BR>
            <input type="submit" value="OK">
        </form>
    </main><!-- .content -->
</div>
<!-- .container-->
<%@ include file="../../../aside.jsp" %>
<%@ include file="../../../footer.jsp" %>