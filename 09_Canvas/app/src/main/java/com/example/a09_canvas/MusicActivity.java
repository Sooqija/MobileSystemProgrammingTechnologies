package com.example.a09_canvas;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class MusicActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView noMusicTextView;
    ArrayList<AudioModel> songList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        recyclerView = findViewById(R.id.recyclerView);
        noMusicTextView = findViewById(R.id.Music);

        if (!checkPermission()) {
            requestPermission();
            return;
        }

        String[] projection = {
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DURATION
        };

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null, null);

        while (cursor.moveToNext()) {
            AudioModel songData = new AudioModel(cursor.getString(1), cursor.getString(0), cursor.getString(2));
            if (new File(songData.getPath()).exists())
                songList.add(songData);
        }

        if (songList.size() == 0) {
            noMusicTextView.setText("There is no music");
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new MusicListAdapter(songList, getApplicationContext()));
        }

        Button bPreviousPage = findViewById(R.id.PreviousPage);
        bPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MusicActivity.this, ParticlesActivity.class);
                startActivity(intent);
            }
        });

        Button bNext = findViewById(R.id.NextPage);
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MusicActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });
    }

    public static class MyMediaPlayer {
        static MediaPlayer instance;
        public static int currentIndex = -1;

        public static MediaPlayer getInstance() {
            if (instance == null) {
                instance = new MediaPlayer();
            }
            return instance;
        }
    }

    public class AudioModel implements Serializable {
        String path;
        String title;
        String duration;

        public AudioModel (String path, String title, String duration) {
            this.path = path;
            this.title = title;
            this.duration = duration;
        }

        public String getPath() {
            return path;
        }

        public String getTitle() {
            return title;
        }

        public String getDuration() {
            return duration;
        }
    }

    public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {
        ArrayList<AudioModel> songList;
        Context context;
        SeekBar seekBar;

        public MusicListAdapter(ArrayList<AudioModel> songList, Context context) {
            this.songList = songList;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
            return new MusicListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            AudioModel songData = songList.get(position);
            holder.titleSong.setText(songData.getTitle());

            if (MyMediaPlayer.currentIndex == position) {
                holder.titleSong.setTextColor(Color.GRAY);
            } else {
                holder.titleSong.setTextColor(Color.BLACK);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyMediaPlayer.getInstance().reset();
                    MyMediaPlayer.currentIndex = position;
                    setMusic();

                    MusicActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
                            if (mediaPlayer != null) {
                                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                                TextView currentTime = findViewById(R.id.CurrentTime);
                                currentTime.setText(convertToMMSS(mediaPlayer.getCurrentPosition()+""));
                                ImageView PlayMusic = findViewById(R.id.PauseResume);
                                if (mediaPlayer.isPlaying()) {
                                    PlayMusic.setImageResource(R.drawable.ic_baseline_pause_24);
                                } else {
                                    PlayMusic.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                                }
                            }
                            new Handler().postDelayed(this, 100);
                        }
                    });

                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
                            if (mediaPlayer != null && fromUser) {
                                mediaPlayer.seekTo(progress);
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });
                }
            });
        }

        private void setMusic() {
            TextView titleSong = findViewById(R.id.Title);

            TextView totalTime = findViewById(R.id.TotalTime);

            AudioModel currentSong = songList.get(MyMediaPlayer.currentIndex);
            titleSong.setText(currentSong.getTitle());
            totalTime.setText(convertToMMSS(currentSong.getDuration()));

            ImageView PlayMusic = findViewById(R.id.PauseResume);
            ImageView PlayNext = findViewById(R.id.SkipNext);
            ImageView PlayPrevious = findViewById(R.id.SkipPrevious);

            PlayMusic.setOnClickListener(v -> pauseMusic());
            PlayNext.setOnClickListener(v -> playNextSong());
            PlayPrevious.setOnClickListener(v -> playPreviousSong());

            seekBar = findViewById(R.id.Progress);
            titleSong.setSelected(true);
            playMusic(currentSong);
        }

        private void playMusic (AudioModel currentSong) {
            MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource(currentSong.getPath());
                mediaPlayer.prepare();
                mediaPlayer.start();
                seekBar.setProgress(0);
                seekBar.setMax(mediaPlayer.getDuration());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void playNextSong () {
            if (MyMediaPlayer.currentIndex == songList.size()-1)
                return;
            MyMediaPlayer.currentIndex += 1;
            MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
            mediaPlayer.reset();
            setMusic();
        }

        private void playPreviousSong () {
            if (MyMediaPlayer.currentIndex == 0)
                return;
            MyMediaPlayer.currentIndex -= 1;
            MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
            mediaPlayer.reset();
            setMusic();
        }

        private void pauseMusic () {
            MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.start();
            }
        }

        public String convertToMMSS(String duration) {
            Long millis = Long.parseLong(duration);
            return String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                    TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
        }

        @Override
        public int getItemCount() {
            return songList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView titleSong;
            ImageView iconImageSong;

            public ViewHolder (View itemView) {
                super(itemView);
                iconImageSong = itemView.findViewById(R.id.Icon);
                titleSong = itemView.findViewById(R.id.TitleSong);
            }
        }
    }

    boolean checkPermission () {
        int result = ContextCompat.checkSelfPermission(MusicActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    void requestPermission () {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MusicActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(MusicActivity.this, "READ PERMISION IS REQUIRED, PLEASE ALLOW FROM SETTINGS", Toast.LENGTH_SHORT);
        } else {
            ActivityCompat.requestPermissions(MusicActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
        }
    }
}
