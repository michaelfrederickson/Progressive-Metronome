package com.github.michaelfredeickson.progressivemetronome.metronome;

import android.media.AudioManager;
import android.media.ToneGenerator;

public class Metronome {

    private int tempo;
    private int measure;
    private ToneGenerator beep;
    private ToneGenerator firstBeep;
    private int timeSignatureNumerator;
    private int timeSignatureDenominator;
    private int accent;
    private int subdivision;

    public Metronome() {
        tempo = 120;
        measure = 4;
        beep = new ToneGenerator(AudioManager.FLAG_PLAY_SOUND, 10);
        firstBeep = new ToneGenerator(AudioManager.FLAG_PLAY_SOUND, 10);
        timeSignatureNumerator = 4;
        timeSignatureDenominator = 4;
        subdivision = 1;
        accent = 1;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getMeasure() {
        return measure;
    }

    public void upMeasure() {
        if (this.measure < 8) {
            this.measure++;
        }
    }

    public void downMeasure() {
        if (this.measure > 2) {
            this.measure--;

        }
    }

    public ToneGenerator getBeep() {
        return beep;
    }

    public void setBeep(ToneGenerator beep) {
        this.beep = beep;
    }

    public ToneGenerator getFirstBeep() {
        return firstBeep;
    }

    public void setFirstBeep(ToneGenerator firstBeep) {
        this.firstBeep = firstBeep;
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

    public void setAccent(int accent){

        this.accent = accent;

    }

    public int getAccent(){

        return this.accent;

    }

    public void setSubdivision(int subdivision){

        this.subdivision = subdivision;

    }

    public int getSubdivision(){

        return this.subdivision;

    }
}
