package me.stefan.tech;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.stefan.tech.R;

public class MMProgressDialog extends Dialog {

    private ProgressBar mProgressView;
    private TextView mMessageView;

    private CharSequence mMessage;
    private boolean mIndeterminate;
    private int mProgressSize;
    private float mTextSize;
    private int mWidth, mHeight;
    private float mDimAmount;

    public MMProgressDialog(Context context) {
        super(context, R.style.MMProgressDialog);
    }

    public MMProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public MMProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static MMProgressDialog show(Context context, CharSequence message) {
        return show(context, message, false);
    }

    public static MMProgressDialog show(Context context, CharSequence message, boolean indeterminate) {
        return show(context, message, indeterminate, false, null);
    }

    public static MMProgressDialog show(Context context, CharSequence message, boolean indeterminate,
                                        boolean cancelable, OnCancelListener cancelListener) {
        MMProgressDialog dialog = new MMProgressDialog(context);
        dialog.setMessage(message);
        dialog.setIndeterminate(indeterminate);
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        dialog.show();
        return dialog;
    }

    public void setMessage(CharSequence message) {
        if (mProgressView != null) {
            mMessageView.setText(message);
        } else {
            mMessage = message;
        }
    }

    public static MMProgressDialog show(Context context, CharSequence message, boolean indeterminate,
                                        boolean cancelable) {
        return show(context, message, indeterminate, cancelable, null);
    }

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View layout = inflater.inflate(R.layout.mm_progress_dialog, null);
        mProgressView = (ProgressBar) layout.findViewById(R.id.mm_progress_dialog_icon);
        mMessageView = (TextView) layout.findViewById(R.id.mm_progress_dialog_msg);
        setContentView(layout);
        Window window = getWindow();
        if (null != window) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.dimAmount = mDimAmount;
            layoutParams.gravity = Gravity.CENTER;
            window.setAttributes(layoutParams);
        }
        if (mMessage != null) {
            setMessage(mMessage);
        }
        setIndeterminate(mIndeterminate);
        if (mWidth > 0 && mHeight > 0) {
            ViewGroup.LayoutParams lp = layout.getLayoutParams();
            lp.width = Helper.dpToPixel(mWidth, getContext());
            lp.height = Helper.dpToPixel(mHeight, getContext());
            layout.setLayoutParams(lp);
        }
        if (mProgressSize > 0) {
            ViewGroup.LayoutParams lp = mProgressView.getLayoutParams();
            lp.width = Helper.dpToPixel(mProgressSize, getContext());
            lp.height = Helper.dpToPixel(mProgressSize, getContext());
            mProgressView.setLayoutParams(lp);
        }
        if (mTextSize > 0) {
            mMessageView.setTextSize(mTextSize);
        }
        super.onCreate(savedInstanceState);
    }

    public boolean isIndeterminate() {
        if (mProgressView != null) {
            return mProgressView.isIndeterminate();
        }
        return mIndeterminate;
    }

    public void setIndeterminate(boolean indeterminate) {
        if (mProgressView != null) {
            mProgressView.setIndeterminate(indeterminate);
        } else {
            mIndeterminate = indeterminate;
        }
    }

    public void setMessage(int messageId) {
        mMessage = getContext().getResources().getString(messageId);
        if (mProgressView != null) {
            mMessageView.setText(mMessage);
        }
    }

    public void hideProgress() {
        if (mProgressView != null) {
            mProgressView.setVisibility(View.GONE);
            mMessageView.setGravity(Gravity.CENTER);
        }
    }

    public MMProgressDialog setProgressSize(int dpSize) {
        mProgressSize = dpSize;
        return this;
    }

    public MMProgressDialog setTextSize(float dpSize) {
        mTextSize = dpSize;
        return this;
    }

    public MMProgressDialog setFrameSize(int width, int height) {
        mWidth = width;
        mHeight = height;
        return this;
    }

    public MMProgressDialog setDimAmount(float dimAmount) {
        if (dimAmount >= 0 && dimAmount <= 1) {
            mDimAmount = dimAmount;
        }
        return this;
    }

}
