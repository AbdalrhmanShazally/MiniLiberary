package com.revature.util;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        try {
            System.out.println(ConnectionUtil.getConnection().isValid(5));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
