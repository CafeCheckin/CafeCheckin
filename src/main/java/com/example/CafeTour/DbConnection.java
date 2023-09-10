package com.example.CafeTour;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
        static {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
