package com.example.postgresdemo;

import org.testcontainers.containers.PostgreSQLContainer;

public class CompetencyPostgresqlContainer extends PostgreSQLContainer<CompetencyPostgresqlContainer> {
    private static final String IMAGE_VERSION = "postgres:11.1";
    private static CompetencyPostgresqlContainer container;

    private CompetencyPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static CompetencyPostgresqlContainer getInstance() {
        if (container == null) {
            container = new CompetencyPostgresqlContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("jdbc:postgresql://localhost:5432/postgres", container.getJdbcUrl());
        System.setProperty("postgres", container.getUsername());
        System.setProperty("123456", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
