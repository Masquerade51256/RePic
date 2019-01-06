package com.rpgroup.bn.view.fragment;

import static com.example.apple.marvelgallery.view.common.ViewExtKt.mMap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rpgroup.bn.view.navigation.NavigationFragment;
import com.rpgroup.bn.R;
import com.rpgroup.bn.presenter.pagePresenter.PersonalPresenter;
import com.rpgroup.bn.view.common.MainListAdapter;
import com.rpgroup.bn.view.adapter.PersonalPicAdapter;
import com.rpgroup.bn.model.PersonalPic;
import java.util.List;

public class PersonalFragment extends NavigationFragment {
  private View view;
  private RecyclerView mRecyclerView;
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private PersonalPresenter mPersonalPresenter;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.page_personal, container, false);
    init();
    return view;
  }

  private void init() {
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
        }, 700);
      }
    });
    mPersonalPresenter.onCreate();
  }

  public void show(List<PersonalPic> pics) {
    List<PersonalPicAdapter> personalPicAdapters = mMap(pics);
    mRecyclerView.setAdapter(new MainListAdapter(personalPicAdapters));
  }

}
