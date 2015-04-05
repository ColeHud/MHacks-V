package test.testcello2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.Runnable;
import java.lang.Math;
import java.lang.InterruptedException;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Arm;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;
import com.thalmic.myo.XDirection;
import com.thalmic.myo.scanner.ScanActivity;


public class MainActivity extends ActionBarActivity {

       private static Context context;
       public SoundData player;

    //create my object


    //add up the values of a string array
    public String addValues(String[] array) {
        String stringToReturn = "";

        for (String value : array) {
            stringToReturn += value + ", ";
        }
        return stringToReturn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.context = getApplicationContext();

        player = new SoundData(getApplicationContext());
        //indicator label to tell which button is being pressed
        final TextView textView = (TextView) findViewById(R.id.texttest);


        //Myo Hub Declaration
        Hub hub = Hub.getInstance();
        if (!hub.init(this)) {
            finish();
            return;
        }
        hub.addListener(mListener);
        hub.setLockingPolicy(Hub.LockingPolicy.NONE);
        //button 1
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton1);

        textView.setText("0");

        imageButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    player.buttonPress[0] = "1";
                    textView.setText(addValues(player.buttonPress));

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    player.buttonPress[0] = "0";
                    textView.setText(addValues(player.buttonPress));

                }
                return true;
            }
        });

        //button 2
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        textView.setText("0");

        imageButton2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    player.buttonPress[1] = "1";
                    textView.setText(addValues(player.buttonPress));

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    player.buttonPress[1] = "0";
                    textView.setText(addValues(player.buttonPress));

                }
                return true;
            }
        });

        //button 3
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        textView.setText("0");

        imageButton3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    player.buttonPress[2] = "1";
                    textView.setText(addValues(player.buttonPress));

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    player.buttonPress[2] = "0";
                    textView.setText(addValues(player.buttonPress));

                }
                return true;
            }
        });

        //button 4
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        textView.setText("0");

        imageButton4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    player.buttonPress[3] = "1";
                    textView.setText(addValues(player.buttonPress));

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    player.buttonPress[3] = "0";
                    textView.setText(addValues(player.buttonPress));

                }
                return true;
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        // We don't want any callbacks when the Activity is gone, so unregister the listener.
        Hub.getInstance().removeListener(mListener);

        if (isFinishing()) {
            // The Activity is finishing, so shutdown the Hub. This will disconnect from the Myo.
            Hub.getInstance().shutdown();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            onScanActionSelected();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onScanActionSelected() {
        // Launch the ScanActivity to scan for Myos to connect to.
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }


    //    final TextView myoView =(TextView) findViewById(R.id.myoInfo);
    private DeviceListener mListener = new AbstractDeviceListener() {
        @Override
        public void onPose(Myo myo, long timestamp, Pose pose) {
            if((pose == Pose.WAVE_IN) && player.optarg < 3) ++player.optarg;
            else if((pose== Pose.WAVE_OUT) && player.optarg > 0) --player.optarg;
        }

        @Override
        public void onGyroscopeData(Myo myo, long timestamp, final Vector3 accel) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView myoinfo = (TextView) findViewById(R.id.myoInfo);
                    myoinfo.setText(accel.toString());
                }
            });
            player.play_note(Math.abs(accel.y()));
        }
    };

//    public void play_sound(){
//        MediaPlayer player = MediaPlayer.create(this, R.raw.fullcelloscale);
//        player.seekTo(150);
//        player.start();
//        try{ Thread.sleep(500);} catch (Exception e) {}
//        player.pause();
//    }


}