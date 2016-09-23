package id.duza.pushnotificationpractice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnPush;
    private EditText etTitle;
    private EditText etContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPush = (Button) findViewById(R.id.btn_push);
        etTitle = (EditText) findViewById(R.id.et_title);
        etContent = (EditText) findViewById(R.id.et_content);

        btnPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = etTitle.getText().toString().trim();
                String content = etContent.getText().toString().trim();
                //Input validation
                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
                    //Explicit intent when the notification clicked (Notification action)
                    Intent resultIntent = new Intent(MainActivity.this, SpecialActivity.class);
                    //allows the notification manager to use your application's permissions to execute a predefined piece of code
                    PendingIntent pendingIntent = PendingIntent.getActivity(
                            MainActivity.this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    //specify the UI information and actions for a notification
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(title)
                            .setContentText(content)
                            .setContentIntent(pendingIntent);

                    NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    mNotificationManager.notify(1, mBuilder.build());
                }
            }
        });
    }
}
