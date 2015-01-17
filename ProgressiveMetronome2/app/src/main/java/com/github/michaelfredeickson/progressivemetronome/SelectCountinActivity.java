package com.github.michaelfredeickson.progressivemetronome;

import android.app.Activity;
import android.content.Intent;
import com.github.michaelfredeickson.progressivemetronome.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;


public class SelectCountinActivity extends Activity implements Serializable {

    PlayIncrementedSectionActivity playIncrementedSection;
    IncrementorController ic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcountin);

        ic = (IncrementorController) getIntent().getSerializableExtra("incrementorController");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_countin, menu);
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

    public void startIncrementSection(View view) {

        setCountdown();
        playIncrementedSection = new PlayIncrementedSectionActivity(); //pass in arrayList to constructor, which adds count in and passes to SoundGenerator
        Intent intent = new Intent(this, PlayIncrementedSectionActivity.class);
        intent.putExtra("ic", (Serializable) ic);
        startActivity(intent);
    }

    private void setCountdown() {

        try {
            final EditText countdownEditText = (EditText) findViewById(R.id.countdown_edit_text);
            int countdown = Integer.parseInt(countdownEditText.getText().toString() + "");
            ic.setCountdown(countdown);
        }catch(Exception e){
            final EditText countdownEditText = (EditText) findViewById(R.id.countdown_edit_text);
            int countdown = Integer.parseInt(countdownEditText.getHint().toString() + "");
            ic.setCountdown(countdown);
        }

    }


}
