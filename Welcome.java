package FinalProject;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.*;

public class Welcome extends JFrame{
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

            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(Color.CYAN);
            mainPanel.setLayout(new GridBagLayout());

            

            JPanel leftPanel = new JPanel();
            ImageIcon icon = new ImageIcon("img_1.png");
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImg);
            JLabel l1 = new JLabel(scaledIcon);
            leftPanel.add(l1);

            add(leftPanel, BorderLayout.WEST);

            JScrollPane scPanel = new JScrollPane(mainPanel);
            add(scPanel);






            setResizable(true);
            //getContentPane().setBackground(Color.decode("#010D1A"));
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(1000, 1000);
            //setExtendedState(MAXIMIZED_BOTH);
            setVisible(true);
        }
}


