package it.feio.android.omninotes.mobileprism.screens;

import it.feio.android.omninotes.R;
import it.feio.android.omninotes.mobileprism.templates.PrismElement;
import it.feio.android.omninotes.mobileprism.templates.PrismRVChildElement;
import it.feio.android.omninotes.mobileprism.templates.PrismRecyclerView;
import it.feio.android.omninotes.mobileprism.templates.PrismScreen;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AlphaScreen extends PrismScreen {
    public static PrismElement expandMenu = genel(R.id.fab_expand_menu_button);
    public static PrismElement fabNote = genel(R.id.fab_note);
    public static PrismRecyclerView notesList = genlist(R.id.list);
    public static PrismRVChildElement firstNote = genAtPosition(0, R.id.list);
    public static PrismElement rooty = genel(R.id.root);
}
