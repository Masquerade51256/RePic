package com.rpgroup.bn.view.fragment;

import static com.example.apple.marvelgallery.view.common.ViewExtKt.mMap;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rpgroup.bn.DemoFragment;
import com.rpgroup.bn.R;
import com.rpgroup.bn.presenter.PersonalPresenter;
import com.rpgroup.bn.view.common.MainListAdapter;
import com.rpgroup.bn.view.adapter.PersonalPicAdapter;
import com.rpgroup.bn.model.PersonalPic;
import java.util.ArrayList;
import java.util.List;

public class PersonalFragment extends DemoFragment{
private View view;
private int picNumber=0;
private ArrayList<String> urlGroup;
private RecyclerView mRecyclerView;
private SwipeRefreshLayout mSwipeRefreshLayout;
private PersonalPresenter mPersonalPresenter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.personal_page, container, false);
    init();
    return view;
  }

  private void init(){
    mPersonalPresenter = new PersonalPresenter(this);
    mRecyclerView = view.findViewById(R.id.PPrecyclerView);
    mRecyclerView.setLayoutManager((LayoutManager) new GridLayoutManager(getActivity(),2));
    mSwipeRefreshLayout = view.findViewById(R.id.PPswipeRefreshView);

    mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
      @Override
      public void onRefresh() {
        mPersonalPresenter.onRefresh();
        mSwipeRefreshLayout.postDelayed(new Runnable() {
          @Override
          public void run() {
            mSwipeRefreshLayout.setRefreshing(false);
          }
        }, 1000);
      }
    });
    mPersonalPresenter.onCreate();
  }



  public void show(List<PersonalPic> pics){
    List<PersonalPicAdapter> ppas = mMap(pics);
    mRecyclerView.setAdapter(new MainListAdapter(ppas));
  }

}
