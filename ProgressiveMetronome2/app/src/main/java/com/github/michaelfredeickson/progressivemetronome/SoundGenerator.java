package com.github.michaelfredeickson.progressivemetronome;

import android.media.ToneGenerator;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SoundGenerator {

    private Timer timer;
    private int measure;
    private int currentBeep = 1;
    private ToneGenerator beep;
    private ToneGenerator firstBeep;
    private int currentTotalBeeps = 1;
    private int totalBeeps;


    public SoundGenerator(int tempo, int measure, ToneGenerator beep, ToneGenerator firstBeep) {

        this.beep = beep;
        this.firstBeep = firstBeep;
        this.measure = measure;
        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {


                try {
                    if (currentBeep == 1) {
                        SoundGenerator.this.firstBeep.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 100);
                        currentBeep++;
                    } else if (currentBeep == SoundGenerator.this.measure) {
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
