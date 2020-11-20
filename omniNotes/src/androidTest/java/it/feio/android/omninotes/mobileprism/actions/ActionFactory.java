package it.feio.android.omninotes.mobileprism.actions;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

import it.feio.android.omninotes.mobileprism.utils.Utils;

public class ActionFactory {
    public static ViewAction logChildInfosAction() {
        Log.d("swolfe", "logChildInfos() about to return a new ViewAction");
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isDisplayed();
            }

            @Override
            public String getDescription() {
                return null;
            }

            private void recursivelyGetChildInfos(View view, int numRecursions) {
                if ( numRecursions >= 10 ) {
                    Log.d("swolfe", "recursivelyGetChildInfos hit our recursion limit");
                } else {
                    Log.d("swolfe", "beginning a view get stuffs");
                    boolean isTextView = view.getClass().toString().contains(("TextView"));
                    if ( isTextView) {
                        String t = ((TextView) view).getText().toString();
                        Log.d("swolfe", String.format("found TextView %s with text: %s", Utils.getResName(view), t));
                    } else {
                        try {
                            ViewGroup vg = (ViewGroup) view;
                            Log.d("swolfe", "vg children: " + vg.getChildCount());
                            for (int i = 0; i < vg.getChildCount(); i++) {
                                View v = vg.getChildAt(0);
                                Log.d("swolfe", String.format("vg child at 0 class: %s",  v.getClass()));
                                try {
                                    Log.d("swolfe", String.format("vg child at 0 resName: %s", Utils.getResName(v.getId())));
                                } catch (Resources.NotFoundException e) {
                                    recursivelyGetChildInfos(v, numRecursions + 1);
                                }
                            }
                        } catch (ClassCastException cce) {
                            Log.d("swolfe", "logDownstreamViews() ClassCastException: " + cce.getMessage());
                        } catch (Exception e) {
                            Log.d("swolfe", "logDownstreamViews() exception: " + e.getClass());
                        }
                    }
                }
            }

            @Override
            public void perform(UiController uiController, View view) {
                recursivelyGetChildInfos(view, 0);
            }
        };
    }
}
