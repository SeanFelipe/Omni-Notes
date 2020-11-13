package it.feio.android.omninotes.mobileprism.tests;


import android.util.Log;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.feio.android.omninotes.MainActivity;
import it.feio.android.omninotes.mobileprism.screens.AlphaScreen;
import it.feio.android.omninotes.mobileprism.screens.NewNoteScreen;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PrismTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void createNewNote() throws InterruptedException {
        AlphaScreen.expandMenu.click();
        AlphaScreen.optionsMenu.click();
        NewNoteScreen.title.set("prism title 13");
        String t = NewNoteScreen.title.getText(mActivityTestRule);
        Log.d("swolfe", "NewNote string: " + t);
        //NewNote.title.matchesText("prism title 11");
        Thread.sleep(500);
        NewNoteScreen.back.click();
        // now get top view's title
        Thread.sleep(3000);
    }
}
