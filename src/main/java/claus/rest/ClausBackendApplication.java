package claus.rest;

import claus.backend.database.DB;
import claus.backend.domain.elements.CategoryDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ClausBackendApplication {


    public static void main(String... args) throws SQLException
    {
        SpringApplication.run(ClausBackendApplication.class, args);
    }
}