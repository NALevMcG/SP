package com.example.nicklevesque.thepanthertour;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
created by ntlevesque on 2/24/17
 Activity associated with the frontpage of my app
 */
public class FrontPage extends AppCompatActivity {

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    /* Called to do initial creation of the fragment. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
        StartAnimations();

    }

    /* Method that starts and finishes my animated splashscreen at the beginning*/
    private void StartAnimations() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        animation.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        animation.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(animation);

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    //start next activity once the splashscreen is done
                    Intent intent = new Intent(FrontPage.this,
                            MainDrawer.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    FrontPage.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    FrontPage.this.finish();
                }

            }
        };
        splashTread.start();

    }

}
