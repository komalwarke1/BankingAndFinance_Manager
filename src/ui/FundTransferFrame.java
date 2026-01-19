package ui;

import service.TransactionService;

import javax.swing.*;
import java.awt.*;

public class FundTransferFrame extends JFrame {

    private JTextField fromAccField, toAccField, amountField;

    public FundTransferFrame() {

        setTitle("Fund Transfer");
        setSize(420, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("Transfer Money");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(140, 20, 200, 30);

        JLabel fromLabel = new JLabel("From Account ID:");
        fromLabel.setBounds(60, 80, 130, 25);

        fromAccField = new JTextField();
        fromAccField.setBounds(200, 80, 140, 25);

        JLabel toLabel = new JLabel("To Account ID:");
        toLabel.setBounds(60, 120, 130, 25);

        toAccField = new JTextField();
        toAccField.setBounds(200, 120, 140, 25);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(60, 160, 130, 25);

        amountField = new JTextField();
        amountField.setBounds(200, 160, 140, 25);

        JButton transferBtn = new JButton("Transfer");
        transferBtn.setBounds(160, 210, 100, 30);

        transferBtn.addActionListener(e -> transfer());

        panel.add(title);
        panel.add(fromLabel);
        panel.add(fromAccField);
        panel.add(toLabel);
        panel.add(toAccField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(transferBtn);

        add(panel);
        setVisible(true);
    }

    private void transfer() {
        try {
            int fromAcc = Integer.parseInt(fromAccField.getText());
            int toAcc = Integer.parseInt(toAccField.getText());
            double amount = Double.parseDouble(amountField.getText());

            TransactionService service = new TransactionService();
            boolean success = service.transferMoney(fromAcc, toAcc, amount);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Transfer Successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Insufficient Balance or Error",
                        "Failed",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Input",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
