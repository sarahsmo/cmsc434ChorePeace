package com.example.sarahsmolen.choreandpeace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class DefaultHome extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_stats:
                    Intent i1 = new Intent(DefaultHome.this, StatsActivity.class);
                    startActivity(i1);
                    return true;
                case R.id.navigation_add:
                    Intent i3 = new Intent(DefaultHome.this, NewChore.class);
                    startActivity(i3);
                    return true;
                case R.id.navigation_home:
                    Intent i5 = new Intent(DefaultHome.this, DefaultHome.class);
                    startActivity(i5);
                    return true;
                case R.id.navigation_groups:
                    Intent i4 = new Intent(DefaultHome.this, GroupsActivity.class);
                    startActivity(i4);
                    return true;
                case R.id.navigation_settings:
                    Intent i2 = new Intent(DefaultHome.this, SettingsActivity.class);
                    startActivity(i2);
                    return true;
            }
            return false;
        }
    };

    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        disableShiftMode(navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
