//package com.querino.healthcheck;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.actuate.health.Health;
//import org.springframework.boot.actuate.health.HealthIndicator;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//@Component
//public class CustomHealthIndicator implements HealthIndicator {
//    private static final Logger logger = LoggerFactory.getLogger(CustomHealthIndicator.class);
//
//    @Override
//    public Health health() {
//        try {
//            boolean isServiceHealthy = verifyH2DatabaseConnection();
//            if (isServiceHealthy) {
//                return Health.up()
//                        .withDetail("database", "H2")
//                        .withDetail("status", "Connected")
//                        .build();
//            }
//            return Health.down()
//                    .withDetail("database", "H2")
//                    .withDetail("status", "Connection failed")
//                    .build();
//        } catch (Exception e) {
//            logger.error("Health check failed", e);
//            return Health.down()
//                    .withDetail("database", "H2")
//                    .withDetail("error", e.getMessage())
//                    .build();
//        }
//    }
//
//    private boolean verifyH2DatabaseConnection() {
//        //class for name driver
//
//        try {
//            Class.forName("org.h2.Driver");
//        } catch (ClassNotFoundException e) {
//            logger.error("H2 Driver not found", e);
//            return false;
//        }
//
//        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "")) {
//            // Using try-with-resources to ensure connection is closed
//            return conn.isValid(5);
//        } catch (Exception e) {
//            logger.error("Database connection verification failed", e);
//            return false;
//        }
//    }
//}