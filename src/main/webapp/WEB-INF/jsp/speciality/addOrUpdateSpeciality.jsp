<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
        <c:if test="${action=='update'}">
            <h1>Редактирование</h1>
        </c:if>

        <c:if test="${action=='add'}">
            <h1>Добавление новой специальности</h1>
        </c:if>

        <form action="/speciality/addOrUpdateSpeciality" method="post">

            <c:if test="${action=='update'}">
                <input type="hidden" name="id" value="${speciality.id}">
            </c:if>

            <c:if test="${action=='add'}">
                <input type="hidden" name="id" value="0">
            </c:if>

            <input type="hidden" name="action" value="${action}">

            <label>Название: </label><input type="text" value="<c:if test="${action=='update'}">${speciality.name}</c:if>" name="name"><BR>
            <label>Семестр: </label><input type="text" value="<c:if test="${action=='update'}">${speciality.semesterCount.toString()}</c:if>" name="semesterCount"><BR>

            <input type="submit" value="OK">
        </form>
<%@ include file="../../../footer.jsp" %>