package ui;

import dao.UserDao;
import dao.UserDao;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private JTextField nameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;

    public RegisterFrame() {

        setTitle("User Registration");
        setSize(450, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("Create New Account");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(120, 20, 250, 30);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(70, 80, 120, 25);

        nameField = new JTextField();
        nameField.setBounds(190, 80, 170, 25);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(70, 120, 120, 25);

        emailField = new JTextField();
        emailField.setBounds(190, 120, 170, 25);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(70, 160, 120, 25);

        passwordField = new JPasswordField();
        passwordField.setBounds(190, 160, 170, 25);

        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setBounds(70, 200, 120, 25);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(190, 200, 170, 25);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(190, 250, 100, 30);

        registerBtn.addActionListener(e -> registerUser());

        panel.add(title);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(confirmLabel);
        panel.add(confirmPasswordField);
        panel.add(registerBtn);

        add(panel);
        setVisible(true);
    }

    private void registerUser() {

        String name = nameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "All fields are required!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Passwords do not match!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserDao userDAO = new UserDao();
        boolean success = userDAO.register(name, email, password);

        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Registration Successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new LoginFrame();
        }
    }
}
