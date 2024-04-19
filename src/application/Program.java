package application;

import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Connection conn = null;
        Statement  stmt = null;

        try {
            conn = DB.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql = "UPDATE seller SET BaseSalary = %.2f WHERE DepartmentId = %d";

            int rows1 = stmt.executeUpdate(sql.formatted(2090.0, 1));
            int x = 1;

            if(x < 2) {
                throw new SQLException("Fake Error");
            }

            int rows2 = stmt.executeUpdate(sql.formatted(3090.0, 2));
            conn.commit();

            System.out.println("Rows1 updated: " + rows1);
            System.out.println("Rows2 updated: " + rows2);

        }catch(SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e);
            } catch (SQLException ex) {
                throw new DbException("Error trying to rollback! Caused by: " + ex.getMessage());
            }
        }finally {
            DB.closeStatement(stmt);
            DB.closeConnection();
        }
    }
}
