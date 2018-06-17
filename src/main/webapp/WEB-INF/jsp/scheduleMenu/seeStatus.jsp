<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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


<c:forEach var="i" items="${deskop.list}">
    <c:out value="${i}"/>
</c:forEach>

<h2>Расписание</h2>
<c:set var="day" value="0"/>
<c:set var="dayOfWeek" value="Понедельник"/>
<c:set var="columns" value="${fn:length(groups)+1}"/>


<div class="table-responsive">
    <table class="table table-striped">
        <tr>
            <th>№ пары \ Группа</th>
            <c:forEach var="gr" items="${groups}">
                <th><c:out value="${gr.id}"/></th>
            </c:forEach>
        </tr>

        <c:forEach var="day" items="${desktop.weekDay}">

            <tr>
                <td colspan="${columns}"><label><c:out value="${day}"/></label></td>
            </tr>

            <c:forEach var="subject" items="${desktop.lessonNumber}" varStatus="num">
                <tr>
                    <td><c:out value="урок № ${num.count}"/></td>
                    <c:forEach var="gr2" items="${groups}">
                        <td><c:out value="${subject} у группы №${gr2.id}"/></td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </c:forEach>
    </table>
</div>
<%--</jsp:useBean>--%>
<a href="${pageContext.request.contextPath}seeScheduleGroupsInProgress">Назад</a>
<%@ include file="../../../footer.jsp" %>