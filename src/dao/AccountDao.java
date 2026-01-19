package dao;

import model.Account;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDao {

    public boolean createAccount(Account account) {

        String query = "INSERT INTO accounts (user_id, account_type, balance) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, account.getUserId());
            ps.setString(2, account.getAccountType());
            ps.setDouble(3, account.getBalance());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public double getBalance(int accountId) {
        String query = "SELECT balance FROM accounts WHERE account_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateBalance(Connection con, int accountId, double newBalance) throws Exception {
        String query = "UPDATE accounts SET balance=? WHERE account_id=?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDouble(1, newBalance);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        }
    }
}
