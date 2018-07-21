package com.example.pallav.paypalpaymentintegration;

import java.io.Serializable;

/**
 * Created by Pallav Johari on 7/18/2018.
 */
@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class PaymentPOJO implements Serializable {
    private String id;
    private String status;
    private String time;
    private String intent ;

    public PaymentPOJO() {
        this.id = id;
        this.status = status;
        this.time = time;
        this.intent = intent;
    }


    public PaymentPOJO(String id, String status, String time, String intent) {
        this.id = id;
        this.status = status;
        this.time = time;
        this.intent = intent;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }



    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getIntent() {
        return intent;
    }


}
