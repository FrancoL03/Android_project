//package AlarmClock_part_menghaoWang;
//
//import java.util.Calendar;
//import android.app.Activity;
//import android.app.AlarmManager;
//import android.app.PendingIntent;
//import android.app.TimePickerDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.TimePicker;
////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//public class Activity01 extends Activity {
//
//    private Button msetButton;
//    private Button mcancelButton;
//    private AlarmReceiver uIReceiver;
//    private TextView mTextView;
//    private Calendar calendar;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
//////        calendar = Calendar.getInstance();
//////        mTextView = (TextView)this.findViewById(R.id.mText);
//////        msetButton = (Button)this.findViewById(R.id.setTimeButton);
//////        mcancelButton = (Button)findViewById(R.id.cancelButton);
//////        msetButton.setOnClickListener(new OnClickListener() {
////            @Override
//            public void onClick(View v) {
//
//                calendar.setTimeInMillis(System.currentTimeMillis());
//                int hour = calendar.get(Calendar.HOUR_OF_DAY);
//                int minute = calendar.get(Calendar.MINUTE);
//
//                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
//
//
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
////                        calendar.setTimeInMillis(System.currentTimeMillis());
////                        //set(f, value) changes field f to value.
////                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
////                        calendar.set(Calendar.MINUTE, minute);
////                        calendar.set(Calendar.SECOND, 0);
////                        calendar.set(Calendar.MILLISECOND, 0);
//
//                        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
//                        intent.putExtra("music", true);
//                        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
//                        AlarmManager am;
//                        am = (AlarmManager)getSystemService(ALARM_SERVICE);
//                        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//                        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(10*1000), (24*60*60*1000), pendingIntent);
////                        String tmps = "Setting time to be: "+format(hourOfDay)+":"+format(minute);
////                        mTextView.setText(tmps);
//                    }
//
//                },hour,minute,true).show();
//            }
//        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//        mcancelButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
//                intent.putExtra("music", true);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
//                AlarmManager am;
//                am = (AlarmManager)getSystemService(ALARM_SERVICE);
//                am.cancel(pendingIntent);
//                mTextView.setText("Already_cancelled");
//            }
//        });
//
// ////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//    }
////    private String format(int x) {
////        String s = ""+x;
////        if(s.length() == 1)
////            s = "0"+s;
////        return s;
////    }
//    /////////////////////////////////////////////////////////////////////////////////////////////////////
//
//}