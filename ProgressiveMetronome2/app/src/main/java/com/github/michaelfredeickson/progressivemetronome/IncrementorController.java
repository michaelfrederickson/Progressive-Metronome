package com.github.michaelfredeickson.progressivemetronome;

import java.io.Serializable;
import com.github.michaelfredeickson.progressivemetronome.IncrementorActivity;
public class IncrementorController implements Serializable {

    private IncrementorActivity IncrementorActivity;
    private IncrementMetronome incrementMetronome;
    private MetronomeActivity metronomeActivity;
//    private SoundGenerator soundGenerator;
//    private ParseMetronome parseMetronome;

    public IncrementorController(IncrementorActivity IncrementorActivity) {
        this.IncrementorActivity = IncrementorActivity;
        incrementMetronome = new IncrementMetronome();


    }

    public IncrementorController(MetronomeActivity MetronomeActivity){

        this.metronomeActivity = MetronomeActivity;
        incrementMetronome = new IncrementMetronome();

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

    public void upMeasure() {
        incrementMetronome.upNumBeatsInMeasure();
        IncrementorActivity.updateMeasureView(incrementMetronome.getNumBeatsInMeasure());

//        if (soundGenerator != null) {
//            soundGenerator.stop();
//        }
//        soundGenerator = new SoundGenerator(metronome.getTempo(), metronome.getMeasure(), metronome.getBeep(), metronome.getFirstBeep());
    }

    public void downMeasure() {
        incrementMetronome.downNumBeatsInMeasure();
        IncrementorActivity.updateMeasureView(incrementMetronome.getNumBeatsInMeasure());

//        if (soundGenerator != null) {
//            soundGenerator.stop();
//        }
//        soundGenerator = new SoundGenerator(metronome.getTempo(), metronome.getMeasure(), metronome.getBeep(), metronome.getFirstBeep());
    }

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

    public void setSubdivision(int subDivision){

        incrementMetronome.setSubDivision(subDivision);

    }

    public int getSubDivision(){

        return incrementMetronome.getSubDivision();
    }

    public void setAccent(int accent){

        incrementMetronome.setAccent(accent);

    }

    public int getAccent(){

        return incrementMetronome.getAccent();

    }
}
