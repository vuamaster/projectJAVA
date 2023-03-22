package dao;

import connection.MyConnection;
import model.Department;
import model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    public List<Department> getAll(){
        final String sql = "SELECT * FROM departments";
        List<Department> departmentList = new ArrayList<>();
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Department d = new Department();
                d.setId((rs.getLong("dept_id")));
                d.setName(rs.getString("dept_name"));
                d.setEmail(rs.getString("email"));
                d.setPhone((rs.getString("phone")));
                d.setAddress(rs.getString("address"));
                d.setManagerID(rs.getLong("manager_id"));
                departmentList.add(d);
            }
            conn.close();
            stmt.close();
            rs.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return departmentList;
    }
    public Department getBuyID(long id){
        final String sql ="SELECT * FROM departments where `dept_id` ="+id;
        Department d = null;
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                d = new Department();
                d.setId((rs.getLong("dept_id")));
                d.setName(rs.getString("dept_name"));
                d.setEmail(rs.getString("email"));
                d.setPhone((rs.getString("phone")));
                d.setAddress(rs.getString("address"));
                d.setManagerID(rs.getLong("manager_id"));
            }
            rs.close();
            conn.close();
            stmt.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return d;
    }
    public Department getBuyPhone(String phone){
        final String sql ="SELECT * FROM departments where `phone` ="+"'"+phone+"'";
        Department d = null;
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                d = new Department();
                d.setId((rs.getLong("dept_id")));
                d.setName(rs.getString("dept_name"));
                d.setEmail(rs.getString("email"));
                d.setPhone((rs.getString("phone")));
                d.setAddress(rs.getString("address"));
                d.setManagerID(rs.getLong("manager_id"));
            }
            rs.close();
            conn.close();
            stmt.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return d;
    }
    public List<Department> getBuyName(String name){
        final String sql ="SELECT * FROM departments where `dept_name` like "+"'%"+name+"%'";
        List<Department> departmentList = new ArrayList<>();
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Department d = new Department();
                d.setId((rs.getLong("dept_id")));
                d.setName(rs.getString("dept_name"));
                d.setEmail(rs.getString("email"));
                d.setPhone((rs.getString("phone")));
                d.setAddress(rs.getString("address"));
                d.setManagerID(rs.getLong("manager_id"));
                departmentList.add(d);
            }
            rs.close();
            conn.close();
            stmt.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return departmentList;
    }
    public void insert(Department d){
        final  String sql =String.format("INSERT INTO `departments` (`dept_name`, `email`, `phone`, `address`, `manager_id`) VALUES ('%s', '%s', '%s', '%s', '%d')",
                d.getName(), d.getEmail(),d.getPhone(), d.getAddress(), d.getManagerID()
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
        catch (Exception e){
            e.printStackTrace();
        }

        //UPDATE `projectjava`.`departments` SET `dept_name` = 'IT', `email` = 'g@gmail.com', `phone` = '111222', `address` = 'HN' WHERE (`dept_id` = '2');
    }
    public void update(Department d, long id) {
        Department tmp = getBuyID(id);
        if (tmp == null) {
            throw new RuntimeException("phòng ban không tồn tại!");
        }
        final String sql = String.format("UPDATE `projectjava`.`departments` SET `dept_name` = '%s', `email` = '%s', `phone` = '%s', `address` = '%s' WHERE (`dept_id` = '%d')",
                d.getName(), d.getEmail(), d.getPhone(), d.getAddress(), id
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateManager(Department d, long id){
        Department tmp = getBuyID(id);
        if (tmp == null) {
            throw new RuntimeException("phòng ban không tồn tại!");
        }
        final String sql = String.format("UPDATE `projectjava`.`departments` SET `manager_id` = '%d' WHERE (`dept_id` = '%d')",
                d.getManagerID(), id
        );
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);
            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(long id){
        Department d = getBuyID(id);
        if (d == null){
            throw new RuntimeException("phòng ban không tồn tại!");
        }
        final String sql = "DELETE FROM `departments` WHERE `dept_id` = "+id;
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
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
