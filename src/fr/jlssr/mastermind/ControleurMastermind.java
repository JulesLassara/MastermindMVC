package fr.jlssr.mastermind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurMastermind implements ActionListener {

    private VueMastermind vue;
    private ModeleMastermind modele;
    private Etat etat;
    private JButton jbactif;
    private int essai;

    public ControleurMastermind(VueMastermind vue) {
        this.vue = vue;
        this.modele = new ModeleMastermind(4, 6);
        this.etat = Etat.DEBUT_COMBINAISON;
        this.essai = -1;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton jb = (JButton) event.getSource();
        switch(this.etat) {
            case DEBUT_COMBINAISON:
                this.essai++;
                this.etat = Etat.CHOIX_COULEUR;
                break;
            case CHOIX_COULEUR:
                if(this.vue.appartientPalette(jb)) {
                    this.jbactif = jb;
                    this.etat = Etat.CHOIX_POSITION;
                }
                break;
            case CHOIX_POSITION:
                if(this.vue.appartientCombinaison(jb, this.essai)) {
                    jb.setBackground(this.jbactif.getBackground());
                    this.etat = Etat.CHOIX_COULEUR;
                }
                break;
            case FIN:
                break;
        }
    }

    private enum Etat {
        DEBUT_COMBINAISON,
        CHOIX_COULEUR,
        CHOIX_POSITION,
        FIN;
    }

}
