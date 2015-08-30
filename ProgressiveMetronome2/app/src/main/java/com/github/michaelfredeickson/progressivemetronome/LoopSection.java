package com.github.michaelfredeickson.progressivemetronome;

import android.media.AudioManager;
import android.media.ToneGenerator;

/**
 * Created by michaelfrederickson on 12/4/14.
 */
public class LoopSection implements Runnable {

    private int numRepeats;
    private int bpm, numBeatsInMeasure;
    private long period;
    ToneGenerator firstBeep;
    ToneGenerator secondaryEmphasisBeep;
    ToneGenerator normalBeep;
    int numSections;
    int[] bpmList;
    private volatile boolean continueRunning = true;
    int countdown;
    Thread blinker;
    Thread loopThread;

    public LoopSection(int[] bpmList, int numBeatsInMeasure, int numSections, int numRepeats, int countdown, Thread loopThread) {
//        period = 60000 / bpmList[0];

        this.bpmList = bpmList;
        this.numBeatsInMeasure = numBeatsInMeasure;
        this.numSections = numSections;
        this.numRepeats = numRepeats;
        this.countdown = countdown;
        this.loopThread = loopThread;

        firstBeep = new ToneGenerator(AudioManager.FLAG_PLAY_SOUND, 70);
        normalBeep = new ToneGenerator(AudioManager.FLAG_PLAY_SOUND, 70);

        run();
    }

    public void doBeep(int beepNumber) {
        if (beepNumber == 1) {
            // do it loud
            firstBeep.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 100);//just pass in a variable for duration here??
        } else {
            normalBeep.startTone(ToneGenerator.TONE_CDMA_DIAL_TONE_LITE, 100);
        }

    }

    public void stop() {
        continueRunning = false;
        blinker = null;
        loopThread.interrupt();
    }


    @Override
    public void run() {

        while(continueRunning){

        blinker = loopThread;
        int sectionNum = 1;
        int bpmIndex = 0;
        long previous, current, dif, left;

        if (bpmList.length != numSections) {
            System.exit(1);
        }
        while (sectionNum <= numSections && continueRunning) {

            period = 60000 / bpmList[bpmIndex];

            for (int measureNumber = 1; measureNumber <= numRepeats; measureNumber++) {
                for (int beat = 1; beat <= numBeatsInMeasure; beat++) {

                    previous = System.currentTimeMillis();

                    if (!continueRunning || blinker != loopThread) {
                        break;
                    }


                    doBeep(beat);


                    //check this many beats
                    current = System.currentTimeMillis();
                    dif = current - previous;
                    left = period - dif;

                    // Maybe for loop should be within try-catch?
                    try {
                        loopThread.sleep(left);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }

                if (!continueRunning || blinker != loopThread) {
                    break;
                }
            }
            sectionNum++;
            bpmIndex++;

            if (sectionNum >= numSections) {
                continueRunning = false;
            }

//            if(!continueRunning){
//
//            }

        }

    }

    }
}
