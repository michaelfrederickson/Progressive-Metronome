package com.github.michaelfredeickson.progressivemetronome.practicesection;

import java.io.Serializable;

public class PracticeSectionMetronome implements Serializable {

    private int startingTempo;
    private int endingTempo;
    private int numBeatsInMeasure;
//    private ToneGenerator beep;
//    private ToneGenerator firstBeep;


    private int countdown;
    private double subDivision;
    private int accent;
    private int timeSignatureNumerator;
    private int timeSignatureDenominator;

    private int passageLength;
    private int increase;
    private int repetitions;



    public PracticeSectionMetronome() {
        startingTempo = 120;
        endingTempo = 140;
        numBeatsInMeasure = 4;
//        beep = new ToneGenerator(AudioManager.FLAG_PLAY_SOUND, 70);
//        firstBeep = new ToneGenerator(AudioManager.FLAG_PLAY_SOUND, 70);

        countdown = 4;
        subDivision = 1;
        accent = 1;

        passageLength = 4;
        repetitions = 2;
        increase = 5;
    }

    public int getStartingTempo() {
        return startingTempo;
    }

    public void setStartingTempo(int tempo) {
        this.startingTempo = tempo;
    }

    public int getEndingTempo() {
        return endingTempo;
    }

    public void setEndingTempo(int tempo) {
        this.endingTempo = tempo;
    }

    public void setNumBeatsInMeasure(int numBeatsInMeasure){

        this.numBeatsInMeasure = numBeatsInMeasure;

    }

    public int getNumBeatsInMeasure() {
        return numBeatsInMeasure;
    }

    public void upNumBeatsInMeasure() {
        if (this.numBeatsInMeasure < 8) {
            this.numBeatsInMeasure++;
        }
    }

    public void downNumBeatsInMeasure() {
        if (this.numBeatsInMeasure > 2) {
            this.numBeatsInMeasure--;

        }
    }

//    public ToneGenerator getBeep() {
//        return beep;
//    }
//
//    public void setBeep(ToneGenerator beep) {
//        this.beep = beep;
//    }
//
//
//
//    public ToneGenerator getFirstBeep() {
//        return firstBeep;
//    }
//
//    public void setFirstBeep(ToneGenerator firstBeep) {
//        this.firstBeep = firstBeep;
//    }

    public int getPassageLength() {
        return passageLength;
    }

    public void setPassageLength(int passageLength) {
        this.passageLength = passageLength;
    }

//    public void changePassageLength(int passageLength) {this.passageLength = passageLength;}

    public int getIncrease() {
        return increase;
    }

    public void setIncrease(int increase) {
        this.increase = increase;
    }

//    public void changeIncrease(int increase) { this.increase = increase;}

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

//    public void changeRepetitions(int repetitions) { this.repetitions = repetitions; }

    public int getCountdown() {return countdown;}

    public void setCountdown(int countdown) { this.countdown = countdown; }

    public void setSubDivision(double subDivision){

        this.subDivision = subDivision;

    }

    public double getSubDivision(){

        return subDivision;

    }

    public void setAccent(int accent){

        this.accent = accent;

    }

    public int getAccent(){

        return accent;

    }

    public void setTimeSignatureNumerator(int timeSignatureNumerator){

        this.timeSignatureNumerator = timeSignatureNumerator;

    }

    public int getTimeSignatureNumerator(){

        return this.timeSignatureNumerator;

    }

    public void setTimeSignatureDenominator(int timeSignatureDenominator){

        this.timeSignatureDenominator = timeSignatureDenominator;

    }

    public int getTimeSignatureDenominator(){

        return this.timeSignatureDenominator;

    }




}
