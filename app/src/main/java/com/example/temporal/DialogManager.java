package com.example.temporal;

import android.app.Activity;
import android.content.Context;

public class DialogManager {

    private Activity activity;

    private LoadingDialog loadingDialog;
    private ErrorDialog errorDialog;

    public DialogManager(Context context) {
        this.activity = (Activity) context;
    }

    public void showLoadingView() {
        loadingDialog = new LoadingDialog(activity);
        loadingDialog.show();
    }

    public void hideLoadingView() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public void showErrorView(String error) {
        errorDialog = new ErrorDialog(activity);
        errorDialog.setErrorText(error);
        errorDialog.show();
    }

    public void hideErrorDialog() {
        if (errorDialog != null && errorDialog.isShowing()) {
            errorDialog.dismiss();
        }
    }

}
