package com.example.preference_demo;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.oplus.flashbackmsgsdk.ButtonMessage;
import com.oplus.flashbackmsgsdk.MessageStub;
import com.oplus.flashbackmsgsdk.MessageTag;
import com.oplus.flashbackmsgsdk.MonitorClient;
import com.oplus.flashbackmsgsdk.MonitorListener;
import com.oplus.flashbackmsgsdk.SendCallback;
import com.oplus.flashbackmsgsdk.ServiceLifecycleCallback;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_container, new SettingsFragment()).commit();
        updateMsg();
    }

    MonitorClient client;
    MonitorListener listener;
    MessageStub mStub;

    public void updateMsg() {
        client = MonitorClient.getInstance();
        listener = new MonitorListener() {
            @Override
            public void onChanged(MessageStub stub, int errorCode) {
                mStub = stub;
                Log.e("wenming", "闪回消息回调：" + stub);
            }
        };
        // 查询是否支持闪回2.0
        boolean isSupported = client.isSupported(getApplicationContext());
        client.registerListener(getApplicationContext(), listener);
        client.addServiceStatusCallback(new ServiceLifecycleCallback() {
            @Override
            public void onCreated() {
                Log.e("wenming", "onCreated：");
            }

            @Override
            public void onDestroyed() {
                Log.e("wenming", "onDestroyed：");
            }
        });

        Log.e("wenming", "闪回是否支持：" + isSupported);

        client.send(getApplicationContext(), mStub, ButtonMessage.KEY_BUTTON_CENTER, new SendCallback() {
            @Override
            public void onResult(int errorCode) {

            }
        });
        client.registerTagListener(getApplicationContext(), MessageTag.TAG_TEXT, listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.unregisterListener(getApplicationContext(), listener);
    }
}