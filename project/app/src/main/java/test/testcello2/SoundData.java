package test.testcello2;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import java.util.Random;

/**
 * Created by Walex on 1/18/2015.
 */
public class SoundData
{
    public MediaPlayer player;

    Random randall = new Random();


    //constructor
    SoundData(Context c)
    {
        SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

        /** soundId for Later handling of sound pool **/
        int soundId = sp.load(c, R.raw.fullcelloscale, 1); // in 2nd param u have to pass your desire ringtone

        sp.play(soundId, 1, 1, 0, 0, 1);

        player = MediaPlayer.create(c, R.raw.fullcelloscale); // in 2nd param u have to pass your desire ringtone
        //mPlayer.prepare();


    }

    public String[] buttonPress = {"0", "0", "0", "0"};
    public int optarg = 0;

    public int button(){
        if(buttonPress[3] == "1") return 4;
        else if(buttonPress[2] == "1") return 3;
        else if(buttonPress[1] == "1") return 2;
        else if(buttonPress[0] == "1") return 1;
        else return 0;
    }

    public int note_calc(){
        int butt = button();
        if(optarg == 0){
            if(butt == 1)return 1;
            else if(butt == 2)return 2;
            else if(butt == 3)return 3;
            else if(butt == 4)return 4;
            else return 0;
        }
        else if(optarg == 1){
            if(butt == 1)return 6;
            else if(butt == 2)return 7;
            else if(butt == 3)return 8;
            else if(butt == 4)return 9;
            else return 5;
        }
        else if(optarg == 2){
            if(butt == 1)return 11;
            else if(butt == 2)return 12;
            else if(butt == 3)return 13;
            else if(butt == 4)return 14;
            else return 10;
        }
        else{
            if(butt == 1)return 16;
            else if(butt == 2)return 17;
            else if(butt == 3)return 18;
            else if(butt == 4)return 19;
            else return 15;
        }
    }

    public void play_note(double velocity) {
        Log.d("PLAY NOTE", "Attempted to play note lol");

        if(velocity > 40)
        {
            player.setVolume(1, 1);
            player.start();


            int num = note_calc();
            if (num == 0) {
                player.seekTo(150);
                player.start();
            } else if (num == 1) {
                player.seekTo(7757);
                player.start();
            } else if (num == 2) {
                player.seekTo(15000);
                player.start();
            } else if (num == 3) {
                player.seekTo(22240);
                player.start();
            } else if (num == 4) {
                player.seekTo(29723);
                player.start();
            } else if (num == 5) {
                player.seekTo(37185);
                player.start();
            } else if (num == 6) {
                player.seekTo(44679);
                player.start();
            } else if (num == 7) {
                player.seekTo(52131);
                player.start();
            } else if (num == 8) {
                player.seekTo(59439);
                player.start();
            } else if (num == 9) {
                player.seekTo(66950);
                player.start();
            } else if (num == 10) {
                player.seekTo(74413);
                player.start();
            } else if (num == 11) {
                player.seekTo(81886);
                player.start();
            } else if (num == 12) {
                player.seekTo(89359);
                player.start();
            } else if (num == 13) {
                player.seekTo(96821);
                player.start();
            } else if (num == 14) {
                player.seekTo(104199);
                player.start();
            } else if (num == 15) {
                player.seekTo(111598);
                player.start();
            } else if (num == 16) {
                player.seekTo(119060);
                player.start();
            } else if (num == 17) {
                player.seekTo(126512);
                player.start();
            } else if (num == 18) {
                player.seekTo(133964);
                player.start();
            } else if (num == 19) {
                player.seekTo(141332);
                player.start();

            } else {
                System.out.println("invalid note");
                player.pause();
            }


        }
    }




}