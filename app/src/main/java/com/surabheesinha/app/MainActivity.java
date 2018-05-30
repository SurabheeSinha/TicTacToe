package com.surabheesinha.app;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.surabheesinha.app.Fragment.Welcome;

public class MainActivity extends AppCompatActivity {
    Button buttons[] = new Button[9];
    Button letsplay;
    TextView welcomeText;
    /*private static final int[] BUTTON_IDS = {
            R.id.button00,
            R.id.button01,
            R.id.button02,
            R.id.button10,
            R.id.button11,
            R.id.button12,
            R.id.button20,
            R.id.button21,
            R.id.button22,
    };

    int selectedRow;
    int selectedCol;

    int playerId = 1;

    int spots[][] = new int[3][3];*/

    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*welcomeText = (TextView) findViewById(R.id.textView);
        letsplay = (Button)findViewById(R.id.letsplay);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(900);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        welcomeText.startAnimation(anim);*/

        frameLayout= (FrameLayout)findViewById(R.id.container);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new Welcome());
        fragmentTransaction.commit();




        /*int counter = 0;
        for(int id : BUTTON_IDS) {
            final Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int index = 0;
                    switch(v.getId()) {
                        case R.id.button00:
                            selectedRow = 0;
                            selectedCol = 0;
                            index = 0;
                            break;
                        case R.id.button01:
                            selectedRow = 0;
                            selectedCol = 1;
                            index = 1;
                            break;
                        case R.id.button02:
                            selectedRow = 0;
                            selectedCol = 2;
                            index = 2;
                            break;
                        case R.id.button10:
                            selectedRow = 1;
                            selectedCol = 0;
                            index = 3;
                            break;
                        case R.id.button11:
                            selectedRow = 1;
                            selectedCol = 1;
                            index = 4;
                            break;
                        case R.id.button12:
                            selectedRow = 1;
                            selectedCol = 2;
                            index = 5;
                            break;
                        case R.id.button20:
                            selectedRow = 2;
                            selectedCol = 0;
                            index = 6;
                            break;
                        case R.id.button21:
                            selectedRow = 2;
                            selectedCol = 1;
                            index = 7;
                            break;
                        case R.id.button22:
                            selectedRow = 2;
                            selectedCol = 2;
                            index = 8;
                            break;
                    }
                    // Set v.image corresponding to the current player
                    String text = playerId == 1 ? "1" : "2";
                    if (index >= 0 && index < 9) {
                        buttons[index].setText(text);
                    }
                    computeState();
                }
            });
            buttons[counter++] = button;
        }*/
    }
    /*private void computeState() {
        Toast.makeText(this, "selectedRow: " + selectedRow + "selectedCol: " + selectedCol + "playerId:" + playerId, Toast.LENGTH_SHORT).show();
        spots[selectedRow][selectedCol] = playerId;

        playerId = playerId == 1 ? 2 : 1;
    }*/
}

