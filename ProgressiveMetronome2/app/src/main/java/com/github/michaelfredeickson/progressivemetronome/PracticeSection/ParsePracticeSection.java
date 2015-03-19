package com.github.michaelfredeickson.progressivemetronome.practicesection;

import android.app.Activity;

import com.github.michaelfredeickson.progressivemetronome.LoopSection;
import com.github.michaelfredeickson.progressivemetronome.R;
import com.github.michaelfredeickson.progressivemetronome.metronome.PlayMetronome;

import android.widget.TextView;
/**
 * Created by michaelfrederickson on 3/12/15.
 */
public class ParsePracticeSection {


    int startingTempo;
    int endingTempo;
    int numMeasures;
    int increase;
    int repetitions;
    LoopSection loop;
    int []bpmList;
    int numBeatsInMeasure;
    int numRepeats;
    int numSections;
    int countdown;
    Thread loopThread;
    int timeSignatureNumerator;
    int timeSignatureDenominator;
    double subdivision;
    int accent;
    int [] timeSignature;
    PlayPracticeSection playPracticeSection = null;
    int tempo;

    public ParsePracticeSection(int startingTempo, int endingTempo, double subdivision, int numBeatsInMeasure, int numMeasures, int increase, int repetitions, int countdown, Thread loopThread) {

        this.startingTempo = startingTempo;
        this.endingTempo = endingTempo;
        this.timeSignatureNumerator = startingTempo;
        this.timeSignatureDenominator = endingTempo;
        this.subdivision = subdivision;
        this.numMeasures = numMeasures;
        this.increase = increase;
        this.repetitions = repetitions;
        this.numBeatsInMeasure = numBeatsInMeasure;
        this.countdown = countdown;
        this.loopThread = loopThread;
        this.startingTempo *= subdivision;
        this.endingTempo *= subdivision;
        this.numBeatsInMeasure *= subdivision;
        double accent2;
        accent2 = Double.parseDouble(String.valueOf(this.accent));
        if(this.subdivision != 1.0) {
            accent2 = (accent2 * subdivision) - (this.subdivision - 1);
        }
        this.accent = (int)accent2;





        CreateBpmList();
        numRepeats = CalculateNumRepeats();
        numSections = bpmList.length;

        beginPracticeSection();



    }

    public ParsePracticeSection(int tempo, int[] timeSignature, int accent, int subdivision){


        this.tempo = tempo;
//        this.timeSignatureNumerator = timeSignature[0];
//        this.timeSignatureDenominator = timeSignature[0];
        this.timeSignature = timeSignature;
        this.accent = accent;
        this.subdivision = subdivision;
        this.endingTempo = -1;
        playPracticeSection = new PlayPracticeSection (tempo, timeSignature, accent, subdivision);


    }

    public void Countdown(){

//        final TextView countDownTextView = (TextView) findViewById(R.id.countDown);

//        int countdown;
//        try {
//            countdown = Integer.parseInt(countDownTextView.getText().toString() + "");
//        }catch(Exception e){
//            countdown = Integer.parseInt(countDownTextView.getHint().toString() + "");
//        }

//        countDownTextView.setText(countdown);


    }

    public void beginMetronome(){

        //        parseMetronome = new ParseMetronome(incrementMetronome.getStartingTempo(), incrementMetronome.getEndingTempo(), incrementMetronome.getNumBeatsInMeasure(), incrementMetronome.getPassageLength(), incrementMetronome.getIncrease(), incrementMetronome.getRepetitions(), incrementorMetronome.getCountdown());
//        parseMetronome.Countdown();

        updateMetronome();

        if(!playPracticeSection.continueRunning){
            playPracticeSection.beginMetronome();
        }
    }

    public void updateMetronome(){


        playPracticeSection.tempo = this.tempo;
        playPracticeSection.timeSignature = this.timeSignature;
        playPracticeSection.accent = this.accent;
        playPracticeSection.subdivision = this.subdivision;
        playPracticeSection.numBeatsInMeasure = timeSignatureNumerator;


    }

    public void beginPracticeSection(){

        loop =  new com.github.michaelfredeickson.progressivemetronome.LoopSection(bpmList, numBeatsInMeasure, numSections, repetitions, countdown, new Thread());
        int q = 22;
        int j = 33;

        if(endingTempo > 0) {
            loop =  new com.github.michaelfredeickson.progressivemetronome.LoopSection(bpmList, numBeatsInMeasure, numSections, repetitions, countdown, new Thread());
        }else {

        }

    }

    public void stopMetronome(){
        playPracticeSection.stop();
    }

//    public void play(){
//        loop = new LoopSection(bpmList, numBeatsInMeasure, numSections, numRepeats);
//    }

    public void CreateBpmList(){

        int arrayLength = ((endingTempo - startingTempo)/increase) + 1;
        boolean divisible = true;
        if((endingTempo - startingTempo) % increase != 0){
            arrayLength++;
            divisible = false;
        }


        bpmList = new int[arrayLength];

        int currentTempo = startingTempo;
        int index = 0;

        while(currentTempo <= endingTempo){

            bpmList[index] = currentTempo;
            currentTempo += increase;
            index++;


        }

        if(!divisible){
            bpmList[index] = endingTempo;

        }


    }

    public int CalculateNumRepeats(){

        return numMeasures * repetitions;

    }

    public int getNumRepeats() {return this.numRepeats;}

    public int getNumSections() {return this.numSections;}


}
