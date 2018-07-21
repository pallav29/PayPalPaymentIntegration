package com.example.pallav.paypalpaymentintegration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import java.math.BigDecimal;





public class MainActivity extends AppCompatActivity{

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId("AQcaj10ZHaVnYdPPW1ckfjtW6nCPFjr5T3cGhb0x2tbDlf9qliNJRJR6mGWENR9c_9lURicE6FxqnizA");

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.paypal_button);
    }

    public void beginPayment(View view){
        Intent serviceConfig = new Intent(this, PayPalService.class);
        serviceConfig.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(serviceConfig);

        PayPalPayment payment = new PayPalPayment(new BigDecimal("5.65"), "USD", "My Awesome Item", PayPalPayment.PAYMENT_INTENT_SALE);

        Intent paymentConfig = new Intent(this, PaymentActivity.class);
        paymentConfig.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config); //send the same configuration for restart resiliency
        paymentConfig.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(paymentConfig, 0);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
        if (resultCode == Activity.RESULT_OK){
            if (confirm != null){
                try {
                    Log.i("sampleapp", confirm.toJSONObject().toString(4));

                    // TODO: send 'confirm' to your server for verification.
                    // see https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
                    // for more details.

                } catch (JSONException e) {
                    Log.e("sampleapp", "an extremely unlikely failure occurred: ", e);
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("sampleapp", "The user canceled.");
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("sampleapp", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
        }

        PaymentPOJO paymentPOJO = new PaymentPOJO();
        paymentPOJO.setId(confirm.getProofOfPayment().getPaymentId());
        paymentPOJO.setIntent(confirm.getProofOfPayment().getIntent());
        paymentPOJO.setStatus(confirm.getProofOfPayment().getState());
        paymentPOJO.setTime(confirm.getProofOfPayment().getCreateTime());
        Intent intent = new Intent(MainActivity.this, PaymentConfirmationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("jsonObject", paymentPOJO);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onDestroy(){
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
}