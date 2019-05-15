package com.example.telephony;

import android.net.Uri;
import android.provider.BaseColumns;

public interface SimInfoContract extends BaseColumns {
    String AUTHORITY = "telephony";
    String SIM_INFO = "/siminfo";
    Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + SIM_INFO);

    String DISPLAY_NAME = "display_name";
    String ICC_ID = "icc_id";
    String MCC = "mcc";
    String MNC = "mnc";

}
