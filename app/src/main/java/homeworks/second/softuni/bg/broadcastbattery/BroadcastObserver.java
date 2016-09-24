package homeworks.second.softuni.bg.broadcastbattery;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by spetkova on 9/24/16.
 */

public class BroadcastObserver extends Observable {

    public static BroadcastObserver handler;

    public static BroadcastObserver getInstance() {

        if (handler == null)
            handler = new BroadcastObserver();
        return handler;


    }

//    public BroadcastObserver (){
//    }

    public void updateValue(Object data) {
        synchronized (this) {
            setChanged();
            notifyObservers(data);
        }

    }
}
