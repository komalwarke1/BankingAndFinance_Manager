package ui;

import dao.UserDao;
import dao.UserDao;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    JPanel panel = new JPanel();

    public LoginFrame() {

        // Frame settings
        setTitle("Banking & Finance Manager - Login");
        setSize(420, 320);
        setLocationRelativeTo(null); // Center screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Main panel

        panel.setLayout(null);
        panel.setBackground(new Color(245, 247, 250));

        // Title
        JLabel title = new JLabel("User Login");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(150, 20, 200, 30);

        // Email Label
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(60, 80, 100, 25);

        // Email Field
        emailField = new JTextField();
        emailField.setBounds(160, 80, 180, 25);

        // Password Label
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(60, 120, 100, 25);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(160, 120, 180, 25);

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(160, 170, 90, 30);
        loginBtn.setFocusPainted(false);


        setVisible(true);

        // Action Listener
        loginBtn.addActionListener(e -> loginUser());



        // Add components
        panel.add(title);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(loginBtn);

        add(panel);
        setVisible(true);
        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(260, 170, 90, 30);

        registerBtn.addActionListener(e -> {
            dispose();
            new RegisterFrame();
        });

        panel.add(registerBtn);

    }

    private void loginUser() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter Email and Password",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserDao userDAO = new UserDao();
        int userId = userDAO.loginAndGetUserId(email, password);

        if (userId != -1) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            dispose();
            new DashboardFrame(userId); // 👈 PASS userId
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid Email or Password",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }


    }
}
