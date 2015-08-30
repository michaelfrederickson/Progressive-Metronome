package com.github.michaelfredeickson.progressivemetronome.practicesection;

import com.github.michaelfredeickson.progressivemetronome.MetronomeActivity;

import java.io.Serializable;

public class PracticeSectionController implements Serializable {

    private PracticeSectionActivity IncrementorActivity;
    private PracticeSectionMetronome incrementMetronome;
    private MetronomeActivity metronomeActivity;
//    private SoundGenerator soundGenerator;
//    private ParseMetronome parseMetronome;

    public PracticeSectionController(PracticeSectionActivity IncrementorActivity) {
        this.IncrementorActivity = IncrementorActivity;
        incrementMetronome = new PracticeSectionMetronome();


    }

    public PracticeSectionController(MetronomeActivity MetronomeActivity){

        this.metronomeActivity = MetronomeActivity;
        incrementMetronome = new PracticeSectionMetronome();

    }

    public int getStartingTempo() {
        return incrementMetronome.getStartingTempo();
    }

    public void setStartingTempo(int tempo) {
        incrementMetronome.setStartingTempo(tempo);
    }

    public int getEndingTempo() {
        return incrementMetronome.getEndingTempo();
    }

    public void setEndingTempo(int tempo) {
        incrementMetronome.setEndingTempo(tempo);
    }

    public int passageLength() { return 4; }

    public void startMetronome() {

//        if (soundGenerator != null) {
//            soundGenerator.stop();
//        }
//        soundGenerator = new SoundGenerator(incrementMetronome.getStartingTempo(), incrementMetronome.getNumBeatsInMeasure(), incrementMetronome.getBeep(), incrementMetronome.getFirstBeep());

//        parseMetronome = new ParseMetronome(incrementMetronome.getStartingTempo(), incrementMetronome.getEndingTempo(), incrementMetronome.getNumBeatsInMeasure(), incrementMetronome.getPassageLength(), incrementMetronome.getIncrease(), incrementMetronome.getRepetitions(), incrementorMetronome.getCountdown());
//        parseMetronome.Countdown();

    }

    public void stopMetronome() {
//        if (soundGenerator != null) {
//            soundGenerator.stop();
//        }

    }

//    public void upMeasure() {
//        incrementMetronome.upNumBeatsInMeasure();
//        IncrementorActivity.updateMeasureView(incrementMetronome.getNumBeatsInMeasure());
//
//    }
//
//    public void downMeasure() {
//        incrementMetronome.downNumBeatsInMeasure();
//        IncrementorActivity.updateMeasureView(incrementMetronome.getNumBeatsInMeasure());
//
//    }

    public void setNumMeasures(int numMeasures){

           incrementMetronome.setPassageLength(numMeasures);

    }

    public int getNumMeasures(){

        return incrementMetronome.getPassageLength();
    }

    public void setIncreaseAmount(int increase){

        incrementMetronome.setIncrease(increase);

    }

    public int getIncreaseAmount() {

    return incrementMetronome.getIncrease();}

    public void setRepetitionsAmount(int repetitions){

        incrementMetronome.setRepetitions(repetitions);

    }

    public int getRepetitionsAmount(){

        return incrementMetronome.getRepetitions();
    }

    public void setCountdown(int countdown){

        incrementMetronome.setCountdown(countdown);
    }

    public int getCountdown(){

        return incrementMetronome.getCountdown();

    }

    public int getNumBeatsInMeasure(){

        return incrementMetronome.getNumBeatsInMeasure();

    }

    public void setNumBeatsInMeasure(int numBeatsInMeasure){

        incrementMetronome.setNumBeatsInMeasure(numBeatsInMeasure);
    }

    public void setSubdivision(double subDivision){

        incrementMetronome.setSubDivision(subDivision);

    }

    public double getSubDivision(){

        return incrementMetronome.getSubDivision();
    }

    public void setAccent(int accent){

        incrementMetronome.setAccent(accent);

    }

    public int getAccent(){

        return incrementMetronome.getAccent();

    }

    public void setTimeSignatureNumerator(int timeSignatureNumerator){

        incrementMetronome.setTimeSignatureNumerator(timeSignatureNumerator);

    }

    public void setTimeSignatureDenominator(int timeSignatureDenominator){

        incrementMetronome.setTimeSignatureDenominator(timeSignatureDenominator);

    }

    public int getTimeSignatureNumerator(int timeSignatureNumerator){

       return incrementMetronome.getTimeSignatureNumerator();

    }

    public int getTimeSignatureDenominator(int timeSignatureDenominator){

       return incrementMetronome.getTimeSignatureDenominator();

    }




}
