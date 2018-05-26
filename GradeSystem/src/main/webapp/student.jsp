<%@ page import="ru.innopolis.stc9.gradesystem.db.dao.speciality.SpecialityDaoImpl" %>
<%@ page import="ru.innopolis.stc9.gradesystem.pojo.Speciality" %>
<%@ page import="ru.innopolis.stc9.gradesystem.pojo.Student" %>
<%@ page import="ru.innopolis.stc9.gradesystem.service.StudentService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
<div class="container">
    <main class="content">
        <%
            StudentService studentService = new StudentService();
            String action = "list";
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }
            int id = 0;
            if (request.getParameter("id") != null) {
                id = Integer.parseInt(request.getParameter("id"));
            }
            if (action.equals("new") || action.equals("edit")) {
                if (action.equals("new")) { %>
        <h1>Добавление нового студента</h1>
        <form action="${pageContext.request.contextPath}/student.jsp?action=add" method="post">
            <label>Имя: </label><input type="text" value="name" name="name"><BR>
            <label>Возраст: </label><input type="text" value="" name="age"><BR>
            <label>Прогресс: </label><input type="text" value="0" name="progress"><BR>
            <label>Специальность: (выпадающий список)</label><input type="text" value="" name="speciality"><BR>
            <input type="submit" value="OK">
        </form>
        <% }
            if (action.equals("edit")) {
                Student student = studentService.getById(id);
                request.setAttribute("student", student);
                if (student == null) { %>
        <h1>Запрашиваемый студент не существует.</h1>
        <%
        } else {
        %>
        <h1><%=student.getName()%>
        </h1>
        <form action="${pageContext.request.contextPath}/student.jsp?action=save&id=<%=student.getId()%>" method="post">
            <input type="hidden" name="id" value="<%=student.getId()%>">
            <input type="hidden" name="specialityId" value="<%=student.getSpeciality().getId()%>">
            <input type="text" value="<%=student.getName()%>" name="name"><BR>
            <input type="text" value="<%=student.getAge()%>" name="age"><BR>
            <input type="text" value="<%=student.getProgress()%>" name="progress"><BR>
            <input type="text" value="<%=student.getSpeciality().getName()%>" name="speciality"><BR>
            <input type="submit" value="OK">
        </form>
        <%
                }
            }
        } else {
            if (action.equals("delete")) {
                studentService.deleteById(id);
            }
            if (action.equals("add")) {
                String name = request.getParameter("name");
                int age = Integer.parseInt(request.getParameter("age"));
                float progress = Float.parseFloat(request.getParameter("progress"));
                int specialityId = 1;
                Speciality speciality = null;
                speciality = new Speciality(specialityId, new SpecialityDaoImpl().getByID(specialityId).getName());
                Student student = new Student(0, name, age, progress, speciality);
                studentService.add(student);
            }

            if (action.equals("save")) {
                String name = request.getParameter("name");
                int age = Integer.parseInt(request.getParameter("age"));
                float progress = Float.parseFloat(request.getParameter("progress"));
                int specialityId = Integer.parseInt(request.getParameter("specialityId"));
                Speciality speciality = null;
                speciality = new Speciality(specialityId, new SpecialityDaoImpl().getByID(specialityId).getName());
                Student student = new Student(id, name, age, progress, speciality);
                studentService.updateById(student);
            }
            List<Student> studentList = studentService.getAll();
            request.setAttribute("studentList", studentList);%>

        <h1>Список студентов</h1>
        <p><b><a href="student.jsp?action=new">Добавить нового студента</a></b></p>
        <table border="1">
            <tr>
                <th>№</th>
                <th>Имя студента</th>
                <th>Действие</th>
            </tr>
            <c:forEach var="student" items="${studentList}">
                <tr>
                    <td>${student.id}</td>
                    <td><a href="student.jsp?action=edit&id=${student.id}">${student.name}</a></td>
                    <td><a href="student.jsp?action=delete&id=${student.id}">удалить</a></td>
                </tr>
            </c:forEach>
        </table>
        <%}%>
    </main><!-- .content -->
</div>
<!-- .container-->
<%@ include file="aside.jsp" %>
<%@ include file="footer.jsp" %>