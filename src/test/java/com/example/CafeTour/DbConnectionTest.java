package com.example.CafeTour;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnectionTest {
        static {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Test
        public void testConnection() {
            try (Connection con =
                         DriverManager.getConnection(
                                 "jdbc:mysql://localhost:3306/cafetour?serverTimezone=Asia/Seoul",
                                 "root",
                                 "wnstjr6522")) {
                System.out.println("DB Connection => " + con);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

