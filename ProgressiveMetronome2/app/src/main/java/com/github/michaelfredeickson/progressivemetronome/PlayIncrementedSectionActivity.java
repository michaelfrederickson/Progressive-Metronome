package com.github.michaelfredeickson.progressivemetronome;

import android.app.Activity;
import com.github.michaelfredeickson.progressivemetronome.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;


public class PlayIncrementedSectionActivity extends Activity implements Serializable {

    IncrementorController ic;
    ParseMetronome parseMetronome;
    private static TextView countDownTextView;
    private Button b;
    Thread t = Thread.currentThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_incremented_section);
//        Intent incrementorControllerIntent = getIntent().getSerializableExtra("incrementorController");

        b = (Button) findViewById(R.id.stop_increment_button);

        ic = (IncrementorController) getIntent().getSerializableExtra("ic");

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StopIncrementSection(v);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_incremented_section, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void beginCountdown(View vew){


        parseMetronome = new ParseMetronome(ic.getStartingTempo(), ic.getEndingTempo(), ic.getNumBeatsInMeasure(), ic.getNumMeasures(), ic.getIncreaseAmount(), ic.getRepetitionsAmount(), ic.getCountdown(), t);
        Countdown();
        parseMetronome.begin();


    }

    public void StartIncrementSection(View vew){


        parseMetronome = new ParseMetronome(ic.getStartingTempo(), ic.getEndingTempo(), ic.getNumBeatsInMeasure(), ic.getNumMeasures(), ic.getIncreaseAmount(), ic.getRepetitionsAmount(), ic.getCountdown(), t);
        Countdown();
        parseMetronome.begin();


    }

    public void Countdown(){

        countDownTextView = (TextView) findViewById(R.id.countDown);
        Integer d = ic.getCountdown();
        String f = d.toString();

        countDownTextView.setText(d.toString());

    }

    public void StopIncrementSection(View view){

        parseMetronome.StopMetronome();

    }


}
