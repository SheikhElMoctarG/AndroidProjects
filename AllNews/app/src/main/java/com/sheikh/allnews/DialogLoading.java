package com.sheikh.allnews;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

class DialogLoading {
    private Activity activity;
    private AlertDialog dialog;
    public DialogLoading(Activity myactivity) {
        activity = myactivity;
    }
    public void startDialogLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.coustem_loading,null));
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }
    public void dismessDialogLoading(){
        dialog.dismiss();
    }
}
