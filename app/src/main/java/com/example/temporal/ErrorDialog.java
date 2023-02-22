package com.example.temporal;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class ErrorDialog extends Dialog {
    public ErrorDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.error_view);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = getWindow();
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
        getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, R.color.black_light)));
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setCancelable(false);
    }

    public void setErrorText(String text) {
        if (!AppTextUtils.isEmpty(text)) {
            ((TextView) findViewById(R.id.dialog_error_text)).setText(text);
        }
    }
}
