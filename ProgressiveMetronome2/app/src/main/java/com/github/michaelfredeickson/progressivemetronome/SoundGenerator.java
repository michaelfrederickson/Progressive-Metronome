package com.github.michaelfredeickson.progressivemetronome;

import android.media.ToneGenerator;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SoundGenerator {

    private Timer timer;
    private int timeSignatureNumerator;
    private int timeSignatureDenominator;
    private int currentBeep = 1;
    private ToneGenerator beep;
    private ToneGenerator firstBeep;
    private int currentTotalBeeps = 1;
    private int totalBeeps;
    private int subdivision;


    public SoundGenerator(int tempo, int [] measure, ToneGenerator beep, ToneGenerator firstBeep, int subidivion) {

        this.beep = beep;
        this.firstBeep = firstBeep;
        this.timeSignatureNumerator = measure[0];
        this.subdivision = subidivion;
        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {


                try {
                    if (currentBeep == 1) {
                        SoundGenerator.this.firstBeep.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 100);
                        currentBeep++;
                    } else if (currentBeep == SoundGenerator.this.timeSignatureNumerator) {
                        SoundGenerator.this.beep.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 100);
                        currentBeep = 1;
                    } else {
                        SoundGenerator.this.beep.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 100);
                        currentBeep++;
                    }
//                    currentBeep = (currentBeep % this.measure) + 1;
                    if (currentBeep == 1) {

                    }


                } catch (Exception e) {
                    System.err.println("ERROR when beeping");
                }
                return;
            }
        };

        timer.schedule(timerTask, new Date(), 60000 / tempo);
    }

    public void stop() {
        timer.cancel();

    }

    public void purge() {
        timer.purge();
    }

}
