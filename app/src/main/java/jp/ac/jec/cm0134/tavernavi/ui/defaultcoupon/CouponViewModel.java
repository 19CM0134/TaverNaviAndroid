package jp.ac.jec.cm0134.tavernavi.ui.defaultcoupon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CouponViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CouponViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}