package com.janosgyerik.stackoverflow.crm;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementTest {
    public void test(DataSource dataSource) throws SQLException {
        String sex = "sex";
        String age = "age";

        try (Connection con = dataSource.getConnection()) {
            String updateSexString = "UPDATE sex SET count=count+1 WHERE name=?";
            try (PreparedStatement st = con.prepareStatement(updateSexString)) {
                st.setString(1, sex);
                st.executeUpdate();
            }

            String updateAgeString = "UPDATE age SET count=count+1 WHERE id=?";
            try (PreparedStatement st = con.prepareStatement(updateAgeString)) {
                st.setString(1, age);
                st.executeUpdate();
            }
        }
    }
}
