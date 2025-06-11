package FinalProject;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;

public class Staff extends JFrame implements ActionListener {

    String[] cblist = {"bicycle","accessory", "customer", "payment"};


    public Staff() {
        
        setTitle("Manager");

        JPanel signPanel = new JPanel();
        signPanel.setBackground(Color.GRAY);
        signPanel.setBorder(BorderFactory.createEmptyBorder());
        signPanel.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        JButton signButton = new JButton("Sign Out");
        signButton.addActionListener(e->{
            new Welcome();
            dispose();
        });

        gb.anchor = GridBagConstraints.LINE_END;
        gb.gridx = 1;
        gb.gridy = 1;
        gb.gridheight = 1;
        gb.gridwidth = 1;
        gb.weightx=0.2;
        gb.ipadx = 20;
        signPanel.add(signButton, gb);
        add(signPanel, BorderLayout.NORTH);        
        
        JPanel mp = new JPanel();
        mp.setLayout(new BoxLayout(mp, BoxLayout.PAGE_AXIS));
        

        JPanel p1 = new JPanel();
        p1.setBackground(Color.GRAY);
        JTextArea ta1 = new JTextArea(5,30);

        JPanel bp1 = new JPanel();
        JButton b1 = new JButton("Insert");
        JButton b2 = new JButton("Update");
        JButton b3 = new JButton("Delete");
        JButton b4 = new JButton("See all records");
        JButton b5 = new JButton("Clear records");
        bp1.setLayout(new BoxLayout(bp1, BoxLayout.PAGE_AXIS));
        bp1.setBackground(Color.GRAY);
        bp1.add(b1);
        bp1.add(b2);
        bp1.add(b3);
        bp1.add(b4);
        bp1.add(b5);

        JComboBox<String> combo = new JComboBox<>(cblist);
        combo.addActionListener(this);

        p1.add(ta1);
        p1.add(bp1);
        p1.add(combo);

        mp.add(p1);

        JScrollPane sp = new JScrollPane(mp);
        add(sp);

        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setSize(550, 300);
        setVisible(true);
    
        b1.addActionListener(e -> {
            String table = combo.getSelectedItem().toString();
            if (table.equalsIgnoreCase("bicycle")) {
                String model = JOptionPane.showInputDialog(this, "Enter model:");
                String type = JOptionPane.showInputDialog(this, "Enter type:");
                String status = JOptionPane.showInputDialog(this, "Enter status:");
                String priceStr = JOptionPane.showInputDialog(this, "Enter price per hour:");
                String locationIdStr = JOptionPane.showInputDialog(this, "Enter location_id:");
                String supplierIdStr = JOptionPane.showInputDialog(this, "Enter supplier_id:");
                String accessoryIdStr = JOptionPane.showInputDialog(this, "Enter accessory_id:");
                String addedByStr = JOptionPane.showInputDialog(this, "Enter added_by:");
                if (model != null && !model.isEmpty()) {
                    insertBicycle(model, type, status, priceStr, locationIdStr, supplierIdStr, accessoryIdStr, addedByStr);
                }
            } else if (table.equalsIgnoreCase("customer")) {
                String name = JOptionPane.showInputDialog(this, "Enter name:");
                String email = JOptionPane.showInputDialog(this, "Enter email:");
                String phone = JOptionPane.showInputDialog(this, "Enter phone:");
                String password = JOptionPane.showInputDialog(this, "Enter password:");
                LocalDate temp = LocalDate.now();
                java.sql.Date regDate = java.sql.Date.valueOf(temp);
                if (name != null && !name.isEmpty()) {
                    insertCustomer(name, email, phone, password, regDate);
                }
            } else if (table.equalsIgnoreCase("accessory")) {
                String type = JOptionPane.showInputDialog(this, "Enter type:");
                String priceStr = JOptionPane.showInputDialog(this, "Enter price:");
                if (type != null && !type.isEmpty()) {
                    insertAccessory(type, priceStr);
                }
            
            } else {
                LocalDate temp = LocalDate.now();
                java.sql.Date payDate = java.sql.Date.valueOf(temp);                
                String amountStr = JOptionPane.showInputDialog(this, "Enter price:");
                String payMeth = JOptionPane.showInputDialog(this, "Enter payment method:");
                String status = JOptionPane.showInputDialog(this, "Enter status:");
                if (amountStr != null && !amountStr.isEmpty()) {
                    insertPayment(payDate, amountStr, payMeth, status);
                }
            }
        });

        b2.addActionListener(e -> {
            String table = combo.getSelectedItem().toString();
            if (table.equalsIgnoreCase("bicycle")) {
                int ID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter ID:"));
                String model = JOptionPane.showInputDialog(this, "Enter model:");
                String type = JOptionPane.showInputDialog(this, "Enter type:");
                String status = JOptionPane.showInputDialog(this, "Enter status:");
                String priceStr = JOptionPane.showInputDialog(this, "Enter price per hour:");
                String locationIdStr = JOptionPane.showInputDialog(this, "Enter location_id:");
                String supplierIdStr = JOptionPane.showInputDialog(this, "Enter supplier_id:");
                String accessoryIdStr = JOptionPane.showInputDialog(this, "Enter accessory_id:");
                String addedByStr = JOptionPane.showInputDialog(this, "Enter added_by:");
                if (model != null && !model.isEmpty()) {
                    updateBicycle(model, type, status, priceStr, locationIdStr, supplierIdStr, accessoryIdStr, addedByStr, ID);
                }
            } else if (table.equalsIgnoreCase("customer")) {
                int ID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter ID:"));
                String name = JOptionPane.showInputDialog(this, "Enter name:");
                String email = JOptionPane.showInputDialog(this, "Enter email:");
                String phone = JOptionPane.showInputDialog(this, "Enter phone:");
                String password = JOptionPane.showInputDialog(this, "Enter password:");
                LocalDate temp = LocalDate.now();
                java.sql.Date regDate = java.sql.Date.valueOf(temp);
                if (name != null && !name.isEmpty()) {
                    updateCustomer(name, email, phone, password, regDate, ID);
                }
            } else if (table.equalsIgnoreCase("accessory")) {
                int ID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter ID:"));
                String type = JOptionPane.showInputDialog(this, "Enter type:");
                String priceStr = JOptionPane.showInputDialog(this, "Enter price:");
                if (type != null && !type.isEmpty()) {
                    updateAccessory(type, priceStr, ID);
                }
            } else {
                int ID = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter ID:"));
                LocalDate temp = LocalDate.now();
                java.sql.Date payDate = java.sql.Date.valueOf(temp);                
                String amountStr = JOptionPane.showInputDialog(this, "Enter price:");
                String payMeth = JOptionPane.showInputDialog(this, "Enter payment method:");
                String status = JOptionPane.showInputDialog(this, "Enter status:");
                if (amountStr != null && !amountStr.isEmpty()) {
                    updatePayment(ID, payDate, amountStr, payMeth, status);
                }
            }
        });

        b3.addActionListener(e -> {
            String table = combo.getSelectedItem().toString();
            String idStr = JOptionPane.showInputDialog(this, "Enter id to delete:");
            if (idStr != null && !idStr.isEmpty()) {
                deleteById(table, Integer.parseInt(idStr));
            }
        });

        b4.addActionListener(e -> {
            String table = combo.getSelectedItem().toString();
            ta1.append(fetchRecords(table));
        });

        combo.addActionListener(e -> {
            ta1.setText("");
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {}

    private static final String DB_URL = "jdbc:mysql://localhost:3306/bicycle_rental_system";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "RamoRam";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    private void insertBicycle(String model, String type, String status, String priceStr, String locationIdStr, String supplierIdStr, String accessoryIdStr, String addedByStr) {
        String sql = "INSERT INTO bicycle (model, type, status, price_per_hour, location_id, supplier_id, accessory_id, added_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, model);
            stmt.setString(2, type);
            stmt.setString(3, status);
            stmt.setBigDecimal(4, new java.math.BigDecimal(priceStr));
            stmt.setInt(5, Integer.parseInt(locationIdStr));
            stmt.setInt(6, Integer.parseInt(supplierIdStr));
            stmt.setInt(7, Integer.parseInt(accessoryIdStr));
            stmt.setInt(8, Integer.parseInt(addedByStr));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Inserted into bicycle");
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Insert failed: " + ex.getMessage());
        }
    }

    private void updateBicycle(String model, String type, String status, String priceStr, String locationIdStr, String supplierIdStr, String accessoryIdStr, String addedByStr, int id) {
        String sql = "UPDATE bicycle SET model=?, type=?, status=?, price_per_hour=?, location_id=?, supplier_id=?, accessory_id=?, added_by=?) WHERE bicycle_id=?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, model);
            stmt.setString(2, type);
            stmt.setString(3, status);
            stmt.setBigDecimal(4, new java.math.BigDecimal(priceStr));
            stmt.setInt(5, Integer.parseInt(locationIdStr));
            stmt.setInt(6, Integer.parseInt(supplierIdStr));
            stmt.setInt(7, Integer.parseInt(accessoryIdStr));
            stmt.setInt(8, Integer.parseInt(addedByStr));
            stmt.setInt(9, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Updated bicycle");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Update failed: " + ex.getMessage());
        }
    }


    private void insertCustomer(String name, String email, String phone, String password, java.sql.Date regisDate){
        String sql = "INSERT INTO staff (full_name, email, phone, password, registered_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, password);
            stmt.setDate(5, regisDate);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Inserted into customer");
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Insert failed: " + ex.getMessage());
        }
    }

    private void updateCustomer(String name, String email, String phone, String password, java.sql.Date regisDate, int id){
        String sql = "UPDATE renter SET full_name=?, email=?, phone=?, password=?, registered_at=? WHERE renter_id=?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, password);
            stmt.setDate(5, regisDate);
            stmt.setInt(6, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Updated customer");
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Update failed: " + ex.getMessage());
        }
    }

    private void insertAccessory(String type, String priceStr) {
        String sql = "INSERT INTO accessory (type, price) VALUES (?, ?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, type);
            stmt.setBigDecimal(2, new java.math.BigDecimal(priceStr));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Inserted into accessory");
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Insert failed: " + ex.getMessage());
        }
    }

    private void updateAccessory(String type, String priceStr, int id) {
        String sql = "UPDATE accessory SET type=?, price=? WHERE accessory_id=?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, type);
            stmt.setBigDecimal(2, new java.math.BigDecimal(priceStr));
            stmt.setInt(3, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Updated accessory");
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Update failed: " + ex.getMessage());
        }
    }


    private void insertPayment(java.sql.Date date, String meth, String amountStr, String status) {
        String sql = "INSERT INTO payment (payment_time, amount, payment_method, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, date);
            stmt.setString(2, meth);
            stmt.setBigDecimal(3, new java.math.BigDecimal(amountStr));
            stmt.setString(4, status);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Inserted into payment");
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Insert failed: " + ex.getMessage());
        }
    }

    private void updatePayment(int id, java.sql.Date date, String meth, String amountStr, String status) {
        String sql = "UPDATE bicycle SET payment_time=?, amount=?, payment_method=?, status=? WHERE renter_id=?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, date);
            stmt.setString(2, meth);
            stmt.setBigDecimal(3, new java.math.BigDecimal(amountStr));
            stmt.setString(4, status);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Updated payment");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Update failed: " + ex.getMessage());
        }
    }


    private void deleteById(String table, int id) {
        String idCol = table + "_id";
        String sql = "DELETE FROM " + table + " WHERE " + idCol + "=?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Deleted from " + table);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Delete failed: " + ex.getMessage());
        }
    }

    private String fetchRecords(String table) {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT * FROM " + table;
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if (table.equalsIgnoreCase("bicycle")) {
                    sb.append("ID: ").append(rs.getInt("bicycle_id"))
                      .append(", Model: ").append(rs.getString("model"))
                      .append(", Type: ").append(rs.getString("type"))
                      .append(", Status: ").append(rs.getString("status"))
                      .append(", Price/hr: ").append(rs.getBigDecimal("price_per_hour"))
                      .append(", Location ID: ").append(rs.getInt("location_id"))
                      .append(", Supplier ID: ").append(rs.getInt("supplier_id"))
                      .append(", Accessory ID: ").append(rs.getInt("accessory_id"))
                      .append(", Added By: ").append(rs.getInt("added_by"))
                      .append(", Added At: ").append(rs.getString("added_at"))
                      .append("\n");
                } else if (table.equalsIgnoreCase("accessory")) {
                    sb.append("ID: ").append(rs.getInt("accessory_id"))
                      .append(", Type: ").append(rs.getString("type"))
                      .append(", Price: ").append(rs.getBigDecimal("price"))
                      .append("\n");
                } else if (table.equalsIgnoreCase("customer")) {
                    sb.append("ID: ").append(rs.getInt("renter_id"))
                      .append(", Name: ").append(rs.getString("full_name"))
                      .append(", Email: ").append(rs.getString("email"))
                      .append(", Phone: ").append(rs.getString("phone"))
                      .append(", Password: ").append(rs.getString("password"))
                      .append(", Registered at: ").append(rs.getDate("registered_at"))
                      .append("\n");
                } else if (table.equalsIgnoreCase("staff")){
                    sb.append("ID: ").append(rs.getInt("staff_id"))
                      .append(", Name: ").append(rs.getString("full_name"))
                      .append(", Email: ").append(rs.getString("email"))
                      .append(", Phone: ").append(rs.getString("phone"))
                      .append(", Password: ").append(rs.getString("password"))
                      .append(", Role: ").append(rs.getString("role"))
                      .append(", Manager ID: ").append(rs.getInt("manager_id"))
                      .append("\n");
                } else if (table.equalsIgnoreCase("supplier")) {
                    sb.append("ID: ").append(rs.getInt("renter_id"))
                      .append(", Name: ").append(rs.getString("full_name"))
                      .append(", Email: ").append(rs.getString("email"))
                      .append(", Phone: ").append(rs.getString("phone"))
                      .append(", Password: ").append(rs.getString("password"))
                      .append(", Registered at: ").append(rs.getDate("registered_at"))
                      .append("\n");
                } else {
                    sb.append("ID: ").append(rs.getInt("renter_id"))
                      .append(", Name: ").append(rs.getString("full_name"))
                      .append(", Email: ").append(rs.getString("email"))
                      .append(", Phone: ").append(rs.getString("phone"))
                      .append(", Password: ").append(rs.getString("password"))
                      .append(", Registered at: ").append(rs.getDate("registered_at"))
                      .append("\n");
                }
            }
        } catch (SQLException ex) {
            sb.append("Fetch failed: ").append(ex.getMessage());
        }
        return sb.toString();
    }
}
