package management;

import java.sql.*;

public class MovieManagement {

    public void addMovie(String title, String director, int year) {
        String sql = "{call add_movie(?, ?, ?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, title);
            cstmt.setString(2, director);
            cstmt.setInt(3, year);
            cstmt.execute();
            System.out.println("🎉 Thêm phim thành công!");

        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }

    public void listMovies() {
        String sql = "{call list_movies()}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {

            System.out.println("\n========= DANH SÁCH PHIM =========");
            System.out.printf("%-5s %-30s %-20s %-10s\n", "ID", "Tiêu đề", "Đạo diễn", "Năm SX");
            System.out.println("----------------------------------------------------------------");

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.printf("%-5d %-30s %-20s %-10d\n",
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("director"),
                        rs.getInt("release_year"));
            }
            if (!hasData) {
                System.out.println("Không có phim nào trong danh sách.");
            }
            System.out.println("===================================\n");

        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }

    public void updateMovie(int id, String title, String director, int year) {
        String sql = "{call update_movie(?, ?, ?, ?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id);
            cstmt.setString(2, title);
            cstmt.setString(3, director);
            cstmt.setInt(4, year);

            int rowsAffected = cstmt.executeUpdate();
            System.out.println("Cập nhật thông tin phim thành công!");

        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }

    public void deleteMovie(int id) {
        String sql = "{call delete_movie(?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, id);
            cstmt.execute();
            System.out.println("Xóa phim thành công!");

        } catch (SQLException e) {
            System.err.println("Lỗi cơ sở dữ liệu: " + e.getMessage());
        }
    }
}