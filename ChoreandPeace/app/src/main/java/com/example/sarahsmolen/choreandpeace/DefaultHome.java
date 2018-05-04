package com.example.sarahsmolen.choreandpeace;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class DefaultHome extends AppCompatActivity {

    private CalendarView mCalendarView;

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
                    startActivityForResult(i3, 1);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final TextView c5 = (TextView)findViewById(R.id.chore5);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                c5.setVisibility(View.VISIBLE);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    private void createAndShowAlertDialog(final TextView view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DefaultHome.this);
        builder.setTitle("Have you completed this chore?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                view.setVisibility(View.INVISIBLE);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_home);

        final TextView c5 = (TextView)findViewById(R.id.chore5);
        c5.setVisibility(View.INVISIBLE);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = month+1 + "/" + dayOfMonth + "/" + year;

                TextView c1 = (TextView)findViewById(R.id.chore1);
                TextView c2 = (TextView)findViewById(R.id.chore2);
                TextView c3 = (TextView)findViewById(R.id.chore3);
                TextView c4 = (TextView)findViewById(R.id.chore4);

                //if not 5/16, 5/19, 5/22
                if(!date.equals("5/16/2018") && !date.equals("5/19/2018") && !date.equals("5/22/2018")){
                    c1.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    c3.setVisibility(View.VISIBLE);
                    c4.setVisibility(View.VISIBLE);
                }else{
                    if(date.equals("5/16/2018")){
                        c1.setVisibility(View.VISIBLE);
                        c2.setVisibility(View.INVISIBLE);
                        c3.setVisibility(View.INVISIBLE);
                        c4.setVisibility(View.INVISIBLE);
                    }
                    if(date.equals("5/19/2018")){
                        c2.setVisibility(View.VISIBLE);
                        c1.setVisibility(View.INVISIBLE);
                        c3.setVisibility(View.INVISIBLE);
                        c4.setVisibility(View.INVISIBLE);
                    }
                    if(date.equals("5/22/2018")){
                        c3.setVisibility(View.VISIBLE);
                        c4.setVisibility(View.VISIBLE);
                        c1.setVisibility(View.INVISIBLE);
                        c2.setVisibility(View.INVISIBLE);
                    }
                }

            }
        });

        final TextView c1 = (TextView)findViewById(R.id.chore1);
        final TextView c2 = (TextView)findViewById(R.id.chore2);
        final TextView c3 = (TextView)findViewById(R.id.chore3);
        final TextView c4 = (TextView)findViewById(R.id.chore4);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAndShowAlertDialog(c1);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAndShowAlertDialog(c2);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAndShowAlertDialog(c3);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAndShowAlertDialog(c4);
            }
        });

    }
}