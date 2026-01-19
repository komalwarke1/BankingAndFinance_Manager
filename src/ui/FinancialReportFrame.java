package ui;

import dao.ReportDao;

import javax.swing.*;
import java.awt.*;

public class FinancialReportFrame extends JFrame {

    public FinancialReportFrame() {

        setTitle("Financial Report");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        int accountId = Integer.parseInt(
                JOptionPane.showInputDialog(this, "Enter Account ID")
        );

        ReportDao dao = new ReportDao();
        double sent = dao.getTotalSentAmount(accountId);
        double received = dao.getTotalReceivedAmount(accountId);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        panel.add(new JLabel("Total Amount Sent: ₹ " + sent));
        panel.add(new JLabel("Total Amount Received: ₹ " + received));
        panel.add(new JLabel("Net Balance Change: ₹ " + (received - sent)));

        add(panel);
        setVisible(true);
    }
}
