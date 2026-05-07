package com.arstw.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "";
    private String operator = "";
    private double firstValue = 0;
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Number buttons
        setNumberButton(R.id.btn0, "0");
        setNumberButton(R.id.btn1, "1");
        setNumberButton(R.id.btn2, "2");
        setNumberButton(R.id.btn3, "3");
        setNumberButton(R.id.btn4, "4");
        setNumberButton(R.id.btn5, "5");
        setNumberButton(R.id.btn6, "6");
        setNumberButton(R.id.btn7, "7");
        setNumberButton(R.id.btn8, "8");
        setNumberButton(R.id.btn9, "9");

        // Operator buttons
        setOperatorButton(R.id.btnAdd, "+");
        setOperatorButton(R.id.btnSubtract, "-");
        setOperatorButton(R.id.btnMultiply, "*");
        setOperatorButton(R.id.btnDivide, "/");

        // Other buttons
        Button btnEquals = findViewById(R.id.btnEquals);
        btnEquals.setOnClickListener(v -> calculateResult());

        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(v -> clear());

        Button btnDecimal = findViewById(R.id.btnDecimal);
        btnDecimal.setOnClickListener(v -> {
            if (!currentInput.contains(".")) {
                currentInput += ".";
                display.setText(currentInput);
            }
        });
    }

    private void setNumberButton(int buttonId, String number) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            if (isNewOperation) {
                currentInput = number;
                isNewOperation = false;
            } else {
                currentInput += number;
            }
            display.setText(currentInput);
        });
    }

    private void setOperatorButton(int buttonId, String op) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                firstValue = Double.parseDouble(currentInput);
                operator = op;
                isNewOperation = true;
            }
        });
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondValue = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstValue + secondValue;
                    break;
                case "-":
                    result = firstValue - secondValue;
                    break;
                case "*":
                    result = firstValue * secondValue;
                    break;
                case "/":
                    if (secondValue != 0) {
                        result = firstValue / secondValue;
                    } else {
                        display.setText("Error");
                        currentInput = "";
                        operator = "";
                        return;
                    }
                    break;
            }

            currentInput = String.valueOf(result);
            display.setText(currentInput);
            operator = "";
            isNewOperation = true;
        }
    }

    private void clear() {
        currentInput = "";
        operator = "";
        firstValue = 0;
        isNewOperation = true;
        display.setText("0");
    }
}
