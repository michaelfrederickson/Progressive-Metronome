package com.github.michaelfredeickson.progressivemetronome.practicesection;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by michaelfrederickson on 2/14/15.
 */
public class CustomSpinnerItemSelectedListenerForPracticeSection implements OnItemSelectedListener {

        PracticeSectionController practiceSectionController;
        int spinnerID;

        public CustomSpinnerItemSelectedListenerForPracticeSection(PracticeSectionController practiceSectionController, int spinnerID) {
            this.practiceSectionController = practiceSectionController;
            this.spinnerID = spinnerID;
        }

        @Override
        public void onItemSelected(AdapterView parent, View view, int pos, long id) {

            switch (spinnerID) {

                case 1:
                    Integer timeSignatureNumeratorSelected = Integer.valueOf((String) parent.getItemAtPosition(pos));
                    practiceSectionController.setTimeSignatureNumerator(timeSignatureNumeratorSelected);
                    break;

                case 2:
                    Integer timeSignatureDenominatorSelected = Integer.valueOf((String) parent.getItemAtPosition(pos));
                    practiceSectionController.setTimeSignatureDenominator(timeSignatureDenominatorSelected);
                    break;

                case 3:
                    Map accentMap = getSpinnerHashMap(1);
                    String accentKey = (String) parent.getItemAtPosition(pos);
                    Integer accent = (Integer) accentMap.get(accentKey);
                    practiceSectionController.setAccent(accent);
                    break;

                case 4:
                    Map subdivisionMap = getSpinnerHashMap(2);
                    String subdivisionKey = (String) parent.getItemAtPosition(pos);
                    Double subdivision = (Double) subdivisionMap.get(subdivisionKey);
                    practiceSectionController.setSubdivision(subdivision);

                default:
                    break;

            }
//        super.OnItemSelectedListener(parent, view, pos, id);
//        int timeSignatureSelected = parent.getItemAtPosition(pos);
//        Toast.makeText(parent.getContext(), "Selected Country : " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView parent) {
        }

        public Map getSpinnerHashMap(int mapId) {

            switch (mapId) {

                case 1:
                    Map accentMap = new HashMap();
                    accentMap.put("1st Beat", 1);
                    accentMap.put("2nd Beat", 2);
                    accentMap.put("3rd Beat", 3);
                    accentMap.put("4th Beat", 4);
                    accentMap.put("5th Beat", 5);
                    accentMap.put("6th Beat", 6);
                    accentMap.put("7th Beat", 7);
                    accentMap.put("8th Beat", 8);
                    return accentMap;


                case 2:
                    Map subdivisionMap = new HashMap();
                    subdivisionMap.put("Sixteenth Note", .25);
                    subdivisionMap.put("Eighth Note", .5);
                    subdivisionMap.put("Quarter Note", 1.0);
                    subdivisionMap.put("Half Note", 2.0);
                    subdivisionMap.put("Whole Note", 4.0);
                    return subdivisionMap;

                default:
                    return null;

            }

        }
    }


