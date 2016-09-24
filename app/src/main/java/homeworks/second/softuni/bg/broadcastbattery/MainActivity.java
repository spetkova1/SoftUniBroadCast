package homeworks.second.softuni.bg.broadcastbattery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends Activity implements Observer {

    private MyReceiver mReceiver;
    private TextView mBatteryDrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBatteryDrop = (TextView) findViewById(R.id.battery_drop);

        mReceiver = new MyReceiver();
        mReceiver.SetAlarm(getApplicationContext());
        BroadcastObserver.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        mBatteryDrop.setText(o.toString() + "%");

    }
}
