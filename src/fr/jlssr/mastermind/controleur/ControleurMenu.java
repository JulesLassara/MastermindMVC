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
        int reponse;

        switch(jmi.getText()) {
            case "Rejouer":
                this.fenetre.creerNouvelleVueMastermind();
                break;
            case "Sauvegarder":
                reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment sauvegarder la partie ?");
                if(reponse == JOptionPane.YES_OPTION) {
                    this.fenetre.sauvegarderVueMastermind("active");
                }
                break;
            case "Restaurer":
                reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment restaurer la partie ?");
                if(reponse == JOptionPane.YES_OPTION) {
                    this.fenetre.restaurerVueMastermindFichier("active");
                }
                break;
            case "Quitter":
                System.exit(JFrame.EXIT_ON_CLOSE);
                break;
        }

    }

}
