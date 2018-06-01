<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<div class="container">
    <main class="content">
        <h1>Редактирование</h1>
        <form action="/person/update" method="post">
            <label>Имя: </label><input type="text" value="${person.name}" name="name"><BR>
            <label>День рождения: </label><input type="text" value="${person.birthday}" name="birthday"><BR>
            <label>Адрес: </label><input type="text" value="${person.address}" name="address"><BR>            <label>Специальность: (выпадающий список)</label><input type="text" value="" name="speciality"><BR>
            <input type="submit" value="OK">
        </form>
    </main><!-- .content -->
</div>
<!-- .container-->
<%@ include file="../../../aside.jsp" %>
<%@ include file="../../../footer.jsp" %>