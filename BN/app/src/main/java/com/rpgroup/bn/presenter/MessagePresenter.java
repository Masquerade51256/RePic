package com.rpgroup.bn.presenter;

import android.util.Log;
import com.rpgroup.bn.data.loader.CreationLoader;
import com.rpgroup.bn.model.PersonalPic;
import com.rpgroup.bn.view.fragment.MessageFragment;
import io.reactivex.functions.Consumer;
import java.util.List;

public class MessagePresenter extends BasePresenter{
  private MessageFragment view;
  public MessagePresenter(MessageFragment view){
    this.view=view;
    loadPics();
  }
  public void onCreate(){loadPics();
    Log.i("myrefresh", "onRefresh: init");}
  public void onRefresh(){loadPics();}

  private CreationLoader mCreationLoader=new CreationLoader();

  private void loadPics() {
    mCreationLoader.findAllCreation().subscribe(
            new Consumer<List<PersonalPic>>() {
      @Override
      public void accept(List<PersonalPic> creations) {
        view.show(creations);
        Log.i("myrefresh", "onRefresh: doing1");
      }
    },
            new Consumer<Throwable>() {
      @Override
      public void accept(Throwable throwable) throws Exception {
        Log.i("myrefresh", "onRefresh: error1");
      }
    });
  }
}
