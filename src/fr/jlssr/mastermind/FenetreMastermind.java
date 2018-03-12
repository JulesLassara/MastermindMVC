package fr.jlssr.mastermind;

import fr.jlssr.mastermind.controleur.ControleurMenu;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FenetreMastermind extends JFrame {

    private VueMastermind vue;
    private JMenuItem itemNbCouleurs, itemTailleCombinaison;

    public FenetreMastermind() {

        this.vue = new VueMastermind();

        this.setTitle("Mastermind v1.1");
        this.setLayout(new GridLayout(1, 1));
        this.add(this.vue);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initMenu();
        this.pack();
        this.setVisible(true);
    }

    private void initMenu() {
        JMenuBar menu = new JMenuBar();
        ControleurMenu controleur = new ControleurMenu(this);

        //MENU JEU
        JMenu jeu = new JMenu("Jeu");
        JMenuItem rejouer = new JMenuItem("Rejouer");
        JMenuItem sauvegarder = new JMenuItem("Sauvegarder");
        JMenuItem restaurer = new JMenuItem("Restaurer");
        JMenuItem quitter = new JMenuItem("Quitter");
        jeu.add(rejouer);
        jeu.add(sauvegarder);
        jeu.add(restaurer);
        jeu.addSeparator();
        jeu.add(quitter);

        //MENU LISTENERS
        rejouer.addActionListener(controleur);
        sauvegarder.addActionListener(controleur);
        restaurer.addActionListener(controleur);
        quitter.addActionListener(controleur);

        //MENU OPTIONS
        JMenu options = new JMenu("Options");
        this.itemNbCouleurs = new JMenu("nombre de couleurs");
        this.itemTailleCombinaison = new JMenu("taille combinaison");
        for(int i = 2; i <= 10; i++) {
            this.itemNbCouleurs.add(new JMenuItem(""+i));
            this.itemTailleCombinaison.add(new JMenuItem(""+i));
        }
        options.add(this.itemNbCouleurs);
        options.add(this.itemTailleCombinaison);

        //AJOUT DES MENUS SUR LA BAR
        menu.add(jeu);
        menu.add(options);
        this.setJMenuBar(menu);
    }

    private void changerItemNbCouleurs(JMenuItem item) {
        this.itemNbCouleurs.setBackground(null);
        this.itemNbCouleurs = item;
    }

    private void changerItemTailleCombinaison(JMenuItem item) {
        this.itemTailleCombinaison.setBackground(null);
        this.itemTailleCombinaison = item;
    }

    public void creerNouvelleVueMastermind() {
        VueMastermind vue = new VueMastermind();
        this.reloadVue(vue);
    }

    public void restaurerVueMastermindFichier(String nomFichier) {
        VueMastermind vue = null;

        FileInputStream fileIn = null;
        ObjectInputStream objIn = null;

        try {
            fileIn = new FileInputStream(nomFichier + ".save");
            objIn = new ObjectInputStream(fileIn);
            vue = (VueMastermind) objIn.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fileIn != null) {
                try {
                    fileIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (objIn != null) {
                try {
                    objIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.reloadVue(vue);
    }

    public void sauvegarderVueMastermind(String nomFichier) {
        FileOutputStream fileOut = null;
        ObjectOutputStream objOut = null;

        try {
            fileOut = new FileOutputStream(nomFichier + ".save");
            objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(this.vue);

            System.out.println("Done");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (objOut != null) {
                try {
                    objOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void reloadVue(VueMastermind vue) {
        this.remove(this.vue);
        this.vue = vue;
        this.add(this.vue);
        this.pack();
        this.repaint();
    }

}
