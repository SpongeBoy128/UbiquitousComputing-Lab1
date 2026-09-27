package com.example.kidsguessgame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int secretNumber;
    private int guessCount;

    private EditText guessInput;
    private Button guessButton;
    private TextView resultText;
    private TextView guessCountText;
    private Button playAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guessInput = findViewById(R.id.guessInput);
        guessButton = findViewById(R.id.guessButton);
        resultText = findViewById(R.id.resultText);
        guessCountText = findViewById(R.id.guessCountText);
        playAgainButton = findViewById(R.id.playAgainButton);

        startNewGame();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleGuess();
            }
        });

        playAgainButton.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                startNewGame();
            }
        });
    }

    private void startNewGame() {
        secretNumber = new Random().nextInt(30) + 1;
        guessCount = 0;

        resultText.setText("");
        guessCountText.setText("");
        guessInput.setText("");
        playAgainButton.setVisibility(View.GONE);
        guessButton.setEnabled(true);
    }

    private void handleGuess() {
        String guessStr = guessInput.getText().toString().trim();

        if (guessStr.isEmpty()) {
            resultText.setText("Please enter a number.");
            return;
        }

        int guess;
        try {
            guess = Integer.parseInt(guessStr);
        } catch (NumberFormatException e) {
            resultText.setText("Enter a valid number.");
            return;
        }

        if (guess < 1 || guess > 30) {
            resultText.setText("Number needs to be between 1 and 30.");
            return;
        }

        guessCount++;

        if (guess == secretNumber) {
            resultText.setText("Correct! The number was " + secretNumber);
            guessCountText.setText("You guessed it in " + guessCount + " tries.");
            playAgainButton.setVisibility(View.VISIBLE);
            guessButton.setEnabled(false);
        } else if (guess < secretNumber) {
                resultText.setText("Higher!");
        } else {
            resultText.setText("Lower!");
        }
    }
}