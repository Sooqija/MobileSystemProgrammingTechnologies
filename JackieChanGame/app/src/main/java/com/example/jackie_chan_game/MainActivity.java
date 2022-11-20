package com.example.jackie_chan_game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

// TODO Анимация файрбола
// TODO Анимация талисмана овцы
// TODO Реализовать Люк для завершения игры
// TODO Анимация ухода в невидимость
// TODO Анимация воды

public class MainActivity extends AppCompatActivity {

    Button GoUp;
    Button GoRight;
    Button GoDown;
    Button GoLeft;
    int state0 = 0;
    int state1 = 0;
    int state2 = 0;
    int state3 = 0;
    int state4 = 0;
    DialogClick dialog_click;
    DialogChoose dialog_choose;
    DialogDone dialog_done;
    DialogPause dialog_pause;
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
    OneAboveRoom aboveRoom;
    TwoRightRoom rightRoom;
    ThreeBelowRoom belowRoom;
    FourLeftRoom leftRoom;

    String global_case = "";
    int current = -1;
    int keys = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ListTalismanAdapter(MainActivity.this, R.layout.list_talisman, TalismansNames, TalismansImages);

        dialog_click = new DialogClick(MainActivity.this);
        dialog_choose = new DialogChoose(MainActivity.this);
        dialog_done = new DialogDone(MainActivity.this);
        dialog_pause = new DialogPause(MainActivity.this);

        // This
        mainRoom = new ZeroMainRoom(state0, 0);
    }

    public class ZeroMainRoom {

        // Common
        ImageView GoldKey;
        ImageView JackieChan;

        // Special
        ImageView Tower;

        ZeroMainRoom (int state, int who_was_that) {
            init();
            startEntryAnimation(who_was_that);

            switch (state) {
                case 0: {
                    Tower.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            global_case = "Tower";
                            dialog_click.showDialog("This is a tower. One of the key on the roof.");
                        }
                    });
                    break;
                }
                case 1: {
                    GoldKey.setVisibility(View.INVISIBLE);
                }
            }
        } // constructor ZeroMainRoom

        void init () {
            setContentView(R.layout.activity_main);
            activatePause(findViewById(R.id.Pause));
            JackieChan = findViewById(R.id.JackieChan);
            GoldKey = findViewById(R.id.Key);
            Tower = findViewById(R.id.Tower);
            GoUp = findViewById(R.id.GoUp); GoRight = findViewById(R.id.GoRight);
            GoDown = findViewById(R.id.GoDown); GoLeft = findViewById(R.id.GoLeft);
            GoUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_leave_main_to_up);
                    anim.setStartOffset(500);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override public void onAnimationStart(Animation animation) { }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            aboveRoom = new OneAboveRoom(state1);
                        }
                        @Override public void onAnimationRepeat(Animation animation) { }
                    });
                    JackieChan.startAnimation(anim);
                }
            });
            GoRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_leave_main_to_right);
                    anim.setStartOffset(500);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override public void onAnimationStart(Animation animation) { }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            rightRoom = new TwoRightRoom(state2);
                        }
                        @Override public void onAnimationRepeat(Animation animation) { }
                    });
                    JackieChan.startAnimation(anim);
                }
            });
            GoDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_leave_main_to_down);
                    anim.setStartOffset(500);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override public void onAnimationStart(Animation animation) { }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            belowRoom = new ThreeBelowRoom(state3);
                        }
                        @Override public void onAnimationRepeat(Animation animation) { }
                    });
                    JackieChan.startAnimation(anim);
                }
            });
            GoLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_leave_main_to_left);
                    anim.setStartOffset(500);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override public void onAnimationStart(Animation animation) { }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            leftRoom = new FourLeftRoom(state4);
                        }
                        @Override public void onAnimationRepeat(Animation animation) { }
                    });
                    JackieChan.startAnimation(anim);
                }
            });
        } // init

        void startEntryAnimation(int who_was_that) {
            keyAnimation();
            switch (who_was_that) {
                case 0: {
                    break;
                }
                case 1: {
                    Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_leave_main_to_up);
                    anim.setStartOffset(500);
                    anim.setInterpolator(new ReverseInterpolator());
                    JackieChan.startAnimation(anim);
                    break;
                }
                case 2: {
                    Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_leave_main_to_right);
                    anim.setStartOffset(500);
                    anim.setInterpolator(new ReverseInterpolator());
                    JackieChan.startAnimation(anim);
                    break;
                }
                case 3: {
                    Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_leave_main_to_down);
                    anim.setStartOffset(500);
                    anim.setInterpolator(new ReverseInterpolator());
                    JackieChan.startAnimation(anim);
                    break;
                }
                case 4: {
                    Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_leave_main_to_left);
                    anim.setStartOffset(500);
                    anim.setInterpolator(new ReverseInterpolator());
                    JackieChan.startAnimation(anim);
                    break;
                }
            }
        } // startEntryAnimation
    }

    public class OneAboveRoom {

        // Common
        ImageView GoldKey;
        ImageView JackieChan;
        int order;

        // Special
        ImageView Jade;
        ImageView Toru;
        ImageView ShadowEater;
        ImageView Shadow;
        TextView TimerView;

        OneAboveRoom (int state) {
            init();
            switch (state) {
                case 0: {
                    startEntryAnimation();
                    Jade.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            global_case = "Jade";
                            dialog_click.showDialog("\"О, привет Джейд!\" Кажется, она держит в руках магический пылесос от дяди.");
                        }
                    });
                    Toru.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            global_case = "Toru";
                            dialog_click.showDialog("Плохой день. Тень Тору съел поедатель теней.");
                        }
                    });
                    ShadowEater.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            global_case = "ShadowEater";
                            dialog_click.showDialog("Это поедатель теней. Опасное существо, призванное одной из масок ОНИ. Съедая тени они увеличиваются в размере. Благо, в тени других предметов Вы в безопасности.");
                        }
                    });
                    break;
                }
                case 1: {
                    ShadowEater.setVisibility(View.INVISIBLE);
                }
            }
        } // constructor OneAboveRoom

        void init () {
            setContentView(R.layout.activity_room1);
            activatePause(findViewById(R.id.Pause));
            JackieChan = findViewById(R.id.JackieChan);
            GoldKey = findViewById(R.id.Key);
            Jade = findViewById(R.id.Jade);
            Toru = findViewById(R.id.Toru);
            ShadowEater = findViewById(R.id.ShadowEater);
            Shadow = findViewById(R.id.Shadow);
            TimerView = findViewById(R.id.TimerView);
            order = 0;

            GoDown = findViewById(R.id.GoDown);
            GoDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_entry_up);
                    anim.setStartOffset(500);
                    anim.setInterpolator(new ReverseInterpolator());
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override public void onAnimationStart(Animation animation) { }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mainRoom = new ZeroMainRoom(state0, 1);
                        }
                        @Override public void onAnimationRepeat(Animation animation) { }
                    });
                    JackieChan.startAnimation(anim);
                }
            });
        } // init

        void startEntryAnimation() {
            keyAnimation();
            Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_entry_up);
            anim.setStartOffset(500);
            Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shadow_eater_move_to_torus_shadow);
            ShadowEater.startAnimation(anim1);
            ShadowEater.setRotationY(180);
            JackieChan.startAnimation(anim);
            anim1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) { }
                @Override
                public void onAnimationEnd(Animation animation) {
                    Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shadow_eaten);
                    Animation anim3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shadow_eater_grows);
                    anim2.setStartOffset(500);
                    anim3.setStartOffset(500);
                    Shadow.startAnimation(anim2);
                    ShadowEater.startAnimation(anim3);
                    anim2.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) { }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Shadow.setVisibility(View.INVISIBLE);
                            Animation anim4 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.toru_dies);
                            anim4.setStartOffset(500);
                            Toru.startAnimation(anim4);
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) { }
                    });
                }
                @Override
            public void onAnimationRepeat(Animation animation) { }
            });
        } // startEntryAnimation
    } // OneAboveRoom

    public class TwoRightRoom {

        // Common
        ImageView GoldKey;
        ImageView JackieChan;
        int order;

        // Special
        ImageView Ventile;
        ImageView Crab;
        ImageView Crack;
        ImageView Water;
        ImageView Stare;
        ImageView JackieChanCopy;
        ImageView Brace;
        TextView TimerView;

        TwoRightRoom (int state) {
            init();
            startEntryAnimation();

            switch (state) {
                case 0: {
                    Ventile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            global_case = "Ventile";
                            dialog_click.showDialog("Кажется кто-то перекрыл вентиль. Он еще и проржавел. Мне одному не хватит сил его открутить.");
                        }
                    });
                    Crab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            global_case = "Crab";
                            dialog_click.showDialog("Теперь вы не утоните в воде.");
                        }
                    });
                    Crack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            global_case = "Crack";
                            dialog_click.showDialog("Ну и дырень. Понятно каким образом эта комната была затоплена.");
                        }
                    });
                    Stare.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            global_case = "Stare";
                            dialog_click.showDialog("Ух, сколько воды. Эта комната явно была затоплена.");
                        }
                    });
                    Water.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            global_case = "Water";
                            dialog_click.showDialog("Лестница была разрушена. Теперь тут осталась просто вода.");
                        }
                    });
//                    JackieChanCopy.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            global_case = "JakieChanCopy";
//                            dialog_click.showDialog("Ваша \"Ян\" половинка. Если с ней что-то случится, то и \"Инь\" половине тоже не сдобровать!");
//                        }
//                    });
                    Brace.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            global_case = "Brace";
                            dialog_click.showDialog("Кусок ржавого металла под водой. Не думаю, что от него много пользы.");
                        }
                    });
                    break;
                }
                case 1: {
                    Crack.setVisibility(View.GONE);
                    Stare.setVisibility(View.GONE);
                }
            }
        } // constructor TwoRightRoom

        void init () {
            order = 0;
            setContentView(R.layout.activity_room2);
            activatePause(findViewById(R.id.Pause));
            JackieChan = findViewById(R.id.JackieChan);
            GoldKey = findViewById(R.id.Key);
            Ventile = findViewById(R.id.Ventile);
            Crack = findViewById(R.id.Crack);
            Crab = findViewById(R.id.Crab);
            JackieChanCopy = findViewById(R.id.JackieChanCopy);
            Water = findViewById(R.id.Water);
            Stare = findViewById(R.id.Stare);
            Brace = findViewById(R.id.Brace);
            TimerView = findViewById(R.id.TimerView);
            GoLeft = findViewById(R.id.GoLeft);
            GoLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_entry_right);
                    anim.setStartOffset(500);
                    anim.setInterpolator(new ReverseInterpolator());
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override public void onAnimationStart(Animation animation) { }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mainRoom = new ZeroMainRoom(state0, 2);
                        }
                        @Override public void onAnimationRepeat(Animation animation) { }
                    });
                    JackieChan.startAnimation(anim);
                }
            });
        } // init

        void startEntryAnimation() {
            keyAnimation();
            Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_entry_right);
            anim.setStartOffset(500);
            JackieChan.startAnimation(anim);
        } // startEntryAnimation
    }

    public class ThreeBelowRoom {
        // Common
        ImageView GoldKey;
        ImageView JackieChan;
        int order;

        // Special
        ImageView SuperMoose;
        ImageView Dwarf;
        ImageView MonkeyKing;
        ImageView Wall;
        ImageView Stone;

        ThreeBelowRoom (int state) {
            switch (state) {
                case 0: {
                    init();

                    startEntryAnimation();

                    SuperMoose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog_click.showDialog("Это СуперЛось. Обычная кукла.");
                            global_case = "SuperMoose";
                        }
                    });
                    Dwarf.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog_click.showDialog("Это Гном Полицейский. Обычная кукла.");
                            global_case = "Dwarf";
                        }
                    });
                    MonkeyKing.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog_click.showDialog("Это Король Обезьян. Обычная кукла.");
                            global_case = "MonkeyKing";
                        }
                    });
                    Wall.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog_click.showDialog("Это дверь. Она захлопнулась! Кажется, Вы в ловушке");
                            global_case = "Wall";
                        }
                    });
                    break;
                }
                case 1: {
                    JackieChan = findViewById(R.id.JackieChan);
                    dialog_done.showDialog("Завал. Пройти нельзя");
                    Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_leave_main_to_down);
                    anim.setStartOffset(500);
                    anim.setInterpolator(new ReverseInterpolator());
                    JackieChan.startAnimation(anim);
                    break;
                }
            }

        }

        void init() {
            setContentView(R.layout.activity_room3);
            activatePause(findViewById(R.id.Pause));
            JackieChan = findViewById(R.id.JackieChan);
            GoldKey = findViewById(R.id.Key);
            Wall = findViewById(R.id.Wall);
            SuperMoose = findViewById(R.id.SuperMoose);
            Dwarf = findViewById(R.id.Dwarf);
            MonkeyKing = findViewById(R.id.MonkeyKing);
            Stone = findViewById(R.id.Stone);
            order = 0;
        }

        void startEntryAnimation() {
            Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_go_down_down);
            anim.setStartOffset(500);
            JackieChan.startAnimation(anim);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override public void onAnimationStart(Animation animation) { }
                @Override
                public void onAnimationEnd(Animation animation) {
                    Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.gold_key);
                    anim.setStartOffset(500);
                    Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.wall);
                    anim1.setStartOffset(500);
                    GoldKey.startAnimation(anim);
                    Wall.setVisibility(View.VISIBLE);
                    Wall.startAnimation(anim1);
                    keys++;
                    keyAnimation();
                }
                @Override public void onAnimationRepeat(Animation animation) { }
            });
        }

    } // ThreeBelowRoom

    public class FourLeftRoom {

        // Common
        ImageView GoldKey;
        ImageView JackieChan;
        int order;

        // Special
        ImageView Uncle;
        ImageView Bed;

        FourLeftRoom (int state) {
            init();
            startEntryAnimation();
            switch (state) {
                case 0: {
                    Uncle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            global_case = "Uncle";
                            dialog_click.showDialog("Это Дядя. Он спит. Наверное, он изучал магию ключей. Будет лучше, если его не будить.");
                        }
                    });

                    GoldKey.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (order == 0) {
                                dialog_done.showDialogEndGame("Звон ключей разбудил дядю! Он поручил Вам подмести лавку. Вы програли. Еще раз?");
                            } else if (order == 1) {
                                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_dog_talisman);
                                anim.setStartOffset(500);
                                JackieChan.startAnimation(anim);
                                anim.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) { }
                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.gold_key);
                                        anim1.setStartOffset(500);
                                        Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_dog_talisman_back);
                                        anim2.setStartOffset(500);
                                        GoldKey.startAnimation(anim1);
                                        JackieChan.startAnimation(anim2);
                                        keys++;
                                        keyAnimation();
                                    }
                                    @Override
                                    public void onAnimationRepeat(Animation animation) { }
                                });
                                state4 = 1;
                            }
                        }
                    });
                    break;
                }
                case 1: {
                    GoldKey.setVisibility(View.GONE);
                }
            }
        }

        void init() {
            setContentView(R.layout.activity_room4);
            activatePause(findViewById(R.id.Pause));
            JackieChan = findViewById(R.id.JackieChan);
            GoldKey = findViewById(R.id.Key);
            Uncle = findViewById(R.id.Uncle);
            GoRight = findViewById(R.id.GoRight);
            order = 0;
            GoRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_go_right);
                    anim.setStartOffset(500);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override public void onAnimationStart(Animation animation) { }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mainRoom = new ZeroMainRoom(state0, 4);
                        }
                        @Override public void onAnimationRepeat(Animation animation) { }
                    });
                    JackieChan.startAnimation(anim);
                }
            });
        }

        void startEntryAnimation() {
            keyAnimation();
            Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_go_left_left);
            anim.setStartOffset(500);
            JackieChan.startAnimation(anim);
        }
    } // FourLeftRoom

    public void Do() {
        switch (global_case) {
            case "Tower": {
                switch (TalismansNames.get(current)) {
                    case "Rooster Talisman": {
                        keys++; // !!!
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
                                keyAnimation();
                            } @Override public void onAnimationRepeat(Animation animation) { }
                        });
                        anim1.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }
                            @Override
                            public void onAnimationEnd(Animation animation) {
                                state0 = 1;
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
                break;
            }
            case "SuperMoose": {
                switch (TalismansNames.get(current)) {
                    case "Rat Talisman": {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        belowRoom.order++;
                        if (belowRoom.order == 1) {
                            dialog_done.showDialog("Вы оживили СуперЛося.");
                        } else {
                            dialog_done.showDialog("Ничего не произошло.");
                        }
                        break;
                    }
                    default: {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.showDialog("Ничего не произошло.");
                        break;
                    }
                }
                break;
            }
            case "Dwarf": {
                switch (TalismansNames.get(current)) {
                    case "Rat Talisman": {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);

                        dialog_done.showDialogEndGame("Гном полицейский убивает Вас из напоясного лазера. Вы проиграли. Еще раз?");
                        break;
                    }
                    default: {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.showDialog("Ничего не произошло.");
                        break;
                    }
                }
                break;
            }
            case "MonkeyKing": {
                switch (TalismansNames.get(current)) {
                    case "Rat Talisman": {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.showDialogEndGame("Король обезьян превращает Вас в куклу и становится живым. Вы проиграли. Еще раз?");
                        break;
                    }
                    default: {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.showDialog("Ничего не произошло.");
                        break;
                    }
                }
                break;
            }
            case "Wall": {
                switch (TalismansNames.get(current)) {
                    case "Dragon Talisman": {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        belowRoom.Wall.clearAnimation();
                        belowRoom.Wall.setVisibility(View.GONE);
                        belowRoom.order++;
                        state3 = 1;
                        if (belowRoom.order == 2) {
                            Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.stone_fall);
                            anim.setStartOffset(500);
                            Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.moose_fly_1);
                            anim1.setStartOffset(200);
                            Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.moose_fly_2);
                            Animation anim3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_fly);
                            belowRoom.Stone.startAnimation(anim);
                            belowRoom.SuperMoose.startAnimation(anim1);
                            anim1.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) { }
                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    belowRoom.SuperMoose.startAnimation(anim2);
                                    belowRoom.JackieChan.startAnimation(anim3);
                                }
                                @Override
                                public void onAnimationRepeat(Animation animation) { }
                            });
                            anim.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) { }
                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    dialog_done.showDialog("Вы успешно спаслись");
                                    mainRoom = new ZeroMainRoom(state0, 3);
                                }
                                @Override
                                public void onAnimationRepeat(Animation animation) { }
                            });
                        } else {
                            Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.stone_fall);
                            anim.setStartOffset(500);
                            Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_go_up);
                            anim1.setStartOffset(200);
                            belowRoom.Stone.setVisibility(View.VISIBLE);
                            belowRoom.Stone.startAnimation(anim);
                            belowRoom.JackieChan.startAnimation(anim1);
                            anim.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) { }
                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    dialog_done.showDialogEndGame("Вас завалило каменной глыбой. Вы проиграли. Еще раз?");
                                }
                                @Override
                                public void onAnimationRepeat(Animation animation) { }
                            });
                        }
                        break;
                    }
                    default: {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.showDialog("Ничего не произошло.");
                        break;
                    }
                }
                break;
            }
            case "Uncle": {
                switch (TalismansNames.get(current)) {
                    case "Sheep Talisman": {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        // TODO Применение талисмана овцы
                        dialog_done.showDialog("Вы проникли в сон дяди. Ему снится его молодость. Даа..., тогда в молодости он бы спал как младенец.");
                        break;
                    }
                    case "Dog Talisman": {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        leftRoom.order++;
                        dialog_done.showDialog("Вы придали дяде молодости. Теперь у него будет хороший сон.");
                        break;
                    }
                    default: {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.showDialog("Ничего не произошло.");
                        break;
                    }
                }
                break;
            }
            case "ShadowEater": {
                switch (TalismansNames.get(current)) {
                    case "Snake Talisman": {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);

                        aboveRoom.order++;
                        if (aboveRoom.order == 1) {
                            aboveRoom.JackieChan.clearAnimation();
                            aboveRoom.JackieChan.setVisibility(View.INVISIBLE);
                            dialog_done.showDialog("Отлично! Если Вас не видно, то и тени у Вас нет");
                        } else {
                            dialog_done.showDialog("Ничего не произошло.");
                        }
                        break;
                    }
                    default: {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.showDialog("Ничего не произошло.");
                        break;
                    }
                }
                break;
            }
            case "Toru": {
                switch (TalismansNames.get(current)) {
                    case "Horse Talisman": {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        aboveRoom.order++;
                        if (aboveRoom.order == 2) {
                            Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.toru_dies);
                            anim.setStartOffset(500);
                            anim.setInterpolator(new ReverseInterpolator());
                            aboveRoom.Toru.startAnimation(anim);
                            anim.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {}
                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    aboveRoom.Shadow.clearAnimation();
                                    aboveRoom.Shadow.setVisibility(View.VISIBLE);
                                    Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shadow_eater_grows);
                                    anim2.setInterpolator(new ReverseInterpolator());
                                    aboveRoom.ShadowEater.startAnimation(anim2);
                                    Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.toru_save);
                                    anim1.setStartOffset(500);
                                    aboveRoom.Toru.startAnimation(anim1);
                                    aboveRoom.Shadow.startAnimation(anim1);
                                    anim1.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) { }
                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            aboveRoom.Shadow.clearAnimation();
                                            aboveRoom.Shadow.setVisibility(View.INVISIBLE);
                                            aboveRoom.JackieChan.setVisibility(View.VISIBLE);
                                            Animation anim3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_horse_talisman);
                                            aboveRoom.JackieChan.startAnimation(anim3);
                                            aboveRoom.Shadow.setVisibility(View.VISIBLE);
                                            dialog_done.showDialogTimer("Ура, Тору в безопасности! Но применение талисмана вывело Вас из невидимости! Поедатель теней вот-вот съест Вашу тень, берегись!", "Вы не успели ничего сделать и пожиратель теней съел Вашу тень.", aboveRoom.TimerView, 10);
                                        }
                                        @Override
                                        public void onAnimationRepeat(Animation animation) {}
                                    });
                                }
                                @Override
                                public void onAnimationRepeat(Animation animation) {}
                            });
                        } else {
                            dialog_done.showDialogEndGame("Применение талисмана вывело Вас из невидимости. Вы проиграли. Еще раз?");
                        }
                        break;
                    }
                    default: {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.showDialogEndGame("Вы выходите на свет и пожиратель теней поглащает Вашу тень. Вы проиграли. Еще раз?");
                        break;
                    }
                }
                break;
            }
            case "Jade": {
                switch (TalismansNames.get(current)) {
                    case "Rabbit Talisman": {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.cancel();
                        aboveRoom.order++;
                        if (aboveRoom.order == 3) {
                            Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_rabbit_talisman);
                            Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shadow_move);
                            aboveRoom.JackieChan.startAnimation(anim);
                            aboveRoom.Shadow.clearAnimation();
                            aboveRoom.Shadow.setVisibility(View.VISIBLE);
                            aboveRoom.Shadow.startAnimation(anim1);
                            aboveRoom.Shadow.setRotation(-40);
                            aboveRoom.Shadow.setRotationX(-40);
                            Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shadow_eater_move);
                            aboveRoom.ShadowEater.startAnimation(anim2);
                            anim2.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) { }
                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    Animation anim3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jade_takes_down_shadow_eater);
                                    aboveRoom.Jade.startAnimation(anim3);
                                    anim3.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) { }
                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            aboveRoom.ShadowEater.clearAnimation();
                                            aboveRoom.ShadowEater.setVisibility(View.INVISIBLE);
                                            keys++;
                                            keyAnimation();
                                            Animation anim4 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.gold_key);
                                            anim4.setStartOffset(500);
                                            aboveRoom.GoldKey.setVisibility(View.VISIBLE);
                                            aboveRoom.GoldKey.startAnimation(anim4);
                                            anim4.setAnimationListener(new Animation.AnimationListener() {
                                                @Override
                                                public void onAnimationStart(Animation animation) { }
                                                @Override
                                                public void onAnimationEnd(Animation animation) {
                                                    dialog_done.showDialog("Джейд удается поглотить пылесосом пожирателя теней. В благодарность за помощь Вы получаете ключ.");
                                                    state1 = 1;
                                                }
                                                @Override
                                                public void onAnimationRepeat(Animation animation) { }
                                            });
                                        }
                                        @Override
                                        public void onAnimationRepeat(Animation animation) { }
                                    });
                                }
                                @Override
                                public void onAnimationRepeat(Animation animation) { }
                            });

                  } else {
                            dialog_done.showDialogEndGame("Вы выходите на свет к Джейд и Вашу тень поглощает пожиратель теней. Вы проиграли. Еще раз?");
                        }
                        break;
                    }
                    default: {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.showDialogEndGame("Вы выходите на свет к Джейд и Вашу тень поглощает пожиратель теней. Вы проиграли. Еще раз?");
                        break;
                    }
                }
                break;
            }
            case "Stare": {
                switch (TalismansNames.get(current)) {
                    case "Tiger Talisman": {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        rightRoom.order++;
                        if (rightRoom.order == 1) {
                            rightRoom.JackieChanCopy.setVisibility(View.VISIBLE);
                            Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_tiger_talisman);
                            anim.setStartOffset(500);
                            Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_copy_tiger_talisman);
                            anim1.setStartOffset(500);
                            rightRoom.JackieChan.startAnimation(anim);
                            rightRoom.JackieChanCopy.startAnimation(anim1);
                            anim1.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) { }
                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    Animation anim3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_copy_move_to_stare);
                                    anim3.setStartOffset(500);
                                    rightRoom.JackieChanCopy.startAnimation(anim3);
                                    anim3.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {}
                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            rightRoom.Stare.setVisibility(View.INVISIBLE);
                                            rightRoom.Water.setVisibility(View.VISIBLE);
                                            GoLeft.setEnabled(false);
                                            dialog_done.showDialogTimer("О нет, лестница разрушилась! Вы сейчас утоните.", "Вы не успели спастись и утонули.",rightRoom.TimerView, 10);
                                        }
                                        @Override
                                        public void onAnimationRepeat(Animation animation) {}
                                    });

                                }
                                @Override
                                public void onAnimationRepeat(Animation animation) {}
                            });
                        }
                        break;
                    }
                    default: {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.showDialogEndGame("Лестница разрушилась, вы утонули.");
                        break;
                    }

                }
                break;
            }
            case "Water": {
                switch (TalismansNames.get(current)) {
                    case "Monkey Talisman": {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.cancel();
                        rightRoom.order++;
                        if (rightRoom.order == 2) {
                            rightRoom.JackieChanCopy.clearAnimation();
                            rightRoom.JackieChanCopy.setVisibility(View.INVISIBLE);
                            rightRoom.Crab.setVisibility(View.VISIBLE);
                            dialog_done.showDialog("Вы превратились в краба. Теперь вы не утоните.");
                        }
                        break;
                    }
                    default: {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.showDialogEndGame("Ничего не произошло");
                        break;
                    }
                }
                break;
            }
//            case "JakieChanCopy": {
//                switch (TalismansNames.get(current)) {
//                    case "Monkey Talisman": {
//                        TalismansNames.remove(current);
//                        TalismansImages.remove(current);
//                        rightRoom.order++;
//                        if (rightRoom.order == 2) {
//                            rightRoom.JackieChanCopy.clearAnimation();
//                            rightRoom.JackieChanCopy.setVisibility(View.INVISIBLE);
//                            rightRoom.Crab.setVisibility(View.VISIBLE);
//                            dialog_done.showDialog("Вы превратились в краба. Теперь вы не утоните.");
//                        }
//                        break;
//                    }
//                    default: {
//                        TalismansNames.remove(current);
//                        TalismansImages.remove(current);
//                        dialog_done.showDialogEndGame("Ничего не произошло");
//                        break;
//                    }
//                }
//                break;
//            }
            case "Ventile": {
                switch (TalismansNames.get(current)) {
                    case "Ox Talisman": {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        rightRoom.order++;
                        if (rightRoom.order == 3) {
                            GoLeft.setEnabled(false);
                            // TODO Анимация вентиля, ключа, потока воды
                            Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jakie_move_to_ventile);
                            anim.setStartOffset(500);
                            rightRoom.JackieChan.startAnimation(anim);
                            anim.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {}
                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ventile_rotate);
                                    rightRoom.Ventile.startAnimation(anim1);
                                    anim1.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {}
                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.gold_key);
                                            anim2.setStartOffset(500);
                                            rightRoom.GoldKey.setVisibility(View.VISIBLE);
                                            rightRoom.GoldKey.startAnimation(anim2);
                                            keys++;
                                            keyAnimation();
                                            anim2.setAnimationListener(new Animation.AnimationListener() {
                                                @Override
                                                public void onAnimationStart(Animation animation) { }
                                                @Override
                                                public void onAnimationEnd(Animation animation) {
                                                    dialog_done.showDialogTimer("Вы открыли клапан, ключ выплыл из дыры в трубе. Но напор воды стал хлещет с такой силой, что вентиль просто невыдержал нагрузки и сломался. Еще вот вот и вся комната будет затоплена!", "Комната была затоплена, Вы проиграли. Еще раз?", rightRoom.TimerView, 5);
                                                    Animation anim3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ventile_rotate);
                                                    rightRoom.Ventile.startAnimation(anim3);
                                                    anim3.setFillAfter(false);
                                                    anim3.setAnimationListener(new Animation.AnimationListener() {
                                                        @Override
                                                        public void onAnimationStart(Animation animation) {}
                                                        @Override
                                                        public void onAnimationEnd(Animation animation) {
                                                            rightRoom.Ventile.startAnimation(anim3);
                                                        }
                                                        @Override
                                                        public void onAnimationRepeat(Animation animation) {
                                                            rightRoom.Ventile.startAnimation(anim3);
                                                        }
                                                    });
                                                }
                                                @Override
                                                public void onAnimationRepeat(Animation animation) {
                                                }
                                            });
                                        }
                                        @Override
                                        public void onAnimationRepeat(Animation animation) {}
                                    });
                                }
                                @Override
                                public void onAnimationRepeat(Animation animation) { }
                            });
                        }
                        break;
                    }
                    default: {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.showDialogEndGame("Ничего не произошло.");
                        break;
                    }
                }
                break;
            }
            case "Crack":
            case "Brace": {
                switch (TalismansNames.get(current)) {
                    case "Pig Talisman": {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.cancel();
                        rightRoom.order++;
                        if (rightRoom.order == 4) {
                            Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.crab_move_to_crack);
                            anim.setStartOffset(500);
                            rightRoom.Crab.setVisibility(View.VISIBLE);
                            rightRoom.Crab.startAnimation(anim);
                            rightRoom.Crab.setRotation(180);
                            Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.brace_move_to_crack);
                            anim1.setStartOffset(500);
                            rightRoom.Brace.startAnimation(anim1);
                            anim1.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {}
                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    rightRoom.Ventile.clearAnimation();
                                    rightRoom.Crack.setVisibility(View.INVISIBLE);
                                    dialog_done.showDialog("Вы заварили дыру в трубе. Вы спаслись.");
                                    GoLeft.setEnabled(true);
                                }
                                @Override
                                public void onAnimationRepeat(Animation animation) {}
                            });
                        }
                        break;
                    }
                    default: {
                        TalismansNames.remove(current);
                        TalismansImages.remove(current);
                        dialog_done.showDialogEndGame("Ничего не произошло.");
                        break;
                    }
                }
                break;
            }
        }
    } // Do


    void keyAnimation () {
        ArrayList<Integer> Keys = new ArrayList<>(Arrays.asList(R.id.Key1, R.id.Key2, R.id.Key3, R.id.Key4, R.id.Key5));;
        for (int i = 0; i < keys; i++) {
            ImageView element = findViewById(Keys.get(i));
            element.setVisibility(View.VISIBLE);
            Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.get_gold_key);
            element.startAnimation(anim);
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
        Timer timer;
        int second = 0;
        String show_time;
        TextView TimerView;

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
            DialogDoneOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

        public void showDialogEndGame(String message) {
            DialogClickMessage.setText(message);
            DialogDoneOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    resetGame();
                }
            });
            dialog.show();
        }

        public void showDialogTimer(String message, String end_message, TextView _TimerView, int time) {
            TimerView = _TimerView;
            DialogClickMessage.setText(message);
            DialogDoneOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    timer = new Timer();
                    second = time;
                    TimerView.setVisibility(View.VISIBLE);
                    timer.schedule((TimerTask) (new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread((Runnable) (new Runnable() {
                                public void run() {
                                    if (second > 0) {
                                        show_time = "0:";
                                        show_time += (second < 10) ? "0" + String.valueOf(second) : String.valueOf(second);
                                        second--;
                                        second %= 60;
                                        TimerView.setText(show_time);
                                    } else {
                                        timer.cancel();
                                        TimerView.setVisibility(View.INVISIBLE);
                                        dialog_done.showDialogEndGame(end_message);
                                    }

                                }
                            }));
                        }
                    }), 0L, 1000L);
                }
            });
            dialog.show();
        }

        public void cancel() {
            timer.cancel();
            TimerView.setVisibility(View.INVISIBLE);
        }
    } // DialogDone

    public class DialogPause {
        Dialog dialog;
        TextView DialogPauseMessage;

        Button DialogPauseReset;
        Button DialogPauseMenu;
        Button DialogPauseRules;
        Button DialogPauseBack;

        DialogPause(Context context) {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_menu);
            DialogPauseMessage = dialog.findViewById(R.id.Message);
            DialogPauseReset = dialog.findViewById(R.id.Reset);
            DialogPauseMenu = dialog.findViewById(R.id.Menu);
            DialogPauseRules = dialog.findViewById(R.id.Rules);
            DialogPauseBack = dialog.findViewById(R.id.Back);
            DialogPauseReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    resetGame();
                }
            });
            DialogPauseMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Intent intent = new Intent(MainActivity.this, LoadGameActivity.class);
                    startActivity(intent);
                }
            });
            DialogPauseRules.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    Intent intent = new Intent(MainActivity.this, RulesActivity.class);
                    startActivity(intent);
                }
            });
            DialogPauseBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }

        void showDialog() {
            dialog.show();
        }
    } // DialogDone

    void activatePause(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_pause.showDialog();
            }
        });
    }

    void resetGame () {
        TalismansNames = new ArrayList<>(Arrays.asList("Rooster Talisman", "Ox Talisman", "Snake Talisman",
                                                       "Sheep Talisman", "Dragon Talisman", "Rabbit Talisman",
                                                       "Rat Talisman", "Horse Talisman", "Monkey Talisman",
                                                       "Dog Talisman", "Pig Talisman", "Tiger Talisman"));
        TalismansImages = new ArrayList<>(Arrays.asList(R.drawable.rooster_talisman, R.drawable.ox_talisman, R.drawable.snake_talisman,
                                                        R.drawable.sheep_talisman, R.drawable.dragon_talisman, R.drawable.rabbit_talisman,
                                                        R.drawable.rat_talisman, R.drawable.horse_talisman, R.drawable.monkey_talisman,
                                                        R.drawable.dog_talisman, R.drawable.pig_talisman, R.drawable.tiger_talisman));
        state0 = 0;
        state1 = 0;
        state2 = 0;
        state3 = 0;
        state4 = 0;
        keys = 0;
        adapter = new ListTalismanAdapter(MainActivity.this, R.layout.list_talisman, TalismansNames, TalismansImages);
        adapter.notifyDataSetChanged();
        dialog_click = new DialogClick(MainActivity.this);
        dialog_choose = new DialogChoose(MainActivity.this);
        dialog_done = new DialogDone(MainActivity.this);
        dialog_pause = new DialogPause(MainActivity.this);

        mainRoom = new ZeroMainRoom(state0, 0);
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

    public class ReverseInterpolator implements Interpolator {
        @Override
        public float getInterpolation(float paramFloat) {
            return Math.abs(paramFloat -1f);
        }
    }
}


