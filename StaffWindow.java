package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StaffWindow extends JFrame implements ActionListener {

    String[] cblist = {"Bikes", "Accessories", "Customer"};
    private ArrayList<Bike> allBikes = new ArrayList<>();
    private ArrayList<Accessory> allAccessories = new ArrayList<>();
    private ArrayList<Customer> allCustomers = new ArrayList<>();
    private JTextArea displayArea;

    public StaffWindow() {
        setTitle("Staff Window");

        JPanel mp = new JPanel();
        mp.setLayout(new BoxLayout(mp, BoxLayout.PAGE_AXIS));

        JPanel p1 = new JPanel();
        p1.setBackground(Color.GRAY);
        displayArea = new JTextArea(5, 30);
        displayArea.setEditable(false); // Staff can only view, not edit

        JComboBox combo = new JComboBox(cblist);
        combo.addActionListener(this);

        p1.add(displayArea);
        p1.add(combo);

        mp.add(p1);

        JScrollPane sp = new JScrollPane(mp);
        add(sp);

        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setSize(600, 600);
        setVisible(true);

        // Sample data for testing
        allBikes.add(new Bike(1, "Mountain Bike", "$500"));
        allBikes.add(new Bike(2, "Road Bike", "$700"));
        allAccessories.add(new Accessory(1, "Helmet", "$50"));
        allAccessories.add(new Accessory(2, "Lock", "$20"));
        allCustomers.add(new Customer(1, "John Doe", "john@example.com"));
        allCustomers.add(new Customer(2, "Jane Smith", "jane@example.com"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox com = (JComboBox) e.getSource();
        String sel = (String) com.getSelectedItem();
        displayArea.setText(""); // Clear previous content

        if (sel.equals("Bikes")) {
            for (Bike bike : allBikes) {
                displayArea.append("Bike ID: " + bike.id + "\nName: " + bike.name + "\nPrice: " + bike.price + "\n\n");
            }
        } else if (sel.equals("Accessories")) {
            for (Accessory accessory : allAccessories) {
                displayArea.append("Accessory ID: " + accessory.id + "\nName: " + accessory.name + "\nPrice: " + accessory.price + "\n\n");
            }
        } else if (sel.equals("Customer")) {
            for (Customer customer : allCustomers) {
                displayArea.append("Customer ID: " + customer.id + "\nName: " + customer.name + "\nContact: " + customer.contact + "\n\n");
            }
        }
    }

    class Bike {
        int id;
        String name;
        String price;

        Bike(int id, String name, String price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }

    class Accessory {
        int id;
        String name;
        String price;

        Accessory(int id, String name, String price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }

    class Customer {
        int id;
        String name;
        String contact;

        Customer(int id, String name, String contact) {
            this.id = id;
            this.name = name;
            this.contact = contact;
        }
    }


}
