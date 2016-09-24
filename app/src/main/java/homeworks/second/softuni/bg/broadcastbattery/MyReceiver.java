package homeworks.second.softuni.bg.broadcastbattery;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.Toast;

/**
 * Created by spetkova on 9/24/16.
 */

public class MyReceiver extends BroadcastReceiver {
    final public static String ONE_TIME = "onetime";


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Receiver is called", Toast.LENGTH_SHORT).show();
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = ((float) level / (float) scale) * 100;

        BroadcastObserver.getInstance().updateValue(batteryPct);

    }

    public void SetAlarm(Context context) {

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyReceiver.class);
        intent.putExtra(ONE_TIME, Boolean.FALSE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        Toast.makeText(context, "SetAlarm", Toast.LENGTH_LONG).show();
        //After 1 hour
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 60, pi);
    }
}
