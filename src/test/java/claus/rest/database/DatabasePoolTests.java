package claus.rest.database;

import claus.backend.database.DB;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabasePoolTests {
    @AfterEach
    void teardown() throws SQLException {
        DB.clearPool();
    }

    @Test
    void AddConnectionToPool() throws SQLException {
        int size = 2;
        for (int i = 0; i < size; i++)
            DB.getConnection();

        assert (DB.activePoolSize() == size);
    }

    @Test
    void freeConnection() throws SQLException {
        DB.getConnection();
        Connection con = DB.getConnection();
        DB.freeConnection(con);

        assert (DB.activePoolSize() == 1);
    }

    @Test
    void getFirstFreeConnection() throws SQLException {
        DB.getConnection();
        Connection con = DB.getConnection();
        DB.getConnection();

        DB.freeConnection(con);

        Connection conCopy = DB.getConnection();

        assert (con.equals(conCopy));
    }

    @Test
    void cleanPool() throws SQLException
    {
        DB.getConnection();
        var con  = DB.getConnection();
        DB.freeConnection(con);
        DB.cleanPool();

        assert (DB.poolSize() == 1);
    }
}
