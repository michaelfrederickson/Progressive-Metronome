package com.github.michaelfredeickson.progressivemetronome.practicesection;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.github.michaelfredeickson.progressivemetronome.R;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class PracticeSectionActivity extends Activity implements Serializable {

    PracticeSectionController practiceSectionController;
    SelectCountInFragment select;
    DisplayCountInFragment diplayCountInFragment;
    CharSequence timeSignatureNumeratorDefault = "4";
    CharSequence timeSignatureDenominatorDefault = "4";
    CharSequence accentDefaultPosition = "1st Beat";
    CharSequence subdivisionDefaultPosition = "Quarter Note";
    Spinner subdivisionSelectionSpinner;
    Spinner secondarySubidivisionSelectionSpinner;
    Spinner measureNumeratorSpinner;
    Spinner measureDenominatorSpinner;
    Spinner accentSelectionSpinner;
    Map subdivisionMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_section_metronome);

        practiceSectionController = new PracticeSectionController(this);

        //Set listeners for widgets and default display for tempo views
        setStartingTempoView();
        setStartingSliderListener();
        setEndingTempoView();
        setEndingSliderListener();

        setPassageLengthEditText();
        setIncreaseEditText();
        setRepetitionsEditText();

        setSubdivisionSelectionListener();
        setTimeSignatureNumeratorSpinnerListener();
        setTimeSignatureDenominatorSpinnerListener();
        setAccentSelectionListener();


    }


    public void start(View view) {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.commit();
        select = new SelectCountInFragment();
        fragmentTransaction.replace(android.R.id.content, select);

//        setFields();
//        selectCountinActivity = new SelectCountinActivity(); //pass in arrayList to constructor, which adds count in and passes to SoundGenerator
//        Intent intent = new Intent(this, SelectCountinActivity.class);
//        intent.putExtra("practiceSectionController", (Serializable) practiceSectionController);
//         startActivity(intent);

//        practiceSectionController.startMetronome();


    }

    public void stop(View view) {
        practiceSectionController.stopMetronome();
    }

    private void setStartingTempoView() {
        TextView tempoView = ((TextView) findViewById(R.id.starting_tempo));
        tempoView.setText(practiceSectionController.getStartingTempo() + "");
    }

    private void setStartingSliderListener() {
        SeekBar slider = (SeekBar) findViewById(R.id.starting_tempo_slider);
        slider.setMax(300);
        slider.setProgress(practiceSectionController.getStartingTempo());
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //practiceSectionController.startMetronome();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                practiceSectionController.stopMetronome();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                practiceSectionController.setStartingTempo(progress);
                setStartingTempoView();
            }
        });
    }

    private void setEndingTempoView() {
        TextView tempoView = ((TextView) findViewById(R.id.ending_tempo));
        tempoView.setText(practiceSectionController.getEndingTempo() + "");
    }

    private void setEndingSliderListener() {
        SeekBar slider = (SeekBar) findViewById(R.id.ending_tempo_slider);
        slider.setMax(300);
        slider.setProgress(practiceSectionController.getEndingTempo());
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                practiceSectionController.startMetronome();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                practiceSectionController.stopMetronome();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                practiceSectionController.setEndingTempo(progress);
                setEndingTempoView();
            }
        });
    }



    private void setPassageLengthEditText() {

            final EditText passageLengthEditText = (EditText) findViewById(R.id.number_of_measures_edit_text);

            passageLengthEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    int numMeasures = Integer.parseInt(passageLengthEditText.getHint().toString() + "");
                    practiceSectionController.setNumMeasures(numMeasures);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int numMeasures = Integer.parseInt(passageLengthEditText.getText().toString() + "");
                    practiceSectionController.setNumMeasures(numMeasures);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

    private void setIncreaseEditText() {


            final EditText increaseEditText = (EditText) findViewById(R.id.tempo_increase_edit_text);
            increaseEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    int increase = Integer.parseInt(increaseEditText.getHint().toString() + "");
                    practiceSectionController.setIncreaseAmount(increase);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int increase = Integer.parseInt(increaseEditText.getText().toString() + "");
                    practiceSectionController.setIncreaseAmount(increase);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

    }

    private void setRepetitionsEditText() {

            final EditText repetitionsEditText = (EditText) findViewById(R.id.number_of_repetitions_edit_text);
            repetitionsEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    int repetitions = Integer.parseInt(repetitionsEditText.getHint().toString() + "");
                    practiceSectionController.setRepetitionsAmount(repetitions);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int repetitions = Integer.parseInt(repetitionsEditText.getText().toString() + "");
                    practiceSectionController.setRepetitionsAmount(repetitions);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
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
        subdivisionSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String subdivisionKey = (String) parent.getItemAtPosition(position);
//                System.out.println(subdivisionMap.get(subdivisionKey));
                Integer subdivision = (Integer) subdivisionMap.get(subdivisionKey);
                practiceSectionController.setSubdivision(subdivision);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        subdivisionSelectionSpinner.setOnItemSelectedListener(new com.github.michaelfredeickson.progressivemetronome.metronome.CustomSpinnerItemSelectedListenerForMetronome(metronomeController, 4));
    }

    private void setSubdivisionSelectionListener(){
        subdivisionSelectionSpinner = (Spinner) findViewById(R.id.subdivision_selection_spinner_practice_metronome);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.subdivision_selection_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subdivisionSelectionSpinner.setAdapter(adapter);
        int defaultPosition = adapter.getPosition(subdivisionDefaultPosition);
        subdivisionSelectionSpinner.setSelection(defaultPosition);
        subdivisionMap = getSpinnerHashMap(2);
        subdivisionSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String subdivisionKey = (String) parent.getItemAtPosition(position);
                Integer subdivision = (Integer) subdivisionMap.get(subdivisionKey);
                practiceSectionController.setSubdivision(subdivision);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        subdivisionSelectionSpinner.setOnItemSelectedListener(new CustomSpinnerItemSelectedListenerForPracticeSection(practiceSectionController, 4));
    }

    private void setTimeSignatureNumeratorSpinnerListener(){
        measureNumeratorSpinner = (Spinner) findViewById(R.id.time_signature_numerator_practice_section);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time_signature_numerator_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        measureNumeratorSpinner.setAdapter(adapter);
        int defaultPosition = adapter.getPosition(timeSignatureNumeratorDefault);
        measureNumeratorSpinner.setSelection(defaultPosition);
        measureNumeratorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer timeSignatureNumeratorSelected = Integer.valueOf((String) parent.getItemAtPosition(position));
                practiceSectionController.setTimeSignatureNumerator(timeSignatureNumeratorSelected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        measureNumeratorSpinner.setOnItemSelectedListener(new CustomSpinnerItemSelectedListenerForPracticeSection(practiceSectionController, 1));
    }

    private void setTimeSignatureDenominatorSpinnerListener(){
        measureDenominatorSpinner = (Spinner) findViewById(R.id.time_signature_denominator_practice_section);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time_signature_denominator_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        measureDenominatorSpinner.setAdapter(adapter);
        int defaultPosition = adapter.getPosition(timeSignatureDenominatorDefault);
        measureDenominatorSpinner.setSelection(defaultPosition);
        measureDenominatorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer timeSignatureDenominatorSelected = Integer.valueOf((String) parent.getItemAtPosition(position));
                if(timeSignatureDenominatorSelected == 8 && subdivisionMap.size() > 3){
                    setSecondarySubdivisionSelectionListener();
                }else if(timeSignatureDenominatorSelected == 4 && subdivisionMap.size() == 3){
                    setSubdivisionSelectionListener();
                }
                practiceSectionController.setTimeSignatureDenominator(timeSignatureDenominatorSelected);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        measureDenominatorSpinner.setOnItemSelectedListener(new CustomSpinnerItemSelectedListenerForPracticeSection(practiceSectionController, 2));
    }

    private void setAccentSelectionListener(){
        accentSelectionSpinner = (Spinner) findViewById(R.id.accent_selection_spinner_practice_section);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.accent_selection_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accentSelectionSpinner.setAdapter(adapter);
        int defaultPosition = adapter.getPosition(accentDefaultPosition);
        accentSelectionSpinner.setSelection(defaultPosition);
        accentSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Map accentMap = getSpinnerHashMap(1);
                String accentKey = (String) parent.getItemAtPosition(position);
                Integer accent = (Integer) accentMap.get(accentKey);
                practiceSectionController.setAccent(accent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        accentSelectionSpinner.setOnItemSelectedListener(new CustomSpinnerItemSelectedListenerForPracticeSection(practiceSectionController, 3));
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