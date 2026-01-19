package ui;

import dao.TransactionDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class TransactionHistoryFrame extends JFrame {

    public TransactionHistoryFrame(int loggedInUserId) {

        setTitle("Transaction History");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] columns = {
                "Txn ID", "From Account", "To Account", "Amount", "Date"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        int accountId = Integer.parseInt(
                JOptionPane.showInputDialog(this, "Enter Account ID")
        );

        TransactionDao dao = new TransactionDao();
        Vector<Vector<Object>> data = dao.getTransactionsByAccount(accountId);

        for (Vector<Object> row : data) {
            model.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        setVisible(true);
    }
}
