package com.surabheesinha.app.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.surabheesinha.app.R;

import java.util.ArrayList;
import java.util.List;


public class PlayersInfo extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    EditText playerName1;
    EditText playerName2;
    Spinner player1MarkSelect;
    Spinner selectP2;
    String markSelectedbyP1 = null;
    String markSelectedbyP2 = null;
    TextView textMarkForP2;
    Button letsPlayButton;
    String p1name = null;
    String p2name = null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PlayersInfo() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static PlayersInfo newInstance(String param1, String param2) {
        PlayersInfo fragment = new PlayersInfo();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.playersinfo, container, false);
        playerName1 = (EditText) view.findViewById(R.id.pname1);
        playerName1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        playerName2 = (EditText) view.findViewById(R.id.pname2);
        playerName2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        letsPlayButton = (Button) view.findViewById(R.id.letsplay);
        player1MarkSelect = (Spinner) view.findViewById(R.id.pselect1);
        textMarkForP2 = (TextView) view.findViewById(R.id.textmarkSelectedbyP2);
        final List<String> list = new ArrayList<String>();
        list.add("Mark");
        list.add("O");
        list.add("X");
        ArrayAdapter<String> arrayAdapterP1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);
        arrayAdapterP1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        player1MarkSelect.setPrompt("Select a mark");
        player1MarkSelect.setAdapter(arrayAdapterP1);

        player1MarkSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                if (position > 0) {
                    markSelectedbyP1 = list.get(position);
                    Log.e("markSelectedbyP1", markSelectedbyP1);
                    if (markSelectedbyP1 == "X") {
                        markSelectedbyP2 = "O";
                        textMarkForP2.setText(markSelectedbyP2);
                    }
                } else {
                    markSelectedbyP2 = "X";
                    textMarkForP2.setText(markSelectedbyP2);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        textMarkForP2.setText(markSelectedbyP2);
        letsPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p1name = playerName1.getText().toString();
                ;
                String p2name = playerName2.getText().toString();
                if (playerName1.getText().toString().matches("")) {
                    p1name = "Player1";
                }

                if (playerName1.getText().toString().matches("")) {
                    p2name = "Player2";
                }

                Log.e("Player1", p1name);
                Log.e("Player2", p2name);

                if (markSelectedbyP1 == null || markSelectedbyP2 == null) {
                    markSelectedbyP1 = "O";
                    markSelectedbyP2 = "X";
                }
                Fragment fragment = new GamePage();
                Bundle args = new Bundle();
                args.putString("player1name", p1name);
                args.putString("player2name", p2name);
                args.putString("P1 mark", markSelectedbyP1);
                args.putString("P2 mark", markSelectedbyP2);
                fragment.setArguments(args);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return view;
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
