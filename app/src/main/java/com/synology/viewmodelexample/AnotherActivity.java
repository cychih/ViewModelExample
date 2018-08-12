package com.synology.viewmodelexample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnotherActivity extends AppCompatActivity {
    @Bind(R.id.vm_count) TextView tvVMCount;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        ButterKnife.bind(this);

        setupViewModel();
    }

    private void setupViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        final Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                tvVMCount.setText(integer.toString());
            }
        };

        mViewModel.getAccumulationData().observe(this, observer);
    }


    @OnClick(R.id.btn_acc)
    public void onAccClicked() {
        mViewModel.accumulate();
    }
}
