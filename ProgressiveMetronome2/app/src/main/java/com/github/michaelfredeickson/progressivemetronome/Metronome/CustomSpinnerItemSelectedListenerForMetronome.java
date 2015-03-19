package com.github.michaelfredeickson.progressivemetronome.metronome;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.view.View;
import android.widget.Spinner;

import com.github.michaelfredeickson.progressivemetronome.R;
import com.github.michaelfredeickson.progressivemetronome.practicesection.PracticeSectionController;

import java.util.HashMap;
import java.util.Map;

public class CustomSpinnerItemSelectedListenerForMetronome implements OnItemSelectedListener {

    MetronomeController metronomeController;
    PracticeSectionController practiceSectionController;
    int spinnerID;

    public CustomSpinnerItemSelectedListenerForMetronome(MetronomeController metronomeController, int spinnerID){
        this.metronomeController = metronomeController;
        this.spinnerID = spinnerID;
    }


    @Override
    public void onItemSelected(AdapterView parent, View view, int pos, long id) {

        switch(spinnerID){

            case 1: Integer timeSignatureNumeratorSelected = Integer.valueOf((String) parent.getItemAtPosition(pos));
                metronomeController.setTimeSignatureNumerator(timeSignatureNumeratorSelected);
                break;

            case 2: Integer timeSignatureDenominatorSelected = Integer.valueOf((String) parent.getItemAtPosition(pos));
                metronomeController.setTimeSignatureDenominator(timeSignatureDenominatorSelected);
                break;

            case 3: Map accentMap = getSpinnerHashMap(1);
                String accentKey = (String) parent.getItemAtPosition(pos);
                Integer accent = (Integer) accentMap.get(accentKey);
                metronomeController.setAccent(accent);
                break;

            case 4: Map subdivisionMap = getSpinnerHashMap(2);
                String subdivisionKey = (String) parent.getItemAtPosition(pos);
                System.out.println(subdivisionMap.get(subdivisionKey));
                Integer subdivision = (Integer) subdivisionMap.get(subdivisionKey);
                metronomeController.setSubdivision(subdivision);

            case 5: Map secondarySubdivisionMap = getSpinnerHashMap(2);
                String secondarySubdivisionKey = (String) parent.getItemAtPosition(pos);
                System.out.println(secondarySubdivisionMap.get(secondarySubdivisionKey));
                Integer secondarySubdivision = (Integer) secondarySubdivisionMap.get(secondarySubdivisionKey);
                metronomeController.setSubdivision(secondarySubdivision);


            default: break;

        }
//        super.OnItemSelectedListener(parent, view, pos, id);
//        int timeSignatureSelected = parent.getItemAtPosition(pos);
//        Toast.makeText(parent.getContext(), "Selected Country : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView parent) {
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
                subdivisionMap.put("Quintuples", 5);
                subdivisionMap.put("Sextuplets", 6);
                return subdivisionMap;

            default: return null;

        }

    }
}
