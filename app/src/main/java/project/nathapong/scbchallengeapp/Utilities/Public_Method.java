package project.nathapong.scbchallengeapp.Utilities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.afollestad.materialdialogs.MaterialDialog;

import project.nathapong.scbchallengeapp.R;

public class Public_Method {

    private static MaterialDialog dialogLoading;

    public static void showErrorDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(context.getText(R.string.notice))
                .setMessage(message)
                .setPositiveButton(context.getText(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showLoading(Context context) {
        dialogLoading = new MaterialDialog.Builder(context)
                .content(context.getText(R.string.please_wait))
                .progress(true, 0)
                .cancelable(false)
                .canceledOnTouchOutside(false)
                .show();
    }

    public static void hideLoading(){
        if (dialogLoading.isShowing()){
            dialogLoading.dismiss();
        }
    }
}
