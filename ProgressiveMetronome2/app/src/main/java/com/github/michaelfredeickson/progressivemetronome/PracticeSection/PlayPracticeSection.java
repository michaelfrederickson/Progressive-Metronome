package com.github.michaelfredeickson.progressivemetronome.practicesection;

import android.media.AudioManager;
import android.media.ToneGenerator;
/**
 * Created by michaelfrederickson on 3/12/15.
 */
public class PlayPracticeSection implements Runnable{

    private int numRepeats;
    private int bpm;
    public int numBeatsInMeasure;
    private long period;
    ToneGenerator firstBeep;
    ToneGenerator secondaryEmphasisBeep;
    ToneGenerator normalBeep;
    int numSections;
    int[] bpmList;
    public volatile boolean continueRunning = false;
    int countdown;
    Thread loopThread;
    int tempo;
    int timeSignature[];
    double subdivision;
    int accent;

    public PlayPracticeSection(int tempo, int[] timeSignature, int accent, double subdivision) {

        this.tempo = tempo;
//        this.numBeatsInMeasure = timeSignature[1];
        this.numBeatsInMeasure = timeSignature[0];
        firstBeep = new ToneGenerator(AudioManager.FLAG_PLAY_SOUND, 50);
        normalBeep = new ToneGenerator(AudioManager.FLAG_PLAY_SOUND, 50);
        secondaryEmphasisBeep = new ToneGenerator(AudioManager.FLAG_PLAY_SOUND, 60);
        this.timeSignature = timeSignature;
        this.subdivision = subdivision;
        this.accent = accent;
        double accent2;
        accent2 = Double.parseDouble(String.valueOf(this.accent));
        if(this.subdivision != 1.0) {
            accent2 = (accent2 * subdivision) - (this.subdivision - 1);
        }
        this.accent = (int)accent2;

    }

        public void doBeep(int beepNumber) {
            if (beepNumber == 1) {
                // do it loud
                firstBeep.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 100);//just pass in a variable for duration here??
            } else if(beepNumber == accent) {
                secondaryEmphasisBeep.startTone(ToneGenerator.TONE_CDMA_DIAL_TONE_LITE, 100);
            } else {
                normalBeep.startTone(ToneGenerator.TONE_CDMA_DIAL_TONE_LITE, 100);
            }

        }

        public void beginMetronome(){

            continueRunning = true;
            new Thread(this).start();
        }


        public void stop() {
            continueRunning = false;


        }


        @Override
        public void run() {

//
//       double subdividedTempoOne = Math.ceil(tempo/((timeSignature[0]/timeSignature[1]) * subdivision));
//        long subdividedTempo = (new Double(subdividedTempoOne).longValue());
            tempo *= subdivision;
            numBeatsInMeasure *= subdivision;
//            if(subdivision != 1) {
//                accent = (accent * subdivision) - (subdivision - 1);
//
//            }
            double numBeatsRatio = updateNumBeatsInMeasure(timeSignature[1], subdivision);


//        period = 60000/tempo;

            period = 60000/tempo;

            long previous, current, dif, left;
            int beat;

//        if (bpmList.length != numSections) {
//            System.exit(1);
//        }
            while (continueRunning) {

                for (beat = 1; beat <= numBeatsInMeasure; beat++) {

                    previous = System.currentTimeMillis();

                    if(!continueRunning){
                        break;
                    }



                    doBeep(beat);


                    //check this many beats
                    current = System.currentTimeMillis();
                    dif = current - previous;
                    left = period - dif;

                    // Maybe for loop should be within try-catch?
                    try {
                        Thread.sleep(left);
                    } catch (InterruptedException i) {
                        i.printStackTrace();
                    }
                }


//
//                if(!continueRunning){
//                    break;
//                }



//            if(!continueRunning){
//
//            }

            }

        }

        public double updateNumBeatsInMeasure(int beatEmphasis, double subdivision){

            double ratio = 2.2;

            switch(beatEmphasis){

                case 2:
                    break;

                case 4:
                    break;

                case 8:
                    break;

                default:
                    break;


            }

            return ratio;

        }
}
