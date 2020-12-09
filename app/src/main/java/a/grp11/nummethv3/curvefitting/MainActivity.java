package a.grp11.nummethv3.curvefitting;

import a.grp11.nummethv3.R;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText x_values;
    EditText y_values;
    Button submit_x;
    Button submit_y;
    Button update_x;
    Button insert_x;
    Button update_y;
    Button insert_y;
    ListView show_x;
    ListView show_y;
    public static int i;
    public static ArrayList<String> x_axis=new ArrayList<String>();
    public static ArrayList<String> y_axis=new ArrayList<String>();
    TextView dispx;
    public static String appPackageName;

public MainActivity(){}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_2);x_values=(EditText)findViewById(R.id.x);
        y_values=(EditText)findViewById(R.id.y);
        submit_x=(Button)findViewById(R.id.submit_x);
        submit_y=(Button)findViewById(R.id.submit_y);
        update_x=(Button)findViewById(R.id.updatex);
        update_y=(Button)findViewById(R.id.updatey);
        show_x=(ListView)findViewById(R.id.x_axis);
        registerForContextMenu(show_x);
        show_y=(ListView)findViewById(R.id.y_axis);
        registerForContextMenu(show_y);

        appPackageName=getApplicationContext().getPackageName();// getPackageName() from Context or Activity



        x_values.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    submit_x.performClick();
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                    ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
                            .showSoftInput(x_values, InputMethodManager.SHOW_FORCED);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                    ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
                            .showSoftInput(y_values, InputMethodManager.SHOW_FORCED);
                }
                return false;
            }
        });
        y_values.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    submit_y.performClick();
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                    ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
                            .showSoftInput(y_values, InputMethodManager.SHOW_FORCED);
                }
                return false;
            }
        });

        submit_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity.x[i]=Double.parseDouble(x_values.getText().toString());
                //x_axis.add(i,Double.toString(MainActivity.x[i]));
                if (x_values.getText().toString()==null || x_values.getText().toString().trim().equals("") || y_values.getText().toString()==null || y_values.getText().toString().trim().equals("")){
                    Toast.makeText(getBaseContext(),"Input Field is Empty", Toast.LENGTH_LONG).show();
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .repeat(1)
                            .playOn(x_values);
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .repeat(1)
                            .playOn(y_values);
                }
                else if (x_axis.contains(x_values.getText().toString())){
                    Toast.makeText(getBaseContext(),"You've already entered that..", Toast.LENGTH_LONG).show();
                    YoYo.with(Techniques.Tada)
                            .duration(700)
                            .repeat(1)
                            .playOn(x_values);
                }
                else {
                    x_axis.add(x_values.getText().toString());
                    y_axis.add(y_values.getText().toString());
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, x_axis);
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, y_axis);

                    show_x.setAdapter(adapter);
                    ((EditText) findViewById(R.id.x)).setText("");
                    show_y.setAdapter(adapter1);
                    ((EditText) findViewById(R.id.y)).setText("");
                }
            }
        });
        show_x.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        show_y.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }
    public void calc_fit(View view) {
        if (y_axis.size()!=x_axis.size()){
            Toast.makeText(getBaseContext(),"Sizes of x and y don't match", Toast.LENGTH_LONG).show();
            YoYo.with(Techniques.Pulse)
                    .duration(700)
                    .repeat(1)
                    .playOn(show_x);
            YoYo.with(Techniques.Pulse)
                    .duration(700)
                    .repeat(1)
                    .playOn(show_y);
        }
        else if(y_axis.size()<2 && x_axis.size()<2){
            Toast.makeText(getBaseContext(),"Please enter at least 2 data points", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(this, Choose_fit.class);
            startActivity(intent);
        }
    }
    public void clear(View view) {
        x_axis.clear();
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, x_axis);
        show_x.setAdapter(adapter1);
        ((EditText)findViewById(R.id.x)).setText("");
        y_axis.clear();
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, y_axis);
        show_y.setAdapter(adapter2);
        ((EditText)findViewById(R.id.y)).setText("");
    }


    public String getPath(Uri uri) {
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return "";
    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Log.v(TAG,"Permission is granted");
                return true;
            } else {

                //Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            //Log.v(TAG,"Permission is granted");
            return true;
        }
    }




    @Override
    public void onResume() {
        super.onResume();
        //list.setAdapter(null);
        //updateMyList();
        //adapter=new LazyAdapter(this, ((String[])names.toArray(new String[0])),
          //      ((String[])status.toArray(new String[0])));
        //list.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, x_axis);
        show_x.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, y_axis);
        show_y.setAdapter(adapter2);
        ((EditText) findViewById(R.id.y)).setText("");
    }

    /**
     * MENU
     */



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.edit:
                // edit stuff here
                final int pos =info.position;
                //edit_xi(pos);
                x_values.setText(x_axis.get(pos));
                update_x=(Button)findViewById(R.id.updatex);
                update_x.setVisibility(View.VISIBLE);
                submit_x.setVisibility(View.INVISIBLE);
                update_x.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //MainActivity.x[i]=Double.parseDouble(x_values.getText().toString());
                        //x_axis.add(i,Double.toString(MainActivity.x[i]));
                        if (x_values.getText().toString()==null || x_values.getText().toString().trim().equals("")){
                            Toast.makeText(getBaseContext(),"Input Field is Empty", Toast.LENGTH_LONG).show();
                        }
                        else if (x_axis.contains(x_values.getText().toString())){
                            Toast.makeText(getBaseContext(),"You've already entered that..", Toast.LENGTH_LONG).show();
                        }
                        else {
                            x_axis.set(pos, x_values.getText().toString());
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, x_axis);
                            show_x.setAdapter(adapter);
                            ((EditText) findViewById(R.id.x)).setText("");
                            update_x.setVisibility(View.INVISIBLE);
                            submit_x.setVisibility(View.VISIBLE);
                        }

                    }
                });
                return true;
            case R.id.delete:
                // remove stuff here
                int pos1 =info.position;
                x_axis.remove(pos1);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, x_axis);
                show_x.setAdapter(adapter);
                ((EditText) findViewById(R.id.x)).setText("");
                return true;
            case R.id.insert:
                //insert element
                x_axis.add(info.position,"");
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, x_axis);
                show_x.setAdapter(adapter1);
                ((EditText) findViewById(R.id.x)).setText("");
                insert_x=(Button)findViewById(R.id.insertx);
                insert_x.setVisibility(View.VISIBLE);
                submit_x.setVisibility(View.INVISIBLE);
                insert_x.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //MainActivity.x[i]=Double.parseDouble(x_values.getText().toString());
                        //x_axis.add(i,Double.toString(MainActivity.x[i]));
                        if (x_values.getText().toString()==null || x_values.getText().toString().trim().equals("")){
                            Toast.makeText(getBaseContext(),"Input Field is Empty", Toast.LENGTH_LONG).show();
                        }
                        else if (x_axis.contains(x_values.getText().toString())){
                            Toast.makeText(getBaseContext(),"You've already entered that..", Toast.LENGTH_LONG).show();
                        }
                        else {
                            x_axis.set(info.position, x_values.getText().toString());
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, x_axis);
                            show_x.setAdapter(adapter);
                            ((EditText) findViewById(R.id.x)).setText("");
                            insert_x.setVisibility(View.INVISIBLE);
                            submit_x.setVisibility(View.VISIBLE);
                        }

                    }
                });

                return true;
            //y_axis list view
            case R.id.edity:
                // edit stuff here
                final int posy =info.position;
                //edit_yi(pos);
                y_values.setText(y_axis.get(posy));
                y_values.requestFocus();
                update_y=(Button)findViewById(R.id.updatey);
                update_y.setVisibility(View.VISIBLE);
                submit_y.setVisibility(View.INVISIBLE);
                update_y.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //MainActivity.x[i]=Double.parseDouble(x_values.getText().toString());
                        //x_axis.add(i,Double.toString(MainActivity.x[i]));
                        if (y_values.getText().toString()==null || y_values.getText().toString().trim().equals("")){
                            Toast.makeText(getBaseContext(),"Input Field is Empty", Toast.LENGTH_LONG).show();
                        }
                        else {
                            y_axis.set(posy, y_values.getText().toString());
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, y_axis);
                            show_y.setAdapter(adapter);
                            ((EditText) findViewById(R.id.y)).setText("");
                            update_y.setVisibility(View.INVISIBLE);
                            submit_y.setVisibility(View.VISIBLE);
                        }

                    }
                });
                return true;
            case R.id.deletey:
                // remove stuff here
                y_axis.remove(info.position);
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, y_axis);
                show_y.setAdapter(adapter3);
                ((EditText) findViewById(R.id.y)).setText("");
                return true;
            case R.id.inserty:
                //insert element
                y_values.requestFocus();
                y_axis.add(info.position,"");
                ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, y_axis);
                show_y.setAdapter(adapter4);
                ((EditText) findViewById(R.id.y)).setText("");
                insert_y=(Button)findViewById(R.id.inserty);
                insert_y.setVisibility(View.VISIBLE);
                submit_y.setVisibility(View.INVISIBLE);
                insert_y.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //MainActivity.x[i]=Double.parseDouble(x_values.getText().toString());
                        //x_axis.add(i,Double.toString(MainActivity.x[i]));
                        if (y_values.getText().toString()==null || y_values.getText().toString().trim().equals("")){
                            Toast.makeText(getBaseContext(),"Input Field is Empty", Toast.LENGTH_LONG).show();
                        }
                        else {
                            y_axis.set(info.position, y_values.getText().toString());
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, y_axis);
                            show_y.setAdapter(adapter);
                            ((EditText) findViewById(R.id.y)).setText("");
                            insert_y.setVisibility(View.INVISIBLE);
                            submit_y.setVisibility(View.VISIBLE);
                        }

                    }
                });

                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

}
