package com.example.jd.s04;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.jd.Config;

public class InserterExercise {
    private static final Logger log = LogManager.getLogger(InserterExercise.class);

    private static final String INSERT_SERVICE_BY_NAME_AND_LOCATION = """
    		INSERT INTO service(name, location_id) 
    		values ('%s', %s)
    		""";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Pass me a service name and its location!");
            return;
        }

        DataSource ds = Config.getDataSource();
        try (Connection conn = ds.getConnection(); //
                Statement stmt = conn.createStatement()) {
        	String sql = String.format(INSERT_SERVICE_BY_NAME_AND_LOCATION, args[0], args[1]);
        	int lines = stmt.executeUpdate(sql);
        	System.out.printf("Insert executed, %d lines affected%n", lines);
        } catch (SQLException se) {
            log.fatal("Can't insert", se);
        }
    }
}
