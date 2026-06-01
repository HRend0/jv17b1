package management;

import java.util.Scanner;

public class Main {
    private static final MovieManagement manager = new MovieManagement();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = -1;
        do {
            System.out.println("===== HỆ THỐNG QUẢN LÝ PHIM =====");
            System.out.println("1. Thêm phim");
            System.out.println("2. Liệt kê danh sách phim");
            System.out.println("3. Sửa thông tin phim");
            System.out.println("4. Xóa phim");
            System.out.println("0. Thoát");
            System.out.print("Mời bạn chọn chức năng (0-4): ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1 -> handleAddMovie();
                    case 2 -> manager.listMovies();
                    case 3 -> handleUpdateMovie();
                    case 4 -> handleDeleteMovie();
                    case 0 -> System.out.println("Tạm biệt!");
                    default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn từ 0 đến 4.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Vui lòng nhập vào một số nguyên!");
            }
            System.out.println();
        } while (choice != 0);
    }

    private static void handleAddMovie() {
        System.out.println("\n--- THÊM PHIM MỚI ---");
        String title = getNonEmptyInput("Nhập tiêu đề phim: ");
        String director = getNonEmptyInput("Nhập đạo diễn: ");
        int year = getIntInput("Nhập năm phát hành: ");

        manager.addMovie(title, director, year);
    }

    private static void handleUpdateMovie() {
        System.out.println("\n--- CẬP NHẬT THÔNG TIN PHIM ---");
        int id = getIntInput("Nhập ID phim cần sửa: ");
        String title = getNonEmptyInput("Nhập tiêu đề mới: ");
        String director = getNonEmptyInput("Nhập đạo diễn mới: ");
        int year = getIntInput("Nhập năm phát hành mới: ");

        manager.updateMovie(id, title, director, year);
    }

    private static void handleDeleteMovie() {
        System.out.println("\n--- XÓA PHIM ---");
        int id = getIntInput("Nhập ID phim cần xóa: ");
        manager.deleteMovie(id);
    }

    private static String getNonEmptyInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.err.println("Lỗi: Trường này không được để trống! Vui lòng nhập lại.");
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.err.println("Lỗi: Sai định dạng! Vui lòng nhập một số nguyên.");
            }
        }
    }
}