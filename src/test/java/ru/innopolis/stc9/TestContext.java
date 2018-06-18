package ru.innopolis.stc9;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.innopolis.stc9.db.dao.person.PersonDao;
import ru.innopolis.stc9.db.dao.person.PersonDaoImpl;
import ru.innopolis.stc9.db.dao.roles.RoleDao;
import ru.innopolis.stc9.db.dao.roles.RoleDaoImpl;
import ru.innopolis.stc9.service.IPersonService;
import ru.innopolis.stc9.service.IRoleService;
import ru.innopolis.stc9.service.PersonService;
import ru.innopolis.stc9.service.RoleService;

@Configuration
public class TestContext {
    @Bean
    public IPersonService personService() {
        return Mockito.mock(PersonService.class);
    }

    @Bean
    public IRoleService roleService() {
        return Mockito.mock(RoleService.class);
    }

    @Bean
    public PersonDao personDao() {
        return Mockito.mock(PersonDaoImpl.class);
    }

    @Bean
    public RoleDao roleDao() {
        return Mockito.mock(RoleDaoImpl.class);
    }
}
