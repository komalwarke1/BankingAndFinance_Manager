package ui;

import dao.AccountDao;
import dao.AccountDao;
import model.Account;

import javax.swing.*;
import java.awt.*;

public class CreateAccountFrame extends JFrame {

    private JComboBox<String> accountTypeBox;
    private JTextField balanceField;
    private int userId;  // Logged-in user

    public CreateAccountFrame(int userId) {
        this.userId = userId;

        setTitle("Create Bank Account");
        setSize(420, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("Create New Account");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(120, 20, 200, 30);

        JLabel typeLabel = new JLabel("Account Type:");
        typeLabel.setBounds(70, 80, 120, 25);

        accountTypeBox = new JComboBox<>(new String[]{
                "Savings", "Current", "Salary", "Student"
        });
        accountTypeBox.setBounds(190, 80, 150, 25);

        JLabel balanceLabel = new JLabel("Initial Balance:");
        balanceLabel.setBounds(70, 120, 120, 25);

        balanceField = new JTextField();
        balanceField.setBounds(190, 120, 150, 25);

        JButton createBtn = new JButton("Create Account");
        createBtn.setBounds(190, 170, 140, 30);

        createBtn.addActionListener(e -> createAccount());

        panel.add(title);
        panel.add(typeLabel);
        panel.add(accountTypeBox);
        panel.add(balanceLabel);
        panel.add(balanceField);
        panel.add(createBtn);

        // BACK / LOGOUT BUTTON
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(20, 20, 80, 25);



        add(panel);


        setVisible(true);
    }

    private void createAccount() {
        try {
            String type = accountTypeBox.getSelectedItem().toString();
            double balance = Double.parseDouble(balanceField.getText());

            Account account = new Account(userId, type, balance);
            AccountDao dao = new AccountDao();

            if (dao.createAccount(account)) {
                JOptionPane.showMessageDialog(this,
                        "Account Created Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Account Creation Failed",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid balance amount",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
