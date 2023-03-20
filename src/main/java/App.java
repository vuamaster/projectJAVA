import dao.DepartmentDAO;
import dao.EmployeeDAO;

import java.util.Scanner;

public class App {
    private static EmployeeDAO employeeDAO =new EmployeeDAO();
    private static DepartmentDAO departmentDAO = new DepartmentDAO();
    private static void mainMenu(){
        System.out.println("------ QUẢN LÝ NHÂN SỰ --------");
        System.out.println("1. Quản lý nhân viên");
        System.out.println("2. Quản lý phòng ban");
        System.out.println("3. thoát");
    }
    private static void mainMenu1(){
        System.out.println("------ QUẢN LÝ NHÂN SỰ --------");
        System.out.println("1. Danh sách nhân viên theo bảng");
        System.out.println("2. Nhập một nhân viên mới ");
        System.out.println("3. Xóa một nhân viên");
        System.out.println("4. Sửa thông tin nhân viên");
        System.out.println("5. Tìm kiếm một nhân viên");
        System.out.println("6. Phòng ban nhân viên");
        System.out.println("7. Tính thuế thu nhập cá nhân cho nhân viên");
        System.out.println("8. thoát");
    }
    private static void mainMenu2(){
        System.out.println("------ QUẢN LÝ NHÂN SỰ --------");
        System.out.println("1. Danh sách phòng ban theo bảng");
        System.out.println("2. Nhập một phòng ban mới ");
        System.out.println("3. Xóa một phòng ban");
        System.out.println("4. Sửa thông tin phòng ban");
        System.out.println("5. Tìm kiếm phòng ban");
        System.out.println("6. thoát");
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int option = -1;
        do {
            mainMenu();
            System.out.print("Nhập lựa chọn: ");
            try {
                option = Integer.parseInt(in.nextLine());
            }
            catch (Exception ex){
                System.out.println("Nhập sai định dạng!");
                continue;
            }
            if(option <1 || option>3){
                System.out.print("Vui lòng nhập lại : ");
                continue;
            }
            switch (option){
                case 1:
                    int option1 = -1;
                    do {
                        mainMenu1();
                        System.out.print("Nhập lựa chọn: ");
                        try {
                            option = Integer.parseInt(in.nextLine());
                        } catch (Exception ex) {
                            System.out.println("Nhập sai định dạng!");
                            continue;
                        }
                        if (option < 1 || option > 8) {
                            System.out.println("Vui lòng nhập lại : ");
                            continue;
                        }
                        switch (option1){
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
                        break;
                    }
                    while (option1 != 8);
                    break;
                case 2:
                    int option2 = -1;
                    do {
                        mainMenu2();
                        System.out.print("Nhập lựa chọn: ");
                        try {
                            option = Integer.parseInt(in.nextLine());
                        } catch (Exception ex) {
                            System.out.println("Nhập sai định dạng!");
                            continue;
                        }
                        if (option < 1 || option > 6) {
                            System.out.print("Vui lòng nhập lại : ");
                            continue;
                        }
                        switch (option2){
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
                        }
                        break;
                    }
                    while (option2 != 6);
                    break;
                case 3:
                    break;
            }
        }
        while (option != 3);
    }
}
