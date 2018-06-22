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
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.innopolis.stc9.TestContext;
import ru.innopolis.stc9.pojo.Person;
import ru.innopolis.stc9.service.IPersonService;
import ru.innopolis.stc9.service.IRoleService;

import java.util.ArrayList;
import java.util.List;

import static org.powermock.api.mockito.PowerMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class PersonControllerTest {
    MockMvc mockMvc;
    private List<Person> personList;
    private Person person1;
    @Autowired
    IPersonService personServiceMock;

    @Autowired
    IRoleService roleServiceMock;

    @Before
    public void setUp() {
        personList = new ArrayList<>();
        mockMvc = MockMvcBuilders.standaloneSetup(new PersonController(personServiceMock, roleServiceMock)).build();
        person1 = new Person(10, "Роман", new java.sql.Date(1976 - 03 - 30), "info@info.ru", 1);
        Person person2 = new Person(20, "Василий", new java.sql.Date(1983 - 04 - 20), "info@stc.ru", 2);
        personList.add(person1);
        personList.add(person2);
        when(personServiceMock.getAll()).thenReturn(personList);
        when(personServiceMock.getById(10)).thenReturn(person1);
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/personAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("/personList"))
                .andExpect(model().attribute("personList", hasSize(2)));
    }

    @Test
    public void add2() throws Exception {
        mockMvc.perform(post("/addOrUpdate")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .requestAttr("id", "11")
                .requestAttr("action", "add")
                .requestAttr("name", "test3")
                .requestAttr("birthday","1976-03-30")
                .requestAttr("email","info@stc.ru")
                .requestAttr("role","1"))
                .andExpect(view().name("redirect:personAll"));
    }

    @Test
    public void deletePerson() throws Exception {
        mockMvc.perform(get("/deletePerson")
                .requestAttr("id", "10"))
                .andExpect(view().name("redirect:personAll"));
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(get("/person")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .requestAttr("id", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("/getPerson"))
                .andExpect(model().attribute("person", person1));
    }
}