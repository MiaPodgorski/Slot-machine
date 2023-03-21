package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView counter;
    ImageView textView1;
    ImageView textView3;
    Button button;

    Drawable cherry;
    Drawable grape;
    Drawable pear;
    Drawable strawberry;

    int time;
    int time2;
    int time3;
    int speed;
    int speed2;
    int speed3;

    CountEvent event;
    Handler handler;

    CountEvent2 event2;
    Handler handler2;

    CountEvent3 event3;
    Handler handler3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = findViewById(R.id.counter);
        textView1= findViewById(R.id.textView1);
        textView3= findViewById(R.id.textView3);
        button = findViewById(R.id.button);



        cherry=getDrawable(R.drawable.cherry);
        grape=getDrawable(R.drawable.grape);
        pear=getDrawable(R.drawable.pear);
        strawberry=getDrawable(R.drawable.strawberry);

        time = 0;
        time2=0;
        time3=0;
        speed=100;
        speed2=150;
        speed3=200;

        event = new CountEvent();
        handler = new Handler();

        event2 = new CountEvent2();
        handler2 = new Handler();

        event3 = new CountEvent3();
        handler3 = new Handler();







        //checking if bundle is empty if not set text to info
        if(savedInstanceState!= null){
            time=savedInstanceState.getInt("time");
      //      counter.setText(time+"");
        }



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button.getText().equals("start")) {
                    handler.postDelayed(event, speed);
                    handler2.postDelayed(event2, speed2);
                    handler3.postDelayed(event3, speed3);
                    button.setText("Stop");
                }else {
                    handler.removeCallbacks(event);
                    handler2.removeCallbacks(event2);
                    handler3.removeCallbacks(event3);
                    button.setText("start");
                    if ((time == time2) && (time==time3)){
                        Toast t =Toast.makeText(getApplicationContext(), "WINNER!", Toast.LENGTH_SHORT);
                        t.show();
                    }else {
                        Toast n = Toast.makeText(getApplicationContext(), "Sorry try again", Toast.LENGTH_SHORT);
                        n.show();
                    }
                }
            }
        });
    }
    private class CountEvent3 implements Runnable{
        @Override
        public void run(){
                time3++;
            if(time3==4){
                time3=0;
            }
            if(time3==0) {
                textView3.setImageDrawable(cherry);
            }else if(time3==1){
                textView3.setImageDrawable(grape);
            }else if(time3==2){
            textView3.setImageDrawable(pear);
           }else if(time3==3){
            textView3.setImageDrawable(strawberry);
        }
            handler3.postDelayed(event3,speed3);
        }
    }

    private class CountEvent2 implements Runnable{
        @Override
        public void run(){
                time2++;
                if(time2==4){
                    time2=0;
                }
            if(time2==0) {
                textView1.setImageDrawable(cherry);
            }else if(time2==1){
                textView1.setImageDrawable(grape);
            }else if(time2==2){
                textView1.setImageDrawable(pear);
            }else if(time2==3){
                textView1.setImageDrawable(strawberry);
            }
                handler2.postDelayed(event2,speed2);
        }
    }

    private class CountEvent implements Runnable{

        @Override
        public void run(){
                time++;
                if(time==4){
                    time=0;
                }
            if(time==0) {
                counter.setImageDrawable(cherry);
            }else if(time==1){
                counter.setImageDrawable(grape);
            }else if(time==2){
                counter.setImageDrawable(pear);
            }else if(time==3){
                counter.setImageDrawable(strawberry);
            }
            handler.postDelayed(event, speed);
        }
    }

    //save info in bundle if activity is destroyed like when reoritnet the screen
    //purpose app retains info
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("time", time);
        super.onSaveInstanceState(savedInstanceState); //must be last line
    }

}