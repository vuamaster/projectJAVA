package dao;

import connection.MyConnection;
import model.Account;
import model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountDAO {
    public Account getBuyID(long id){
        Account account = null;
        try {
            // Tao ket noi
            Connection conn = MyConnection.getConnection();
            // Chuan bi cau lenh, thuc thi
            String sql = "SELECT * FROM `accounts` WHERE `id` = " + id + " LIMIT 1";
            Statement stmt = conn.createStatement();

            // Ket qua
            ResultSet resultSet = stmt.executeQuery(sql);

            if (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUser(resultSet.getString("username"));
            }
            // Dong tai nguyen
            resultSet.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
    public Account getByUserNameAndPassword(String username, String password) {
        Account account = null;
        try {
            Connection conn = MyConnection.getConnection();
            String sql = String.format("SELECT id, username FROM accounts WHERE username='%s' AND password='%s' LIMIT 1 ",
                    username, password);

            // THUC THI
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            if (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUser(resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }
    public void insert(Account a){
        final  String sql =String.format("INSERT INTO `projectjava`.`accounts` (`username`, `password`) VALUES ('%s', '%s')",
               a.getUser(),a.getPassword()
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
    public Account getByUserName(String username) {
        Account account = null;
        try {
            Connection conn = MyConnection.getConnection();
            String sql = String.format("SELECT id, username FROM accounts WHERE username='%s' LIMIT 1 ",
                    username);

            // THUC THI
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            if (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUser(resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }
    public void delete(String username){
        Account account = getByUserName(username);
        if (account == null){
            System.out.println("tài khoản này không tồn tại!");
        }
        final String sql = "DELETE FROM `accounts` WHERE `username` = "+"'"+username+"'";
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
