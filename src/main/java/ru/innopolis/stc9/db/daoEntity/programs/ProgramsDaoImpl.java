package ru.innopolis.stc9.db.daoEntity.programs;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.entity.Program;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProgramsDaoImpl implements ProgramsDao {
    private static final Logger logger = Logger.getLogger(ProgramsDaoImpl.class);
    public final String ClassName = this.getClass().getName();

//    @Autowired
//    SpecialityDao spdi;
//    @Autowired
//    SubjectDao sjdi;

    @Autowired
    private SessionFactory factory;

    @Override
    public Program getById(long id) throws SQLException {
        logger.info("Class " + ClassName + " method getById started, id = " + id);
        Session session = factory.openSession();
        Program program = session.get(Program.class, id);
        session.close();

        logger.info("Class " + ClassName + " method getById finished, id = " + id);
        return program;
    }

    @Override
    public Program getBySpeciality(String specialty) throws SQLException {
//        Program result = null;
//
//        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
//            try (PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT * FROM programs WHERE specialty= ?")) {
//                preparedStatement.setString(1, specialty);
//                try ( ResultSet resultSet = preparedStatement.executeQuery()) {
//
//                    while (resultSet.next()) {
//                        Program program = new Program(
//                                resultSet.getLong("id")
//                                , spdi.getById(resultSet.getLong("specialty"))
//                                , resultSet.getLong("semester")
//                                , sjdi.getById(resultSet.getLong("subject"))
//                                , resultSet.getLong("hours"));
//                        result = program;
//                    }
//                }
//            }
//        }
//        return result;
        return null;
    }

    @Override
    public List<Program> getAll() throws SQLException {
        List<Program> result;
        Session session = factory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Program> criteriaQuery = criteriaBuilder.createQuery(Program.class);
        Root<Program> root = criteriaQuery.from(Program.class);
        criteriaQuery.select(root);
        result = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return result;
    }

    @Override
    public void add(Program program) throws SQLException {
        logger.info("Class " + ClassName + " method add started" + program.getId());
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(program);
        session.getTransaction().commit();
        session.close();

        logger.info("Class " + ClassName + " method add finished");
    }

    @Override
    public void update(Program program) throws SQLException {
        logger.info("Class " + ClassName + " method add started" + program.getId());
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(program);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method update finished, id = " + program.getId());
    }


    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class " + ClassName + " method deleteById started, id = " + id);
        Session session = factory.openSession();
        session.beginTransaction();
        Program program = session.load(Program.class, id);
        session.delete(program);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method deleteById finished, id = " + id);
    }
}
