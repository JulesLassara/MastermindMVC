package fr.jlssr.mastermind.controleur;

import fr.jlssr.mastermind.ModeleMastermind;
import fr.jlssr.mastermind.VueMastermind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ControleurMastermind implements ActionListener {

    private VueMastermind vue;
    private ModeleMastermind modele;
    private Etat etat;
    private Color colorPicked;
    private int essai;

    public ControleurMastermind(VueMastermind vue) {
        this.vue = vue;
        this.modele = new ModeleMastermind(4, 6);
        this.modele.genererCombinaison();
        this.essai = -1;
        this.etat = Etat.DEBUT_COMBINAISON;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton jb = (JButton) event.getSource();

        switch(this.etat) {
            case DEBUT_COMBINAISON:
                if(this.vue.appartientPalette(jb)) {
                    this.pickColor(jb);
                    this.essai++;
                    this.setEtat(Etat.CHOIX_COULEUR);
                }
                break;
            case CHOIX_COULEUR:
                if(this.vue.appartientCombinaison(jb, this.essai)) {
                    jb.setBackground(this.colorPicked);
                    this.setEtat(Etat.CHOIX_POSITION);
                    if(this.vue.combinaisonComplete(this.essai)) {
                        this.vue.activerBtnValider(true);
                    }
                }
                break;
            case CHOIX_POSITION:
                if(this.vue.appartientPalette(jb)) {
                    this.pickColor(jb);
                    this.setEtat(Etat.CHOIX_COULEUR);
                } else if(jb.equals(this.vue.getBtnValider())) {
                    int[] combJoueur = this.vue.combinaisonEnEntiers(this.essai);
                    int[] combOrdi = this.modele.getCombinaison();
                    this.vue.afficherBP(this.essai, this.modele.nbChiffresBienPlaces(combJoueur));
                    this.vue.afficherMP(this.essai, this.modele.nbChiffresMalPlaces(combJoueur));
                    this.vue.activerBtnValider(false);

                    if(Arrays.equals(combJoueur, combOrdi)) {
                        this.vue.afficherCombinaisonOrdinateur(this.modele.getCombinaison());
                        this.setEtat(Etat.FIN);
                    } else {
                        this.vue.desactiverCombinaison(this.essai);
                        this.vue.activerCombinaison(this.essai + 1);
                        this.setEtat(Etat.DEBUT_COMBINAISON);
                    }
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

    private void pickColor(JButton jb) {
        this.colorPicked = jb.getBackground();
    }

    private void setEtat(Etat e) {
        this.etat = e;
    }

}
