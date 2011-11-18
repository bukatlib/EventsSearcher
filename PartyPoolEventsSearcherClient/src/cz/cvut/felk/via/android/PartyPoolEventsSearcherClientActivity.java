package cz.cvut.felk.via.android;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class PartyPoolEventsSearcherClientActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec = tabHost.newTabSpec("search");
		spec.setContent(new Intent(this, SearchEventsActivity.class));
		spec.setIndicator("Search events");
		tabHost.addTab(spec);
	}
}