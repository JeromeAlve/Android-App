package ca.uwaterloo.cs349.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Observable;
import java.util.Observer;

public class WelcomeScreen extends AppCompatActivity implements Observer {

    Model mModel;
    Button btn;
    String name;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        btn = (Button) findViewById(R.id.next);
        username = (EditText)findViewById(R.id.name);
        btn.setEnabled(false);

        // Get Model instance
        mModel = Model.getInstance();
        mModel.addObserver(this);

        mModel.initObservers();

        username.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if(s.toString().equals("")) {
                    btn.setEnabled(false);
                } else {
                    btn.setEnabled(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Create controller for button
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                name = username.getText().toString();
                mModel.setName(name);
                Intent intent = new Intent(getApplicationContext(), TopicSelection.class);

                // Start activity
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void update(Observable o, Object arg)
    {

    }
}
