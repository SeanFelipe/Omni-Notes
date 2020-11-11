package it.feio.android.omninotes.mobileprism.templates;

import androidx.test.espresso.DataInteraction;

import it.feio.android.omninotes.R;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

public abstract class PrismScreen {
    //public static PrismElement genel(String ref, int rid) {
    //return new PrismElement(ref, rid);
    public static PrismElement genel(int rid) {
        return new PrismElement(rid);
    }

    public static PrismElement first(int rid) {
        DataInteraction di = onData(anything()).inAdapterView(allOf(withId(R.id.list))).atPosition(0);
        return new PrismElement(di);
    }
}
