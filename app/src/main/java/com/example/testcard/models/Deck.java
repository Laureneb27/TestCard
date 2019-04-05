package com.example.testcard.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    public Deck(int nbPaire) {
        cards = new ArrayList<Card>(); //plus propre de déclaré dans le constructeur
        int nbCardByColor=13;
        if (nbPaire >=4)
        {
            nbCardByColor = Math.round(nbPaire/4);
        }
        String[][] colors;
        switch (nbPaire)
        {
            case 1:
                colors = new String[][]{{"PIQUE", "1"},{"PIQUE", "1"}};
                //nbCardByColor = 1;
                break;
            case 2:
                colors = new String[][]{{"PIQUE", "1"},{"TREFLE", "1"},{"COEUR", "1"},{"CARREAU", "1"}};
                //colors = new String[]{"PIQUE", "TREFLE", "COEUR", "CARREAU"};
                nbCardByColor = 1;
                break;
            case 3://PAS BON
                colors = new String[][]{{"PIQUE", "1"},{"TREFLE", "1"},{"COEUR", "2"},{"CARREAU", "2"}};
                //colors = new String[]{"PIQUE", "TREFLE", "COEUR"};
                //nbCardByColor = ;
                break;
            case 4:
                colors = new String[][]{{"PIQUE", "2"},{"TREFLE", "2"},{"COEUR", "2"},{"CARREAU", "2"}};
                //colors = new String[]{"PIQUE", "TREFLE", "COEUR", "CARREAU"};
                nbCardByColor = 2;
                break;
            default:
                nbCardByColor = (nbPaire*2);
//                System.out.println("--"+nbCardByColor);
//                System.out.println("-*-"+nbPaire%4);
                if ((nbCardByColor%4)==0)
                {
                    colors = new String[][]{{"PIQUE", nbCardByColor/4+""},{"TREFLE", nbCardByColor/4+""},{"COEUR", nbCardByColor/4+""},{"CARREAU", nbCardByColor/4+""}};
                }
                else
                {
                    nbCardByColor =nbCardByColor/4;
                    colors = new String[][]{{"PIQUE", nbCardByColor+(nbCardByColor%4)/2+""},{"TREFLE", nbCardByColor+(nbCardByColor%4)/2+""},{"COEUR", nbCardByColor+""},{"CARREAU", nbCardByColor+""}};
                }
                break;
        }

        for (int i = 0;i < colors.length; i++)
        {
            System.out.println("--*" + colors[i][0]);
            System.out.println("--*" + colors[i][1]);
              for (int z = Integer.parseInt(colors[i][1]); z > 0; z--)
              {
                switch (z) {
                    case 1:
                        cards.add(new Card(colors[i][0], "A"));
                        break;
                    case 11:
                        cards.add(new Card(colors[i][0], "V"));
                        break;
                    case 12:
                        cards.add(new Card(colors[i][0], "D"));
                        break;
                    case 13:
                        cards.add(new Card(colors[i][0], "R"));
                        break;
                    case 2: //à partir du deux, tous les autres chiffres
                    default:
                        cards.add(new Card(colors[i][0], z + ""));
                        break;
                }
              }
        }

    }


    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw(){
        Card c= cards.get(0);
        cards.remove(c);
        return c;
    }

    public int count(){
        return cards.size();
    }

}
