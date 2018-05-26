package ru.innopolis.stc9.gradesystem.db.connection;

import java.sql.Connection;

public interface ConnectionManager {
    Connection getConnection();
}
