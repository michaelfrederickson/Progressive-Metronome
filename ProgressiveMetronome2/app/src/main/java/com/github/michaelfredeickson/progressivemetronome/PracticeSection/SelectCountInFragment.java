package com.github.michaelfredeickson.progressivemetronome.practicesection;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.github.michaelfredeickson.progressivemetronome.R;

/**
 * Created by michaelfrederickson on 2/13/15.
 */
public class SelectCountInFragment extends Fragment {

    PracticeSectionActivity practiceSectionActivity;
    Button openDisplayCountInFragment;
    EditText countdownEditText;
    PracticeSectionController practiceSectionController;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = (LinearLayout) inflater.inflate(
                R.layout.fragment_select_count_in, container, false);

        practiceSectionActivity = (PracticeSectionActivity) getActivity();
        practiceSectionController = practiceSectionActivity.practiceSectionController;
        openDisplayCountInFragment = (Button) view.findViewById(R.id.openDisplayCountInFragment);
        countdownEditText  = (EditText) view.findViewById(R.id.countdown_edit_text);

        return view;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){


        super.onActivityCreated(savedInstanceState);
        openDisplayCountInFragment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Open fragment to display the count in screen
                DisplayCountInFragment displayCountInFragment = new DisplayCountInFragment();
                FragmentManager thisFragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = thisFragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, displayCountInFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        countdownEditText.setHint("4");
        int countdown = practiceSectionActivity.practiceSectionController.getCountdown();
        countdownEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                int countdown = Integer.parseInt(countdownEditText.getHint().toString() + "");
                practiceSectionActivity.practiceSectionController.setCountdown(countdown);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                int countdown = Integer.parseInt(countdownEditText.getText().toString() + "");
                practiceSectionActivity.practiceSectionController.setCountdown(countdown);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    //Called from button onClick in fragment xml.
    public void openDisplayCountdownFragment(View view){

        //Open fragment to display the count in screen
        DisplayCountInFragment displayCountInFragment = new DisplayCountInFragment();
        this.getFragmentManager().beginTransaction()
                .replace(android.R.id.content, displayCountInFragment)
                .addToBackStack(null)
                .commit();

    }


}
