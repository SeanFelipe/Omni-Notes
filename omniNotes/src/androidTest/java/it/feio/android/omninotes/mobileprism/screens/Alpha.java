package it.feio.android.omninotes.mobileprism.screens;

import it.feio.android.omninotes.R;
import it.feio.android.omninotes.mobileprism.templates.PrismElement;
import it.feio.android.omninotes.mobileprism.templates.PrismScreen;

public class Alpha extends PrismScreen {
    public static PrismElement expandMenu = genel(R.id.fab_expand_menu_button);
    public static PrismElement optionsMenu = genel(R.id.fab_note);
    public static PrismElement mostRecentNoteTitle = first(R.id.fab_note);
}
