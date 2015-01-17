package com.github.michaelfredeickson.progressivemetronome;

import java.io.Serializable;

public class IncrementMetronome implements Serializable {

    private int startingTempo;
    private int endingTempo;
    private int numBeatsInMeasure;
//    private ToneGenerator beep;
//    private ToneGenerator firstBeep;
    private int passageLength;
    private int increase;
    private int repetitions;
    private int countdown;
    private int subDivision;
    private int accent;

    public IncrementMetronome() {
        startingTempo = 120;
        endingTempo = 140;
        numBeatsInMeasure = 4;
//        beep = new ToneGenerator(AudioManager.FLAG_PLAY_SOUND, 70);
//        firstBeep = new ToneGenerator(AudioManager.FLAG_PLAY_SOUND, 70);
        passageLength = 8;
        increase = 5;
        repetitions = 4;
        countdown = 0;
        subDivision = 1;
        accent = 1;
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

    public void setSubDivision(int subDivision){

        this.subDivision = subDivision;

    }

    public int getSubDivision(){

        return subDivision;

    }

    public void setAccent(int accent){

        this.accent = accent;

    }

    public int getAccent(){

        return accent;

    }

}
