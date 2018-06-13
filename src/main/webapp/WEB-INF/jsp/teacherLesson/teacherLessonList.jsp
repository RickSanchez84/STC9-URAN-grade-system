<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>Лекция - преподаватель</h1>
<p><b><a href="/teacherLesson/addOrUpdateTL">Добавить</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th></th>
            <th>Предмет</th>
            <th>Преподаватель</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="teacherLesson" items="${teacherLessonList}">
            <tr>
                <td></td>
                <td>${teacherLesson.subjectName}</td>
                <td>${teacherLesson.teacherName}</td>
                <td><a href="/teacherLesson/updateTeacherLesson?id=${teacherLesson.id}">редактировать</a></td>
                <td><a href="/teacherLesson/deleteTeacherLesson?id=${teacherLesson.id}">удалить</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>