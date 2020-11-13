package it.feio.android.omninotes.mobileprism.templates;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;

public class NotWorking_ActionGetTextViewText implements ViewAction {
    // TODO: make it work
    // https://stackoverflow.com/questions/23381459/how-to-get-text-from-textview-using-espresso
    String out;
    @Override
    public Matcher<View> getConstraints() {
        return isAssignableFrom(TextView.class);
    }

    @Override
    public String getDescription() {
        return "retrieving TextView text";
    }

    @Override
    public void perform(UiController uiController, View view) {
        TextView tv = (TextView) view;
        out = tv.getText().toString();
    }

    public String getText() {
        return out;
    }
}
