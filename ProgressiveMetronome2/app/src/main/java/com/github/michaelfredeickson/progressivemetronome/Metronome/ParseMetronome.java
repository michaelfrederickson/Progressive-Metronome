package com.github.michaelfredeickson.progressivemetronome.metronome;


import android.app.Activity;

import com.github.michaelfredeickson.progressivemetronome.LoopSection;
import com.github.michaelfredeickson.progressivemetronome.R;
import android.widget.TextView;

/**
 * Created by michaelfrederickson on 12/6/14.
 */
public class ParseMetronome extends Activity {

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
    int subdivision;
    int accent;
    int [] timeSignature;
    PlayMetronome playMetronome = null;
    int tempo;

    public ParseMetronome(int startingTempo, int endingTempo, int numBeatsInMeasure, int numMeasures, int increase, int repetitions, int countdown, Thread loopThread) {

        this.startingTempo = startingTempo;
        this.endingTempo = endingTempo;
        this.numMeasures = numMeasures;
        this.increase = increase;
        this.repetitions = repetitions;
        this.numBeatsInMeasure = numBeatsInMeasure;
        this.countdown = countdown;
        this.loopThread = loopThread;





        CreateBpmList();
        numRepeats = CalculateNumRepeats();
        numSections = bpmList.length;

        beginPracticeSection();



    }

    public ParseMetronome(int tempo, int[] timeSignature, int accent, int subdivision){


        this.tempo = tempo;
//        this.timeSignatureNumerator = timeSignature[0];
//        this.timeSignatureDenominator = timeSignature[0];
        this.timeSignature = timeSignature;
        this.accent = accent;
        this.subdivision = subdivision;
        this.endingTempo = -1;
        playMetronome = new PlayMetronome(tempo, timeSignature, accent, subdivision);


    }

    public void Countdown(){

        final TextView countDownTextView = (TextView) findViewById(R.id.countDown);

//        int countdown;
//        try {
//            countdown = Integer.parseInt(countDownTextView.getText().toString() + "");
//        }catch(Exception e){
//            countdown = Integer.parseInt(countDownTextView.getHint().toString() + "");
//        }

        countDownTextView.setText(countdown);


    }

    public void beginMetronome(){

        //        parseMetronome = new ParseMetronome(incrementMetronome.getStartingTempo(), incrementMetronome.getEndingTempo(), incrementMetronome.getNumBeatsInMeasure(), incrementMetronome.getPassageLength(), incrementMetronome.getIncrease(), incrementMetronome.getRepetitions(), incrementorMetronome.getCountdown());
//        parseMetronome.Countdown();

        updateMetronome();

        if(!playMetronome.continueRunning){
            playMetronome.beginMetronome();
        }
    }

    public void updateMetronome(){


        playMetronome.tempo = this.tempo;
        playMetronome.timeSignature = this.timeSignature;
        playMetronome.accent = this.accent;
        playMetronome.subdivision = this.subdivision;
        playMetronome.numBeatsInMeasure = timeSignatureNumerator;


    }

    public void beginPracticeSection(){

        if(endingTempo > 0) {
            loop = new LoopSection(bpmList, numBeatsInMeasure, numSections, numRepeats, countdown, new Thread());
        }else {

        }

    }

    public void stopMetronome(){
        playMetronome.stop();
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
