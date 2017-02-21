package sedco.calllog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by indianic on 21/02/17.
 */

public class PhoneStateBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "PhoneStateBroadcastReceiver";
    Context mContext;
    String incoming_number;
    private int prev_state;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
                TelephonyManager.EXTRA_STATE_RINGING)) {

            // Phone number
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Log.e("STATUS", "INCOMING");
            // Ringing state
            // This code will execute when the phone has an incoming call
        } else if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
                TelephonyManager.EXTRA_STATE_IDLE)) {
            Log.e("STATUS", "CUT");
            Intent intent1 = new Intent(context, MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
            // This code will execute when the call is answered or disconnected
        }


//        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE); //TelephonyManager object
//        CustomPhoneStateListener customPhoneListener = new CustomPhoneStateListener();
//        telephony.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE); //Register our listener with TelephonyManager
//
//        Bundle bundle = intent.getExtras();
//        String phoneNr = bundle.getString("incoming_number");
//        mContext = context;
    }

    /* Custom PhoneStateListener */
    public class CustomPhoneStateListener extends PhoneStateListener {

        private static final String TAG = "CustomPhoneStateListener";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (incomingNumber != null && incomingNumber.length() > 0)
                incoming_number = incomingNumber;

            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    prev_state = state;
                    break;

                case TelephonyManager.CALL_STATE_OFFHOOK:
                    prev_state = state;
                    break;

                case TelephonyManager.CALL_STATE_IDLE:


                    if ((prev_state == TelephonyManager.CALL_STATE_OFFHOOK)) {
                        prev_state = state;
                        //Answered Call which is ended
                    }
                    if ((prev_state == TelephonyManager.CALL_STATE_RINGING)) {
                        prev_state = state;
                        //Rejected or Missed call
                    }
                    break;
            }
        }
    }
}
