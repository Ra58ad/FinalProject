package FinalProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Manager extends JFrame implements ActionListener {

    String[] cblist = {"Bikes","Accessories", "Customer", "Staff","Suppliers", "Payments"};


    public Manager() {
        setTitle("Manager");
        
        JPanel mp = new JPanel();
        mp.setLayout(new BoxLayout(mp, BoxLayout.PAGE_AXIS));

        JPanel p1 = new JPanel();
        p1.setBackground(Color.GRAY);
        JTextArea ta1 = new JTextArea(5,30);

        JPanel bp1 = new JPanel();
        JButton b1 = new JButton("Insert");
        JButton b2 = new JButton("Update");
        JButton b3 = new JButton("Delete");
        bp1.setLayout(new BoxLayout(bp1, BoxLayout.PAGE_AXIS));
        bp1.add(b1);
        bp1.add(b2);
        bp1.add(b3);

        JComboBox combo = new JComboBox(cblist);
        combo.addActionListener(this);

        p1.add(ta1);
        p1.add(bp1);
        p1.add(combo);

        mp.add(p1);

        JScrollPane sp = new JScrollPane(mp);
        add(sp);

        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }
        @Override
        public void actionPerformed(ActionEvent e){
        JComboBox com = (JComboBox) e.getSource();
        String sel = (String) com.getSelectedItem();
        if (sel == "Bikes"){}
        if (sel == "Accessories"){}
        if (sel == "Customer"){}
        if (sel == "Staff"){}
        if (sel == "Payments"){}
    }
}
