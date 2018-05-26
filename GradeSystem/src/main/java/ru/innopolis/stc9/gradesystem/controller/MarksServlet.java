package ru.innopolis.stc9.gradesystem.controller;

import ru.innopolis.stc9.gradesystem.pojo.Mark;
import ru.innopolis.stc9.gradesystem.service.MarksService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MarksServlet extends HttpServlet {
    private MarksService ms = new MarksService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String studId = req.getParameter("StudentId");

        if (studId != null) {
            List<Mark> marks = ms.marks(Integer.parseInt(studId));

            for (Mark mark : marks) {
                resp.getWriter().println("Student: " + mark.getStudent().getName()
                        + " Course:" + mark.getCourse().getName()
                        + " Faculty name: " + mark.getFaculty().getLogin()
                        + " Mark: " + mark.getMarkValue()
                        + " TimeStamp: " + mark.getTimestamp());
            }
        } else {
            resp.getWriter().println("Nothing");
        }

    }
}
