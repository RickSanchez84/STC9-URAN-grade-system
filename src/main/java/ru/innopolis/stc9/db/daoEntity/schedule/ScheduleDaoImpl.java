package ru.innopolis.stc9.db.daoEntity.schedule;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.entity.Group;
import ru.innopolis.stc9.entity.Schedule;
import ru.innopolis.stc9.entity.ScheduleItem;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {
    private static final Logger logger = Logger.getLogger(ScheduleDaoImpl.class);
//    @Autowired
//    private GroupsDao groupsDao;
//    @Autowired
//    private TeacherSubjectDao teachSubjectDao;

    @Autowired
    private SessionFactory factory;

    @Override
    public ScheduleItem getById(long id) throws SQLException {
        logger.info("Class SheduleDaoImpl method getById started, id = " + id);
        Session session = factory.openSession();
        ScheduleItem scheduleItem = session.get(ScheduleItem.class, id);
        session.close();
        logger.info("Class PerformanceDaoImpl method getById finished, id = " + id);
        return scheduleItem;
    }

    @Override
    public ScheduleItem getByLessonId(long id) throws SQLException {
//        ScheduleItem result = null;
//        ResultSet resultSet = null;
//
//        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
//            try (PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT * FROM schedules WHERE lesson_nummber= ?")) {
//                preparedStatement.setLong(1, id);
//                resultSet = preparedStatement.executeQuery();
//            }
//        }
//        finally {if(resultSet!=null)
//            resultSet.close();
//        }
//
//        while (resultSet.next()) {
//            ScheduleItem scheduleItem = parseOneScheduleItem(resultSet);
//            result = scheduleItem;
//        }
//        return result;
        return null;
    }

//    private ScheduleItem parseOneScheduleItem(ResultSet resultSet) throws SQLException {
//        return new ScheduleItem(
//                resultSet.getLong("id")
//                , resultSet.getLong("day_of_week")
//                , resultSet.getLong("lesson_nummber")
//                , groupsDao.getById(resultSet.getLong("group_item"))
//                , teachSubjectDao.getById(resultSet.getLong("subject"))
//                , resultSet.getLong("room"));
//    }

    @Override
    public List<ScheduleItem> getByGroupId(long id) throws SQLException {
//        List<ScheduleItem> result = new ArrayList<>();
//
//        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
//            try (PreparedStatement preparedStatement = connection.prepareStatement(
//                    "SELECT * FROM schedules WHERE group_item= ?")) {
//                preparedStatement.setLong(1, id);
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    while (resultSet.next()) {
//                        ScheduleItem scheduleItem = parseOneScheduleItem(resultSet);
//                        result.add(scheduleItem);
//                    }
//                }
//            }
//        }
//        return result;
        return null;
    }

    @Override
    public List<ScheduleItem> getAll() throws SQLException {
        Session session = factory.openSession();
        List<ScheduleItem> result;
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleItem> criteriaQuery = criteriaBuilder.createQuery(ScheduleItem.class);
        Root<ScheduleItem> root = criteriaQuery.from(ScheduleItem.class);
        criteriaQuery.select(root);
        result = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return result;
    }

    @Override
    public void add(ScheduleItem scheduleItem) throws SQLException {
        logger.info("Class PerformanceDaoImpl method add started" + scheduleItem.getId());
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(scheduleItem);
        session.getTransaction().commit();
        session.close();
        logger.info("Class PerformanceDaoImpl method add finished");
    }

    @Override
    public void update(ScheduleItem scheduleItem) throws SQLException {
        logger.info("Class PerformanceDaoImpl method update started, id = " + scheduleItem.getId());

        Session session = factory.openSession();
        session.beginTransaction();
        session.update(scheduleItem);
        session.getTransaction().commit();
        session.close();
        logger.info("Class PerformanceDaoImpl method update finished, id = " + scheduleItem.getId());
    }


    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class PerformanceDaoImpl method deleteById started, id = " + id);
        Session session = factory.openSession();
        session.beginTransaction();
        ScheduleItem scheduleItem = session.load(ScheduleItem.class, id);
        session.delete(scheduleItem);
        session.getTransaction().commit();
        session.close();
        logger.info("Class PerformanceDaoImpl method deleteById finished, id = " + id);
    }

    @Override
    public Schedule getByGroup(Group group) throws SQLException {
        return null;
    }

//    public Schedule getByGroup(Group group) throws SQLException {
//        // TODO: 17.06.2018 ����������� ��������� select
//        return null;
//    }

}
