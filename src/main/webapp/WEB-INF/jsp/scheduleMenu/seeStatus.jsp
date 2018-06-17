<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>

<div class="table-responsive">
    <table class="table table-striped">
        <tbody>
        <tr>
            <c:forEach var="link" items="${links}">
                <td><a href="${pageContext.request.contextPath}${link.key}">${link.value}</a></td>
            </c:forEach>
        </tr>
        </tbody>
    </table>
</div>
<a href="${pageContext.request.contextPath}seeScheduleGroupsInProgress">Назад</a>
<%@ include file="../../../footer.jsp" %>