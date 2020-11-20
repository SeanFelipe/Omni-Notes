package it.feio.android.omninotes.mobileprism.templates;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;

import it.feio.android.omninotes.mobileprism.utils.Utils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class PrismRVChildElement {
    int position;
    int parentRecyclerViewId;
    ViewInteraction parentInteraction;

    public PrismRVChildElement(int position, int parentRecyclerViewId) {
        this.position = position;
        parentInteraction = onView(withId(parentRecyclerViewId));
    }

    public void click() {
        parentInteraction.perform(RecyclerViewActions.actionOnItemAtPosition(position, ViewActions.click()));
    }
}