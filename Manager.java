package FinalProject;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;

public class Manager extends JFrame implements ActionListener {

    String[] cblist = {"Bikes","Accessories", "Customer", "Staff","Suppliers", "Payments"};


    public Manager() {
        
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
            } else {
                String model = JOptionPane.showInputDialog(this, "Enter model to insert:");
                if (model != null && !model.isEmpty()) {
                    insertSimple(table, model);
                }
            }
        });

        b2.addActionListener(e -> {
            String table = combo.getSelectedItem().toString();
            if (table.equalsIgnoreCase("bicycle")) {
                String idStr = JOptionPane.showInputDialog(this, "Enter bicycle_id to update:");
                String model = JOptionPane.showInputDialog(this, "Enter new model:");
                if (idStr != null && model != null && !idStr.isEmpty() && !model.isEmpty()) {
                    updateBicycle(Integer.parseInt(idStr), model);
                }
            } else {
                String idStr = JOptionPane.showInputDialog(this, "Enter id to update:");
                String model = JOptionPane.showInputDialog(this, "Enter new model:");
                if (idStr != null && model != null && !idStr.isEmpty() && !model.isEmpty()) {
                    updateSimple(table, Integer.parseInt(idStr), model);
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
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {}

    // JDBC connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bicycle_rental_system";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "RamoRam";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    // --- BICYCLE TABLE HANDLING ---
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

    private void updateBicycle(int id, String model) {
        String sql = "UPDATE bicycle SET model=? WHERE bicycle_id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, model);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Updated bicycle");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Update failed: " + ex.getMessage());
        }
    }

    // --- GENERIC SIMPLE TABLE HANDLING (assumes 'id' and 'model' columns) ---
    private void insertSimple(String table, String model) {
        String sql = "INSERT INTO " + table + " (model) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, model);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Inserted into " + table);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Insert failed: " + ex.getMessage());
        }
    }

    private void updateSimple(String table, int id, String model) {
        String idCol = table + "_id";
        String sql = "UPDATE " + table + " SET model=? WHERE " + idCol + "=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, model);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Updated in " + table);
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
                } else {

                    // For other tables, just show id and model if they exist
                    try {
                        sb.append("ID: ").append(rs.getInt(1))
                          .append(", Model: ").append(rs.getString("model"))
                          .append("\n");
                    } catch (SQLException ex) {
                        sb.append("Fetch failed: ").append(ex.getMessage()).append("\n");
                    }
                }
            }
        } catch (SQLException ex) {
            sb.append("Fetch failed: ").append(ex.getMessage());
        }
        return sb.toString();
    }
}

