package ru.innopolis.stc9.db.dao.programs;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.db.connection.ConnectionManagerImpl;
import ru.innopolis.stc9.db.dao.speciality.SpecialityDao;
import ru.innopolis.stc9.db.dao.subjects.SubjectDao;
import ru.innopolis.stc9.pojo.Program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProgramsDaoImpl implements ProgramsDao {
    private static final Logger logger = Logger.getLogger(ProgramsDaoImpl.class);
    public final String ClassName = this.getClass().getName();

    @Autowired
    SpecialityDao spdi;
    @Autowired
    SubjectDao sjdi;

    @Override
    public Program getById(long id) throws SQLException {
        logger.info("Class " + ClassName + " method getById started, id = " + id);
        Program program = null;


        int iid = (int) id;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM programs WHERE id= ?")) {
                preparedStatement.setInt(1, iid);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        program = parseProgram(resultSet);
                    }
                }
            }
        }

        logger.info("Class " + ClassName + " method getById finished, id = " + id);
        return program;
    }

    @Override
    public Program getBySpeciality(String specialty) throws SQLException {
        Program result = null;

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM programs WHERE specialty= ?")) {
                preparedStatement.setString(1, specialty);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        Program program = parseProgram(resultSet);
                        result = program;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<Program> getAll() throws SQLException {
        ArrayList<Program> result = new ArrayList<>();

        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM programs ORDER BY id")) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Program program = parseProgram(resultSet);
                        result.add(program);
                    }
                }
            }
        }

        return result;
    }

    @Override
    public long add(Program program) throws SQLException {
        logger.info("Class " + ClassName + " method add started");
        long result = 0;

        String sql = "INSERT INTO programs (specialty, semester, subject, hours) VALUES (?,?,?,?) RETURNING id";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, program.getSpecialty().getId());
                statement.setLong(2, program.getSemester());
                statement.setLong(3, program.getSubject().getId());
                statement.setLong(4, program.getHours());
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        result = rs.getLong("id");
                    }
                }
            }
        }
        logger.info("Class " + ClassName + " method add finished");
        return result;
    }

    @Override
    public void update(Program program) throws SQLException {
        logger.info("Class " + ClassName + " method update started, id = " + program.getId());

        String sql = "UPDATE programs SET specialty = ?, semester = ?, subject= ?, hours= ?  WHERE id = " + program.getId() + "";

        executeStatement(program, sql);
        logger.info("Class " + ClassName + " method update finished, id = " + program.getId());
    }

    private void executeStatement(Program program, String sql) throws SQLException {
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, (long) program.getSpecialty().getId());
                statement.setLong(2, program.getSemester());
                statement.setLong(3, (long) program.getSubject().getId());
                statement.setLong(4, program.getHours());
                statement.executeUpdate();
            }
        }
    }

    @Override
    public void deleteById(long id) throws SQLException {
        logger.info("Class " + ClassName + " method deleteById started, id = " + id);
        String sql = "DELETE FROM programs WHERE id=?";
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        }
        logger.info("Class " + ClassName + " method deleteById finished, id = " + id);
    }

    @Override
    public List<Program> getAllForSpecialty(long specialtyId) throws SQLException {
        ArrayList<Program> result = new ArrayList<>();
        try (Connection connection = new ConnectionManagerImpl().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM programs where specialty = ? ;")) {
                preparedStatement.setLong(1, specialtyId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Program program = parseProgram(resultSet);
                        result.add(program);
                    }
                }
            }
        }
        return result;
    }

    private Program parseProgram(ResultSet resultSet) throws SQLException {
        return new Program(
                resultSet.getLong("id")
                , spdi.getById(resultSet.getLong("specialty"))
                , resultSet.getLong("semester")
                , sjdi.getById(resultSet.getLong("subject"))
                , resultSet.getLong("hours"));
    }
}
