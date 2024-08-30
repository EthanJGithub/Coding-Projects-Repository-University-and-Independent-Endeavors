package com.example.finalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    GraphicsView graphicsView;
    EditText numDisksEditText;
    Button resetButton, solveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphicsView = findViewById(R.id.graphicsView);
        numDisksEditText = findViewById(R.id.numDisksEditText);
        resetButton = findViewById(R.id.resetButton);
        solveButton = findViewById(R.id.solveButton);

        resetButton.setOnClickListener(v -> {
            int numDisks = Integer.parseInt(numDisksEditText.getText().toString());
            graphicsView.setupRods(numDisks);
        });

        solveButton.setOnClickListener(v -> {
            graphicsView.startSolving();
        });
    }
}
