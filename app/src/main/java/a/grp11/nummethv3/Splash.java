package a.grp11.nummethv3;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;



public class Splash extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        // Hide the status bar.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //Hide action bar
// If your minSdkVersion is 11 or higher, instead use:
//getActionBar().hide();

        //animation on logo
        ImageView logo=(ImageView)findViewById(R.id.logo);
        YoYo.with(Techniques.FadeIn)
                .duration(700)
                .repeat(1)
                .playOn(logo);
        //setting the font of Title and subtitle(Note store a file inside the assets/fonts/font.ttf)
        TextView title=(TextView)findViewById(R.id.title);
        final Typeface titlefont  = Typeface.createFromAsset(getAssets(),"fonts/font.ttf");
        title.setTypeface(titlefont);
        TextView subtitle=(TextView)findViewById(R.id.subtitle);
        subtitle.setTypeface(titlefont);

        TextView Loading=(TextView)findViewById(R.id.loading);
        Loading.setTypeface(titlefont);


        //animating the title
        YoYo.with(Techniques.SlideInUp)
                .duration(700)
                .repeat(1)
                .playOn(title);

        //animating the subtitle
        YoYo.with(Techniques.SlideInUp)
                .duration(700)
                .repeat(1)
                .playOn(subtitle);
        //animating the Loading
        YoYo.with(Techniques.SlideInUp)
                .duration(700)
                .repeat(1)
                .playOn(Loading);
        //to display the activity only for 700 milliseconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Splash.this, HomeActivity.class); //launch the mainactivity after the splash screen ends
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();       //to remove it from history
            }
        }, 2000); //700 is the time in milliseconds
    }

}
