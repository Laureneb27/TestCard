package com.example.testcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    ListView listeScore;
    Button newGame;
    ArrayList<String> scoreList;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        listeScore = (ListView) findViewById(R.id.listeScore);
        newGame = (Button) findViewById(R.id.newGame);




        Intent intent = getIntent();
        int nbPaire = intent.getIntExtra("nbPaire",26);
        long timer = intent.getLongExtra("timer",00);


        extras =intent.getExtras();
        if (extras.containsKey("ListScore"))
        {
            scoreList = intent.getStringArrayListExtra("ListScore");
            scoreList.add(nbPaire+" paires - "+timer+"s");
        }
        else
        {
            scoreList = new ArrayList<>();
            scoreList.add(nbPaire+" paires - "+timer+"s");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scoreList);
        listeScore.setAdapter(arrayAdapter);


        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                System.out.println("***"+scoreList.size());
                Intent homeActivity = new Intent(ScoreActivity.this, HomeActivity.class);
                Bundle b = new Bundle();
                b.putStringArrayList("ListScore",scoreList);
                homeActivity.putExtras(b);

                startActivity(homeActivity);
            }
        });


    }
}
