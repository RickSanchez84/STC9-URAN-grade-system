package ru.innopolis.stc9.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.innopolis.stc9.TestContext;
import ru.innopolis.stc9.pojo.Subject;
import ru.innopolis.stc9.service.ISubjectService;
import ru.innopolis.stc9.service.IRoleService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration

public class SubjectControllerTest {

    private MockMvc mockMvc;
    private List<Subject> subjectList;
    private Subject subject;

    @Autowired
    ISubjectService subjectServiceMock;

    @Before
    public void setUp() {
        subjectList = new ArrayList<>();
        mockMvc = MockMvcBuilders.standaloneSetup(new SubjectController(subjectServiceMock)).build();
        subject = new Subject(10,"Some subject");
        Subject subject2 = new Subject(20,"Some subject 2");
        subjectList.add(subject);
        subjectList.add(subject2);
        when(subjectServiceMock.getAll()).thenReturn(subjectList);
        when(subjectServiceMock.getById(10)).thenReturn(subject);
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/subjectAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("/subjectList"))
                .andExpect(model().attribute("subjectList", hasSize(2)));
    }

    @Test
    public void addOrUpdate() throws Exception {
        mockMvc.perform(post("/addOrUpdate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .requestAttr("id", "10")
                .requestAttr("name", "test3"))
                .andExpect(view().name("redirect:subjectAll"));
    }

    @Test
    public void deleteSubject() throws Exception {
        mockMvc.perform(get("/deleteSubject")
                .requestAttr("id", "10"))
                .andExpect(view().name("redirect:subjectAll"));
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(get("/subject")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .requestAttr("id", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("/getSubject"))
                .andExpect(model().attribute("subject", subject));
    }
}
