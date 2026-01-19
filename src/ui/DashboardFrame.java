package ui;

import javax.swing.*;

public class DashboardFrame extends JFrame {

    private int loggedInUserId; // ✅ STORE USER ID

    public DashboardFrame(int userId) {

        this.loggedInUserId = userId;

        setTitle("Banking Dashboard");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel label = new JLabel("Welcome to Banking Dashboard", JLabel.CENTER);
        label.setBounds(100, 30, 300, 30);
        add(label);

        JButton createAccountBtn = new JButton("Create Account");
        createAccountBtn.setBounds(150, 100, 200, 30);

        createAccountBtn.addActionListener(e -> {
            new CreateAccountFrame(loggedInUserId); // ✅ NOW VALID
        });

        add(createAccountBtn);

        JButton transferBtn = new JButton("Fund Transfer");
        transferBtn.setBounds(150, 150, 200, 30);

        transferBtn.addActionListener(e -> {
            new FundTransferFrame();
        });

        add(transferBtn);

        JButton historyBtn = new JButton("Transaction History");
        historyBtn.setBounds(150, 200, 200, 30);
        historyBtn.addActionListener(e ->
                new TransactionHistoryFrame(loggedInUserId)
        );


        JButton reportBtn = new JButton("Financial Report");
        reportBtn.setBounds(150, 250, 200, 30);
        reportBtn.addActionListener(e -> new FinancialReportFrame());

        add(historyBtn);
        add(reportBtn);

        // ✅ BACK / LOGOUT BUTTON
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(20, 20, 80, 25);

        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new LoginFrame(); // 🔁 Go back to login
            }
        });

        add(logoutBtn);



        setVisible(true);
    }
}
