package sedco.calllog;

import android.database.Cursor;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tvInfo);

        ArrayList<CallLogModel> calllogList = getCallDetails();
        if (calllogList != null && calllogList.size() > 0) {
            for (int i = 0; i < calllogList.size(); i++) {
                if (calllogList.get(i).getDircode() == CallLog.Calls.INCOMING_TYPE) {
                    String str = "Phone No - " + calllogList.get(i).getPhnNumber() + "\nDuration - " + calllogList.get(i).getCallDuration() + "\n Datetime - " + calllogList.get(i).getCallDate();
                    textView.setText(str);
                    break;
                }
            }
        }
    }

    private ArrayList<CallLogModel> getCallDetails() {

        ArrayList<CallLogModel> callLogList = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        int name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        int calllogid = managedCursor.getColumnIndex(CallLog.Calls._ID);

        sb.append("Call Details :");
        while (managedCursor.moveToNext()) {
            CallLogModel callLogModel = new CallLogModel();
            String callogID = managedCursor.getString(calllogid);
            String uname = managedCursor.getString(name);
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            String callDayTime = Utils.convertMilisToDate(callDate);
            String callDuration = Utils.getDurationString(Integer.parseInt(managedCursor.getString(duration)));
            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }

            callLogModel.setCalllogId(callogID);
            callLogModel.setUsername(uname);
            callLogModel.setPhnNumber(phNumber);
            callLogModel.setCallDate(callDayTime);
            callLogModel.setCallDuration(callDuration);
            callLogModel.setDir(dir);
            callLogModel.setDircode(dircode);
            callLogModel.setCallType(dir);
            callLogList.add(callLogModel);
        }
        managedCursor.close();
        return callLogList;
    }
}
