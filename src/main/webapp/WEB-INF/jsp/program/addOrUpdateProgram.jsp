<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../../header.jsp" %>
<%@ include file="../../../aside.jsp" %>
        <form class="editForm" action="/program/addOrUpdateProgram" method="post" name="editForm">
            <c:if test="${action=='update'}">
                <input type="hidden" name="id" value="${program.id}">
            </c:if>
            <c:if test="${action=='add'}">
                <input type="hidden" name="id" value="0">
            </c:if>
            <input type="hidden" name="action" value="${action}">

                    <c:if test="${action=='update'}">
                        <h2>Редактирование</h2>
                    </c:if>
                    <c:if test="${action=='add'}">
                        <h2>Добавление новой программы</h2>
                    </c:if>
            <ul>
                <li>
                    <label for="speciality">Специальность:</label>
                    <select id="speciality" name="speciality">
                        <c:forEach var="spec" items="${specList}">
                            <option value="${spec.id}" <c:if test="${program.speciality eq spec.id}">selected</c:if>>${spec.name}</option>
                        </c:forEach>
                    </select>
                </li>

                <li>
                    <label for="semestr">Семестры:</label>
                    <select id="semestr" name="semestr">
                        <c:forEach var="sem" items="${semList}">
                            <option value= ${spec.name} </option>
                        </c:forEach>
                    </select>
                </li>

                <li>
                    <label for="subject">предмет:</label>
                    <select id="subject" name="subject">
                        <c:forEach var="subj" items="${subjList}">
                                  <option value="${subj.id}" <c:if test="${program.subject eq subj.id}">selected</c:if>>${subj.name}</option>
                        </c:forEach>
                    </select>
                </li>
                <li>

                <li>
                    <label for="hours">кол-во часов:</label>
                    <select id="hours" name="hours">
                        <c:forEach var="hour" items="${hourList}">
                            <option value= ${subj.name}</option>
                        </c:forEach>
                    </select>
                </li>
                <li>

                    <button class="submit" type="submit">OK</button>
                </li>
            </ul>
        </form>
<%@ include file="../../../footer.jsp" %>