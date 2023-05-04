package sg.edu.rp.c346.id22016635.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText enterAmount;

    EditText enterPax;

    ToggleButton serviceCharge;

    ToggleButton GST;

    EditText enterDiscount;

    Button split;

    Button reset;

    TextView finalBill;

    TextView payPerPax;

    RadioGroup payMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        enterAmount = findViewById(R.id.amountInput);
        enterPax = findViewById(R.id.paxInput);
        serviceCharge = findViewById(R.id.svsInput);
        GST = findViewById(R.id.gstInput);
        enterDiscount = findViewById(R.id.discountInput);
        split = findViewById(R.id.splitButton);
        reset = findViewById(R.id.resetButton);
        finalBill = findViewById(R.id.totalBill);
        payPerPax = findViewById(R.id.eachPays);
        payMethod = findViewById(R.id.paymentRadiogrp);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //service charge and GST//
                payPerPax.setText("");
                if (enterAmount.getText().length() != 0 && enterPax.getText().length() != 0 && enterDiscount.getText().length() != 0 && Integer.parseInt(enterAmount.getText().toString()) != 0 && Integer.parseInt(enterPax.getText().toString()) != 0) {
                    finalBill.setTextColor(getResources().getColor(R.color.black));

                    double finalAmt = 0;
                    if (serviceCharge.isChecked() && GST.isChecked()) {
                        finalAmt = Double.parseDouble(enterAmount.getText().toString()) * 1.17;
                    } else if (!serviceCharge.isChecked() && GST.isChecked()) {
                        finalAmt = Double.parseDouble(enterAmount.getText().toString()) * 1.07;
                    } else if (serviceCharge.isChecked() && !GST.isChecked()) {
                        finalAmt = Double.parseDouble(enterAmount.getText().toString()) * 1.10;
                        finalBill.setText("1.10");
                    } else {
                        finalAmt = Double.parseDouble(enterAmount.getText().toString());
                    }


                    //discount//

                    if (enterDiscount.getText().length() != 0) {
                        finalAmt = finalAmt * ((1 - Double.parseDouble(enterDiscount.getText().toString()) / 100));
                    }


                    //payment method//
                    int payChoice = payMethod.getCheckedRadioButtonId();
                    String choice = "";
                    if (payChoice == R.id.payNowRadioInput) {
                        choice = " via PayNow to 912345678";
                    } else {
                        choice = " in Cash";

                    }
                    //reset//


                    //end//
                    int numPax = Integer.parseInt(enterPax.getText().toString());
                    finalBill.setText(String.format("Total bill: $%.2f", finalAmt));
                    payPerPax.setText(String.format("Each pays: $%.2f%s", finalAmt / numPax, choice));

                }else{
                    finalBill.setText("Error: Requirements not filled");
                    finalBill.setTextColor(getResources().getColor(R.color.red));
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterAmount.setText("");
                enterPax.setText("");
                serviceCharge.setChecked(false);
                GST.setChecked(false);
                enterDiscount.setText("");
                finalBill.setText("");
                payPerPax.setText("");
                payMethod.check(R.id.cashRadioInput);
            }
        });
    }
}







