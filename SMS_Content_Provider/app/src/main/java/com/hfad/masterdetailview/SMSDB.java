package com.hfad.masterdetailview;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by matthewtduffin on 26/07/16.
 */
public class SMSDB {

    Context self;
    Map<String, ArrayList<String>> msgs;

    private static SMSDB instance;
    private SMSDB (Context self) {

        this.self = self;
        msgs = new HashMap<>();
    }

    public static SMSDB getInstance(Context self) {
        if (instance == null) {
            instance = new SMSDB(self);

        }
        return instance;
    }

    public void readSMS(){
        if (msgs.isEmpty()) {
            Log.i("Empty","Empty");
            Cursor cursor = self.getContentResolver().query(Uri.parse("content://sms/"), null, null, null, null);
            for (int j = 0; j < cursor.getCount(); j++) {
                cursor.moveToPosition(j);
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    String key = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                    String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
                    if (!msgs.containsKey(key)) {
                        msgs.put(cursor.getString(cursor.getColumnIndexOrThrow("address")), new ArrayList<String>());
                        msgs.get(key).add(body);
                    } else {
                        if (!msgs.get(key).contains(body)) {
                            msgs.get(key).add(body);
                        }
                    }
                }
            }
            cursor.close();
        }
    }

    public Map<String, ArrayList<String>> getMessages(){
        return msgs;
    }
}
