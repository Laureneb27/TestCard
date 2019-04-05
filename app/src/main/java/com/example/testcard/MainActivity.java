package com.example.testcard;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testcard.models.Card;
import com.example.testcard.models.Deck;
import com.example.testcard.models.Memory;
import com.example.testcard.views.CardView;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Timer;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Deck deck;
    FlexboxLayout flexboxLayout;
    int paireFind;
    TextView textScore;
    Chronometer timer;
    ArrayList<String> scoresList;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (Chronometer) findViewById(R.id.timer);
        timer.start();


        Intent intent = getIntent();
        int nbPaire = intent.getIntExtra("nbPaire",26);

        extras =intent.getExtras();
        if (extras.containsKey("ListScore"))
        {
            scoresList = intent.getStringArrayListExtra("ListScore");
        }

        deck = new Deck(nbPaire);
        deck.shuffle();
        System.out.println("****"+deck.count());
        flexboxLayout = (FlexboxLayout) findViewById(R.id.FlexboxLayoutCards);

        System.out.println("deck : "+deck.count());
        while (deck.count() > 0) {
            Card c = deck.draw();
            CardView cardView = new CardView(this, c);
            cardView.setOnClickListener(this);
            flexboxLayout.addView(cardView);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        int nbPaire = intent.getIntExtra("nbPaire",26);

        int nbCartes =flexboxLayout.getFlexItemCount();

        textScore = (TextView) findViewById(R.id.textScore);
        Memory memory = new Memory();

        int nbCardVisible=0;

        ArrayList<CardView> CardVisible = new ArrayList<>();
        for (int i=0;i<flexboxLayout.getFlexItemCount();i++)
        {
            CardView card = (CardView) flexboxLayout.getFlexItemAt(i);
            if (card.isTurn()==false&& card.isFind()==false)
            {
                nbCardVisible++;
                CardVisible.add(card);
            }
        }
        if (nbCardVisible ==2)
        {
            CardVisible.get(0).setTurn(true);
            CardVisible.get(1).setTurn(true);
        }
        CardView cardView = (CardView) v;
        cardView.setTurn(false);

        nbCardVisible=0;

        CardVisible.clear();
        for (int i=0;i<flexboxLayout.getFlexItemCount();i++)
        {
            CardView card = (CardView) flexboxLayout.getFlexItemAt(i);
            if (card.isTurn()==false)
            {
                if (card.isTurn()==false&& card.isFind()==false)
                {
                    nbCardVisible++;
                    CardVisible.add(card);
                }
            }
        }

        if (nbCardVisible ==2)
        {
            if (memory.isSame(CardVisible.get(0),CardVisible.get(1))==true)
            {
                System.out.println("Memes cartes");

                CardVisible.get(0).setFind(true);
                CardVisible.get(1).setFind(true);
                paireFind++;
                textScore.setText("Nombre de paire trouvé : "+paireFind);
            }
        }
        if (nbPaire == paireFind)
        {
            timer.stop();
            long elapsedMillis = SystemClock.elapsedRealtime() - timer.getBase();
            System.out.println(elapsedMillis);

            Intent scoreActivity = new Intent(MainActivity.this, ScoreActivity.class);
            Bundle b = new Bundle();
            b.putInt("nbPaire", paireFind);
            b.putLong("timer",Math.round(elapsedMillis/1000));

            if (extras.containsKey("ListScore"))
            {
                b.putStringArrayList("ListScore",scoresList);
            }

            scoreActivity.putExtras(b);
            startActivity(scoreActivity);

        }
    }
}
