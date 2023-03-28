import dao.AccountDAO;
import dao.DepartmentDAO;
import dao.EmployeeDAO;
import model.Account;
import model.Department;
import model.Employee;
import service.AuthenService;

import java.util.List;
import java.util.Scanner;

public class App {
    private static boolean isLoginSuccess = false;
    private static AuthenService authenService = new AuthenService();
    private static EmployeeDAO employeeDAO =new EmployeeDAO();
    private static DepartmentDAO departmentDAO = new DepartmentDAO();
    private static AccountDAO accountDAO = new AccountDAO();
    private static void mainMenu(){
        System.out.println("------ QUẢN LÝ NHÂN SỰ --------");
        System.out.println("1. Quản lý nhân viên");
        System.out.println("2. Quản lý phòng ban");
        System.out.println("3. Quản lí tài khoản");
        System.out.println("4. Đăng xuất");
    }
    private static void mainMenuTaikhoan(){
        System.out.println("------ QUẢN LÝ TÀI KHOẢN --------");
        System.out.println("1. Thêm tài khoản mới");
        System.out.println("2. Xóa tài khoản");
        System.out.println("3. Quay lại");
    }
    private static void themTK(Scanner in){
        Account a =new Account();
        String userName = "";
        String passWord = "";
        List<Account> accountList = accountDAO.getAll();
        boolean check = false;
        while (true){
        System.out.print("\tNhập tên tài khoản: ");
        userName = in.nextLine();
        check = false;
            for (Account a1:accountList) {
                if (userName.equalsIgnoreCase(a1.getUser())){
                    System.out.println("tài khoản này đã tồn tại!");
                    check = true;
                    break;
                }
            }
            if (!check) break;
        }
        if (!check){
        System.out.print("\tNhập mật khẩu: ");
        passWord = in.nextLine();
        }
        a.setUser(userName);
        a.setPassword(passWord);
        accountDAO.insert(a);
    }
    private static void xoaTK(Scanner in){
        System.out.print("\tNhập tên tài khoản cần xóa: ");
        String username = in.nextLine();
        accountDAO.delete(username);
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
    private static void dsNV(){
        List<Employee> employeeList = employeeDAO.getAll();
        System.out.printf("%-10s %-15s %-20s %-15s %-10s %-15s %-15s %-10s %-15s %-10s %-10s", "Mã NV", "Tên NV", "Email", "Số điện thoại", "Địa chỉ", "Ngày thuê",
                            "ngày sinh", "giới tính", "lương", "chức vụ","phòng ban");
        System.out.println();
        for (int i = 0; i < employeeList.size(); i++) {
            Employee e = employeeList.get(i);
            System.out.printf("%-10d %-15s %-20s %-15s %-10s %-15s %-15s %-10d %-15d %-10s %-10d\n",
                    e.getId(),e.getFullName(),e.getEmail(),e.getPhone(),e.getAddress(),e.getHireDate(),e.getBirthDay(),e.getGender(),e.getSalary(),e.getPostion(),e.getDepartmentID());
        };
    }
    private static void tinhthue(Scanner in){
        System.out.print("Nhập ID nhân viên cần tính thuế : ");
        long id = 0;
        try {
            id = Long.parseLong(in.nextLine());
        } catch (Exception ex) {
            System.out.println("\tNhập sai định dạng!");
        }
        int luongHD= employeeDAO.getBuyID(id).getSalary();
        // tính lương hưởng thực tế
        double luongTT = luongHD - luongHD*0.08 - luongHD*0.015 - luongHD*0.01 - 11000000;
        // tinh thuế
        double tax = 0;
        if (luongTT <= 5000000 ){
            tax = luongTT * 0.05;
        }else if (luongTT <= 10000000){
            tax = (luongTT * 0.1) - 250000;
        }else if (luongTT <= 18000000){
            tax = (luongTT * 0.15) - 750000;
        }else if (luongTT <= 32000000){
            tax = (luongTT * 0.2) - 1650000;
        }else if (luongTT <= 52000000){
            tax = (luongTT * 0.25) - 3250000;
        }else if (luongTT <= 80000000){
            tax = (luongTT * 0.3) - 5850000;
        }else {
            tax = (luongTT * 0.35) - 9850000;
        }
        System.out.printf("\t\t%-10s %-15s %-15s %-15s \n", "Mã NV", "Tên NV", "Lương", "TTNCN");
        System.out.printf("\t\t%-10d %-15s %-15d %-15.2f \n", employeeDAO.getBuyID(id).getId(), employeeDAO.getBuyID(id).getFullName(),employeeDAO.getBuyID(id).getSalary(), tax);

    }
    private static void themNV(Scanner in){
        Employee e = new Employee();
        System.out.print("\tNhập tên NV: ");
        e.setFullName(in.nextLine());
        System.out.print("\tNhập email: ");
        e.setEmail(in.nextLine());
        System.out.print("\tNhập SĐT: ");
        e.setPhone(in.nextLine());
        System.out.print("\tNhập địa chỉ: ");
        e.setAddress(in.nextLine());
        System.out.print("\tNhập ngày thuê theo định dạng dd/mm/yyyy: ");
        e.setHireDate(in.nextLine());
        System.out.print("\tNhập ngày sinh theo định dạng dd/mm/yyyy: ");
        e.setBirthDay(in.nextLine());
        System.out.print("\tNhập giới tính (nam nhập 1, nữ nhập 0): ");
        e.setGender(Integer.parseInt(in.nextLine()));
        System.out.print("\tNhập lương : ");
        e.setSalary(Integer.parseInt(in.nextLine()));
        System.out.print("\tNhập chức vụ: ");
        e.setPostion(in.nextLine());
        System.out.print("\tPhòng ban: \n");
        List<Department> departmentList = departmentDAO.getAll();
        for (int i = 0; i < departmentList.size(); i++) {
            System.out.printf("\t\t%-5d %-20s \n", i+1, departmentList.get(i).getName());
        }
        // Tam thoi nhap chinh xac
        System.out.print("\tChọn phòng ban : ");
        long dept_id =  departmentList.get(Integer.parseInt(in.nextLine()) - 1).getId();
        e.setDepartmentID(dept_id);
        e.setStatus(1);
        employeeDAO.insert(e);
    }
    private static void xoaNv(Scanner in){
        dsNV();
        System.out.print("\tNhập ID nhân viên cần xóa: ");
        long id=0;
        try {
            id = Long.parseLong(in.nextLine());
        } catch (Exception ex) {
            System.out.println("Nhập sai định dạng!");
        }
        departmentDAO.updateManagernull(employeeDAO.getBuyID(id).getDepartmentID());
        employeeDAO.delete(id);
    }
    private static void suaTTcoBan(Scanner in){
        System.out.print("\tNhập ID nhân viên cần sửa : ");
        long id = Integer.parseInt(in.nextLine());
        Employee e = new Employee();
        System.out.print("\tNhập tên NV: ");
        e.setFullName(in.nextLine());
        System.out.print("\tNhập email: ");
        e.setEmail(in.nextLine());
        System.out.print("\tNhập SĐT: ");
        e.setPhone(in.nextLine());
        System.out.print("\tNhập địa chỉ: ");
        e.setAddress(in.nextLine());
        System.out.print("\tNhập ngày thuê theo định dạng dd/mm/yyyy: ");
        e.setHireDate(in.nextLine());
        System.out.print("\tNhập ngày sinh theo định dạng dd/mm/yyyy: ");
        e.setBirthDay(in.nextLine());
        System.out.print("\tNhập giới tính (nam nhập 1, nữ nhập 0): ");
        e.setGender(Integer.parseInt(in.nextLine()));
        System.out.print("\tNhập lương : ");
        e.setSalary(Integer.parseInt(in.nextLine()));
        System.out.print("\tNhập chức vụ: ");
        e.setPostion(in.nextLine());
        System.out.print("\tPhòng ban: \n");
        List<Department> departmentList = departmentDAO.getAll();
        for (int i = 0; i < departmentList.size(); i++) {
            System.out.printf("\t\t%-5d %-20s \n", i+1, departmentList.get(i).getName());
        }
        // Tam thoi nhap chinh xac
        System.out.print("\tChọn phòng ban : ");
        long dept_id =  departmentList.get(Integer.parseInt(in.nextLine()) - 1).getId();
        e.setDepartmentID(dept_id);
        System.out.print("\tNhập trạng thái (đã nghỉ việc nhập 0 ,đang làm nhập 1): ");
        e.setStatus(Integer.parseInt(in.nextLine()));
        employeeDAO.update(e,id);
    }
    private static void suaTTPBNV(Scanner in){
        System.out.print("\tNhập ID nhân viên cần sửa : ");
        long id = Integer.parseInt(in.nextLine());
        Employee e = new Employee();
        System.out.print("\tPhòng ban: \n\t\t0 xóa phòng ban\n");
        List<Department> departmentList = departmentDAO.getAll();
        for (int i = 0; i < departmentList.size(); i++) {
            System.out.printf("\t\t%-5d %-20s \n", i+1, departmentList.get(i).getName());
        }
        // Tam thoi nhap chinh xac
        System.out.print("\tChọn phòng ban : ");
        int tmp = Integer.parseInt(in.nextLine());
        if (tmp == 0){
            employeeDAO.updatePBNullID(id);
        }else {
            long dept_id = departmentList.get(tmp - 1).getId();
            e.setDepartmentID(dept_id);
            employeeDAO.updatePB(e,id);
        }
    }
    private static void timNVMenu(){
        System.out.println("------ TÌM KIẾM NHÂN VIÊN --------");
        System.out.println("\t\t1. Tìm theo ID");
        System.out.println("\t\t2. Tìm theo tên ");
        System.out.println("\t\t3. Tìm theo SĐT");
        System.out.println("\t\t4. Tìm theo email");
        System.out.println("\t\t5. Quay lại");
    }
    private static void timNVtheoID(Scanner in){
        System.out.print("\tNhập ID Nhân viên cần tìm: ");
        long id=0;
        try {
            id = Long.parseLong(in.nextLine());
        } catch (Exception ex) {
            System.out.println("Nhập sai định dạng!");
        }
        Employee e =employeeDAO.getBuyID(id);
        if (e == null){
            System.out.println("không tìm thấy nhân viên này");
        } else
            System.out.println(e);
    }
    private static void timNVtheoSdt(Scanner in){
        System.out.print("\tNhập SĐT của nhân viên cần tìm: ");
        String sdt = in.nextLine();
        Employee e = employeeDAO.getBuyPhone(sdt);
        if(e == null){
            System.out.println(" không tìm thấy nhân viên này ");
        }else
            System.out.println(e);
    }
    private static void timNVtheoEmail(Scanner in){
        System.out.print("\tNhập email của nhân viên cần tìm: ");
        String sdt = in.nextLine();
        Employee e = employeeDAO.getBuyEmail(sdt);
        if(e == null){
            System.out.println(" không tìm thấy nhân viên này ");
        }else
            System.out.println(e);
    }
    private static void timNVtheoTen(Scanner in){
        System.out.print("\tNhập tên nhân viên cần tìm: ");
        String name = in.nextLine();
        List<Employee> employeeList = employeeDAO.getBuyName(name);
        if (employeeList.size() == 0){
            System.out.println("không tìm thấy nhân viên này");
        }else {
            for (int i = 0; i < employeeList.size(); i++) {
                Employee e = employeeList.get(i);
                System.out.printf("%-10d %-15s %-20s %-15s %-10s %-15s %-15s %-10d %-15d %-10s %-10d\n",
                        e.getId(),e.getFullName(),e.getEmail(),e.getPhone(),e.getAddress(),e.getHireDate(),e.getBirthDay(),e.getGender(),e.getSalary(),e.getPostion(),e.getDepartmentID());
            }
        }
    }
    private static void mainMenuPhongban(){
        System.out.println("------ QUẢN LÝ PHÒNG BAN --------");
        System.out.println("\t1. Danh sách phòng ban theo bảng");
        System.out.println("\t2. Nhập một phòng ban mới ");
        System.out.println("\t3. Xóa một phòng ban");
        System.out.println("\t4. Sửa thông tin phòng ban");
        System.out.println("\t5. Tìm kiếm phòng ban");
        System.out.println("\t6. Danh sách nhân viên trong phòng ban");
        System.out.println("\t7. Quay lại");
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
    private static void dsNVtrongPB(Scanner in){
        System.out.print("nhập ID phòng ban cần xem :");
        long id=0;
        try {
            id = Long.parseLong(in.nextLine());
        } catch (Exception ex) {
            System.out.println("Nhập sai định dạng!");
        }
        List<Employee> employeeList = employeeDAO.getEmployeeinDepartment(id);
        System.out.printf("%-10s %-15s %-10s %-20s %-15s %-15s %-15s %-10s %-15s %-10s", "Mã NV", "Tên NV","giới tính", "Email", "Số điện thoại", "Ngày thuê",
                 "lương","mã phòng","tên phòng","mã quản lý");
        System.out.println();
        for (int i = 0; i < employeeList.size(); i++) {
            Employee e = employeeList.get(i);
            System.out.printf("%-10d %-15s %-10d %-20s %-15s %-15s %-15s %-10d %-15s %-10d \n",
                    e.getId(),e.getFullName(),e.getGender(),e.getEmail(),e.getPhone(),e.getHireDate(),e.getSalary(),e.getDepartmentID(),e.getDeptname(),e.getManagerID());
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
        departmentDAO.insert(d);
    }
    private static void suaTTPhongBan(Scanner in){
        System.out.print("nhập ID phòng ban cần sửa: ");
        long id = 0;
        try {
            id =  Long.parseLong(in.nextLine());
        }
        catch (Exception ex){
            System.out.println("nhập sai định dạng!");
        }
        Department d = new Department();
        System.out.print("nhập tên phòng ban: ");
        d.setName(in.nextLine());
        System.out.print("nhập địa chỉ: ");
        d.setAddress(in.nextLine());
        System.out.print("nhập email: ");
        d.setEmail(in.nextLine());
        System.out.print("nhập phone: ");
        d.setPhone(in.nextLine());
        departmentDAO.update(d,id);
    }
    private static void thayTruongPhong(Scanner in){
        System.out.print("nhập ID phòng ban cần sửa: ");
        long id = 0;
        try {
            id =  Long.parseLong(in.nextLine());
        }
        catch (Exception ex){
            System.out.println("nhập sai định dạng!");
        }
        Department d = new Department();
        long managerid = 0;
        System.out.print("nhập Id trưởng phòng: ");
        try {
            managerid =  Long.parseLong(in.nextLine());
        }
        catch (Exception ex){
            System.out.println("nhập sai định dạng!");
        }
        d.setManagerID(managerid);
        departmentDAO.updateManager(d,id);
    }
    private static void xoaPB(Scanner in){
        dsPhongBan();
        System.out.print("\tNhập ID phòng ban cần xóa: ");
        long id=0;
        try {
            id = Long.parseLong(in.nextLine());
        } catch (Exception ex) {
            System.out.println("Nhập sai định dạng!");
        }
        long managerID = departmentDAO.getBuyID(id).getManagerID();
        if (managerID == 0){
            employeeDAO.updatePBNull(id);
            departmentDAO.delete(id);
        } else {
            employeeDAO.updatePBNullID(departmentDAO.getBuyID(id).getManagerID());
            departmentDAO.delete(id);
        }
    }
    private static void timTheoID(Scanner in){
        System.out.print("\tNhập ID phòng ban cần tìm: ");
        long id=0;
        try {
            id = Long.parseLong(in.nextLine());
        } catch (Exception ex) {
            System.out.println("Nhập sai định dạng!");
        }
        Department d =departmentDAO.getBuyID(id);
        if (d == null){
            System.out.println("không tìm thấy phòng ban này");
        } else
            System.out.println(d);
    }
    private static void timTheoTen(Scanner in){
        System.out.print("\tNhập tên phòng ban cần tìm: ");
        String name = in.nextLine();
        List<Department> departmentList = departmentDAO.getBuyName(name);
        if (departmentList.size() == 0){
            System.out.println("không tìm thấy phòng ban này");
        }else {
            for (int i = 0; i < departmentList.size(); i++) {
                Department d2 = departmentList.get(i);
                System.out.printf("%-20d %-20s %-20s %-20s %-20s %-20d\n", d2.getId(), d2.getName(), d2.getEmail(), d2.getPhone(), d2.getAddress(), d2.getManagerID());
            }
        }
    }
    private static void timTheoSDT(Scanner in){
        System.out.print("\tNhập SĐT phòng ban cần tìm: ");
        String sdt = in.nextLine();
        Department d =departmentDAO.getBuyPhone(sdt);
        if(d == null){
            System.out.println(" không tìm thấy phòng ban này ");
        }else
            System.out.println(d);
    }
    private static void suaPBMenu(){
        System.out.println("------ SỬA THÔNG TIN PHÒNG BAN --------");
        System.out.println("\t\t1. Thay thông tin cơ bản");
        System.out.println("\t\t2. Thay đổi trưởng phòng ");
        System.out.println("\t\t3. Quay lại");
    }
    private static void timPBMenu(){
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
            System.out.print("Nhập username: ");
            String username = in.nextLine();
            System.out.print("Nhập password: ");
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
                    if (option < 1 || option > 4) {
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
                                        // danh sách nhân viên theo bảng
                                        dsNV();
                                        break;
                                    case 2:
                                        // nhập nhân viên mới
                                        themNV(in);
                                        break;
                                    case 3:
                                        xoaNv(in);
                                        // xóa một nhân viên
                                        break;
                                    case 4:
                                        // sửa nv
                                        suaTTcoBan(in);
                                        break;
                                    case 5:
                                        // tìm nv
                                        int option4 = -1;
                                        do {
                                            timNVMenu();
                                            System.out.print("Nhập lựa chọn: ");
                                            try {
                                                option4 = Integer.parseInt(in.nextLine());
                                            } catch (Exception ex) {
                                                System.out.println("Nhập sai định dạng!");
                                                continue;
                                            }
                                            if (option4 < 1 || option4 > 5) {
                                                System.out.println("Vui lòng nhập lại : ");
                                                continue;
                                            }
                                            switch (option4) {
                                                case 1:
                                                    // tìm theo ID
                                                    timNVtheoID(in);
                                                    break;
                                                case 2:
                                                    // tìm theo tên
                                                    timNVtheoTen(in);
                                                    break;
                                                case 3:
                                                    // tìm theo SĐT
                                                    timNVtheoSdt(in);
                                                    break;
                                                case 4:
                                                    //tìm theo email
                                                    timNVtheoEmail(in);
                                                    break;
                                            }
                                        }
                                        while (option4 != 5);
                                        break;
                                    case 6:
                                        // cập nhật thông tin phòng ban nhân viên
                                        suaTTPBNV(in);
                                        break;
                                    case 7:
                                        // tính thuế thu nhập cá nhân
                                        tinhthue(in);
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
                                if (option2 < 1 || option2 > 7) {
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
                                        xoaPB(in);
                                        break;
                                    case 4:
                                        // sửa phòng ban
                                        int option5 = -1;
                                        do {
                                            suaPBMenu();
                                            System.out.print("Nhập lựa chọn: ");
                                            try {
                                                option5 = Integer.parseInt(in.nextLine());
                                            } catch (Exception ex) {
                                                System.out.println("Nhập sai định dạng!");
                                                continue;
                                            }
                                            if (option5 < 1 || option5 > 3) {
                                                System.out.println("Vui lòng nhập lại : ");
                                                continue;
                                            }
                                            switch (option5) {
                                                case 1:
                                                    // sửa thông tin cơ bản
                                                    suaTTPhongBan(in);
                                                    break;
                                                case 2:
                                                    // thay trưởng phòng
                                                    thayTruongPhong(in);
                                                    break;
                                            }
                                        }
                                        while (option5 != 3);
                                        break;
                                    case 5:
                                        // Tìm kiếm phòng ban
                                        int option6 = -1;
                                        do {
                                            timPBMenu();
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
                                                    timTheoID(in);
                                                    break;
                                                case 2:
                                                    timTheoTen(in);
                                                    break;
                                                case 3:
                                                    timTheoSDT(in);
                                                    break;
                                            }
                                        }
                                        while (option6 != 4);
                                        break;
                                    case 6:
                                        dsNVtrongPB(in);
                                        break;
                                }
                            }
                            while (option2 != 7);
                            break;
                        case 3:
                            int optiontk = -1;
                            do {
                                mainMenuTaikhoan();
                                System.out.print("Nhập lựa chọn: ");
                                try {
                                    optiontk = Integer.parseInt(in.nextLine());
                                } catch (Exception ex) {
                                    System.out.println("Nhập sai định dạng!");
                                    continue;
                                }
                                if (optiontk < 1 || optiontk > 3) {
                                    System.out.print("Vui lòng nhập lại : ");
                                    continue;
                                }
                                switch (optiontk) {
                                    case 1:
                                        themTK(in);
                                        break;
                                    case 2:
                                        xoaTK(in);
                                        break;
                                }
                            }
                            while (optiontk != 3);
                            break;

                    }
                }
                while (option != 4);
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
