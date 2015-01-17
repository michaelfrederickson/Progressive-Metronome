package com.github.michaelfredeickson.progressivemetronome;

import android.app.Activity;
import android.content.Intent;
import com.github.michaelfredeickson.progressivemetronome.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.Serializable;


public class IncrementorActivity extends Activity implements Serializable {

    IncrementorController incrementorController;
    SelectCountinActivity selectCountinActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incrementor);

        incrementorController = new IncrementorController(this);

        updateStartingTempoView();
        setStartingSliderListener();
        updateEndingTempoView();
        setEndingSliderListener();
//        updatePassageLengthEditText();
        setPassageLengthEditText();
//        updateIncreaseEditText();
        setIncreaseEditText();
//        updateRepetitionsEditText();
        setRepetitionsEditText();


    }


    public void start(View view) {

        setFields();
        selectCountinActivity = new SelectCountinActivity(); //pass in arrayList to constructor, which adds count in and passes to SoundGenerator
        Intent intent = new Intent(this, SelectCountinActivity.class);
        intent.putExtra("incrementorController", (Serializable) incrementorController);
         startActivity(intent);

//        incrementorController.startMetronome();


    }

    public void stop(View view) {
        incrementorController.stopMetronome();
    }

//    public void updateTempo(View view){
//        SeekBar slider = (SeekBar) findViewById(R.id.starting_tempo_slider);
//        int newTempo = slider.getProgress();
//        incrementorController.setTempo(newTempo);
//        updateStartingTempoView();
//    }

    private void updateStartingTempoView() {
        TextView tempoView = ((TextView) findViewById(R.id.starting_tempo));
        tempoView.setText(incrementorController.getStartingTempo() + "");
    }

    private void setStartingSliderListener() {
        SeekBar slider = (SeekBar) findViewById(R.id.starting_tempo_slider);
        slider.setMax(300);
        slider.setProgress(incrementorController.getStartingTempo());
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //incrementorController.startMetronome();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                incrementorController.stopMetronome();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                incrementorController.setStartingTempo(progress);
                updateStartingTempoView();
            }
        });
    }

    private void updateEndingTempoView() {
        TextView tempoView = ((TextView) findViewById(R.id.ending_tempo));
        tempoView.setText(incrementorController.getEndingTempo() + "");
    }

    private void setEndingSliderListener() {
        SeekBar slider = (SeekBar) findViewById(R.id.ending_tempo_slider);
        slider.setMax(300);
        slider.setProgress(incrementorController.getEndingTempo());
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                incrementorController.startMetronome();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                incrementorController.stopMetronome();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                incrementorController.setEndingTempo(progress);
                updateEndingTempoView();
            }
        });
    }

    public void downMeasure(View view) {
        incrementorController.downMeasure();
    }

    public void upMeasure(View view) {
        incrementorController.upMeasure();
    }

    public void updateMeasureView(int measure) {
        TextView measureView = (TextView) findViewById(R.id.measure);
        measureView.setText(measure + "");
    }

    private void setEditTextListener() {



    }

    private void setFields(){

        setPassageLengthEditText();
        setIncreaseEditText();
        setRepetitionsEditText();


    }



    private void setPassageLengthEditText() {

        try {
            final EditText passageLengthEditText = (EditText) findViewById(R.id.number_of_measures_edit_text);
            int numMeasures = Integer.parseInt(passageLengthEditText.getText().toString() + "");
            incrementorController.setNumMeasures(numMeasures);
        }catch(Exception e){
            final EditText passageLengthEditText = (EditText) findViewById(R.id.number_of_measures_edit_text);
            int numMeasures = Integer.parseInt(passageLengthEditText.getHint().toString() + "");
            incrementorController.setNumMeasures(numMeasures);
        }

    }

//    private void updatePassageLengthEditText() {
//
//        final EditText passageLengthEditText = (EditText) findViewById(R.id.number_of_measures_edit_text);
//           int numMeasures = Integer.parseInt (passageLengthEditText.getText().toString() + "");
//        incrementorController.setNumMeasures(numMeasures);
//
//
//    }

    private void setIncreaseEditText() {

        try {
            final EditText increaseEditText = (EditText) findViewById(R.id.tempo_increase_edit_text);
            int increase = Integer.parseInt(increaseEditText.getText().toString() + "");
            incrementorController.setIncreaseAmount(increase);
        }catch(Exception e){
            final EditText increaseEditText = (EditText) findViewById(R.id.tempo_increase_edit_text);
            int increase = Integer.parseInt(increaseEditText.getHint().toString() + "");
            incrementorController.setIncreaseAmount(increase);
        }

    }

//    private void updateIncreaseEditText() {
//
//        final EditText increaseEditText = (EditText) findViewById(R.id.tempo_increase_edit_text);
//        int increase = Integer.parseInt (increaseEditText.getText().toString() + "");
//        incrementorController.setIncreaseAmount(increase);
//
//
//    }

    private void setRepetitionsEditText() {

        try {
            final EditText repetitionsEditText = (EditText) findViewById(R.id.number_of_repetitions_edit_text);
            int repetitions = Integer.parseInt(repetitionsEditText.getText().toString() + "");
            incrementorController.setRepetitionsAmount(repetitions);
        }catch(Exception e){
            final EditText repetitionsEditText = (EditText) findViewById(R.id.number_of_repetitions_edit_text);
            int repetitions = Integer.parseInt(repetitionsEditText.getHint().toString() + "");
            incrementorController.setRepetitionsAmount(repetitions);
        }

    }

//    private void updateRepetitionsEditText() {
//
//        final EditText repetitionsEditText = (EditText) findViewById(R.id.number_of_repetitions_edit_text);
//        int repetitions = Integer.parseInt (repetitionsEditText.getText().toString() + "");
//        incrementorController.setRepetitionsAmount(repetitions);
//
//
//    }

}