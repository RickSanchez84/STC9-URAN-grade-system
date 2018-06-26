package ru.innopolis.stc9.db.daoEntity.group_structure;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.entity.GroupStructure;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class GroupStructureDaoImpl implements GroupStructureDao {
    private static final Logger logger = Logger.getLogger(GroupStructureDaoImpl.class);
    public final String ClassName = this.getClass().getName();

    @Autowired
    private SessionFactory factory;

    @Override
    public GroupStructure getById(long id) {
        logger.info("Class " + ClassName + " method getById started, id = " + id);
        Session session = factory.openSession();
        GroupStructure groupStructure = session.get(GroupStructure.class, id);
        session.close();
        return groupStructure;
    }


    @Override
    public GroupStructure getByGroup(Long group) {
        return null;
    }

    @Override
    public List<GroupStructure> getAll() {
        Session session = factory.openSession();
        List<GroupStructure> result;
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<GroupStructure> criteriaQuery = criteriaBuilder.createQuery(GroupStructure.class);
        Root<GroupStructure> root = criteriaQuery.from(GroupStructure.class);
        criteriaQuery.select(root);
        result = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return result;
    }


    @Override
    public void add(GroupStructure groupStructure) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(groupStructure);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(GroupStructure groupStructure) {
        logger.info("Class " + ClassName + " method update started, id = " + groupStructure.getId());

        Session sessionUpdate = factory.openSession();
        sessionUpdate.beginTransaction();
        sessionUpdate.update(groupStructure);
        sessionUpdate.getTransaction().commit();
        sessionUpdate.close();
        logger.info("Class " + ClassName + " method update finished, id = " + groupStructure.getId());
    }

    @Override
    public void deleteById(long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        GroupStructure groupStructure = session.load(GroupStructure.class, id);
        session.delete(groupStructure);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method deleteById finished, id = " + id);
    }
}
