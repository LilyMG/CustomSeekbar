package com.example.lilit.customseekbar;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity implements  SeekBarWithTwoThumb.SeekBarChangeListener {

    private SeekBarWithTwoThumb swtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swtt = (SeekBarWithTwoThumb) findViewById(R.id.myseekbar);
        swtt.setSeekBarChangeListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void SeekBarValueChanged(int thumb1Value, int thumb2Value, Bitmap thumb) {

        int width = thumb.getWidth();
        int height = thumb.getHeight();
        float scaleWidth = ((float) thumb1Value) / width;
        float scaleHeight = ((float) thumb2Value) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        thumb= Bitmap.createBitmap(thumb, 0, 0, width, height, matrix, false);
        swtt.setThumb(thumb);

        Log.d("MyLog", "value 1 : " + thumb1Value);
        Log.d("MyLog", "value 2 : " + thumb2Value);
    }
}
