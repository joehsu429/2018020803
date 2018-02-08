package com.example.student.a2018020803;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    JobScheduler jobScheduler;
    JobInfo jobInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder( 1,
                new ComponentName(getPackageName(),
                        MyJobService.class.getName() ) );
//        builder.setPeriodic(3000);//23
//        jobInfo = builder.build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) //26以上版本要每15分鐘才能更新1次
            //Manifest要記得加android:permission="android.permission.BIND_JOB_SERVICE"
        {
            jobInfo = builder.setMinimumLatency(3000).build();
        } else {
            jobInfo = builder.setPeriodic(3000).build();
        }
    }
    public void click1(View v)
    {
        jobScheduler.schedule(jobInfo);
    }
    public void click2(View v)
    {
        jobScheduler.cancel(1);
    }
}
