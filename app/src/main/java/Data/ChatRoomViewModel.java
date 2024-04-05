package Data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

    public class ChatRoomViewModel extends ViewModel {
        public MutableLiveData<ArrayList<Chatmessage>> messages = new MutableLiveData<>();
    }


