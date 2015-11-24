package com.jollydroid.menuexample;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ActionMode.Callback actionModeCallback;
    private TextView textView;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionModeCallback = new ActionMode.Callback() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.my_menu_two, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
    /*            // родные пункты меню "нумеруются" с 1, дополнительные - со 100
                while (menu.getItem(0).getOrder() < 100) {
                    MenuItem item = menu.getItem(0);
                    menu.removeItem(item.getItemId());
                    // теперь родные будут "нумероваться" с 200, то есть
                    // станут после дополнительных
                    menu.add(item.getGroupId(), item.getItemId(),
                            item.getOrder() + 200, item.getTitle());
                }
                return true;*/
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_compass:
                        View view = getCurrentFocus();
                        if (view instanceof TextView) {
                            TextView textView1 = (TextView) view;
                            final int selStart = textView1.getSelectionStart();
                            final int selEnd = textView1.getSelectionEnd();

                            final CharSequence selectedText = textView1.getText().subSequence(selStart, selEnd);
                            toast("compass: " + selectedText);

                        } else {
                            toast("compass");
                        }

                        mode.finish();

                        return true;
                    case R.id.action_camera:
                        toast("camera");
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        };

        textView = (TextView) findViewById(R.id.text_view_hello);
        editText = (EditText) findViewById(R.id.edit_text_hello);

        textView.setTextIsSelectable(true);
        textView.setCustomSelectionActionModeCallback(actionModeCallback);

        editText.setCustomSelectionActionModeCallback(actionModeCallback);
        editText.setCustomInsertionActionModeCallback(actionModeCallback);


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
    утилитка
     */

    private void toast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
