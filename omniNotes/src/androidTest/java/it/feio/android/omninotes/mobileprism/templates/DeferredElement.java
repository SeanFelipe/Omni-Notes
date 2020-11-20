package it.feio.android.omninotes.mobileprism.templates;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import it.feio.android.omninotes.mobileprism.utils.Utils;

public class DeferredElement {
    int aid;
    public DeferredElement(int id) {
        aid = id;
    }

    public String getText() {
        Log.d("swolfe", "Deferred Click");
        Activity a = Utils.getActivity();
        TextView tv = (TextView) a.findViewById(aid);
        return tv.getText().toString();
    }
}
