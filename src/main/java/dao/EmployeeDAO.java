package dao;

import connection.MyConnection;
import model.Department;
import model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public List<Employee> getAll(){
        final String sql = "SELECT * FROM employees";
        List<Employee> employeeList = new ArrayList<>();
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Employee e = new Employee();
                e.setId(rs.getString("employee_id"));
                e.setFullName(rs.getString("fullname"));
                e.setEmail(rs.getString("email"));
                e.setPhone(rs.getString("phone"));
                e.setAddress(rs.getString("address"));
                e.setHireDate(rs.getString("hire_date"));
                e.setBirthDay(rs.getString("birth_day"));
                e.setGender(rs.getInt("gender"));
                e.setSalary(rs.getInt("salary"));
                e.setPostion(rs.getString("postion"));
                e.setDepartmentID(rs.getString("department_id"));
                e.setStatus(rs.getInt("status"));
                employeeList.add(e);
            }
            conn.close();
            stmt.close();
            rs.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return employeeList;
    }
    public Employee getBuyID(String id){
        final String sql ="SELECT * FROM `employees` where `employee_id` ="+"'"+id+"'";
        Employee e = null;
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                e = new Employee();
                e.setId(rs.getString("employee_id"));
                e.setFullName(rs.getString("fullname"));
                e.setEmail(rs.getString("email"));
                e.setPhone(rs.getString("phone"));
                e.setAddress(rs.getString("address"));
                e.setHireDate(rs.getString("hire_date"));
                e.setBirthDay(rs.getString("birth_day"));
                e.setGender(rs.getInt("gender"));
                e.setSalary(rs.getInt("salary"));
                e.setPostion(rs.getString("postion"));
                e.setDepartmentID(rs.getString("department_id"));
                e.setStatus(rs.getInt("status"));
            }
            rs.close();
            conn.close();
            stmt.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return e;
    }
    public void insert(Employee e){
        final  String sql =String.format("INSERT INTO `projectjava`.`employees` (`employee_id`, `fulltname`, `email`, `phone`, `address`, `hire_date`, `birth_day`, `gender`, `salary`, `postion`, `department_id`, `satus`) VALUES ('1', '%s', '%s', '%s', '%s', '%s', '%s', '1', '1', '%s', '%s', '1')",
                e.getId(),e.getFullName(),e.getEmail(),e.getPhone(),e.getAddress(),e.getHireDate(),e.getBirthDay(),e.getGender(),e.getSalary(),e.getPostion(),e.getDepartmentID(),e.getStatus()
        );
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);
            if (rs == 0){
                System.out.println("thêm thất bại");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void update(Employee e, String id) {
        Employee tmp = getBuyID(id);
        if (tmp == null) {
            throw new RuntimeException("phòng ban không tồn tại!");
        }
        final String sql = String.format("UPDATE `projectjava`.`employees` SET `fulltname` = '%s', `email` = '%s', `phone` = '%s', `address` = '%s', `hire_date` = '%s', `birth_day` = '%s', `gender` = '%d', `salary` = '%d', `postion` = '%s', `department_id` = '%s', `status` = '%d' WHERE (`employee_id` = '%s')",
                e.getFullName(),e.getEmail(),e.getPhone(),e.getAddress(),e.getHireDate(),e.getBirthDay(),e.getGender(),e.getSalary(),e.getPostion(),e.getDepartmentID(),e.getStatus(), id
        );
        System.out.println(sql);
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);
            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void delete(String id){
        Employee e = getBuyID(id);
        if (e == null){
            throw new RuntimeException("phòng ban không tồn tại!");
        }
        final String sql = "DELETE FROM `employees` WHERE `id` = "+"'"+id+"'";
        System.out.println(sql);
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);
            if (rs == 0){
                System.out.println("xóa thất bại!");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
