package fr.jlssr.mastermind.controleur;

import fr.jlssr.mastermind.FenetreMastermind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurMenu implements ActionListener {

    private FenetreMastermind fenetre;

    public ControleurMenu(FenetreMastermind fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        JMenuItem jmi = (JMenuItem) event.getSource();

        switch(jmi.getText()) {
            case "Rejouer":
                this.fenetre.creerNouvelleVueMastermind();
                break;
            case "Sauvegarder":
                this.fenetre.sauvegarderVueMastermind("active");
                break;
            case "Restaurer":
                this.fenetre.restaurerVueMastermindFichier("active");
                break;
            case "Quitter":
                System.exit(JFrame.EXIT_ON_CLOSE);
                break;
        }

    }

}
