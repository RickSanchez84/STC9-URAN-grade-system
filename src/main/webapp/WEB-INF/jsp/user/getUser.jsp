<%@ page import="org.apache.log4j.Logger" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! static Logger logger = Logger.getLogger(getUser_jsp.class); %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
<% logger.info("JSP: getUser.jsp starts");%>
<h1>${person.name}</h1>
<p><b>День рождения:</b> ${person.birthday}</p>
<p>Адрес:</b> ${person.email}</p>
<% logger.info("JSP: getUser.jsp stops");%>
<%@ include file="../../../footer.jsp" %>