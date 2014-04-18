package com.gramer.drools.decision;

import lombok.Data;

@Data
public class QuickQuoteInputProfile {

    private String state = null;
    private String gender = null;
    private int age = 0;
    private Double faceAmount = null;
    private boolean adverseDiagnosis = false;
    private SmokerProfile smokingProf = null;

}
