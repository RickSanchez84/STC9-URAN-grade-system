package ru.innopolis.stc9.db.daoEntity.teacherSubject;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.entity.TeacherSubject;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;


@Repository
public class TeacherSubjectDaoImpl implements TeacherSubjectDao {
    private static final Logger logger = Logger.getLogger(TeacherSubjectDaoImpl.class);
    public final String ClassName = this.getClass().getName();
    //    @Autowired
//    private PersonDao personDao;
//    @Autowired
//    private SubjectDao subjectDao;
    @Autowired
    private SessionFactory factory;

    @Override
    public TeacherSubject getById(long id) throws SQLException {
        Session session = factory.openSession();
        TeacherSubject teacherSubject = session.get(TeacherSubject.class, id);
        session.close();
        logger.info("Class " + ClassName + " method getById finished, id = " + id);
        return teacherSubject;
    }

    @Override
    public TeacherSubject getByName(String name) throws SQLException {


        return null;
    }

    @Override
    public List<TeacherSubject> getAll() throws SQLException {
        Session session = factory.openSession();
        List<TeacherSubject> result;
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TeacherSubject> criteriaQuery = criteriaBuilder.createQuery(TeacherSubject.class);
        Root<TeacherSubject> root = criteriaQuery.from(TeacherSubject.class);
        criteriaQuery.select(root);
        result = session.createQuery(criteriaQuery).getResultList();
        session.close();


        return result;
    }

    @Override
    public void add(TeacherSubject teacherSubject) throws SQLException {
        logger.info("Class " + ClassName + " method add started");
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(teacherSubject);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method add finished");
    }


    @Override
    public void update(TeacherSubject teacherSubject) throws SQLException {
        logger.info("Class " + ClassName + " method update started, id = " + teacherSubject.getId());

        Session session = factory.openSession();
        session.beginTransaction();
        session.update(teacherSubject);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method update finished, id = " + teacherSubject.getId());
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class " + ClassName + " method deleteById started, id = " + id);
        Session session = factory.openSession();
        session.beginTransaction();
        TeacherSubject role = session.load(TeacherSubject.class, id);
        session.delete(role);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method deleteById finished, id = " + id);
    }
}
