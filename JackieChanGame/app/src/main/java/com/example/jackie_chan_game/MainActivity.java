package com.example.jackie_chan_game;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tutorials.android.particles.CommonParticles;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button GoUp;
    Button GoRight;
    Button GoDown;
    Button GoLeft;
    DialogClick dialog_click;
    DialogChoose dialog_choose;
    DialogDone dialog_done;
    ArrayList<String> TalismansNames = new ArrayList<>(Arrays.asList("Rooster Talisman", "Ox Talisman", "Snake Talisman",
                                                                     "Sheep Talisman", "Dragon Talisman", "Rabbit Talisman",
                                                                     "Rat Talisman", "Horse Talisman", "Monkey Talisman",
                                                                     "Dog Talisman", "Pig Talisman", "Tiger Talisman"));
    ArrayList<Integer> TalismansImages = new ArrayList<>(Arrays.asList(R.drawable.rooster_talisman, R.drawable.ox_talisman, R.drawable.snake_talisman,
                                                                       R.drawable.sheep_talisman, R.drawable.dragon_talisman, R.drawable.rabbit_talisman,
                                                                       R.drawable.rat_talisman, R.drawable.horse_talisman, R.drawable.monkey_talisman,
                                                                       R.drawable.dog_talisman, R.drawable.pig_talisman, R.drawable.tiger_talisman));
    ListTalismanAdapter adapter;
    ZeroMainRoom mainRoom;

    String global_case = "";
    int current = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoUp = findViewById(R.id.GoUp); GoRight = findViewById(R.id.GoRight);
        GoDown = findViewById(R.id.GoDown); GoLeft = findViewById(R.id.GoLeft);

        adapter = new ListTalismanAdapter(MainActivity.this, R.layout.list_talisman, TalismansNames, TalismansImages);

        dialog_click = new DialogClick(MainActivity.this);
        dialog_choose = new DialogChoose(MainActivity.this);
        dialog_done = new DialogDone(MainActivity.this);

        mainRoom = new ZeroMainRoom(0);
    }

    public class ZeroMainRoom {

        // Common
        ImageView GoldKey;
        ImageView JackieChan;


        // Special
        ImageView Tower;

        ZeroMainRoom (int state) {
            JackieChan = findViewById(R.id.JackieChan);
            GoldKey = findViewById(R.id.Key);

            switch (state) {
                case 0: {
                    Tower = findViewById(R.id.Tower);
                    Tower.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog_click.showDialog("This is a tower. One of the key on the roof.");
                            global_case = "Tower";
                        }
                    });
                    break;
                }
            }
        }
    }

    public class DialogClick {
        Dialog dialog;
        TextView DialogClickMessage;
        Button DialogClickCancel;
        Button DialogClickUseTalisman;

        DialogClick(Context context) {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog);
            DialogClickMessage = dialog.findViewById(R.id.Message);
            DialogClickCancel = dialog.findViewById(R.id.Cancel);
            DialogClickUseTalisman = dialog.findViewById(R.id.UseTalisman);
            DialogClickCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            DialogClickUseTalisman.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    dialog_choose.showDialog();
                }
            });
        }

        void showDialog(String message) {
            DialogClickMessage.setText(message);
            dialog.show();
        }
    } // Dialog Click

    public class DialogChoose {
        Dialog dialog;
        Button DialogChooseCancel;
        Button DialogChooseUseTalisman;

        DialogChoose (Context context) {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_talismans);
            DialogChooseCancel = dialog.findViewById(R.id.Cancel);
            DialogChooseUseTalisman = dialog.findViewById(R.id.UseTalisman);
            DialogChooseUseTalisman.setEnabled(false);
            DialogChooseCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            DialogChooseUseTalisman.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Do();
                }
            });
            ListView listView = dialog.findViewById(R.id.ListTalismans);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    current = position;
                    DialogChooseUseTalisman.setEnabled(true);
                    for (int i = 0; i < TalismansNames.size(); i++) {
                        View view1 = getViewByPosition(i, listView);
                        view1.setBackgroundColor(Color.WHITE);
                    }
                    View view1 = getViewByPosition(position, listView);
                    view1.setBackgroundColor(Color.GREEN);
                }
            });
        }

        private View getViewByPosition(int pos, ListView listView) {
            final int firstListItemPosition = listView.getFirstVisiblePosition();
            final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

            if (pos < firstListItemPosition || pos > lastListItemPosition ) {
                return listView.getAdapter().getView(pos, null, listView);
            } else {
                final int childIndex = pos - firstListItemPosition;
                return listView.getChildAt(childIndex);
            }
        }

        void showDialog() {
            dialog.show();
        }

    } // DialogChoose

    public class DialogDone {
        Dialog dialog;
        TextView DialogClickMessage;
        Button DialogDoneOK;

        DialogDone(Context context) {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_done);
            DialogClickMessage = dialog.findViewById(R.id.Message);
            DialogDoneOK = dialog.findViewById(R.id.Cancel);
            DialogDoneOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }

        void showDialog(String message) {
            DialogClickMessage.setText(message);
            dialog.show();
        }
    } // DialogDone

    public void Do() {
        switch (global_case) {
            // Tower
            case "Tower": {
                switch (TalismansNames.get(current)) {
                    case "Rooster Talisman": {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tower);
                        anim.setStartOffset(500);
                        Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tower_back);
                        anim1.setStartOffset(500);
                        Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.gold_key);

                        anim.setAnimationListener(new Animation.AnimationListener() {
                            @Override public void onAnimationStart(Animation animation) {
//                                    CommonParticles.rainingParticles(findViewById(R.id.Jackie), new int[] {
//                                            Color.BLACK,Color.BLUE,Color.GREEN,Color.RED,Color.YELLOW
//                                    }).oneShot();
                            }
                            @Override
                            public void onAnimationEnd(Animation animation) {
                                mainRoom.JackieChan.startAnimation(anim1);
                                mainRoom.GoldKey.startAnimation(anim2);
                            } @Override public void onAnimationRepeat(Animation animation) { }
                        });
                        anim1.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }
                            @Override
                            public void onAnimationEnd(Animation animation) {
                                dialog_done.showDialog("Вы взлетаете на башню и достаете ключ.");
                            } @Override public void onAnimationRepeat(Animation animation) { }
                        });

                        mainRoom.JackieChan.startAnimation(anim);
                        break;
                    }
                    default: {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.showDialog("Nothing happened");
                        break;
                    }
                    }
                }
                case "Tiger": {

                }

            }
        }

    public class ListTalismanAdapter extends ArrayAdapter {

        private final int resourceLayout;
        private Context context;
        private ArrayList<String> TalismansNames;
        private ArrayList<Integer> TalismansImages;

        public ListTalismanAdapter(Context context, int resource, ArrayList<String> _Names, ArrayList<Integer> _TalismansImages) {
            super(context, resource, _Names);
            this.resourceLayout = resource;
            this.context = context;
            this.TalismansNames = _Names;
            TalismansImages = _TalismansImages;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(resourceLayout, parent, false);
            TextView TalismanName = rowView.findViewById(R.id.TalismanName);
            ImageView TalismanImage = rowView.findViewById(R.id.TalismanImage);
            TalismanName.setText(String.valueOf(TalismansNames.get(position)));
            TalismanImage.setImageResource(TalismansImages.get(position));

            return rowView;
        }
    } // ListTalismanAdapter
}


