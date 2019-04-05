package com.example.testcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    EditText nbPaire;
    Button buttonJouer;
    ArrayList<String> scoresList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Intent intent = getIntent();
        if (intent.getExtras()!=null)
        {
            scoresList = intent.getStringArrayListExtra("ListScore");
        }


        nbPaire = (EditText) findViewById(R.id.textNbPaire);
        buttonJouer = (Button) findViewById(R.id.buttonJouer);
        buttonJouer.setEnabled(false);

        nbPaire.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (Integer.parseInt(nbPaire.getText().toString()) <=2 || Integer.parseInt(nbPaire.getText().toString()) <=26 )
                {
                    buttonJouer.setEnabled(true);
                }
                else if (nbPaire.getText().toString() == "")
                {
                    nbPaire.setText(0);
                }
                else
                {
                    Toast.makeText(HomeActivity.this, "Veuillez entrer un nombre entre 2 et 26.",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(HomeActivity.this, MainActivity.class);
                Bundle b = new Bundle();
                b.putInt("nbPaire", Integer.parseInt(nbPaire.getText().toString()));

                if (intent.getExtras() !=null)
                {
                    b.putStringArrayList("ListScore",scoresList);
                }
                mainActivity.putExtras(b);
                startActivity(mainActivity);

            }
        });
    }
}
