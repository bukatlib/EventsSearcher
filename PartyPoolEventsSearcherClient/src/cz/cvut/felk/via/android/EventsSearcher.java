package cz.cvut.felk.via.android;

/**
 * Inspired by Google API example. (FragmentTabs)
 */

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;

/**
 * Base of tab activity.
 * @author Libor Bukata
 */
public class EventsSearcher extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        
        // Application navigation.
        final ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // Application name is undisplay.
        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

        // Create search tab.
        ActionBar.Tab searchTab = bar.newTab();
        searchTab.setText(this.getResources().getString(R.string.search_tab_name));
        searchTab.setTabListener(new TabListener<SearchTab>(this, "search", SearchTab.class));
        
        // Create add event tab.
        ActionBar.Tab addEventTab = bar.newTab();
        addEventTab.setText(this.getResources().getString(R.string.add_tab_name));
        addEventTab.setTabListener(new TabListener<AddEventTab>(this,"addEvent", AddEventTab.class));
        
        // Add tabs.
        bar.addTab(searchTab);
        bar.addTab(addEventTab);

        // Display last selected tab.
        if (savedInstanceState != null) {
            bar.setSelectedNavigationItem(savedInstanceState.getInt("tabId", 0));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	// Save current selected tab.
        super.onSaveInstanceState(outState);
        outState.putInt("tabId", getActionBar().getSelectedNavigationIndex());
    }

    public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
    	
    	// Main activity reference.
        private final Activity mActivity;
        // String ID of added tab.
        private final String mTag;
        // Class that extends Fragment.
        private final Class<T> mClass;
        // Restore parameters of mClass.
        private final Bundle mArgs;
        // Saved instance of mClass.
        private Fragment mFragment;

        public TabListener(Activity activity, String tag, Class<T> clz) {
            this(activity, tag, clz, null);
        }

        public TabListener(Activity activity, String tag, Class<T> clz, Bundle args) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
            mArgs = args;

            // Check to see if we already have a fragment for this tab, probably
            // from a previously saved state.  If so, deactivate it, because our
            // initial state is that a tab isn't shown.
            mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
            if (mFragment != null) {
                FragmentTransaction ft = mActivity.getFragmentManager().beginTransaction();
                ft.remove(mFragment);
                ft.commit();
            }
        }

        public void onTabSelected(Tab tab, FragmentTransaction ft) {
        	if (mFragment == null)	{
        		// Tab wasn't created yet. New instance have to be created.
        		mFragment = Fragment.instantiate(mActivity, mClass.getName(), mArgs);
        		ft.add(android.R.id.content, mFragment, mTag);
        	} else {
        		ft.add(android.R.id.content, mFragment, mTag); // attach
        	}
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                ft.remove(mFragment);	// detach
            }
        }

        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            // Nothing to do.
        }
    }
}

