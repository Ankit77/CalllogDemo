package sedco.calllog;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;

/**
 * Created by Ankit on 4/24/2016.
 */
public class Utils {


    private static boolean appInstalledOrNot(String uri, Context context) {
        PackageManager pm = context.getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    public static boolean checkLocationServiceEnable(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkGPSProviderEnable(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }


        if (!gps_enabled) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * get the path of App where file are stores
     *
     * @return
     */


    public static long getFileSizeInKB(String path) {
        // Get file from file name
        File file = new File(path);

// Get length of file in bytes
        long fileSizeInBytes = file.length();
// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        long fileSizeInKB = fileSizeInBytes / 1024;
// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        long fileSizeInMB = fileSizeInKB / 1024;
        return fileSizeInKB;
    }


    /**
     * Send SMS for timer sync if internet is not available
     *
     * @param phoneNumber
     * @param message
     */
    public static void sendSMSForTimerSync(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static String convertMilisToDate(String milis) {

        long val = 0;
        if (!TextUtils.isEmpty(milis)) {
            try {
                val = Long.parseLong(milis);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
            // Create a DateFormatter object for displaying date in specified format.
            SimpleDateFormat formatter = new SimpleDateFormat(Const.DATEFORMATE);
            // Create a calendar object that will convert the date and time value in milliseconds to date.
            return formatter.format(val);
        }
        return "";
    }




    /**
     * check weather application is install or not
     *
     * @param packagename
     * @param context
     * @return
     */
    public static boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * Reboot device
     */
    public static void rebootSU() {
        Runtime runtime = Runtime.getRuntime();
        Process proc = null;
        OutputStreamWriter osw = null;
        StringBuilder sbstdOut = new StringBuilder();
        StringBuilder sbstdErr = new StringBuilder();

        String command = "/system/bin/reboot";

        try { // Run Script

            proc = runtime.exec("su");
            osw = new OutputStreamWriter(proc.getOutputStream());
            osw.write(command);
            osw.flush();
            osw.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            if (proc != null)
                proc.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (proc.exitValue() != 0) {
        }
    }


    /**
     * Shutdown device
     */
    public static void shutdownSU() {
        try {
            Process proc = Runtime.getRuntime()
                    .exec(new String[]{"su", "-c", "reboot -p"});
            proc.waitFor();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void copyDatabase(String databasepath, String outputpath) {
        boolean retval;
        try {
            // String comando = "cp -r /data/data/com.google.android.gm/databases/ /sdcard/test";
            String comando = "cp -r " + databasepath + " " + outputpath;
            Process suProcess = null;

            suProcess = Runtime.getRuntime().exec("su");

            DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());
            os.writeBytes(comando + "\n");
            os.flush();
            os.writeBytes("exit\n");
            os.flush();
            try {
                int suProcessRetval = suProcess.waitFor();
                if (255 != suProcessRetval) {
                    // Acceso Root concedido
                    retval = true;
                } else {
                    // Acceso Root denegado
                    retval = false;
                }
            } catch (Exception ex) {
                Log.w("Error  Root", ex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void copyDirectory(File sourceLocation, File targetLocation)
            throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists() && !targetLocation.mkdirs()) {
                throw new IOException("Cannot create dir " + targetLocation.getAbsolutePath());
            }

            String[] children = sourceLocation.list();
            for (int i = 0; i < children.length; i++) {
                copyDirectory(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {

            // make sure the directory we plan to store the recording in exists
            File directory = targetLocation.getParentFile();
            if (directory != null && !directory.exists() && !directory.mkdirs()) {
                throw new IOException("Cannot create dir " + directory.getAbsolutePath());
            }

            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }


    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }






    public static boolean isNetworkAvailable(final Context context) {
        boolean isNetAvailable = false;
        if (context != null) {
            final ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (mConnectivityManager != null) {
                final NetworkInfo activeNetwork = mConnectivityManager.getActiveNetworkInfo();
                if (activeNetwork != null) isNetAvailable = true;
            }
        }
        return isNetAvailable;
    }


    public static String getDurationString(int seconds) {

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return twoDigitString(hours) + ":" + twoDigitString(minutes) + ":" + twoDigitString(seconds);
    }

    public static String twoDigitString(int number) {

        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }




    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }
}
