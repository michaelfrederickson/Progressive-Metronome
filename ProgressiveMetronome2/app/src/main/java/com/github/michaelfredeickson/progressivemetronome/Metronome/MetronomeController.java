package com.github.michaelfredeickson.progressivemetronome.metronome;

public class MetronomeController {

    private Metronome metronome;
    private ParseMetronome parseMetronome = null;


    public MetronomeController() {
        metronome = new Metronome();
        int[] timeSignature = {metronome.getTimeSignatureNumerator(), metronome.getTimeSignatureDenominator()};
        parseMetronome = new ParseMetronome(metronome.getTempo(), timeSignature, metronome.getAccent(), metronome.getSubdivision());

    }

    public int getTempo() {
        return metronome.getTempo();
    }

    public void setTempo(int tempo) {
        metronome.setTempo(tempo);
        parseMetronome.tempo = metronome.getTempo();

    }

    public void startMetronome() {


        parseMetronome.beginMetronome();

    }

    public void stopMetronome() {

        parseMetronome.stopMetronome();

    }

    public void setTimeSignatureNumerator(int timeSignatureNumerator){

        metronome.setTimeSignatureNumerator(timeSignatureNumerator);
        parseMetronome.timeSignatureNumerator = metronome.getTimeSignatureNumerator();

    }


    public void setTimeSignatureDenominator(int timeSignatureDenominator){

        metronome.setTimeSignatureDenominator(timeSignatureDenominator);
        parseMetronome.timeSignatureDenominator = metronome.getTimeSignatureDenominator();

    }


    public void setAccent(int accent){

        metronome.setAccent(accent);
        parseMetronome.accent = metronome.getAccent();

    }



    public void setSubdivision(int subdivision){

        metronome.setSubdivision(subdivision);
        parseMetronome.subdivision = metronome.getSubdivision();

    }

}
