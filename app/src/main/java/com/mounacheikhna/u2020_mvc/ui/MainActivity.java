package com.mounacheikhna.u2020_mvc.ui;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.ViewGroup;

import com.mounacheikhna.u2020_mvc.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindColor;

/**
 * Created by cheikhna on 19/08/15.
 */
public class MainActivity extends Activity {
    @Bind(R.id.main_drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.main_navigation)
    NavigationView drawer;
    @Bind(R.id.main_content)
    ViewGroup content;

    @BindColor(R.color.status_bar)
    int statusBarColor;

    @Inject
    AppContainer appContainer;

}
