package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;

public class Customer extends JFrame {

    private GridBagConstraints gdc = new GridBagConstraints();
    private JButton bikesOffered, bikesRented, payment, staffView;
    private JPanel mainPanel, bikesOfferedPanel, bikesRentedPanel, paymentPanel, staffPanel;
    private ArrayList<RentedBike> rentedBikes = new ArrayList<>();
    private ArrayList<PaymentRecord> paymentRecords = new ArrayList<>();
    private ArrayList<Bike> bikeList = new ArrayList<>();
    private Connection conn;

    public Customer() {
        setTitle("Bicycle Rental System");
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bicycle_rental_system", "root", "RamoRam");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage());
            System.exit(1);
        }




        mainPanel = new JPanel(new CardLayout());
        bikesOfferedPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        bikesOfferedPanel.setBackground(Color.BLACK);
        bikesRentedPanel = new JPanel();
        bikesRentedPanel.setBackground(Color.BLACK);
        paymentPanel = new JPanel();
        paymentPanel.setBackground(Color.BLACK);
        staffPanel = new JPanel(new GridLayout(3, 1));
        staffPanel.setBackground(Color.BLACK);

        bikesOffered = new JButton("Bikes Offered");
        bikesRented = new JButton("Bikes Rented");
        payment = new JButton("Payment");
        staffView = new JButton("Staff View");

        bikesOffered.setBackground(Color.DARK_GRAY);
        bikesRented.setBackground(Color.DARK_GRAY);
        payment.setBackground(Color.DARK_GRAY);
        staffView.setBackground(Color.DARK_GRAY);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(bikesOffered);
        buttonPanel.add(bikesRented);
        buttonPanel.add(payment);
        buttonPanel.add(staffView);

        addSampleBikes();

        mainPanel.add(bikesOfferedPanel, "BikesOffered");
        mainPanel.add(bikesRentedPanel, "BikesRented");
        mainPanel.add(paymentPanel, "Payment");
        mainPanel.add(staffPanel, "Staff");

        add(buttonPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) mainPanel.getLayout();
                if (e.getSource() == bikesOffered) {
                    bikesOffered.setBackground(Color.BLUE);
                    bikesRented.setBackground(Color.DARK_GRAY);
                    payment.setBackground(Color.DARK_GRAY);
                    staffView.setBackground(Color.DARK_GRAY);
                    cl.show(mainPanel, "BikesOffered");
                } else if (e.getSource() == bikesRented) {
                    bikesRented.setBackground(Color.BLUE);
                    bikesOffered.setBackground(Color.DARK_GRAY);
                    payment.setBackground(Color.DARK_GRAY);
                    staffView.setBackground(Color.DARK_GRAY);
                    showRentedBikes();
                    cl.show(mainPanel, "BikesRented");
                } else if (e.getSource() == payment) {
                    payment.setBackground(Color.BLUE);
                    bikesOffered.setBackground(Color.DARK_GRAY);
                    bikesRented.setBackground(Color.DARK_GRAY);
                    staffView.setBackground(Color.DARK_GRAY);
                    showPaymentRecords();
                    cl.show(mainPanel, "Payment");
                } else if (e.getSource() == staffView) {
                    staffView.setBackground(Color.BLUE);
                    bikesOffered.setBackground(Color.DARK_GRAY);
                    bikesRented.setBackground(Color.DARK_GRAY);
                    payment.setBackground(Color.DARK_GRAY);
                    showStaffView();
                    cl.show(mainPanel, "Staff");
                }
            }
        };
        bikesOffered.addActionListener(listener);
        bikesRented.addActionListener(listener);
        payment.addActionListener(listener);
        staffView.addActionListener(listener);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addSampleBikes() {
        bikeList.add(new Bike("/img_1.png", "120.00", "Helmet"));
        bikeList.add(new Bike("/img_2.png", "100.00", "Bike Lock"));
        bikeList.add(new Bike("/img_3.png", "130.00", "Water Bottle"));
        bikeList.add(new Bike("/img_4.png", "150.00", "Helmet, Bike Lock"));
        bikeList.add(new Bike("/img_5.png", "110.00", "Water Bottle"));

        for (Bike bike : bikeList) {
            JLabel bikeLabel = new JLabel(new ImageIcon(getClass().getResource(bike.imagePath)));
            bikeLabel.setText("<html>Price: " + bike.price + "<br>Accessories: " + bike.accessories + "</html>");
            bikeLabel.setForeground(Color.WHITE);
            bikeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            bikeLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
            bikeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            bikeLabel.setVerticalAlignment(SwingConstants.CENTER);

            bikeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    openRentingPage(bike.imagePath, bike.price, bike.accessories);
                }
            });

            bikesOfferedPanel.add(bikeLabel);
        }
    }

    private void openRentingPage(String resourcePath, String price, String accessories) {
        JFrame rentingFrame = new JFrame("Rent Bike");
        rentingFrame.setSize(400, 500);
        rentingFrame.setLocationRelativeTo(null);
        JPanel rentingPanel = new JPanel();
        rentingPanel.setLayout(new BoxLayout(rentingPanel, BoxLayout.Y_AXIS));
        rentingPanel.setBackground(Color.LIGHT_GRAY);

        java.net.URL imgURL = getClass().getResource(resourcePath);
        if (imgURL == null) {
            System.err.println("Couldn't find file: " + resourcePath);
            return;
        }

        ImageIcon icon = new ImageIcon(imgURL);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel bikeImageLabel = new JLabel(scaledIcon);
        bikeImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rentingPanel.add(bikeImageLabel);

        JLabel priceLabel = new JLabel("Price: " + price);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rentingPanel.add(priceLabel);

        JLabel accessoriesLabel = new JLabel("Accessories: " + accessories);
        accessoriesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rentingPanel.add(accessoriesLabel);

        JPanel durationPanel = new JPanel(new FlowLayout());
        durationPanel.add(new JLabel("Start Date:"));
        JTextField startDateField = new JTextField(10);
        durationPanel.add(startDateField);
        durationPanel.add(new JLabel("Return Date:"));
        JTextField returnDateField = new JTextField(10);
        durationPanel.add(returnDateField);
        rentingPanel.add(durationPanel);

        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));

        JLabel paymentLabel = new JLabel("Choose Payment Method:");
        paymentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        paymentPanel.add(paymentLabel);

        JRadioButton teleBirr = new JRadioButton("Telebirr");
        JRadioButton mobileBanking = new JRadioButton("Mobile Banking");

        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(teleBirr);
        paymentGroup.add(mobileBanking);

        paymentPanel.add(teleBirr);
        paymentPanel.add(mobileBanking);
        rentingPanel.add(paymentPanel);

        JButton rentButton = new JButton("Rent Now");
        rentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rentingPanel.add(rentButton);

        rentButton.addActionListener(e -> {
            String startDate = startDateField.getText().trim();
            String returnDate = returnDateField.getText().trim();
            if (startDate.isEmpty() || returnDate.isEmpty() || (!teleBirr.isSelected() && !mobileBanking.isSelected())) {
                JOptionPane.showMessageDialog(rentingFrame, "Please fill all fields and select payment method");
                return;
            }
            rentedBikes.add(new RentedBike("", price, startDate, returnDate));
            paymentRecords.add(new PaymentRecord(price, false));
            JOptionPane.showMessageDialog(rentingFrame, "Bike rented successfully!");
            rentingFrame.dispose();
        });

        rentingFrame.add(rentingPanel);
        rentingFrame.setVisible(true);
    }

    private void showRentedBikes() {
        bikesRentedPanel.removeAll();
        for (RentedBike rb : rentedBikes) {
            String info = "<html>Bike: " + rb.price + "<br>From: " + rb.startDate + "<br>To: " + rb.returnDate + "</html>";
            JLabel label = new JLabel(info);
            label.setForeground(Color.WHITE);
            bikesRentedPanel.add(label);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showPaymentRecords() {
        paymentPanel.removeAll();
        for (PaymentRecord pr : paymentRecords) {
            String info = "<html>Amount: " + pr.amount + "<br>Due: " + (pr.isPaid ? "No" : "Yes") + "</html>";
            JLabel label = new JLabel(info);
            label.setForeground(Color.WHITE);
            paymentPanel.add(label);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showStaffView() {
        staffPanel.removeAll();
        try {
            String sqlBikes = "SELECT * FROM Bicycle";
            Statement stmtBikes = conn.createStatement();
            ResultSet rsBikes = stmtBikes.executeQuery(sqlBikes);
            JTextArea bikesArea = new JTextArea("Bikes:\n");
            while (rsBikes.next()) {
                bikesArea.append("ID: " + rsBikes.getInt("bicycle_id") + ", Model: " + rsBikes.getString("model") + ", Type: " + rsBikes.getString("type") + ", Price/Hour: " + rsBikes.getDouble("price_per_hour") + "\n");
            }
            staffPanel.add(new JScrollPane(bikesArea));

            String sqlAccessories = "SELECT * FROM Accessory";
            Statement stmtAccessories = conn.createStatement();
            ResultSet rsAccessories = stmtAccessories.executeQuery(sqlAccessories);
            JTextArea accessoriesArea = new JTextArea("Accessories:\n");
            while (rsAccessories.next()) {
                accessoriesArea.append("ID: " + rsAccessories.getInt("accessory_id") + ", Type: " + rsAccessories.getString("type") + ", Price: " + rsAccessories.getDouble("price") + "\n");
            }
            staffPanel.add(new JScrollPane(accessoriesArea));

            String sqlCustomers = "SELECT * FROM Renter";
            Statement stmtCustomers = conn.createStatement();
            ResultSet rsCustomers = stmtCustomers.executeQuery(sqlCustomers);
            JTextArea customersArea = new JTextArea("Customers:\n");
            while (rsCustomers.next()) {
                customersArea.append("ID: " + rsCustomers.getInt("renter_id") + ", Name: " + rsCustomers.getString("full_name") + ", Email: " + rsCustomers.getString("email") + "\n");
            }
            staffPanel.add(new JScrollPane(customersArea));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching staff data: " + e.getMessage());
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    class Bike {
        String imagePath;
        String price;
        String accessories;

        Bike(String imagePath, String price, String accessories) {
            this.imagePath = imagePath;
            this.price = price;
            this.accessories = accessories;
        }
    }

    class RentedBike {
        String imagePath;
        String price;
        String startDate;
        String returnDate;

        RentedBike(String imagePath, String price, String startDate, String returnDate) {
            this.imagePath = imagePath;
            this.price = price;
            this.startDate = startDate;
            this.returnDate = returnDate;
        }
    }

    class PaymentRecord {
        String amount;
        boolean isPaid;

        PaymentRecord(String amount, boolean isPaid) {
            this.amount = amount;
            this.isPaid = isPaid;
        }
    }
}

class Main {
        public static void main(String[] args) {
        new Customer();
    }
}
