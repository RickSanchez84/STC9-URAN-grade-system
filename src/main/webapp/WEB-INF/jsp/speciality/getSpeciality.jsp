<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<div class="container">
    <main class="content">
        <h1>${speciality.name}</h1>
        <p><b>Название специальности:</b> ${speciality.name}</p>

    </main><!-- .content -->
</div>
<!-- .container-->
<%@ include file="../../../aside.jsp" %>
<%@ include file="../../../footer.jsp" %>