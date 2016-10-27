package com.hfad.fragmentintro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    boolean isShowingDetail;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager manager = getSupportFragmentManager();

        final FragmentTransaction transaction = manager.beginTransaction();

        Fragment fragment = new ExampleFragment();
        transaction.replace(R.id.my_frame,fragment);
        isShowingDetail = true;

        transaction.commit();

        Button btn = (Button) findViewById(R.id.swap);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentTransaction transaction = manager.beginTransaction();

                if (isShowingDetail) {
                    Fragment fragment1 = new DetailFragment();
                    transaction.replace(R.id.my_frame, fragment1);
                    isShowingDetail = false;
                } else {
                    Fragment fragment = new ExampleFragment();
                    transaction.replace(R.id.my_frame,fragment);
                    isShowingDetail = true;
                }

                transaction.commit();

            }
        });
    }
}
