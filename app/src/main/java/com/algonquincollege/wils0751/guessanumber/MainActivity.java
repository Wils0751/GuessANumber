/**
 * {This is a guessing game the game will generate a number between 1-1000 and a user must guess this number withing 10 trys.
 * It will display to the user if the guess is too high or to low. It also will display your win and if you need to reset your game
 * or not. }
 *
 * @author {Wils0751}
 */
package com.algonquincollege.wils0751.guessanumber;

import android.app.DialogFragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String ABOUT_DIALOG_TAG;
    private int theNumber = 0;
    private int min = 1;
    private int max = 1000;
    private int count = 0;

    static {
        ABOUT_DIALOG_TAG = "About Dialog";

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GenerateRandomNumber();
        Button guessbtn = (Button) findViewById(R.id.guessBtn);
        Button resetbtn = (Button) findViewById(R.id.resetBtn);
        Log.i("myInt", "the number is " + (theNumber));


        guessbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                count++;
                EditText Guess = (EditText) findViewById(R.id.userGuess);

                String Guess1 = Guess.getText().toString();
                if(Guess1.isEmpty()){
                    Guess.setError("Enter a number between 1 and 1000");
                    Guess.requestFocus();
                }
                else {
                    int userGuess = Integer.parseInt(Guess1);

                    if (count > 10) {
                        Toast.makeText(getApplicationContext(), "Please reset ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (userGuess > max || userGuess < min) {
                        Guess.setError("Enter a number between 1 and 1000");
                        Guess.requestFocus();

                        return;
                    }

                    if (userGuess == theNumber && count <= 5) {
                        Toast.makeText(getApplicationContext(), "Superior win!", Toast.LENGTH_SHORT).show();

                        return;
                    }
                    if (userGuess == theNumber && count <= 10 && !(count <= 5)) {
                        Toast.makeText(getApplicationContext(), "Excellent Win!", Toast.LENGTH_SHORT).show();

                        return;
                    }

                    if (userGuess > theNumber) {
                        Toast.makeText(getApplicationContext(), "You guessed to high ", Toast.LENGTH_SHORT).show();

                        return;
                    }
                    if (userGuess < theNumber) {
                        Toast.makeText(getApplicationContext(), "You guessed to low ", Toast.LENGTH_SHORT).show();

                        return;
                    }


                }
            }
        });
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GenerateRandomNumber();
                count = 0;
                Toast.makeText(getApplicationContext(), " Reset Game", Toast.LENGTH_SHORT).show();
                Log.i("myInt", "the number is" + (theNumber));
            }
        });

        resetbtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(getApplicationContext(), "Reset the game the number was: " + (theNumber), Toast.LENGTH_SHORT).show();
                GenerateRandomNumber();
                return true;
            }
        });
    }

    public void GenerateRandomNumber() {

        Random r = new Random();
        theNumber = r.nextInt(max - min) + min;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}











