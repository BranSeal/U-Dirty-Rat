package teamminus1.udirtyrat.models;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;



/**
 * Created on 11/4/2017.
 */

public class PermissionUtils {

    public static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermissions(Activity activity, String messageInCaseOfCustomDialog, int permissionId, String... permissions) {
   /* if (o instanceof Fragment) {
        FragmentCompat.requestPermissions((Fragment) o, permissions, permissionId);
    } else */
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                permissions[0])) {
            showCustomDialogForAskPermission(activity, messageInCaseOfCustomDialog, permissionId, permissions);
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.

        } else  { //Use your Shared Preference
            // No explanation needed, we can request the permission.
            showCustomDialogForAskPermission(activity, messageInCaseOfCustomDialog, permissionId, permissions);

            //showCustomDialogForGoToTheSettings(activity, messageInCaseOfCustomDialog);
            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }
    /*MySharedPreference.getSharedPrefrence(activity).edit().putBoolean(permissions[0], true).commit();*/    //Use your Shared Preference
    }

    private static void showCustomDialogForGoToTheSettings(final Activity activity, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setTitle("Permission Required");
        dialog.setMessage(message);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                //activity.startActivity(intent);
            }
        });
        dialog.show();
        try{ Looper.loop(); } //--- dialog won't stay up waiting for input without this
        catch(RuntimeException e){}
    }

    private static void showCustomDialogForAskPermission(final Activity activity, String message, final int permissionId, final String... permissions) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setTitle("Permission Required...");
        dialog.setMessage(message);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(activity, permissions, permissionId);

            }
        });
        dialog.show();
        try{ Looper.loop(); } //--- dialog won't stay up waiting for input without this
        catch(RuntimeException e){}

    }
}