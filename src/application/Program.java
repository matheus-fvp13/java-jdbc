package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
        SimpleDateFormat sdf   = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn        = null;
        PreparedStatement stmt = null;
        try {
            conn = DB.getConnection();
            stmt = conn.prepareStatement(
                    "INSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)");
            stmt.setString(1, "Luiz Gustavo");
            stmt.setString(2, "lg@gmail.com");
            stmt.setDate(3, new java.sql.Date(sdf.parse("13/08/2002").getTime()));
            stmt.setDouble(4, 3000.0);
            stmt.setInt(5, 4);

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Done! Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            DB.closeStatement(stmt);
            DB.closeConnection();
        }
    }
}
