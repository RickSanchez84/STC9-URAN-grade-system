<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<form class="editForm" action="/teacherLesson/addOrUpdateTL" method="post" name="editForm">
    <c:if test="${action=='update'}">
        <input type="hidden" name="id" value="${teacherLesson.id}">
    </c:if>
    <c:if test="${action=='add'}">
        <input type="hidden" name="id" value="0">
    </c:if>
    <input type="hidden" name="action" value="${action}">

    <c:if test="${action=='update'}">
        <h2>Редактирование</h2>
    </c:if>
    <c:if test="${action=='add'}">
        <h2>Добавление нового преподавателя к предмету</h2>
    </c:if>
    <ul>
        <li>
            <label for="teacherItem">Преподаватель:</label>
            <select id="teacherItem" name="teacher_item">
                <c:forEach var="teacher" items="${teacherList}">
                    <c:set var="teacher_item" value="${teacher.id}"></c:set>
                    <option value="${teacher_item}"
                            <c:if test="${teacherLesson.teacherItem eq teacher_item}">selected</c:if>>${teacher.name}</option>
                </c:forEach>
            </select>
        </li>

        <li>
            <label for="subjectItem">Предмет:</label>
            <select id="subjectItem" name="subject_item">
                <c:forEach var="subject" items="${subjectList}">
                    <c:set var="subject_item" value="${subject.id}"></c:set>
                    <option value="${subject_item}"
                            <c:if test="${teacherLesson.subjectItem eq subject_item}">selected</c:if>>${subject.name}</option>
                </c:forEach>
            </select>
        </li>
        <li>
            <button class="submit" type="submit">OK</button>
        </li>
    </ul>
</form>
<%@ include file="../../../footer.jsp" %>