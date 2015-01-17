package com.github.michaelfredeickson.progressivemetronome;

import android.media.ToneGenerator;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class IncrementSoundGenerator {

    private Timer timer;
    private int measure;
    private int currentBeep = 1;
    private ToneGenerator beep;
    private ToneGenerator firstBeep;
    private int currentTotalBeeps = 1;
    private int totalBeeps;


    public IncrementSoundGenerator(int tempo, int measure, ToneGenerator beep, ToneGenerator firstBeep) {

        this.beep = beep;
        this.firstBeep = firstBeep;
        this.measure = measure;
        timer = new Timer();


    }

    public void play(int tempo, int stopBeat) {

        final int stop = stopBeat;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    if (currentTotalBeeps == stop) {
                        stop();
                        return;
                    } else if (currentBeep == 1) {
                        IncrementSoundGenerator.this.firstBeep.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 100);
                        currentBeep++;
                    } else if (currentBeep == IncrementSoundGenerator.this.measure) {
                        IncrementSoundGenerator.this.beep.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 100);
                        currentBeep = 1;
                    } else {
                        IncrementSoundGenerator.this.beep.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 100);
                        currentBeep++;
                    }


                } catch (Exception e) {
                    System.err.println("ERROR when beeping");
                }

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


