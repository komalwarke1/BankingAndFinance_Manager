package dao;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class TransactionDao {

    public Vector<Vector<Object>> getTransactionsByAccount(int accountId) {

        Vector<Vector<Object>> data = new Vector<>();
        String query =
                "SELECT txn_id, from_acc, to_acc, amount, txn_date " +
                        "FROM transactions " +
                        "WHERE from_acc = ? OR to_acc = ? " +
                        "ORDER BY txn_date DESC";


        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, accountId);
            ps.setInt(2, accountId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("txn_id"));
                row.add(rs.getInt("from_acc"));
                row.add(rs.getInt("to_acc"));
                row.add(rs.getDouble("amount"));
                row.add(rs.getTimestamp("txn_date"));
                data.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
