package it.feio.android.omninotes.alt.prev;


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
        AlphaScreen.fabNote.click();
        NewNoteScreen.title.set("prism title 13");
        //Alpha.mostRecentNote.
        String t = NewNoteScreen.title.getText(mActivityTestRule);
        Log.d("swolfe", "NewNote string: " + t);
        //NewNote.title.matchesText("prism title 11");
        Thread.sleep(500);
        NewNoteScreen.back.click();
        Thread.sleep(3000);
        //String t = Alpha.mostRecentNoteTitle.getText(mActivityTestRule);
        //Log.d("swolfe", "most recent string: " + t);
    }

    /*
    @Test
    public void editExistingNote() throws InterruptedException {
        Alpha.expandMenu.click();
        Alpha.optionsMenu.click();
        NewNote.title.set("editExistingNote");
        //Thread.sleep(5000);
    }

    */
    //public void editAndCancel() throws InterruptedException {
    /*

    @Test
    public void matcherProto() throws InterruptedException {
        String text = "matcherProto 3";
        Alpha.expandMenu.click();
        Alpha.optionsMenu.click();
        NewNote.title.set(text);
        Thread.sleep(500);
        NewNote.title.matchesText(text);
        NewNote.title.matchesText("foobar");
    }

    @Test
    public void checkFirstProto() throws InterruptedException {
        Alpha.expandMenu.click();
        Log.d("swolfe", "checkFirstProto 3");
        Log.d("swolfe", Alpha.mostRecentNoteTitle.toString());
        Thread.sleep(5000);
    }

    @Test
    public void boschTest01() {
        ViewInteraction viewInteraction = onView(
            allOf(withId(R.id.fab_expand_menu_button),
                childAtPosition(
                    allOf(withId(R.id.fab),
                        childAtPosition(
                            withClassName(is("android.widget.FrameLayout")),
                            2)),
                    3),
                isDisplayed()));
        viewInteraction.perform(click());

        ViewInteraction floatingActionButton = onView(
            allOf(withId(R.id.fab_note),
                childAtPosition(
                    allOf(withId(R.id.fab),
                        childAtPosition(
                            withClassName(is("android.widget.FrameLayout")),
                            2)),
                    2),
                isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction editText = onView(
            allOf(withId(R.id.detail_title),
                childAtPosition(
                    allOf(withId(R.id.title_wrapper),
                        childAtPosition(
                            withId(R.id.detail_tile_card),
                            0)),
                    1),
                isDisplayed()));
        editText.perform(click());

        ViewInteraction editText2 = onView(
            allOf(withId(R.id.detail_title),
                childAtPosition(
                    allOf(withId(R.id.title_wrapper),
                        childAtPosition(
                            withId(R.id.detail_tile_card),
                            0)),
                    1),
                isDisplayed()));
        editText2.perform(replaceText("previously"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
            allOf(withId(R.id.detail_content),
                childAtPosition(
                    childAtPosition(
                        withClassName(is("android.widget.LinearLayout")),
                        1),
                    0)));
        editText3.perform(scrollTo(), replaceText("on Bosch"), closeSoftKeyboard());

        ViewInteraction linearLayout = onView(
            allOf(withId(R.id.reminder_layout),
                childAtPosition(
                    childAtPosition(
                        withClassName(is("android.widget.LinearLayout")),
                        1),
                    2)));
        linearLayout.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
            allOf(withId(R.id.buttonPositive), withText("Ok"),
                childAtPosition(
                    allOf(withId(R.id.button_layout),
                        childAtPosition(
                            withId(R.id.llMainContentHolder),
                            2)),
                    5),
                isDisplayed()));
        materialButton.perform(click());
    }

    private static Matcher<View> childAtPosition(
        final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                    && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
    */
}
