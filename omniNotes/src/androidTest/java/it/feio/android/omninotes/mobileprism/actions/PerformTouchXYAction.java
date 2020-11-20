package it.feio.android.omninotes.mobileprism.actions;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.MotionEvents;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

public class PerformTouchXYAction implements ViewAction {
    float x;
    float y;

    public PerformTouchXYAction(float x, float y) {
        Log.d("swolfe", String.format("new perform click action at %f %f", x, y));
        this.x = x;
        this.y = y;
    }

    @Override
    public Matcher<View> getConstraints() {
        return ViewMatchers.isDisplayed();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void perform(UiController uiController, View view) {
        Log.d("swolfe", String.format("touch event at %f %f", this.x, this.y));
        //view.performContextClick(this.x, this.y);
        float[] coords = { this.x, this.y };
        float[] precisions = { 1f, 1f };
        MotionEvent downEvent = MotionEvents.obtainDownEvent(coords, precisions);
        view.dispatchTouchEvent(downEvent);
        view.dispatchTouchEvent(MotionEvents.obtainUpEvent(downEvent, coords));
    }
}
