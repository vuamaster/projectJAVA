package dao;

import connection.MyConnection;
import model.Account;

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
}
