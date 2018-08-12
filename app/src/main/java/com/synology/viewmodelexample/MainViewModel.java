package com.synology.viewmodelexample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * Created by Andrew Chang on 2018/8/11.
 */
public class MainViewModel extends AndroidViewModel {
    private int mAccumulation = 0;
    private MutableLiveData<Integer> mAccumulationData;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Integer> getAccumulationData() {
        if(mAccumulationData == null) {
            mAccumulationData = new MutableLiveData<>();
            mAccumulationData.setValue(mAccumulation);
        }
        return mAccumulationData;
    }

    public void accumulate() {
        mAccumulation++;
        mAccumulationData.setValue(mAccumulation);
    }

    public void reset() {
        mAccumulation = 0;
        mAccumulationData.setValue(mAccumulation);
    }
}
