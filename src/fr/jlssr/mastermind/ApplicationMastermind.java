package fr.jlssr.mastermind;

import java.util.Arrays;

public class ApplicationMastermind {

    public static void main(String[] args) {
        ModeleMastermind mm = new ModeleMastermind(4, 6);
        mm.genererCombinaison();
        int[] comb = mm.getCombinaison();
        System.out.println("Combinaison : " + Arrays.toString(comb));
        System.out.println("toString : " + mm.toString());

        int[] testcomb = {1, 2, 3, 4};
        System.out.println("Nb chiffres bien placés : " + mm.nbChiffresBienPlaces(testcomb));
        System.out.println("Nb chiffres mal placés : " + mm.nbChiffresMalPlaces(testcomb));

        new FenetreMastermind();

    }

}
