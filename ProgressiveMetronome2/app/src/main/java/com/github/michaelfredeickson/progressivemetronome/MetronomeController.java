package com.github.michaelfredeickson.progressivemetronome;

public class MetronomeController {

    private MetronomeActivity metronomeActivity;
    private Metronome metronome;
    private SoundGenerator soundGenerator;
    private ParseMetronome parseMetronome;

    public MetronomeController(MetronomeActivity metronomeActivity) {
        this.metronomeActivity = metronomeActivity;
        metronome = new Metronome();
    }

    public int getTempo() {
        return metronome.getTempo();
    }

    public void setTempo(int tempo) {
        metronome.setTempo(tempo);
    }

    public void startMetronome() {
		if(soundGenerator != null){
			soundGenerator.stop();
		}
		soundGenerator = new SoundGenerator(metronome.getTempo(), metronome.getMeasure(), metronome.getBeep(), metronome.getFirstBeep());
//
//        parseMetronome = new ParseMetronome(metronome.getTempo(), 200, metronome.getMeasure(), 1, 15, 1, 5);
//        parseMetronome.begin();

    }

    public void stopMetronome() {
        if (soundGenerator != null) {
            soundGenerator.stop();
        }
        soundGenerator.stop();
//
//        if (parseMetronome != null) {
//            parseMetronome.StopMetronome();
//        }
//
//        parseMetronome.StopMetronome();
    }

    public void upMeasure() {
        metronome.upMeasure();
        metronomeActivity.updateMeasureView(metronome.getMeasure());

        if (soundGenerator != null) {
            soundGenerator.stop();
        }
//		soundGenerator = new SoundGenerator(metronome.getTempo(), metronome.getMeasure(), metronome.getBeep(), metronome.getFirstBeep());
    }

    public void downMeasure() {
        metronome.downMeasure();
        metronomeActivity.updateMeasureView(metronome.getMeasure());

        if (soundGenerator != null) {
            soundGenerator.stop();
        }
//		soundGenerator = new SoundGenerator(metronome.getTempo(), metronome.getMeasure(), metronome.getBeep(), metronome.getFirstBeep());
    }

    public int getMeasure(){

        return metronome.getMeasure();

    }


}
