package com.rpgroup.bn.presenter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.rpgroup.bn.R;
import com.rpgroup.bn.data.InfoConfig;
import com.rpgroup.bn.data.loader.CreationLoader;
import com.rpgroup.bn.model.PersonalPic;
import com.rpgroup.bn.view.fragment.PersonalFragment;
import io.reactivex.functions.Consumer;
import java.util.List;

public class PersonalPresenter extends BasePresenter{
  private PersonalFragment view;
  private int size = 0;
  public PersonalPresenter(PersonalFragment view){
    this.view=view;
    loadPics();
  }
  public void onCreate(){loadPics();}
  public void onRefresh(){loadPics();}

  private CreationLoader mCreationLoader=new CreationLoader();

  private void loadPics(){
    checkSum(InfoConfig.getUserName());
  }

  private void checkSum(String name) {
    this.mCreationLoader.findByName(name).subscribe(new Consumer<List<PersonalPic>>() {
      @Override
      public void accept(List<PersonalPic> creations) {
        size = creations.size();
        view.show(creations);
        ((TextView)view.getView().findViewById(R.id.tv_creationCount)).setText(Integer.toString(size));
        ((TextView)view.getView().findViewById(R.id.tv_userName_personal)).setText(InfoConfig.getUserName());

      }
    },new Consumer<Throwable>() {
      @Override
      public void accept(Throwable throwable) throws Exception {
      }
    });
  }
}
