package FinalProject;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.*;
import javax.swing.*;
import java.util.*;


public class Login extends JFrame {
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JTextField t1;
    JTextArea ta;
    JPasswordField pa;
    JButton b1;
    JButton b2;
    JButton regb;
    JButton forb;
    JPanel p1;
    JPanel p2;

    public Login()  {

        setTitle("Bicycle Rental");
        Insets in = new Insets(0,0,0,0);

        p1 = new JPanel();
        p1.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        GridBagConstraints bc = new GridBagConstraints();

        l3 = new JLabel("Sign In");
        l3.setFont(new Font("Serif", Font.BOLD, 25));
        l3.setForeground(Color.decode("#A85307"));
        bc.gridx = 1;
        bc.gridy = 0;
        bc.gridheight = 1;
        bc.gridwidth = 1;
        bc.weightx = 0.5;
        bc.weighty = 0.1;
        in.set(0, 0, 30,50);
        bc.insets = in;
        bc.anchor = GridBagConstraints.PAGE_START;
        p1.add(l3, bc);

        Font fo = new Font("Times New Roman", Font.PLAIN, 20);

        l1 = new JLabel("Username: ");
        l1.setFont(fo);
        l1.setForeground(Color.decode("#A85307"));
        gb.gridx = 0;
        gb.gridy = 2;
        gb.gridheight = 1;
        gb.gridwidth = 1;
        //gb.weightx = 0.01;
        gb.weighty = 0.1;
        in.set(0,0,0,0);
        gb.insets = in;
        gb.ipadx = 0;
        gb.ipady = 0;
        p1.add(l1,gb);

        t1 = new JTextField();
        gb.gridx = 0;
        gb.gridy = 3;
        //gb.gridheight = 1;
        gb.gridwidth = 3;
        gb.weighty = 0;
        gb.ipadx = 400;
        in.set(10,0,10,0);
        gb.insets = in;
        //gb.weightx = 0.5;
        //gb.weighty = 0.1;
        p1.add(t1,gb);

        l2 = new JLabel("Password: ");
        l2.setForeground(Color.decode("#A85307"));
        l2.setFont(fo);
        gb.gridx = 0;
        gb.gridy = 4;
        //gb.gridheight = 1;
        gb.gridwidth = 1;
        in.set(0,0,0,0);
        gb.insets = in;
        gb.weighty = 0.1;
        gb.ipadx = 0;
        //gb.weightx = 0.5;
        //gb.weighty = 0.1;
        p1.add(l2,gb);

        pa = new JPasswordField();
        gb.gridx = 0;
        gb.gridy = 5;
        //gb.gridheight = 1;
        gb.gridwidth = 3;
        gb.ipadx = 400;
        //gb.weightx = 0.5;
        //gb.weighty = 0.1;
        p1.add(pa,gb);

        regb = new JButton("Register");
        regb.setBackground(Color.decode("#010D1A"));
        regb.setForeground(Color.CYAN);
        regb.setFont(fo);
        regb.setBorder(BorderFactory.createEmptyBorder());
        gb.gridx = 0;
        gb.gridy = 6;
        gb.gridheight = 1;
        gb.gridwidth = 2;
        gb.ipady = 0;
        gb.ipadx = 100;
        gb.weightx = 0.1;
        gb.weighty = 0;
        in.set(10,0,10,0);
        gb.insets = in;
        p1.add(regb, gb);

        //forb = new JButton("Forgot password");

        p2 = new JPanel();
        b1 = new JButton("OK");
        b1.addActionListener(e -> {
        
            String email = t1.getText();
            String password = new String(pa.getPassword());

            String[] roles = {"Customer (Renter)", "Staff", "Manager"};
            String selectedRole = (String) JOptionPane.showInputDialog(
                    this,
                    "Select your role:",
                    "Role Selection",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    roles,
                    roles[0]
            );

            if (selectedRole == null) return;

            boolean authenticated = false;
            String table = "";
            switch (selectedRole) {
                case "Customer (Renter)":
                    table = "renter"; break;
                case "Staff":
                    table = "staff"; break;
                case "Manager":
                    table = "manager"; break;
            }

            authenticated = authenticate(table, email, password);
            if (authenticated) {
                JOptionPane.showMessageDialog(this, selectedRole + " login successful!");
                dispose();
                
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password for selected role.");
            }
            
        });

        b1.setSize(60, 30);
        p2.add(b1);

        b2 = new JButton("Cancel");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == b2){
                    //new Welcome();
                }
            }
        });
        b2.setSize(60, 30);
        p2.add(b2);

        p1.setOpaque(false);
        p2.setOpaque(false);
        add(p1);
        add(p2, BorderLayout.SOUTH);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#010D1A"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        pack();
        setLocationRelativeTo(null);
        //setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        
    }

    private boolean authenticate(String table, String email, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bicycle_rental_system", "root", "RamoRam"
            );
            String sql = "SELECT * FROM " + table + " WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            boolean found = rs.next();
            rs.close();
            ps.close();
            conn.close();
            return found;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}

class Main {
        public static void main(String[] args) throws IOException {
        new Login();
    }
}
