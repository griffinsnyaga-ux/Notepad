package com.notes.simple;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = findViewById(R.id.titleText);
        TextView subtitle = findViewById(R.id.subtitleText);

        fadeIn(title, 0);
        fadeIn(subtitle, 800);
    }

    private void fadeIn(TextView view, long delay) {
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(2000);
        animation.setStartOffset(delay);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }
}
