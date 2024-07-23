package com.example.faciltymagazine;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Url {
    final String ip = "http://192.168.1.12";


    public String setPost = ip+"/faculty_magazine/setPost.php";
    public String find = ip+"/faculty_magazine/display.php";
    public String display = ip+"/faculty_magazine/single_display.php";
    public String url_lo = ip+"/faculty_magazine/logins.php";
    public void alertMessage (Context cnt, String title, String msg) {
        //

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(cnt);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
