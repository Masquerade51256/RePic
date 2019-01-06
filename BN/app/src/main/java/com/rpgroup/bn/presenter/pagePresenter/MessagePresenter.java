package com.rpgroup.bn.presenter.pagePresenter;

import android.util.Log;
import com.rpgroup.bn.data.loader.CreationLoader;
import com.rpgroup.bn.model.PersonalPic;
import com.rpgroup.bn.presenter.common.BasePresenter;
import com.rpgroup.bn.view.fragment.MessageFragment;
import io.reactivex.functions.Consumer;
import java.util.List;

//发现页面的presenter
public class MessagePresenter extends BasePresenter {
  private final MessageFragment view;
  public MessagePresenter(MessageFragment view) {
    this.view = view;
    loadPics();
  }

  public void onCreate() {
    loadPics();
    Log.i("myRefresh", "onRefresh: init");
  }

  public void onRefresh() {
    loadPics();
  }

  private final CreationLoader mCreationLoader = new CreationLoader();

  private void loadPics() {
    mCreationLoader.findAllCreation().subscribe(
            new Consumer<List<PersonalPic>>() {
      @Override
      public void accept(List<PersonalPic> creations) {
        view.show(creations);
        Log.i("myRefresh", "onRefresh: doing1");
      }
    }, new Consumer<Throwable>() {
      @Override
      public void accept(Throwable throwable) throws Exception {
        Log.i("myRefresh", "onRefresh: error1");
      }
    });
  }
}
