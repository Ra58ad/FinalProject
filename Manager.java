package FinalProject;

import java.awt.*;
import java.awt.event.*;
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
        pack();
        setLocationRelativeTo(null);
        setSize(550, 300);
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
