
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame extends JFrame {
    public static final int DEF_Width = 1280;
    public static final int DEF_Height = 720;

    public Race race;
    public static int n = 5;

    public void changeLog(JFileChooser fileChooser) {
        fileChooser.setVisible(false);

    }


    public Frame() throws Exception {

        JPanel panel = new JPanel();
        JButton start = new JButton("Start");

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // start.setVisible(false);
                new RaceThread(race).start();
            }
        });

        panel.add(start);
        this.getContentPane().add(BorderLayout.SOUTH, panel);
        this.race = new Race(5);
        this.add(race);
        pack();

    }
}
