package com.synology.viewmodelexample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Andrew Chang on 2018/8/11.
 */
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.local_count) TextView tvLocalCount;
    @Bind(R.id.vm_count) TextView tvVMCount;
    @Bind(R.id.observed_count) TextView tvObservedCount;

    private MainViewModel mViewModel;
    private int localInt = 0;
    private int observedCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupViewModel();
    }

    private void setupViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        final Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                tvVMCount.setText(String.valueOf(integer));
                observedCount++;
                onObserved();
            }
        };

        mViewModel.getAccumulationData().observe(this, observer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onCountChanged();
    }

    @OnClick(R.id.btn_acc)
    public void onAccClicked() {
        localInt++;
        mViewModel.accumulate();
        onCountChanged();
    }

    @OnClick(R.id.btn_acc3)
    public void onAcc3Clicked() {
        for(int i=0 ; i< 3 ; i++) {
            onAccClicked();
        }
    }

    @OnClick(R.id.btn_reset)
    public void onResetClicked() {
        localInt = 0;
        mViewModel.reset();
        onCountChanged();
    }

    @OnClick(R.id.btn_launch_activity)
    public void onLaunchActivityClicked() {
        Intent intent = new Intent(this, AnotherActivity.class);
        startActivity(intent);
    }

    private void onObserved() {
        tvObservedCount.setText(String.valueOf(observedCount));
    }

    private void onCountChanged() {
        tvLocalCount.setText(String.valueOf(localInt));
    }
}
