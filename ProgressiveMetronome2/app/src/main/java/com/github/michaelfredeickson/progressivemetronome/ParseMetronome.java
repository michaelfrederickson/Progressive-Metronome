package com.github.michaelfredeickson.progressivemetronome;


import android.app.Activity;
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


        //thanksBen = new BensClass(CreateBpmList(), numBeatsInMeasure, bpmList.length, CalculateNumRepeats());


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

    public void begin(){
        loop = new LoopSection(bpmList, numBeatsInMeasure, numSections, numRepeats, countdown, loopThread);
    }

    public void StopMetronome(){
        loop.stop();
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
