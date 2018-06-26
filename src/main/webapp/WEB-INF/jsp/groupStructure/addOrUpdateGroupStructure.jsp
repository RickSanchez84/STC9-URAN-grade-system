<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>
<%@ include file="/aside.jsp" %>


<form class="editForm" action="/groupStructure/addOrUpdateGroupStructure" method="post" name="editForm">

    <c:if test="${action=='update'}">
        <input type="hidden" name="id" value="${groupStructure.id}">
    </c:if>

    <c:if test="${action=='add'}">
        <input type="hidden" name="id" value="0">
    </c:if>

    <input type="hidden" name="action" value="${action}">
    <c:if test="${action=='update'}">
        <h2>Редактирование</h2>
    </c:if>

    <c:if test="${action=='add'}">
        <h2>Добавление новой группы</h2>
    </c:if>

    <ul>
        <li>
            <label for="group">Student:</label>
            <select id="group" name="group">
                <c:forEach var="person" items="${personList}">
                    <option value="${person.id}"
                            <c:if test="${groupStructure.student_item eq person.id}">selected</c:if>>${person.name}</option>
                </c:forEach>
            </select>
        </li>


        <%--<li>--%>
        <%--<label for="group">Group name:</label>--%>
        <%--<select id="groups" name="group">--%>
        <%--<c:forEach var="group" items="${personList}">--%>
        <%--<option value="${group.id}" <c:if test="${groupStructure.student_item eq person.id}">selected</c:if>>--%>
        <%--${groupStructure.name}--%>
        <%----%>
        <%--</option>--%>
        <%--</c:forEach>--%>
        <%--</select>--%>
        <%--</li>--%>


        <li>
            <button class="submit" type="submit">OK</button>
        </li>
    </ul>


</form>
<%@ include file="/footer.jsp" %>