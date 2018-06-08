<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
        <h1>${program.name}</h1>
        <p><b>специальность:</b> ${program.birthday}</p>
        <p>Адрес:</b> ${program.email}</p>
        <p>Права:</b> ${role}</p>
<%@ include file="../../../footer.jsp" %>