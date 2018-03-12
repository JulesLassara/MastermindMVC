package fr.jlssr.mastermind;

import fr.jlssr.mastermind.controleur.ControleurMastermind;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.Arrays;

public class VueMastermind extends JPanel {

    /**
     * couleurs utilisees dans l'application
     */
    public static final Color[] couleurs = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA};
    /**
     * tableau des champs contenant le nombre de couleurs bien
     * placees pour chaque combinaison.
     */
    private JTextField[] bPIHM;

    /**
     * tableau des champs contenant les couleurs de la combinaison a
     * trouver.
     */
    private JTextField[] combinaisonOrdiIHM;

    /**
     * tableau des champs contenant le nombre de couleurs mal placees
     * pour chaque combinaison.
     */
    private JTextField[] mPIHM;

    /**
     * tableau des champs contenant les combinaisons de couleurs
     * proposees par l'utilisateur.
     */
    private JButton[][] combinaisonsJoueurIHM;

    /**
     * bouton valider
     */
    private JButton btnValider;

    /**
     * nombre de couleurs possible dans la combinaison a trouver
     */
    private int nbCouleurs;

    /**
     * taille de la combinaison a trouver
     */
    private int taille;

    /**
     * nombre maximum d'essais pour trouver la combinaison de
     * couleurs
     */
    public static final int NBMAX_COMBINAISONS = 10;

    /**
     * ensemble des boutons contenant les couleurs de la palette
     */
    private JButton[] paletteIHM;
    private static long serialVersionUID;

    public VueMastermind() {
        this.nbCouleurs = 6;
        this.taille = 4;
        Color colors[] = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA};

        this.bPIHM = new JTextField[VueMastermind.NBMAX_COMBINAISONS];
        this.combinaisonOrdiIHM = new JTextField[this.taille];
        this.mPIHM = new JTextField[VueMastermind.NBMAX_COMBINAISONS];
        this.combinaisonsJoueurIHM = new JButton[VueMastermind.NBMAX_COMBINAISONS][this.taille];
        this.paletteIHM = new JButton[this.nbCouleurs];

        ControleurMastermind controleur = new ControleurMastermind(this);

        this.initialiserGUI(controleur);
        this.activerCombinaison(0);
    }

    private void initialiserGUI(ControleurMastermind controleur) {

        for(int i = 0; i < VueMastermind.NBMAX_COMBINAISONS; i++) {
            this.bPIHM[i] = new JTextField(JTextField.CENTER);
            this.bPIHM[i].setEditable(false);
            this.mPIHM[i] = new JTextField(JTextField.CENTER);
            this.mPIHM[i].setEditable(false);
            for(int j = 0; j < this.taille; j++) {
                this.combinaisonsJoueurIHM[i][j] = new JButton();
                this.combinaisonsJoueurIHM[i][j].setEnabled(false);
                this.combinaisonsJoueurIHM[i][j].addActionListener(controleur);
            }
        }

        for(int i = 0; i < this.taille; i++) {
            this.combinaisonOrdiIHM[i] = new JTextField();
            this.combinaisonOrdiIHM[i].setEditable(false);
        }

        for(int i = 0; i < this.nbCouleurs; i++) {
            this.paletteIHM[i] = new JButton();
            this.paletteIHM[i].setBackground(VueMastermind.couleurs[i]);
            this.paletteIHM[i].addActionListener(controleur);
        }

        this.setLayout(new BorderLayout());

        // GLOBAL PANEL COULEURS
        JPanel gPanelCouleurs = new JPanel();
        gPanelCouleurs.setLayout(new FlowLayout());

        gPanelCouleurs.add(new JLabel("Couleurs"));

        // Panel Couleurs
        JPanel panCouleurs = new JPanel();
        panCouleurs.setLayout(new GridLayout(1, 6));
        for(JButton jb : this.paletteIHM)
            panCouleurs.add(jb);
        gPanelCouleurs.add(panCouleurs);

        this.add(gPanelCouleurs, BorderLayout.NORTH);

        // GLOBAL PANEL PROPOSITIONS
        JPanel gPanelPropositions = new JPanel();
        gPanelPropositions.setLayout(new GridLayout(10, 2));

        JPanel panLigne;
        JPanel panCorrection;

        for(int i = 0; i < VueMastermind.NBMAX_COMBINAISONS; i++) {

            // Panel ligne proposition
            panLigne = new JPanel();
            panLigne.setLayout(new GridLayout(1, 4));
            for(int j = 0; j < this.combinaisonsJoueurIHM[i].length; j++) {
                panLigne.add(this.combinaisonsJoueurIHM[i][j]);
            }

            // Panel correction
            panCorrection = new JPanel();
            panCorrection.setLayout(new GridLayout(2, 2));
            panCorrection.add(new JLabel("BP", JLabel.CENTER));
            panCorrection.add(new JLabel("MP", JLabel.CENTER));
            panCorrection.add(this.bPIHM[i]);
            panCorrection.add(this.mPIHM[i]);

            gPanelPropositions.add(panLigne);
            gPanelPropositions.add(panCorrection);
        }

        this.add(gPanelPropositions, BorderLayout.CENTER);

        // GLOBAL PANEL REPOSE
        JPanel gPanelReponse = new JPanel();
        gPanelReponse.setLayout(new GridLayout(1, 2));

        // Panel réponse
        JPanel reponse = new JPanel();
        reponse.setLayout(new GridLayout(1, 4));
        for(JTextField jtf : this.combinaisonOrdiIHM)
            reponse.add(jtf);

        gPanelReponse.add(reponse);
        this.btnValider = new JButton("Valider");
        this.activerBtnValider(false);
        this.btnValider.addActionListener(controleur);

        gPanelReponse.add(this.btnValider);

        this.add(gPanelReponse, BorderLayout.SOUTH);
    }

    public void activerCombinaison(int i) {
        for(JButton jb : this.combinaisonsJoueurIHM[i])
            jb.setEnabled(true);
    }

    public void afficherBP(int i, int nbBP) {
        String val = "" + nbBP;
        this.bPIHM[i].setText(val);
    }

    public void afficherCombinaisonOrdinateur(int[] tableauCouleurs) {
        for(int i = 0; i < tableauCouleurs.length; i++) {
            this.combinaisonOrdiIHM[i].setBackground(this.chiffreEnCouleur(tableauCouleurs[i]));
        }
    }

    public void afficherMP(int i, int nbMP) {
        String val = "" + nbMP;
        this.mPIHM[i].setText(val);
    }

    public boolean appartientCombinaison(JButton b, int i) {
        return Arrays.asList(this.combinaisonsJoueurIHM[i]).contains(b);
    }

    public boolean appartientPalette(JButton b) {
        return Arrays.asList(this.paletteIHM).contains(b);
    }

    public Color chiffreEnCouleur(int i) {
        return VueMastermind.couleurs[i];
    }

    public boolean combinaisonComplete(int i) {
        for(JButton jb : this.combinaisonsJoueurIHM[i]) {
            if(jb.getBackground() instanceof ColorUIResource) return false;
        }
        return true;
    }

    public int[] combinaisonEnEntiers(int i) {
        int[] vals = new int[this.combinaisonsJoueurIHM[i].length];
        for(int j = 0; j < this.combinaisonsJoueurIHM[i].length; j++) {
            vals[j] = Arrays.asList(VueMastermind.couleurs).indexOf(this.combinaisonsJoueurIHM[i][j].getBackground());
        }

        return vals;
    }

    public static int couleurEnChiffre(Color c) {
        return Arrays.asList(VueMastermind.couleurs).indexOf(c);
    }

    public void desactiverCombinaison(int i) {
        for(JButton jb : this.combinaisonsJoueurIHM[i])
            jb.setEnabled(false);
    }

    public JButton getBtnValider() {
        return this.btnValider;
    }

    public void activerBtnValider(boolean b) {
        this.btnValider.setEnabled(b);
    }

    public int getNbCouleurs() {
        return this.nbCouleurs;
    }

    public int getTaille() {
        return this.taille;
    }

}
