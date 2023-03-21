import dao.DepartmentDAO;
import dao.EmployeeDAO;
import model.Department;
import service.AuthenService;

import java.util.List;
import java.util.Scanner;

public class App {
    private static boolean isLoginSuccess = false;
    private static AuthenService authenService = new AuthenService();
    private static EmployeeDAO employeeDAO =new EmployeeDAO();
    private static DepartmentDAO departmentDAO = new DepartmentDAO();
    private static void mainMenu(){
        System.out.println("------ QUẢN LÝ NHÂN SỰ --------");
        System.out.println("1. Quản lý nhân viên");
        System.out.println("2. Quản lý phòng ban");
        System.out.println("3. Đăng xuất");
    }
    private static void mainMenuNhanvien(){
        System.out.println("------ QUẢN LÝ NHÂN VIÊN --------");
        System.out.println("\t1. Danh sách nhân viên theo bảng");
        System.out.println("\t2. Nhập một nhân viên mới ");
        System.out.println("\t3. Xóa một nhân viên");
        System.out.println("\t4. Sửa thông tin nhân viên");
        System.out.println("\t5. Tìm kiếm một nhân viên");
        System.out.println("\t6. Cập nhật phòng ban cho nhân viên nhân viên");
        System.out.println("\t7. Tính thuế thu nhập cá nhân cho nhân viên");
        System.out.println("\t8. Quay lại");
    }
    private static void suaNV(){
        System.out.println("------ SỬA THÔNG TIN NHÂN VIÊN --------");
        System.out.println("\t\t1. Thay đổi tên");
        System.out.println("\t\t2. Thay đổi email ");
        System.out.println("\t\t3. Thay đổi SĐT");
        System.out.println("\t\t4. Thay đổi địa chỉ");
        System.out.println("\t\t5. Thay đổi ngày sinh");
        System.out.println("\t\t6. Thay đổi giới tính");
        System.out.println("\t\t7. Thay đổi chức vụ");
        System.out.println("\t\t8. Thay đổi trạng thái");
        System.out.println("\t\t9. Quay lại");
    }
    private static void timNV(){
        System.out.println("------ TÌM KIẾM NHÂN VIÊN --------");
        System.out.println("\t\t1. Tìm theo ID");
        System.out.println("\t\t2. Tìm theo tên ");
        System.out.println("\t\t3. Tìm theo SĐT");
        System.out.println("\t\t4. Quay lại");
    }
    private static void mainMenuPhongban(){
        System.out.println("------ QUẢN LÝ PHÒNG BAN --------");
        System.out.println("\t1. Danh sách phòng ban theo bảng");
        System.out.println("\t2. Nhập một phòng ban mới ");
        System.out.println("\t3. Xóa một phòng ban");
        System.out.println("\t4. Sửa thông tin phòng ban");
        System.out.println("\t5. Tìm kiếm phòng ban");
        System.out.println("\t6. Quay lại");
    }
    private static void dsPhongBan(){
        List<Department> departmentList = departmentDAO.getAll();
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s", "Mã Phòng ban", "Tên phòng ban", "Email", "Số điện thoại", "Địa chỉ", "Mã quản lý");
        System.out.println();
        for (int i = 0; i < departmentList.size(); i++) {
            Department d = departmentList.get(i);
            System.out.printf("%-20d %-20s %-20s %-20s %-20s %-20d\n", d.getId(), d.getName(), d.getEmail(), d.getPhone(), d.getAddress(),d.getManagerID());
        };
    }
    private static  void themPhongBan(Scanner in){
        Department d = new Department();
        System.out.print("nhập tên phòng ban: ");
        d.setName(in.nextLine());
        System.out.print("nhập địa chỉ: ");
        d.setAddress(in.nextLine());
        System.out.print("nhập email: ");
        d.setEmail(in.nextLine());
        System.out.print("nhập phone: ");
        d.setPhone(in.nextLine());
        d.setManagerID(null);
        departmentDAO.insert(d);
    }
    private static void suaPB(){
        System.out.println("------ SỬA THÔNG TIN PHÒNG BAN --------");
        System.out.println("\t\t1. Thay đổi tên");
        System.out.println("\t\t2. Thay đổi email ");
        System.out.println("\t\t3. Thay đổi SĐT");
        System.out.println("\t\t4. Thay đổi địa chỉ");
        System.out.println("\t\t5. Thay đổi Trưởng phòng");
        System.out.println("\t\t6. Quay lại");
    }
    private static void timPB(){
        System.out.println("------ TÌM KIẾM PHÒNG BAN --------");
        System.out.println("\t\t1. Tìm theo ID");
        System.out.println("\t\t2. Tìm theo tên ");
        System.out.println("\t\t3. Tìm theo SĐT");
        System.out.println("\t\t4. Quay lại");
    }
    public static void main(String[] args) {
        int loginAttempt = 0; // số lần đăng nhập không thành công
        Scanner in = new Scanner(System.in);

        while (loginAttempt < 3) {
            System.out.print("Nhap username: ");
            String username = in.nextLine();
            System.out.print("Nhap password: ");
            String password = in.nextLine();
            if (authenService.login(username, password)) {
                int option = -1;
                do {
                    mainMenu();
                    System.out.print("Nhập lựa chọn: ");
                    try {
                        option = Integer.parseInt(in.nextLine());
                    } catch (Exception ex) {
                        System.out.println("Nhập sai định dạng!");
                        continue;
                    }
                    if (option < 1 || option > 3) {
                        System.out.print("Vui lòng nhập lại : ");
                        continue;
                    }
                    switch (option) {
                        // vao menu lua chon chuc nang quan li nhan vien
                        case 1:
                            int option1 = -1;
                            do {
                                mainMenuNhanvien();
                                System.out.print("Nhập lựa chọn: ");
                                try {
                                    option1 = Integer.parseInt(in.nextLine());
                                } catch (Exception ex) {
                                    System.out.println("Nhập sai định dạng!");
                                    continue;
                                }
                                if (option1 < 1 || option1 > 8) {
                                    System.out.println("Vui lòng nhập lại : ");
                                    continue;
                                }
                                switch (option1) {
                                    case 1:
                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                        break;
                                    case 4:
                                        int option3 = -1;
                                        do {
                                            suaNV();
                                            System.out.print("Nhập lựa chọn: ");
                                            try {
                                                option3 = Integer.parseInt(in.nextLine());
                                            } catch (Exception ex) {
                                                System.out.println("Nhập sai định dạng!");
                                                continue;
                                            }
                                            if (option3 < 1 || option3 > 8) {
                                                System.out.println("Vui lòng nhập lại : ");
                                                continue;
                                            }
                                            switch (option3) {
                                                case 1:
                                                    break;
                                                case 2:
                                                    break;
                                                case 3:
                                                    break;
                                                case 4:
                                                    break;
                                                case 5:
                                                    break;
                                                case 6:
                                                    break;
                                                case 7:
                                                    break;
                                                case 8:
                                                    break;
                                            }
                                        }
                                        while (option3 != 9);
                                        break;
                                    case 5:
                                        int option4 = -1;
                                        do {
                                            timNV();
                                            System.out.print("Nhập lựa chọn: ");
                                            try {
                                                option4 = Integer.parseInt(in.nextLine());
                                            } catch (Exception ex) {
                                                System.out.println("Nhập sai định dạng!");
                                                continue;
                                            }
                                            if (option4 < 1 || option4 > 4) {
                                                System.out.println("Vui lòng nhập lại : ");
                                                continue;
                                            }
                                            switch (option4) {
                                                case 1:
                                                    break;
                                                case 2:
                                                    break;
                                                case 3:
                                                    break;
                                            }
                                        }
                                        while (option4 != 4);
                                        break;
                                    case 6:
                                        break;
                                    case 7:
                                        break;
                                }
                            }
                            while (option1 != 8);
                            break;
                        // menu lua chon chuc nang quan li phong ban
                        case 2:
                            int option2 = -1;
                            do {
                                mainMenuPhongban();
                                System.out.print("Nhập lựa chọn: ");
                                try {
                                    option2 = Integer.parseInt(in.nextLine());
                                } catch (Exception ex) {
                                    System.out.println("Nhập sai định dạng!");
                                    continue;
                                }
                                if (option2 < 1 || option2 > 6) {
                                    System.out.print("Vui lòng nhập lại : ");
                                    continue;
                                }
                                switch (option2) {
                                    case 1:
                                        // danh sach phong ban theo bang
                                        dsPhongBan();
                                        break;
                                    case 2:
                                        // them phong ban moi
                                        themPhongBan(in);
                                        break;
                                    case 3:
                                        //xoa phong ban
                                        dsPhongBan();
                                        System.out.print("\tNhập ID phòng ban cần xóa: ");
                                        long id = Long.parseLong(in.nextLine());
                                        departmentDAO.delete(id);
                                        break;
                                    case 4:
                                        int option5 = -1;
                                        do {
                                            suaPB();
                                            System.out.print("Nhập lựa chọn: ");
                                            try {
                                                option5 = Integer.parseInt(in.nextLine());
                                            } catch (Exception ex) {
                                                System.out.println("Nhập sai định dạng!");
                                                continue;
                                            }
                                            if (option5 < 1 || option5 > 6) {
                                                System.out.println("Vui lòng nhập lại : ");
                                                continue;
                                            }
                                            switch (option5) {
                                                case 1:
                                                    break;
                                                case 2:
                                                    break;
                                                case 3:
                                                    break;
                                                case 4:
                                                    break;
                                                case 5:
                                                    break;
                                            }
                                        }
                                        while (option5 != 6);
                                        break;
                                    case 5:
                                        int option6 = -1;
                                        do {
                                            timPB();
                                            System.out.print("Nhập lựa chọn: ");
                                            try {
                                                option6 = Integer.parseInt(in.nextLine());
                                            } catch (Exception ex) {
                                                System.out.println("Nhập sai định dạng!");
                                                continue;
                                            }
                                            if (option6 < 1 || option6 > 4) {
                                                System.out.println("Vui lòng nhập lại : ");
                                                continue;
                                            }
                                            switch (option6) {
                                                case 1:
                                                    break;
                                                case 2:
                                                    break;
                                                case 3:
                                                    break;
                                            }
                                        }
                                        while (option6 != 4);
                                        break;
                                }
                            }
                            while (option2 != 6);
                            break;
                        case 3:
                            break;
                    }
                }
                while (option != 3);
            } else {
                // Đăng nhập không thành công
                System.out.println("Sai tài khoản hoặc mật khẩu!");
                loginAttempt++;
            }
        }
        if (loginAttempt >= 3) {
            System.out.println("Bạn đã nhập sai quá 3 lần. Chương trình sẽ thoát.");
        }
        in.close();
    }
}
