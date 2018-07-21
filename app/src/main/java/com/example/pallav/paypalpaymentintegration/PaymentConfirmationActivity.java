package com.example.pallav.paypalpaymentintegration;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;



public class PaymentConfirmationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);

        if (getIntent().getExtras().getSerializable("jsonObject") != null) {

            PaymentPOJO confirmObject = (PaymentPOJO) getIntent().getExtras().getSerializable("jsonObject");

            String create_time = confirmObject.getTime();
            String id = confirmObject.getId();
            String intent2 = confirmObject.getIntent();
            String status = confirmObject.getStatus();

            TableLayout table = (TableLayout) findViewById(R.id.main_table);

            table.setStretchAllColumns(true);
            table.setShrinkAllColumns(true);

            TableRow idRow = new TableRow(this);
            TableRow statusRow = new TableRow(this);
            TableRow approvalTimeRow = new TableRow(this);
            TableRow approvalIntentRow = new TableRow(this);


///////////////////////ID/////////////////////////////////////////////////
            TextView idRowText = new TextView(this);
            idRowText.setText("Approval ID: ");
            idRowText.setTypeface(Typeface.SERIF, Typeface.BOLD);

            TextView idRowValue = new TextView(this);
            idRowValue.setText(id);
            //idRowValue.setGravity(Gravity.CENTER_HORIZONTAL);

            idRow.addView(idRowText);
            idRow.addView(idRowValue);
            table.addView(idRow, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

///////////////////////STATUS/////////////////////////////////////////////////
            TextView statusRowText = new TextView(this);
            statusRowText.setText("Transaction Status: ");
            statusRowText.setTypeface(Typeface.SERIF, Typeface.BOLD);

            TextView statusRowValue = new TextView(this);
            statusRowValue.setText(status);
            //statusRowValue.setGravity(Gravity.CENTER_HORIZONTAL);

            statusRow.addView(statusRowText);
            statusRow.addView(statusRowValue);
            table.addView(statusRow, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

/////////////////////////TIME/////////////////////////////////////////////////
            TextView timeRowText = new TextView(this);
            timeRowText.setText("Transaction Approval Time: ");
            timeRowText.setTypeface(Typeface.SERIF, Typeface.BOLD);

            TextView timeRowValue = new TextView(this);
            timeRowValue.setText(create_time);
            //statusRowValue.setGravity(Gravity.CENTER_HORIZONTAL);

            approvalTimeRow.addView(timeRowText);
            approvalTimeRow.addView(timeRowValue);
            table.addView(approvalTimeRow, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

/////////////////////////INTENT/////////////////////////////////////////////////
            TextView intentRowText = new TextView(this);
            intentRowText.setText("Transaction Intent: ");
            intentRowText.setTypeface(Typeface.SERIF, Typeface.BOLD);

            TextView intentRowValue = new TextView(this);
            intentRowValue.setText(intent2);
            //intentRowValue.setGravity(Gravity.CENTER_HORIZONTAL);

            approvalIntentRow.addView(intentRowText);
            approvalIntentRow.addView(intentRowValue);
            table.addView(approvalIntentRow, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            Toast.makeText(getApplicationContext(), confirmObject.getStatus().toString(), Toast.LENGTH_SHORT).show();

        }

    }
}
