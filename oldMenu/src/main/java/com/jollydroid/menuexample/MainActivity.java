package com.jollydroid.menuexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton;
    private ActionMode.Callback actionModeCallback;
    private ActionMode actionMode;
    private CompoundButton.OnCheckedChangeListener checkedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerForContextMenu(findViewById(R.id.text_view_hello));

        actionModeCallback = new ActionMode.Callback() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.my_menu_two, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_compass:
                        toast("compass");
                        return true;
                    case R.id.action_camera:
                        toast("camera");
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                actionMode = null;
                toggleButton.setChecked(false);
            }
        };

        toggleButton = (ToggleButton) findViewById(R.id.toggle_button_one);
        checkedListener =
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked)
                    {
                        if (isChecked) {
                            actionMode = startActionMode(actionModeCallback);
                            actionMode.setTitle("Action Mode");
                        } else {
                            if (actionMode != null) {
                                actionMode.finish();
                            }
                        }
                    }
                };
        toggleButton.setOnCheckedChangeListener(checkedListener);
    }


    /*
    меню вульгарис
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_foo:
                toast("foo");
                return true;
            case R.id.action_foobar:
                toast("bar");
                return true;
            case R.id.action_baz:
                toast("baz");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
    контекстное меню
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_foo:
                toast("foo");
                return true;
            case R.id.action_foobar:
                toast("bar");
                return true;
            case R.id.action_baz:
                toast("baz");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /*
    утилитка
     */

    private void toast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
