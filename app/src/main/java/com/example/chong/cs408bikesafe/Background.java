package com.example.chong.cs408bikesafe;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

import com.google.android.gms.location.ActivityRecognitionClient;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Date;

public class Background extends Service {

    private Intent mIntentService;
    private PendingIntent mPendingIntent;
    private ActivityRecognitionClient mActivityRecognitionClient;

    private PhoneUnlockedReceiver mUnlockReceiver;

    public enum STATE{
        NOT_BIKING,BIKING
    }

    public STATE state = STATE.NOT_BIKING;

    //violations
    public boolean phoneViolation = false;
    public long startTime;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("TAG", "Service: onCreate");

        mUnlockReceiver = new PhoneUnlockedReceiver(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_USER_PRESENT);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mUnlockReceiver, filter);

        mActivityRecognitionClient = new ActivityRecognitionClient(this);
        mIntentService = new Intent(this, Background.class);
        mIntentService.putExtra("KEY","ACTIVITY");
        mPendingIntent = PendingIntent.getService(this, 1, mIntentService, PendingIntent.FLAG_UPDATE_CURRENT);
        requestActivities();

        long temp = Calendar.getInstance().getTimeInMillis();
        long temp2= Calendar.getInstance().getTimeInMillis();
        Log.e("TAG", Long.toString(temp2-temp));


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        super.onStartCommand(intent,flags,startId);
        Log.d("TAG", "Service: onStartCommand");

        //An activity has arrived
        if(intent != null){//if since android OS calls onstart when app quits.
            String key = intent.getStringExtra("KEY");
            if(key.contentEquals("ACTIVITY")){
                handleActivity(intent);
            }
        }


        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }


    private void handleActivity(Intent intent){
        Log.d("TAG", "Service: handleActivity");
        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);

        ArrayList<DetectedActivity> detectedActivities = (ArrayList) result.getProbableActivities();
        DetectedActivity mostProbable = null;
        for (DetectedActivity activity : detectedActivities) {
            if(mostProbable == null){
                mostProbable = activity;
            }

            if(activity.getConfidence() >= mostProbable.getConfidence()){
                mostProbable = activity;
            }
        }

        if(mostProbable != null && mostProbable.getConfidence() >= 50){
            Log.e("TAG", "Detected activity: " + mostProbable.getType() + ", " + mostProbable.getConfidence());
            int type = mostProbable.getType();
            int confidence = mostProbable.getConfidence();

            switch(type){

                //relevant types
                case DetectedActivity.ON_BICYCLE:{
                    vibrate();
                    if(state != STATE.BIKING){
                        beginBikeSession();
                    }
                    state = STATE.BIKING;
                    break;
                }
                case DetectedActivity.ON_FOOT:{
                    if(state == STATE.BIKING){
                        vibrate();
                        //bike session ended
                        endBikeSession();
                    }
                    state = STATE.NOT_BIKING;
                    break;
                }
                case DetectedActivity.STILL:{
                    if(state == STATE.BIKING){
                        vibrate();
                        //bike session ended
                        endBikeSession();
                    }
                    state = STATE.NOT_BIKING;
                    break;
                }
                case DetectedActivity.TILTING:{
                    if(state == STATE.BIKING){
                        vibrate();
                        //bike session ended
                        endBikeSession();
                    }
                    state = STATE.NOT_BIKING;
                    break;
                }
                case DetectedActivity.WALKING:{
                    if(state == STATE.BIKING){
                        vibrate();
                        //bike session ended
                        endBikeSession();
                    }
                    state = STATE.NOT_BIKING;
                    break;
                }

                //what to do with these?
                case DetectedActivity.RUNNING:{
                    break;
                }
                case DetectedActivity.IN_VEHICLE:{
                    break;
                }
                case DetectedActivity.UNKNOWN:{
                    break;
                }
            }
        }
        else{
            Log.e("TAG", "Confidence < 50");
        }
    }

    //initalise violations and information.(States are not handled here)
    private void beginBikeSession(){
        phoneViolation = false;
        startTime = Calendar.getInstance().getTimeInMillis();

    }

    //check violations and send to server.
    private void endBikeSession(){
        //check violations and send to server

        long endTime = Calendar.getInstance().getTimeInMillis();
        long durationMillis = endTime - startTime;
        long durationSeconds = durationMillis/1000;


    }


    private void requestActivities() {
        Task<Void> task = mActivityRecognitionClient.requestActivityUpdates(
                Constants.DETECTION_INTERVAL_IN_MILLISECONDS,
                mPendingIntent);

        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void result) {
                Toast.makeText(getApplicationContext(),
                        "Successfully requested activity updates",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Requesting activity updates failed to start",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void vibrate(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(1000);
        }
    }
}