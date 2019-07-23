package me.stefan.app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import me.stefan.tech.MMProgressDialog;

public class MainActivity extends AppCompatActivity {

    protected MMProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog(R.string.loading);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
    }

    private void dismissProgressDialog() {
        if (mProgressDialog != null && !isFinishing()) {
            mProgressDialog.dismiss();
        }
    }

    private void showProgressDialog(int messageId) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            return;
        }
        initMMProgressDialog();
        setProgressMessage(messageId);
        if (mProgressDialog != null && !isFinishing()) {
            mProgressDialog.show();
        }
    }

    private void initMMProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new MMProgressDialog(this)
                    .setFrameSize(256, 96)
                    .setProgressSize(54)
                    .setTextSize(18.0f)
                    .setDimAmount(0.3f);
            mProgressDialog.setCancelable(true);
            mProgressDialog.setCanceledOnTouchOutside(true);
        }
    }

    private void setProgressMessage(int messageId) {
        if (mProgressDialog != null && !isFinishing()) {
            mProgressDialog.setMessage(messageId);
        }
    }

}
