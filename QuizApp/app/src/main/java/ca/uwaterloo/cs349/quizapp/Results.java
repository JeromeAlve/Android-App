package ca.uwaterloo.cs349.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class Results extends AppCompatActivity implements Observer {

    Model mModel;
    TextView name, score, wrong;
    Button logout, topicSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setTitle("Results");

        // Get Model instance
        mModel = Model.getInstance();
        mModel.addObserver(this);

        name = (TextView)findViewById(R.id.wName);
        score = (TextView)findViewById(R.id.score);
        wrong = (TextView)findViewById(R.id.wrong);

        mModel.initObservers();

        logout = (Button) findViewById(R.id.logout);
        topicSelection = (Button) findViewById(R.id.topicSelection);

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

        topicSelection.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mModel.logout();
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
        String w = "";
        for(int i = 0; i< mModel.getNum(); i++){
            if(mModel.wrong[i] == true){
                int a = i+1;
                w += " "+ a;
            }
        }

        if(w.length()==0){ w = "NONE!";}
        name.setText("Name:  " + String.valueOf(mModel.getName()));
        score.setText("Your Score: "+ String.valueOf(mModel.score)+"/"+String.valueOf(mModel.getNum()));
        wrong.setText("Wrong Answers: "+ w);
    }
}