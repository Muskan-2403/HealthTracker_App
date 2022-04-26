package com.ultimate.infits;

import java.io.Serializable;

public class UpcomingConsultations implements Serializable {

    private String consultation_date;
    private String consultation_time;
    private String consultation_patient;
    private String consultation_patient_image;
    //private int product_price;
   // private int product_url;

    public UpcomingConsultations(String date, String time, String img, String name) {
        this.consultation_date=date;
        this.consultation_time=time;
        this.consultation_patient=name;
        this.consultation_patient_image=img;

    }

    public String getConsultation_date() {
        return consultation_date;
    }

    public void setConsultation_date(String consultation_date) {
        this.consultation_date = consultation_date;
    }

    public String getConsultation_time() {
        return consultation_time;
    }

    public void setConsultation_time(String consultation_time) {
        this.consultation_time = consultation_time;
    }

    public String getConsultation_patient() {
        return consultation_patient;
    }

    public void setConsultation_patient(String consultation_patient) {
        this.consultation_patient = consultation_patient;
    }

    public String getConsultation_patient_image() {
        return consultation_patient_image;
    }

    public void setConsultation_patient_image(String consultation_patient_image) {
        this.consultation_patient_image = consultation_patient_image;
    }
}
