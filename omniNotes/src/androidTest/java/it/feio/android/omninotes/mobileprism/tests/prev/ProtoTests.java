package it.feio.android.omninotes.mobileprism.tests.prev;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

import it.feio.android.omninotes.MainActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ProtoTests {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /*
    @Test
    public void protoCreate() throws InterruptedException {
        Alpha.expandMenu.click();
        Alpha.optionsMenu.click();
        NewNote.title.set("proto create");
        Thread.sleep(500);
    }
    @Test
    public void protoAssert() throws InterruptedException {
        DataInteraction firstItem = onData(anything())
            .inAdapterView(allOf(withId(R.id.list),
            .inAdapterView(allOf(withId(R.id.list),
                childAtPosition(
                    withClassName(is("android.widget.FrameLayout")),
                    0)))
            .atPosition(0);

    }
}
    */

}
