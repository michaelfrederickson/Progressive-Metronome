package com.github.michaelfredeickson.progressivemetronome.practicesection;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.michaelfredeickson.progressivemetronome.R;

/**
 * Created by michaelfrederickson on 2/13/15.
 */
public class DisplayCountInFragment extends Fragment {

    PracticeSectionActivity practiceSectionActivity;
    Button startCountInButton;
    PracticeSectionController practiceSectionController;
    ParsePracticeSection parsePracticeSection;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        practiceSectionActivity = (PracticeSectionActivity) getActivity();
        practiceSectionController = practiceSectionActivity.practiceSectionController;
        View view = (LinearLayout) inflater.inflate(
                R.layout.fragment_display_count_in, container, false);
        startCountInButton = (Button) view.findViewById(R.id.start_countdown_button);
        setListeners();
        return view;
    }

    public void setListeners() {

        startCountInButton.setOnClickListener(new View.OnClickListener() {


            long period = 60000 / practiceSectionActivity.practiceSectionController.getStartingTempo();
            long remain = practiceSectionActivity.practiceSectionController.getCountdown() * period;

            @Override
            public void onClick(View v) {

                final TextView tv = (TextView) getView().findViewById(R.id.countDown);
                CountDownTimer countDownTimer = new CountDownTimer(remain, period) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        // TODO Auto-generated method stub
                        //action for every tick of the countdown.

                        tv.setText(Long.toString(millisUntilFinished / 500));
                    }

                    @Override
                    public void onFinish() {
                        // TODO Auto-generated method stub
                        //action for when the timer has finished

                        tv.setText("Go");
                        parsePracticeSection = new ParsePracticeSection(practiceSectionController.getStartingTempo(), practiceSectionController.getEndingTempo(), practiceSectionController.getSubDivision(), practiceSectionController.getNumBeatsInMeasure(), practiceSectionController.getNumMeasures(), practiceSectionController.getIncreaseAmount(), practiceSectionController.getRepetitionsAmount(), practiceSectionController.getCountdown(), new Thread());
                    }
                }.start();
            }

        });
    }

}
