package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Final_Project {

    GridBagConstraints gdc = new GridBagConstraints();
    JButton bikesOffered;
    JButton bikesRented;
    JButton Payment;
    JPanel mainPanel;
    JPanel bikesOfferedPanel;
    JPanel bikesRentedPanel;
    JPanel paymentPanel;

    ArrayList<RentedBike> rentedBikes = new ArrayList<>();
    ArrayList<PaymentRecord> paymentRecords = new ArrayList<>();

    Final_Project() {
        JFrame frame = new JFrame();
        JPanel leftPanel = new JPanel();

        leftPanel.setBackground(Color.DARK_GRAY);
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(200, 0));

        JLabel windowLabel = new JLabel("Customer windows");
        windowLabel.setHorizontalAlignment(JLabel.CENTER);
        windowLabel.setForeground(Color.WHITE);

        JPanel windowPanel = new JPanel();
        windowPanel.add(windowLabel);
        windowPanel.setBackground(Color.gray);

        JPanel leftMainPanel = new JPanel();
        leftMainPanel.setLayout(new GridBagLayout());
        leftMainPanel.setBackground(Color.DARK_GRAY);
        bikesOffered = new JButton("Bikes Offered");
        bikesOffered.addActionListener(Listener);
        bikesOffered.setBackground(Color.DARK_GRAY);
        bikesOffered.setForeground(Color.WHITE);
        gdc.gridx = 0;
        gdc.gridy = 0;
        gdc.fill = GridBagConstraints.HORIZONTAL;
        gdc.weightx = 1.0;
        gdc.insets = new Insets(5, 5, 5, 5);

        leftMainPanel.add(bikesOffered, gdc);

        bikesRented = new JButton("Bikes Rented");
        bikesRented.addActionListener(Listener);
        bikesRented.setBackground(Color.DARK_GRAY);
        bikesRented.setForeground(Color.WHITE);
        gdc.gridx = 0;
        gdc.gridy = 1;
        gdc.fill = GridBagConstraints.HORIZONTAL;
        leftMainPanel.add(bikesRented, gdc);

        Payment = new JButton("Payment");
        Payment.setBackground(Color.DARK_GRAY);
        Payment.setForeground(Color.WHITE);
        Payment.addActionListener(Listener);
        gdc.gridx = 0;
        gdc.gridy = 2;
        gdc.fill = GridBagConstraints.HORIZONTAL;

        leftMainPanel.add(Payment, gdc);

        leftPanel.add(windowPanel, BorderLayout.NORTH);
        leftPanel.add(leftMainPanel, BorderLayout.CENTER);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLUE);
        mainPanel.setLayout(new GridBagLayout());

        bikesOfferedPanel = new JPanel();
        bikesOfferedPanel.setBackground(Color.BLUE);
        bikesOfferedPanel.setPreferredSize(new Dimension(600, 400));
        bikesOfferedPanel.setVisible(false);
        bikesOfferedPanel.setLayout(new GridLayout(0, 3, 10, 10));

        addBike("img.png", "100 birr per hour", "Helmet included");
        addBike("img_1.png", "120 birr per hour", "Gloves included");
        addBike("img_2.png", "90 birr per hour", "Basket included");
        addBike("img_3.png", "110 birr per hour", "Bell included");
        addBike("img_4.png", "130 birr per hour", "Lights included");
        addBike("img_5.png", "140 birr per hour", "Reflectors included");

        bikesRentedPanel = new JPanel();
        bikesRentedPanel.setBackground(Color.WHITE);
        bikesRentedPanel.setPreferredSize(new Dimension(600, 400));
        bikesRentedPanel.setVisible(false);
        bikesRentedPanel.setLayout(new BoxLayout(bikesRentedPanel, BoxLayout.Y_AXIS));

        paymentPanel = new JPanel();
        paymentPanel.setBackground(Color.WHITE);
        paymentPanel.setPreferredSize(new Dimension(600, 400));
        paymentPanel.setVisible(false);
        paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));

        mainPanel.add(bikesOfferedPanel);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.setSize(850, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void addBike(String imagePath, String price, String accessories) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);


        JLabel bikeLabel = new JLabel();
        bikeLabel.setIcon(scaledIcon);
        bikeLabel.setText("<html><center>" + price + "<br/>" + accessories + "</center></html>");
        bikeLabel.setForeground(Color.WHITE);
        bikeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        bikeLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        bikeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bikeLabel.setVerticalAlignment(SwingConstants.CENTER);

        bikeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openRentingPage(imagePath, price, accessories);
            }
        });

        bikesOfferedPanel.add(bikeLabel);
    }

    private void openRentingPage(String imagePath, String price, String accessories) {
        JFrame rentingFrame = new JFrame("Rent Bike");
        rentingFrame.setSize(400, 500);
        rentingFrame.setLocationRelativeTo(null);
        JPanel rentingPanel = new JPanel();
        rentingPanel.setLayout(new BoxLayout(rentingPanel, BoxLayout.Y_AXIS));
        rentingPanel.setBackground(Color.LIGHT_GRAY);

        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel bikeImageLabel = new JLabel(scaledIcon);
        bikeImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rentingPanel.add(bikeImageLabel);

        JLabel priceLabel = new JLabel(price);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rentingPanel.add(priceLabel);

        JLabel accessoriesLabel = new JLabel(accessories);
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

        JRadioButton teleBirr = new JRadioButton("tele Birr");
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
            rentedBikes.add(new RentedBike(imagePath, price, startDate, returnDate));
            paymentRecords.add(new PaymentRecord(price, false));
            JOptionPane.showMessageDialog(rentingFrame, "Bike rented successfully!");
            rentingFrame.dispose();
        });

        rentingFrame.add(rentingPanel);
        rentingFrame.setVisible(true);
    }

    private void showRentedBikes() {
        bikesRentedPanel.removeAll();
        bikesRentedPanel.setBackground(Color.BLACK);
        for (RentedBike rb : rentedBikes) {
            String info = "<html>Bike: <br>" + rb.price + "<br>From: " + rb.startDate + " To: " + rb.returnDate + "</html>";
            JLabel label = new JLabel(info);
            label.setForeground(Color.WHITE);
            bikesRentedPanel.add(label);
        }
        updateMainPanel(bikesRentedPanel);
    }

    private void showPaymentRecords() {
        paymentPanel.removeAll();
        paymentPanel.setBackground(Color.BLACK);
        for (PaymentRecord pr : paymentRecords) {
            String info = "<html>Amount: " + pr.amount + "<br>Due: " + (pr.isPaid ? "No" : "Yes") + "</html>";
            JLabel label = new JLabel(info);
            label.setForeground(Color.WHITE);
            paymentPanel.add(label);
        }
        updateMainPanel(paymentPanel);
    }

    private void updateMainPanel(JPanel panelToShow) {
        mainPanel.removeAll();
        mainPanel.add(panelToShow);
        panelToShow.setVisible(true);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    ActionListener Listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == bikesOffered) {
                bikesOffered.setBackground(Color.BLUE);
                bikesRented.setBackground(Color.DARK_GRAY);
                Payment.setBackground(Color.DARK_GRAY);
                updateMainPanel(bikesOfferedPanel);

            } else if (e.getSource() == bikesRented) {
                bikesRented.setBackground(Color.BLUE);
                bikesOffered.setBackground(Color.DARK_GRAY);
                Payment.setBackground(Color.DARK_GRAY);
                showRentedBikes();
            } else if (e.getSource() == Payment) {
                Payment.setBackground(Color.BLUE);
                bikesOffered.setBackground(Color.DARK_GRAY);
                bikesRented.setBackground(Color.DARK_GRAY);
                showPaymentRecords();
            }
        }
    };

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
