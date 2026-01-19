package service;

import dao.AccountDao;
import dao.AccountDao;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionService {

    public boolean transferMoney(int fromAcc, int toAcc, double amount) {

        AccountDao accountDAO = new AccountDao();

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false); // 🔒 Start transaction

            double fromBalance = accountDAO.getBalance(fromAcc);

            if (fromBalance < amount) {
                return false; // insufficient balance
            }

            // Update balances
            accountDAO.updateBalance(con, fromAcc, fromBalance - amount);

            double toBalance = accountDAO.getBalance(toAcc);
            accountDAO.updateBalance(con, toAcc, toBalance + amount);

            // Insert transaction record
            String txnQuery =
                    "INSERT INTO transactions(from_acc, to_acc, amount) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(txnQuery)) {
                ps.setInt(1, fromAcc);
                ps.setInt(2, toAcc);
                ps.setDouble(3, amount);
                ps.executeUpdate();
            }

            con.commit(); // ✅ success
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
