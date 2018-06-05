<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<div class="container">
    <main class="content">
        <c:if test="${action=='update'}">
            <h1>Редактирование</h1>
        </c:if>
        <c:if test="${action=='add'}">
            <h1>Добавление нового предмета</h1>
        </c:if>
        <form action="/subject/addOrUpdateSubject" method="post">
            <c:if test="${action=='update'}">
                <input type="hidden" name="id" value="${subject.id}">
            </c:if>
            <c:if test="${action=='add'}">
                <input type="hidden" name="id" value="0">
            </c:if>
            <input type="hidden" name="action" value="${action}">
            <label>Название: </label><input type="text" value="<c:if test="${action=='update'}">${subject.name}</c:if>"
                                       name="name"><BR>

            <input type="submit" value="OK">
        </form>
    </main><!-- .content -->
</div>
<!-- .container-->
<%@ include file="../../../aside.jsp" %>
<%@ include file="../../../footer.jsp" %>