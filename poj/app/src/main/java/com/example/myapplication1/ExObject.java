package com.example.myapplication1;

import com.google.gson.annotations.SerializedName;

public class ExObject {
    @SerializedName("id")
    public int id;
    @SerializedName("rule")
    public String rule;
    @SerializedName("wordsEng")
    public String [] wordsEng;
    @SerializedName("wordsTrs")
    public String [] wordsTrs;
    @SerializedName("questionwords1")
    public  String [] questionwords1;
    @SerializedName("answerwords1")
    public  String [] answerwords1;
    @SerializedName("correctanswer1")
    public   String [] correctanswer1;
    public ExObject() {

    }

}
