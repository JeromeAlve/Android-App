package ca.uwaterloo.cs349.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class QScreen extends AppCompatActivity implements Observer {

    Model mModel;
    TextView name, pageNum, question;
    Button logout, prev, next;
    ImageView img;
    RadioGroup group;
    RadioButton [] q1 = new RadioButton[4];
    RelativeLayout checkboxes;
    CheckBox [] cb = new CheckBox[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qscreen);
        setTitle("Question");

        // Get Model instance
        mModel = Model.getInstance();
        mModel.addObserver(this);

        name = (TextView)findViewById(R.id.wName1);
        question = (TextView)findViewById(R.id.question);
        pageNum = (TextView)findViewById(R.id.page);
        logout = (Button) findViewById(R.id.logout1);
        prev = (Button) findViewById(R.id.prev);
        next = (Button) findViewById(R.id.next);
        img= (ImageView) findViewById(R.id.image);

        group = (RadioGroup) findViewById(R.id.rb);

        q1[0] = new RadioButton(this);
        q1[0].setText("Canada");
        q1[1] = new RadioButton(this);
        q1[1].setText("Taiwan");
        q1[2] = new RadioButton(this);
        q1[2].setText("China");
        q1[3] = new RadioButton(this);
        q1[3].setText("Peru");
        group.addView(q1[0]); group.addView(q1[1]); group.addView(q1[2]); group.addView(q1[3]);

        checkboxes = (RelativeLayout) findViewById(R.id.checkboxes);

        cb[0] = (CheckBox) findViewById(R.id.checkBox1);
        cb[0].setText("Brazil");
        cb[1] = (CheckBox) findViewById(R.id.checkBox2);
        cb[1].setText("Ivory Coast");
        cb[2] = (CheckBox) findViewById(R.id.checkBox3);
        cb[2].setText("Argentina");
        cb[3] = (CheckBox) findViewById(R.id.checkBox4);
        cb[3].setText("Luxembourg");
        //checkboxes.addView(q1[0]); checkboxes.addView(q1[1]); checkboxes.addView(q1[2]); checkboxes.addView(q1[3]);


        prev.setEnabled(false);
        question.setText("Q1: Select the country that has this flag");
        mModel.initObservers();


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

        prev.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mModel.prevPage();

                if(mModel.getCur() == 1) {
                    prev.setEnabled(false);
                } else {
                    prev.setEnabled(true);
                }
                if(mModel.getCur() == mModel.getNum()){
                    next.setText("Finish");
                }
                else{
                    next.setText("Next");
                }
                pageNum.setText(String.valueOf(mModel.getCur())+"/"+String.valueOf(mModel.getNum()));


                if(mModel.getCur() == 1){
                    img.setImageResource(R.drawable.image1);
                    question.setText("Q1: Select the country that has this flag");
                    q1[0].setText("Canada");
                    q1[1].setText("Taiwan");
                    q1[2].setText("China");
                    q1[3].setText("Peru");

                    for(int i =0; i<4 ; i++){

                        if(cb[i].isChecked()){
                            mModel.answers[1][i]=1;
                        }
                        else {
                            mModel.answers[1][i] = 0;
                        }
                    }

                    for(int i =0; i<4 ; i++){

                        if( mModel.answers[0][i]==1){
                            q1[i].setChecked(true);
                        }
                        else {
                            q1[i].setChecked(false);
                        }
                    }
                    group.setVisibility(View.VISIBLE);
                    checkboxes.setVisibility(View.INVISIBLE);

                }
                if(mModel.getCur() == 2){
                    img.setImageResource(R.drawable.image2);
                    question.setText("Q2: Select the countries that have these flags");

                    for(int i =0; i<4 ; i++){

                        if(q1[i].isChecked()){
                            mModel.answers[2][i]=1;
                        }
                        else {
                            mModel.answers[2][i] = 0;
                        }
                    }
                    for(int i =0; i<4 ; i++){

                        if( mModel.answers[1][i]==1){
                            cb[i].setChecked(true);
                        }
                        else {
                            cb[i].setChecked(false);
                        }
                    }
                    group.setVisibility(View.INVISIBLE);
                    checkboxes.setVisibility(View.VISIBLE);

                }
                if(mModel.getCur() == 3){
                    img.setImageResource(R.drawable.image3);
                    question.setText("Q3: Select the country that has this flag");
                    q1[0].setText("Netherlands");
                    q1[1].setText("Taiwan");
                    q1[2].setText("China");
                    q1[3].setText("Slovakia");

                    for(int i =0; i<4 ; i++){

                        if(q1[i].isChecked()){
                            mModel.answers[3][i]=1;
                        }
                        else {
                            mModel.answers[3][i] = 0;
                        }
                    }
                    for(int i =0; i<4 ; i++){

                        if( mModel.answers[2][i]==1){
                            q1[i].setChecked(true);
                        }
                        else {
                            q1[i].setChecked(false);
                        }
                    }
                    group.setVisibility(View.VISIBLE);
                    checkboxes.setVisibility(View.INVISIBLE);
                }
                if(mModel.getCur() == 4){
                    img.setImageResource(R.drawable.image4);
                    question.setText("Q4: Select the country that has this flag");
                    q1[0].setText("Canada");
                    q1[1].setText("India");
                    q1[2].setText("Brazil");
                    q1[3].setText("South Korea");

                    for(int i =0; i<4 ; i++){

                        if(cb[i].isChecked()){
                            mModel.answers[4][i]=1;
                        }
                        else {
                            mModel.answers[4][i] =0;
                        }
                    }
                    for(int i =0; i<4 ; i++){

                        if( mModel.answers[3][i]==1){
                            q1[i].setChecked(true);
                        }
                        else {
                            q1[i].setChecked(false);
                        }
                    }
                    group.setVisibility(View.VISIBLE);
                    checkboxes.setVisibility(View.INVISIBLE);

                }

            }
        });

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                prev.setEnabled(true);
                mModel.nextPage();
                if(mModel.getCur() == mModel.getNum()){
                    next.setText("Finish");
                }
                else{
                    next.setText("Next");
                }

                if(mModel.getCur() > mModel.getNum()){
                    //FINISHED
                    //Calculate score
                    for(int i = 0; i< 5; i++){
                        mModel.wrong[i] = false;
                    }

                    for(int i =0; i<4 ; i++){

                        if(cb[i].isChecked()){
                            mModel.answers[4][i]=1;
                        }
                        else {
                            mModel.answers[4][i] =0;
                        }
                    }


                    for(int i = 0; i< mModel.getNum(); i++){
                        if(Arrays.equals(mModel.answers[i],mModel.correct[i])){
                            mModel.score++;
                        }
                        else{
                            mModel.wrong[i] = true;
                        }

                    }

                    Intent intent = new Intent(getApplicationContext(), Results.class);
                    startActivity(intent);
                    finish();


                }
                else {
                    //Log.d("num qs", String.valueOf(mModel.getCur()));
                    pageNum.setText(String.valueOf(mModel.getCur()) + "/" + String.valueOf(mModel.getNum()));

                    // Q1 -> Q2
                    if(mModel.getCur() == 2){
                        img.setImageResource(R.drawable.image2);
                        question.setText("Q2: Select the countries that have these flags");

                        for(int i =0; i<4 ; i++){

                            if(q1[i].isChecked()){
                                mModel.answers[0][i]= 1;
                            }
                            else {
                                mModel.answers[0][i] = 0;
                            }
                        }
                        for(int i =0; i<4 ; i++){

                            if( mModel.answers[1][i]==1){
                                cb[i].setChecked(true);
                            }
                            else {
                                cb[i].setChecked(false);
                            }
                        }
                        group.setVisibility(View.INVISIBLE);
                        checkboxes.setVisibility(View.VISIBLE);

                    }
                    if(mModel.getCur() == 3){
                        img.setImageResource(R.drawable.image3);
                        question.setText("Q3: Select the country that has this flag");
                        q1[0].setText("Netherlands");
                        q1[1].setText("Taiwan");
                        q1[2].setText("China");
                        q1[3].setText("Slovakia");

                        for(int i =0; i<4 ; i++){

                            if(cb[i].isChecked()){
                                mModel.answers[1][i]=1;
                            }
                            else {
                                mModel.answers[1][i] = 0;
                            }
                        }
                        for(int i =0; i<4 ; i++){

                            if( mModel.answers[2][i]==1){
                                q1[i].setChecked(true);
                            }
                            else {
                                q1[i].setChecked(false);
                            }
                        }
                        group.setVisibility(View.VISIBLE);
                        checkboxes.setVisibility(View.INVISIBLE);
                    }
                    if(mModel.getCur() == 4){
                        img.setImageResource(R.drawable.image4);
                        question.setText("Q4: Select the country that has this flag");
                        q1[0].setText("Canada");
                        q1[1].setText("India");
                        q1[2].setText("Brazil");
                        q1[3].setText("South Korea");

                        for(int i =0; i<4 ; i++){

                            if(q1[i].isChecked()){
                                mModel.answers[2][i]=1;
                            }
                            else {
                                mModel.answers[2][i] =0;
                            }
                        }
                        for(int i =0; i<4 ; i++){

                            if( mModel.answers[3][i]==1){
                                q1[i].setChecked(true);
                            }
                            else {
                                q1[i].setChecked(false);
                            }
                        }
                        group.setVisibility(View.VISIBLE);
                        checkboxes.setVisibility(View.INVISIBLE);

                    }
                    if(mModel.getCur() == 5){
                        img.setImageResource(R.drawable.image5);
                        question.setText("Q5: Select the countries that have these flags");
                        cb[0].setText("Canada");
                        cb[1].setText("Taiwan");
                        cb[2].setText("South Africa");
                        cb[3].setText("United Kingdom");

                        for(int i =0; i<4 ; i++){

                            if(q1[i].isChecked()){
                                mModel.answers[3][i]=1;
                            }
                            else {
                                mModel.answers[3][i] =0;
                            }
                        }
                        for(int i =0; i<4 ; i++){

                            if( mModel.answers[4][i]==1){
                                cb[i].setChecked(true);
                            }
                            else {
                                cb[i].setChecked(false);
                            }
                        }
                        group.setVisibility(View.INVISIBLE);
                        checkboxes.setVisibility(View.VISIBLE);

                    }
                }
            }
        });

    }

    @Override
    public void update(Observable o, Object arg)
    {
        name.setText("Name:  " + String.valueOf(mModel.getName()));
        pageNum.setText(String.valueOf(mModel.getCur())+"/"+String.valueOf(mModel.getNum()));
    }
}
