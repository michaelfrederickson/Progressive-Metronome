package com.github.michaelfredeickson.progressivemetronome;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;

import com.github.michaelfredeickson.progressivemetronome.metronome.MetronomeController;

import java.util.HashMap;
import java.util.Map;

public class MetronomeActivity extends Activity implements OnItemSelectedListener {

    MetronomeController metronomeController;
//    PracticeSectionActivity incrementorActivity;
    CharSequence timeSignatureNumeratorDefault = "4";
    CharSequence timeSignatureDenominatorDefault = "4";
    CharSequence accentDefaultPosition = "1st Beat";
    CharSequence subdivisionDefaultPosition = "Quarter Note";
    SelectMetronomeTypeFragment select;
    Spinner subdivisionSelectionSpinner;
    Spinner secondarySubidivisionSelectionSpinner;
    Spinner measureNumeratorSpinner;
    Spinner measureDenominatorSpinner;
    Spinner accentSelectionSpinner;
    Map subdivisionMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);

        metronomeController = new MetronomeController();

        //Set tempo view and set Listeners for widgets
        updateTempoView();
        setTempoSliderListener();
        setSubdivisionSelectionListener();
        setTimeSignatureNumeratorSpinnerListener();
        setTimeSignatureDenominatorSpinnerListener();
        setAccentSelectionListener();


        //instantiate fragments



    }


    private void updateTempoView() {
        TextView tempoView = ((TextView) findViewById(R.id.tempo));
        tempoView.setText(metronomeController.getTempo() + "");
    }

    public void start(View view) {

        metronomeController.startMetronome();
    }

    public void stop(View view) {
        metronomeController.stopMetronome();
    }

    public void openSelectMetronomeTypeFramgment(View view) {

//        Configuration config = getResources().getConfiguration();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.commit();
        select = new SelectMetronomeTypeFragment();
        fragmentTransaction.replace(android.R.id.content, select);

    }

    public void StartPracticeSectionActivity(View view) {


        select.StartPracticeSectionActivity(view);

    }

    public void updateTempo(View view) {
        SeekBar slider = (SeekBar) findViewById(R.id.slider);
        int newTempo = slider.getProgress();
        metronomeController.setTempo(newTempo);
        updateTempoView();
    }

    private void setTempoSliderListener() {
        SeekBar slider = (SeekBar) findViewById(R.id.slider);
        slider.setMax(300);
        slider.setProgress(metronomeController.getTempo());
        slider.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//		    	metronomeController.startMetronome();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                metronomeController.stopMetronome();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                metronomeController.setTempo(progress);
                updateTempoView();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView parent, View view, int pos, long id) {

    }


    @Override
    public void onNothingSelected(AdapterView parent) {


    }



    private void setSecondarySubdivisionSelectionListener(){
        secondarySubidivisionSelectionSpinner = (Spinner) findViewById(R.id.subdivision_selection_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.secondary_subdivision_selection_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subdivisionSelectionSpinner.setAdapter(adapter);
        int defaultPosition = adapter.getPosition(subdivisionDefaultPosition);
        subdivisionSelectionSpinner.setSelection(defaultPosition);
        subdivisionMap = getSpinnerHashMap(3);
        subdivisionSelectionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String subdivisionKey = (String) parent.getItemAtPosition(position);
//                System.out.println(subdivisionMap.get(subdivisionKey));
                Integer subdivision = (Integer) subdivisionMap.get(subdivisionKey);
                metronomeController.setSubdivision(subdivision);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        subdivisionSelectionSpinner.setOnItemSelectedListener(new com.github.michaelfredeickson.progressivemetronome.metronome.CustomSpinnerItemSelectedListenerForMetronome(metronomeController, 4));
    }


    private void setSubdivisionSelectionListener(){
        subdivisionSelectionSpinner = (Spinner) findViewById(R.id.subdivision_selection_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.subdivision_selection_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subdivisionSelectionSpinner.setAdapter(adapter);
        int defaultPosition = adapter.getPosition(subdivisionDefaultPosition);
        subdivisionSelectionSpinner.setSelection(defaultPosition);
        subdivisionMap = getSpinnerHashMap(2);
        subdivisionSelectionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String subdivisionKey = (String) parent.getItemAtPosition(position);
                System.out.println(subdivisionMap.get(subdivisionKey));
                Integer subdivision = (Integer) subdivisionMap.get(subdivisionKey);
                metronomeController.setSubdivision(subdivision);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        subdivisionSelectionSpinner.setOnItemSelectedListener(new com.github.michaelfredeickson.progressivemetronome.metronome.CustomSpinnerItemSelectedListenerForMetronome(metronomeController, 4));
    }

    private void setTimeSignatureNumeratorSpinnerListener(){
        measureNumeratorSpinner = (Spinner) findViewById(R.id.time_signature_numerator);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time_signature_numerator_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        measureNumeratorSpinner.setAdapter(adapter);
        int defaultPosition = adapter.getPosition(timeSignatureNumeratorDefault);
        measureNumeratorSpinner.setSelection(defaultPosition);
        measureNumeratorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer timeSignatureNumeratorSelected = Integer.valueOf((String) parent.getItemAtPosition(position));
                metronomeController.setTimeSignatureNumerator(timeSignatureNumeratorSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        measureNumeratorSpinner.setOnItemSelectedListener(new com.github.michaelfredeickson.progressivemetronome.metronome.CustomSpinnerItemSelectedListenerForMetronome(metronomeController, 1));

    }

    private void setTimeSignatureDenominatorSpinnerListener(){
        measureDenominatorSpinner = (Spinner) findViewById(R.id.time_signature_denominator);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time_signature_denominator_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        measureDenominatorSpinner.setAdapter(adapter);
        int defaultPosition = adapter.getPosition(timeSignatureDenominatorDefault);
        measureDenominatorSpinner.setSelection(defaultPosition);
        measureDenominatorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer timeSignatureDenominatorSelected = Integer.valueOf((String) parent.getItemAtPosition(position));
                int k = subdivisionMap.size();
                if(timeSignatureDenominatorSelected == 8 && subdivisionMap.size() > 3){
                    setSecondarySubdivisionSelectionListener();
                }else if(timeSignatureDenominatorSelected == 4 && subdivisionMap.size() == 3){
                    setSubdivisionSelectionListener();
                }

                metronomeController.setTimeSignatureDenominator(timeSignatureDenominatorSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        measureDenominatorSpinner.setOnItemSelectedListener(new com.github.michaelfredeickson.progressivemetronome.metronome.CustomSpinnerItemSelectedListenerForMetronome(metronomeController, 2));
    }

    private void setAccentSelectionListener(){
        accentSelectionSpinner = (Spinner) findViewById(R.id.accent_selection_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.accent_selection_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accentSelectionSpinner.setAdapter(adapter);
        int defaultPosition = adapter.getPosition(accentDefaultPosition);
        accentSelectionSpinner.setSelection(defaultPosition);
        accentSelectionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Map accentMap = getSpinnerHashMap(1);
                String accentKey = (String) parent.getItemAtPosition(position);
                Integer accent = (Integer) accentMap.get(accentKey);
                metronomeController.setAccent(accent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        accentSelectionSpinner.setOnItemSelectedListener(new com.github.michaelfredeickson.progressivemetronome.metronome.CustomSpinnerItemSelectedListenerForMetronome(metronomeController, 3));
    }

    public Map getSpinnerHashMap(int mapId){

        switch(mapId){

            case 1: Map accentMap = new HashMap();
                accentMap.put("1st Beat", 1);
                accentMap.put("2nd Beat", 2);
                accentMap.put("3rd Beat", 3);
                accentMap.put("4th Beat", 4);
                accentMap.put("5th Beat", 5);
                accentMap.put("6th Beat", 6);
                accentMap.put("7th Beat", 7);
                accentMap.put("8th Beat", 8);
                return accentMap;


            case 2: Map subdivisionMap = new HashMap();
                subdivisionMap.put("Quarter Note", 1);
                subdivisionMap.put("Eighth Note", 2);
                subdivisionMap.put("Triplet", 3);
                subdivisionMap.put("Sixteenth Note", 4);
                subdivisionMap.put("Quintuplets", 5);
                subdivisionMap.put("Sextuplets", 6);
                return subdivisionMap;

            case 3: Map secondarySubdivisionMap = new HashMap();
                secondarySubdivisionMap.put("Eighth Note", 1);
                secondarySubdivisionMap.put("Sixteenth Note", 2);
                secondarySubdivisionMap.put("Sixteenth Note Triplets", 3);
                return secondarySubdivisionMap;



            default: return null;

        }

    }
}
