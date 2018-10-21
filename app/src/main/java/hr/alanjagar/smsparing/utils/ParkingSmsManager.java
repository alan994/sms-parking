package hr.alanjagar.smsparing.utils;

import android.telephony.SmsManager;

/**
 * Created by ajagar on 20.10.2018..
 */

public class ParkingSmsManager {
    public void pay(String msg, String number){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, msg, null, null);
    }
}
