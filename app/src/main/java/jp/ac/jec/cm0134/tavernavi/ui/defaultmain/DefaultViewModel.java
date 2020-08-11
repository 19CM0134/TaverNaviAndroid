package jp.ac.jec.cm0134.tavernavi.ui.defaultmain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DefaultViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DefaultViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}