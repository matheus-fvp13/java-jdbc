package application;

import db.DB;

import java.sql.*;

public class Program {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DB.getConnection();
            stmt = conn.prepareStatement(
                    "UPDATE seller "
                            + "SET BaseSalary = BaseSalary + ? "
                            + "WHERE "
                            + "(DepartmentId = ?)");
            stmt.setDouble(1, 200.0);
            stmt.setInt(2, 2);

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Done! Rows affected: " + rowsAffected);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStatement(stmt);
            DB.closeConnection();
        }
    }
}
