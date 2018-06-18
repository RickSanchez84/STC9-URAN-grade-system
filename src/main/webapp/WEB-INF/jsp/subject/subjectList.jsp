<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<h1>Список предметов</h1>
<p><b><a href="/subjectWithTeacher/addOrUpdateSubject">Добавить новый предмет</a></b></p>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>№</th>
            <th>Название предмета</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="subjectWithTeacher" items="${subjectList}">
        <tr>
            <td>${subjectWithTeacher.id}</td>
            <td>
                <a href="/subjectWithTeacher/subjectWithTeacher?id=${subjectWithTeacher.id}">${subjectWithTeacher.name}</a>
            </td>
            <td><a href="/subjectWithTeacher/updateSubject?id=${subjectWithTeacher.id}">редактировать</a></td>
            <td><a href="/subjectWithTeacher/deleteSubject?id=${subjectWithTeacher.id}">удалить</a></td>
        </tr>
        </c:forEach>
    </table>
</div>
<%@ include file="../../../footer.jsp" %>