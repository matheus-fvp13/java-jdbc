package application;

import db.DB;
import db.DbIntegrityException;

import java.sql.*;

public class Program {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DB.getConnection();
            stmt = conn.prepareStatement("DELETE FROM department "
                    + "WHERE "
                    + "Id = ?");
            stmt.setInt(1, 2);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Done! Rows affected: " + rowsAffected);
        }catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        }finally {
            DB.closeStatement(stmt);
            DB.closeConnection();
        }
    }
}
