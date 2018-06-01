package com.surabheesinha.app.Fragment;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.surabheesinha.app.R;


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

    //playerId can be 1 or 2
    int nextPlayerId = 1;
    int winnerId = 0;

    int numberOfButtonsClicked = 0;
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

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String player1name;
    private String player2name;
    private String markForPlayer1;
    private String markForPlayer2;
    Drawable bgImgForSelectedButton;

    TextView playername;
    TextView markSelected;


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
            Log.e("player1nameA", player1name);
            player2name = getArguments().getString("player2name");
            Log.e("player2nameA", player2name);
            markForPlayer1 = getArguments().getString("P1 mark");
            Log.e("P1mark", markForPlayer1);
            markForPlayer2 = getArguments().getString("P2 mark");
            Log.e("P2mark", markForPlayer2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gamepage, container, false);
        playername = (TextView) view.findViewById(R.id.name);
        markSelected = (TextView) view.findViewById(R.id.mark);

        Bundle bundle = this.getArguments();
        if (getArguments() != null) {
            player1name = getArguments().getString("player1name");
            Log.e("player1name", player1name);
            player2name = getArguments().getString("player2name");
            Log.e("player2name", player2name);
            markForPlayer1 = getArguments().getString("P1 mark");
            Log.e("P1mark", markForPlayer1);
            markForPlayer2 = getArguments().getString("P2 mark");
            Log.e("P2mark", markForPlayer2);
        }
        int counter = 0;
        for (int id : BUTTON_IDS) {
            final Button button = view.findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int indexOfSelectedButton = 0;
                    numberOfButtonsClicked++;
                    switch (v.getId()) {
                        case R.id.button00:
                            selectedRow = 0;
                            selectedCol = 0;
                            indexOfSelectedButton = 0;
                            break;
                        case R.id.button01:
                            selectedRow = 0;
                            selectedCol = 1;
                            indexOfSelectedButton = 1;
                            break;
                        case R.id.button02:
                            selectedRow = 0;
                            selectedCol = 2;
                            indexOfSelectedButton = 2;
                            break;
                        case R.id.button10:
                            selectedRow = 1;
                            selectedCol = 0;
                            indexOfSelectedButton = 3;
                            break;
                        case R.id.button11:
                            selectedRow = 1;
                            selectedCol = 1;
                            indexOfSelectedButton = 4;
                            break;
                        case R.id.button12:
                            selectedRow = 1;
                            selectedCol = 2;
                            indexOfSelectedButton = 5;
                            break;
                        case R.id.button20:
                            selectedRow = 2;
                            selectedCol = 0;
                            indexOfSelectedButton = 6;
                            break;
                        case R.id.button21:
                            selectedRow = 2;
                            selectedCol = 1;
                            indexOfSelectedButton = 7;
                            break;
                        case R.id.button22:
                            selectedRow = 2;
                            selectedCol = 2;
                            indexOfSelectedButton = 8;
                            break;
                    }
                    // Set v.image corresponding to the current player
                    if (nextPlayerId == 1) {
                        if (markForPlayer1 == "O") {
                            bgImgForSelectedButton = getContext().getResources().getDrawable(R.drawable.iconknot);
                        } else {
                            bgImgForSelectedButton = getContext().getResources().getDrawable(R.drawable.iconcross);
                        }
                    } else if (nextPlayerId == 2) {
                        if (markForPlayer2 == "O") {
                            bgImgForSelectedButton = getContext().getResources().getDrawable(R.drawable.iconknot);
                        } else {
                            bgImgForSelectedButton = getContext().getResources().getDrawable(R.drawable.iconcross);
                        }
                    }
                    if (indexOfSelectedButton >= 0 && indexOfSelectedButton < 9) {
                        if (nextPlayerId == 1) {
                            playername.setText(player1name);
                            markSelected.setText(markForPlayer1);
                        } else {
                            playername.setText(player2name);
                            markSelected.setText(markForPlayer2);
                        }
                        buttons[indexOfSelectedButton].setCompoundDrawablesWithIntrinsicBounds(bgImgForSelectedButton, null, null, null);
                        buttons[indexOfSelectedButton].setClickable(false);
                    }
                    computeState();
                }
            });
            buttons[counter++] = button;
        }
        return view;
    }

    private void computeState() {
        int points = nextPlayerId == 1 ? player1Points : player2Points;

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

        if (row0Product == pointsForPlayer1ToWin || row1Product == pointsForPlayer1ToWin || row2Product == pointsForPlayer1ToWin || col0Product == pointsForPlayer1ToWin || col1Product == pointsForPlayer1ToWin || col2Product == pointsForPlayer1ToWin || diag1Product == pointsForPlayer1ToWin || diag2Product == pointsForPlayer1ToWin) {
            winnerId = 1;
        } else if (row0Product == pointsForPlayer2ToWin || row1Product == pointsForPlayer2ToWin || row2Product == pointsForPlayer2ToWin || col0Product == pointsForPlayer2ToWin || col1Product == pointsForPlayer2ToWin || col2Product == pointsForPlayer2ToWin || diag1Product == pointsForPlayer2ToWin || diag2Product == pointsForPlayer2ToWin) {
            winnerId = 2;
        }

        if (winnerId != 0) {
            if (winnerId == 1) {
                alertBox(player1name);
            } else {
                alertBox(player2name);
            }
        } else if (numberOfButtonsClicked == 9){
            alertBoxNoWinner();
        }
        // Switch the player
        nextPlayerId = nextPlayerId == 1 ? 2 : 1;
    }

    public void alertBox(String winnerName) {
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Hey " + winnerName + ", You Won !!");
        builder.setMessage("Congratulations " + winnerName + "!!");
        builder.setCancelable(false);
        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Fragment fragment = new PlayersInfo();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                Toast.makeText(getContext(), "Select from the Alert Box", Toast.LENGTH_LONG).show();
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });
        builder.show();
    }

    public void alertBoxNoWinner() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
        builder.setTitle("It's a Draw");
        builder.setMessage("No worries, you can start the game again");
        builder.setCancelable(false);
        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Fragment fragment = new PlayersInfo();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                Toast.makeText(getContext(), "Select from the Alert Box", Toast.LENGTH_LONG).show();
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });
        builder.show();
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
