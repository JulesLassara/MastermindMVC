package fr.jlssr.mastermind;

import javax.swing.*;
import java.awt.*;

public class FenetreMastermind extends JFrame {

    public FenetreMastermind() {
        this.setTitle("Mastermind v0.1");
        this.setLayout(new GridLayout(1, 1));
        this.add(new VueMastermind());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

}
