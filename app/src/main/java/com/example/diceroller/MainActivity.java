package com.example.diceroller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Toast toastie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toastie = Toast.makeText(this, "Nothing!", Toast.LENGTH_SHORT);

        final Button rollButton = findViewById(R.id.button);

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDie(true);
            }
        });

        rollDie(false);
    }

    private void rollDie(boolean showToast) {
        // Create and roll a new die with 6 sides
        final Dice myDie = new Dice(6);
        final int dieRoll = myDie.roll();

        final TextView resultTextView = findViewById(R.id.textView2);

        int drawableResource = 0;
        switch (dieRoll) {
            case 1: drawableResource = 1; break;
            case 2: drawableResource = 2; break;
            case 3: drawableResource = 3; break;
            case 4: drawableResource = 4; break;
            case 5: drawableResource = 5; break;
            default: drawableResource = 6;
        }

        // Display the result
        resultTextView.setText(Integer.toString(drawableResource));

        // Show toast if asked for
        if (showToast) {
            toastie.cancel();

            toastie = Toast.makeText(this, dieRoll + " rolled!", Toast.LENGTH_SHORT);

            toastie.show();
        }
    }
}

class Dice {
    final int numSides;

    Dice(int numSides) { this.numSides = numSides; }

    public int roll() {
        return (int)Math.floor(1 + Math.random() * numSides);
    }
}