package application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {
        Connection conn = null;
        Statement  stmt = null;
        ResultSet  rs   = null;

        try {
            conn = DB.getConnection();
            stmt = conn.createStatement();
            rs   = stmt.executeQuery("SELECT * FROM department");
            while (rs.next()) {
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStatement(stmt);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }
    }
}
