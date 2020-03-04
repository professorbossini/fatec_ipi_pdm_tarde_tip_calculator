package com.example.fatec_ipi_pdm_tarde_tip_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TextView amountTextView;
    private TextView percentTextView;
    private TextView totalTextView;
    private TextView tipTextView;

    private EditText amountEditText;

    private SeekBar percentSeekBar;

    private double billAmount = 0.0;
    private double percent = 0.15;
    private NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private NumberFormat percentFormat =
            NumberFormat.getPercentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountTextView = findViewById(R.id.amountTextView);
        percentTextView = findViewById(R.id.percentTextView);
        totalTextView = findViewById(R.id.totalTextView);
        tipTextView = findViewById(R.id.tipTextView);
        amountEditText = findViewById(R.id.amountEditText);
        percentSeekBar = findViewById(R.id.percentSeekBar);

        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    billAmount = Double.parseDouble(s.toString()) / 100;
                    double tip = billAmount * percent;
                    double total = tip + billAmount;
                    atualizarInterfaceGrafica(billAmount, tip, total);
                }catch (NumberFormatException e){
                    atualizarInterfaceGrafica(0, 0, 0);
                }

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percent = progress / 100D;
                percentTextView.setText(percentFormat.format(percent));
                double tip = percent * billAmount;
                double total = billAmount + tip;
                atualizarInterfaceGrafica(billAmount, tip, total);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    private void atualizarInterfaceGrafica (double amount, double tip, double total){
        amountTextView.setText(currencyFormat.format(amount));
        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
    }
}
