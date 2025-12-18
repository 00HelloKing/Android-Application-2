package com.nuist.setu.killbill.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.core.content.FileProvider;

import com.nuist.setu.killbill.R;
import com.nuist.setu.killbill.data.Bill;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Export all bills as CSV and share.
 */
public final class CsvExporter {

    private CsvExporter() {}

    public static void exportAndShare(Activity activity, List<Bill> bills) {
        try {
            String ts = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
            File outFile = new File(activity.getCacheDir(), "killbill_export_" + ts + ".csv");

            StringBuilder sb = new StringBuilder();
            sb.append("id,amount,category,note,datetime,timestamp,source,paymentApp,receiptUri\n");
            for (Bill b : bills) {
                sb.append(b.id).append(',')
                        .append(b.amount).append(',')
                        .append(csvEscape(b.category)).append(',')
                        .append(csvEscape(b.note)).append(',')
                        .append(csvEscape(DateTimeUtils.formatDateTime(b.timestamp))).append(',')
                        .append(b.timestamp).append(',')
                        .append(csvEscape(b.source)).append(',')
                        .append(csvEscape(b.paymentApp)).append(',')
                        .append(csvEscape(b.receiptUri))
                        .append('\n');
            }

            try (FileOutputStream fos = new FileOutputStream(outFile)) {
                fos.write(sb.toString().getBytes(StandardCharsets.UTF_8));
            }

            Uri uri = FileProvider.getUriForFile(
                    activity,
                    activity.getPackageName() + ".fileprovider",
                    outFile
            );

            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/csv");
            share.putExtra(Intent.EXTRA_STREAM, uri);
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            activity.startActivity(Intent.createChooser(share, activity.getString(R.string.export_csv)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String csvEscape(String s) {
        if (s == null) return "";
        String escaped = s.replace("\"", "\"\"");
        return "\"" + escaped + "\"";
    }
}
