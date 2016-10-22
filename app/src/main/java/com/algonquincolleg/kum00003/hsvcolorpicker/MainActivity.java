package com.algonquincolleg.kum00003.hsvcolorpicker;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import Model.HSVModel;

/**
 * The Controller for RGBAModel.
 *
 * As the Controller:
 *   a) event handler for the View
 *   b) observer of the Model (HSVModel)
 *
 * Features the Update / React Strategy.
 *
 * @author Sercan Kum kum00003@algonquinlive.com
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements Observer
        , SeekBar.OnSeekBarChangeListener
{
    // CLASS VARIABLES
    private static final String ABOUT_DIALOG_TAG = "About";
    private static final String LOG_TAG          = "RGBA";

    // INSTANCE VARIABLES
    // Pro-tip: different naming style; the 'm' means 'member'
    private AboutDialogFragment mAboutDialog;
    private TextView            mColorSwatch;
    private HSVModel            mModel;
    private SeekBar             mHue;
    private SeekBar             mSaturation;
    private SeekBar             mValue;
    private TextView            mHueTV;
    private TextView            mSaturationTV;
    private TextView            mValueTV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate a new AboutDialogFragment()
        // but do not show it (yet)
        mAboutDialog = new AboutDialogFragment();

        // Instantiate a new RGBA model
        // Initialize the model red (max), green (min), blue (min), and alpha (max)
        mModel = new  HSVModel();
        mModel.setHue( HSVModel.MAX_HUE );
        mModel.setSaturation( HSVModel.MIN_PER );
        mModel.setValue( HSVModel.MIN_PER );


        // The Model is observing this Controller (class MainActivity implements Observer)
        mModel.addObserver( this );

        // reference each View
        mColorSwatch = (TextView) findViewById( R.id.colorSwatch );
        mHue = (SeekBar) findViewById( R.id.hueSB );
        mSaturation = (SeekBar) findViewById( R.id.saturationSB );
        mValue  = (SeekBar) findViewById( R.id.valueSB );
        mHueTV = (TextView) findViewById( R.id.Hue);
        mSaturationTV = (TextView) findViewById( R.id.saturation);
        mValueTV = (TextView) findViewById(R.id.value);

        // set the domain (i.e. max) for each component
        mHue.setMax((int)HSVModel.MAX_HUE);
        mSaturation.setMax((int)HSVModel.MAX_PER);
        mValue.setMax((int)HSVModel.MAX_PER);


        // register the event handler for each <SeekBar>
        mHue.setOnSeekBarChangeListener( this );
        mSaturation.setOnSeekBarChangeListener( this );
        mValue.setOnSeekBarChangeListener( this );
        mColorSwatch.setOnLongClickListener(
                new View.OnLongClickListener(){
                    @Override
                    public boolean onLongClick(View view){
                        Toast.makeText(getApplicationContext(), "H:" + mHue.getProgress() + "\u00B0" + "S:" + mSaturation.getProgress() + "%" + "V:" + mValue.getProgress()+"%",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }

        );

        // initialize the View to the values of the Model
        this.updateView();
    }
    public void onTap(View view){

        switch ( view.getId() ) {

            case R.id.blackButton:
                mModel.asBlack();
                break;
            case R.id.redButton:
                mModel.asRed();
                break;
            case R.id.limeButton:
                mModel.asLime();
                break;
            case R.id.blueButton:
                mModel.asBlue();
                break;
            case R.id.yellowButton:
                mModel.asYellow();
                break;
            case R.id.cyanButton:
                mModel.asCyan();
                break;
            case R.id.magentaButton:
                mModel.asMagenta();
                break;
            case R.id.silverButton:
                mModel.asSilver();
                break;
            case R.id.grayButton:
                mModel.asGray();
                break;
            case R.id.maroonButton:
                mModel.asMaroon();
                break;
            case R.id.oliveButton:
                mModel.asOlive();
                break;
            case R.id.greenButton:
                mModel.asGreen();
                break;
            case R.id.purpleButton:
                mModel.asPurple();
                break;
            case R.id.tealButton:
                mModel.asTeal();
                break;
            case R.id.navyButton:
                mModel.asNavy();
                break;

        }
       //HSV
        Toast.makeText(getApplicationContext(), "H:" + mHue.getProgress() + "\u00B0" + "S:" + mSaturation.getProgress() + "%" + "V:" + mValue.getProgress()+"%",Toast.LENGTH_SHORT).show();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * Event handler for the <SeekBar>s: red, green, blue, and alpha.
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        // Did the user cause this event?
        // YES > continue
        // NO  > leave this method
        if ( !fromUser ) {
            return;
        }

        // Determine which <SeekBark> caused the event (switch + case)
        // GET the SeekBar's progress, and SET the model to it's new value
        switch ( seekBar.getId() ) {
            case R.id.hueSB:
                mModel.setHue( mHue.getProgress());
                mHueTV.setText( getResources().getString(R.string.hueProgress, progress).toUpperCase() );
                break;
            case R.id.saturationSB:
                mModel.setSaturation( mSaturation.getProgress());
                mSaturationTV.setText( getResources().getString(R.string.saturationProgress, progress).toUpperCase() );
                break;
            case R.id.valueSB:
                mModel.setValue( mValue.getProgress());
                mValueTV.setText( getResources().getString(R.string.valueProgress, progress).toUpperCase() );
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // No-Operation
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.hueSB:
                mHueTV.setText( getResources().getString(R.string.hue) );
                break;
            case R.id.saturationSB:
                mSaturationTV.setText(getResources().getString(R.string.saturation));
                break;
            case R.id.valueSB:
                mValueTV.setText(getResources().getString(R.string.value));
                break;

        }
    }

    // The Model has changed state!
    // Refresh the View to display the current values of the Model.
    @Override
    public void update(Observable observable, Object data) {
        this.updateView();
    }

    private void updateHueSB() {
        mValue.setProgress( (int) mModel.getHue() );
    }

    private void updateColorSwatch() {
        mColorSwatch.setBackgroundColor(mModel.getColor());
    }

    private void updateSaturationSB() {
        mSaturation.setProgress((int)mModel.getSaturation() );
    }

    private void updateValueSB() {
        mValue.setProgress( (int) mModel.getValue() ); }

    // synchronize each View component with the Model
    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateValueSB();
    }
}
