package ca.uwaterloo.cs349.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class TopicSelection extends AppCompatActivity implements Observer {


    Model mModel;
    TextView name;
    Spinner mspin;
    Button logout;
    Button load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_selection);
        setTitle("Topic Selection");

        // Get Model instance
        mModel = Model.getInstance();
        mModel.addObserver(this);

        name = (TextView)findViewById(R.id.wName);
        mspin=(Spinner) findViewById(R.id.spinner);
        Integer[] items = new Integer[]{1,2,3,4,5};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        mspin.setAdapter(adapter);

        mModel.initObservers();

        logout = (Button) findViewById(R.id.logout);

        // Create controller for button
        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mModel.logout();
                Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
                // Start activity
                startActivity(intent);
                finish();
            }
        });

        load = (Button) findViewById(R.id.load);

        // Create controller for button
        load.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mModel.setNum((Integer) mspin.getSelectedItem());
                //Log.d("num qs", mspin.getSelectedItem().toString());
                Intent intent = new Intent(getApplicationContext(), QScreen.class);
                // Start activity
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void update(Observable o, Object arg)
    {
        name.setText("Welcome  " + String.valueOf(mModel.getName()));
    }
}
