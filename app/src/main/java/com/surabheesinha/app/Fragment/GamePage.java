package com.surabheesinha.app.Fragment;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.surabheesinha.app.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GamePage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GamePage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GamePage extends Fragment {

    Button buttons[] = new Button[9];
    private static final int[] BUTTON_IDS = {
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

    int player1Points = 5;
    int player2Points = 10;

    int pointsForPlayer1ToWin = player1Points * player1Points * player1Points;
    int pointsForPlayer2ToWin = player2Points * player2Points * player2Points;

    int row0Product= 1;
    int row1Product= 1;
    int row2Product= 1;
    int col0Product= 1;
    int col1Product= 1;
    int col2Product= 1;
    int diag1Product= 1;
    int diag2Product= 1;

    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String player1name;
    private String player2name;
    private String markp1;
    private String markp2;
    String p1name;
    String p2name;
    Drawable img;
    int index = 0;


    private OnFragmentInteractionListener mListener;

    public GamePage() {
        // Required empty public constructor
    }


    public static GamePage newInstance(String param1, String param2) {
        GamePage fragment = new GamePage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            player1name = getArguments().getString("player1name");
            Log.e("player1nameA",player1name);
            player2name = getArguments().getString("player2name");
            Log.e("player2nameA",player2name);
            markp1 = getArguments().getString("P1 mark");
            Log.e("P1mark",markp1);
            markp2= getArguments().getString("P2 mark");
            Log.e("P2mark",markp2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gamepage, container, false);
        Bundle bundle = this.getArguments();
        if (getArguments() != null) {
            player1name= getArguments().getString("player1name");
            Log.e("player1name",player1name);
            player2name = getArguments().getString("player2name");
            Log.e("player2name",player2name);
            markp1 = getArguments().getString("P1 mark");
            Log.e("P1mark",markp1);
            markp2= getArguments().getString("P2 mark");
            Log.e("P2mark",markp2);
        }

        int counter = 0;

        for(int id : BUTTON_IDS) {
            final Button button = view.findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

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
                    if(playerId==1){
                        p1name = player1name;
                        if(markp1=="O"){
                            img = getContext().getResources().getDrawable(R.drawable.iconknot);
                            //  ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) view.getLayoutParams();
                            Log.e("p1",p1name+markp1);
                        }else{
                            img = getContext().getResources().getDrawable(R.drawable.iconcross);
                            Log.e("p1",p1name+markp1);
                        }
                    }else if(playerId ==2){
                        p2name = player2name;
                        if(markp2=="O"){
                            img = getContext().getResources().getDrawable(R.drawable.iconknot);
                            Log.e("p2",p2name+markp2);
                        }else{
                            img = getContext().getResources().getDrawable(R.drawable.iconcross);
                            Log.e("p2",p1name+markp2);;
                        }

                    }

                   /* if(playerId==1 && markp1=="O"){
                         img = getContext().getResources().getDrawable(R.drawable.iconknot);
                         Log.e("p1",p1name+markp1);
                    }

                    if(playerId==1 && markp1=="X"){
                         img = getContext().getResources().getDrawable(R.drawable.iconcross);
                        Log.e("p1",p1name+markp1);
                    }
                    if(playerId==2 && markp1=="O"){
                         img = getContext().getResources().getDrawable(R.drawable.iconknot);
                        Log.e("p2",p2name+markp2);
                    }
                    if(playerId==2 && markp1=="X"){
                         img = getContext().getResources().getDrawable(R.drawable.iconcross);
                        Log.e("p2",p1name+markp2);
                    }*/


                   // Drawable img = playerId == 1 && markp1=="O" ? getContext().getResources().getDrawable(R.drawable.iconknot) : getContext().getResources().getDrawable(R.drawable.iconcross);
                    String text = playerId == 1 ? "1" : "2";
                    if (index >= 0 && index < 9) {
                        //buttons[index].setText(text);
                        buttons[index].setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
                        buttons[index].setClickable(false);
                    }
                    computeState();
                }
            });
            buttons[counter++] = button;
        }

        return view;
    }
    private void computeState() {
        int winner = 0;
        int points = playerId == 1 ? player1Points : player2Points;

        if (selectedRow == 0) {
            row0Product *= points;
        }
        if (selectedRow == 1) {
            row1Product *= points;
        }
        if (selectedRow == 2) {
            row2Product *= points;
        }
        if (selectedRow == selectedCol) {
            diag1Product *= points;
        }
        if (selectedRow + selectedCol == 2) {
            diag2Product *= points;
        }
        if (selectedCol == 0) {
            col0Product *= points;
        }
        if (selectedCol == 1) {
            col1Product *= points;
        }
        if (selectedCol == 2) {
            col2Product *= points;
        }

        if (row0Product == pointsForPlayer1ToWin || row1Product== pointsForPlayer1ToWin || row2Product== pointsForPlayer1ToWin || col0Product== pointsForPlayer1ToWin || col1Product== pointsForPlayer1ToWin || col2Product== pointsForPlayer1ToWin || diag1Product== pointsForPlayer1ToWin || diag2Product== pointsForPlayer1ToWin) {
            winner = 1;
            buttons[index].setClickable(false);

        } else if (row0Product == pointsForPlayer2ToWin || row1Product== pointsForPlayer2ToWin || row2Product== pointsForPlayer2ToWin || col0Product== pointsForPlayer2ToWin || col1Product== pointsForPlayer2ToWin || col2Product== pointsForPlayer2ToWin || diag1Product== pointsForPlayer2ToWin || diag2Product== pointsForPlayer2ToWin) {
            winner = 2;
            buttons[index].setClickable(false);
        }

        if (winner != 0) {
            Toast.makeText(getContext(), "Won: " + winner, Toast.LENGTH_SHORT).show();
        }
        playerId = playerId == 1 ? 2 : 1;
    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
