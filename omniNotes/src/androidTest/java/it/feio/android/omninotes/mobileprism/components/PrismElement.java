package it.feio.android.omninotes.mobileprism.components;

import androidx.test.espresso.action.ViewActions;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class PrismElement {
    String ref;
    int aid;

    public PrismElement(int id) {
        aid = id;
    }

    public PrismElement(String refName, int id) {
        ref = refName;
        aid = id;
    }

    public void click() {
        onView(withId(aid)).perform(ViewActions.click());
    }

    public void set(String str) {
        onView(withId(aid)).perform(typeText(str), closeSoftKeyboard());
    }
}