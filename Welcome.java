package FinalProject;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.sql.*;

import javax.imageio.*;
import javax.swing.*;

import FinalProject.Customer.PaymentRecord;
import FinalProject.Customer.RentedBike;

import java.util.*;

public class Welcome extends JFrame{

        private GridBagConstraints gdc = new GridBagConstraints();
        private JButton bikesOffered, bikesRented, payment, staffView;
        private JPanel mainPanel, bikesOfferedPanel, bikesRentedPanel, paymentPanel, staffPanel;
        private ArrayList<RentedBike> rentedBikes = new ArrayList<>();
        private ArrayList<PaymentRecord> paymentRecords = new ArrayList<>();
        ArrayList <String[]> sb = new ArrayList<>();
        private Connection conn;
        String[] imgList = {"img_1.png", "img_2.png", "img_3.png", "img_4.png", "img_5.png"};

        static String DB = "jdbc:mysql://localhost:3306/bicycle_rental_system";
        static String User = "root";
        static String Password = "RamoRam";

        public Welcome() {
            
            JPanel menuPanel = new JPanel();
            menuPanel.setBackground(Color.DARK_GRAY);
            menuPanel.setLayout(new GridBagLayout());
            GridBagConstraints gb = new GridBagConstraints();

            JMenuBar mb = new JMenuBar();

            JMenu bikeMenu = new JMenu("Bikes");

            JMenu contactMenu = new JMenu("Contact");
            JMenuItem ad = new JMenuItem("Address");
            JMenuItem form = new JMenuItem("Contact form");
            contactMenu.add(ad);
            contactMenu.add(form);
            JMenu logMenu = new JMenu("Login");
            JMenu regMenu = new JMenu("Register");

            mb.add(bikeMenu);
            mb.add(contactMenu);
            mb.add(logMenu);
            mb.add(regMenu);
            gb.gridheight = 1;
            gb.gridwidth = 1;
            gb.gridx = 1;
            gb.gridy = 1;
            gb.fill = GridBagConstraints.HORIZONTAL;
            menuPanel.add(mb, gb);
            add(menuPanel, BorderLayout.NORTH);

            JPanel mainPanel1 = new JPanel();
            mainPanel1.setBackground(Color.CYAN);
            mainPanel1.setLayout(new GridBagLayout());

            

            JPanel leftPanel = new JPanel();
            ImageIcon icon = new ImageIcon("img_1.png");
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImg);
            JLabel l1 = new JLabel(scaledIcon);
            leftPanel.add(l1);

            add(leftPanel, BorderLayout.WEST);

            JScrollPane scPanel = new JScrollPane(mainPanel1);
            add(scPanel);

                

    


        try {
            conn = DriverManager.getConnection(DB, User, Password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage());
            System.exit(1);
        }
        fetchBikes();



        mainPanel = new JPanel(new CardLayout());
        bikesOfferedPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        bikesOfferedPanel.setBackground(Color.BLACK);
        bikesRentedPanel = new JPanel();
        bikesRentedPanel.setBackground(Color.BLACK);
        paymentPanel = new JPanel();
        paymentPanel.setBackground(Color.BLACK);


        bikesOffered = new JButton("Bikes Offered");
        bikesRented = new JButton("Bikes Rented");
        payment = new JButton("Payment");

        bikesOffered.setBackground(Color.DARK_GRAY);
        bikesRented.setBackground(Color.DARK_GRAY);
        payment.setBackground(Color.DARK_GRAY);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(bikesOffered);
        buttonPanel.add(bikesRented);
        buttonPanel.add(payment);

        addSampleBikes();

        mainPanel.add(bikesOfferedPanel, "BikesOffered");
        mainPanel.add(bikesRentedPanel, "BikesRented");
        mainPanel.add(paymentPanel, "Payment");


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
            }
        }
        };
        bikesOffered.addActionListener(listener);
        bikesRented.addActionListener(listener);
        payment.addActionListener(listener);
    

        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }



    private void addSampleBikes() {


        for (String[] bike : sb) {
            Random ran = new Random();
            String ranImg = imgList[ran.nextInt(5)];
            JLabel bikeLabel = new JLabel(new ImageIcon(getClass().getResource(ranImg)));
            bikeLabel.setText("<html>Price: " + bike[4]);
            bikeLabel.setForeground(Color.WHITE);
            bikeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
            bikeLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
            bikeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            bikeLabel.setVerticalAlignment(SwingConstants.CENTER);

            bikeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    setVisible(false);
                    new Login();
                    
                }
            });

            bikesOfferedPanel.add(bikeLabel);
        }
    }




private void fetchBikes() {
        
        String sql = "SELECT * FROM bicycle";
        try (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                
                String ID = rs.getString("bicycle_id");
                String model = rs.getString("model");
                String type = rs.getString("type");
                String status = rs.getString("status");
                String price = rs.getString("price_per_hour");
                String[] temp = {ID, model, type, status, price};
                sb.add(temp);
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Fetch failed: " + ex.getMessage());
        }
    
    }
}



