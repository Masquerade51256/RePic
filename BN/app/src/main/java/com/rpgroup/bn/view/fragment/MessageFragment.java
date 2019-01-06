package com.rpgroup.bn.view.fragment;

import static com.example.apple.marvelgallery.view.common.ViewExtKt.mMap2;

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
import com.rpgroup.bn.view.navigation.NavigationFragment;
import com.rpgroup.bn.R;
import com.rpgroup.bn.model.PersonalPic;
import com.rpgroup.bn.presenter.pagePresenter.MessagePresenter;
import com.rpgroup.bn.view.adapter.MessageAdapter;
import com.rpgroup.bn.view.common.MainListAdapter;
import java.util.List;

public class MessageFragment extends NavigationFragment {
  private View view;
  private RecyclerView mRecyclerView;
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private MessagePresenter mMessagePresenter;

  @Override
  public View onCreateView(LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.page_messages, container, false);
    init();
    return view;
  }

  private void init() {
    mMessagePresenter = new MessagePresenter(this);
    Log.i("myRefresh", "onRefresh: before1");
    mRecyclerView = view.findViewById(R.id.SQrecyclerView);
    mRecyclerView.setLayoutManager((LayoutManager) new GridLayoutManager(getActivity(),1));
    mSwipeRefreshLayout = view.findViewById(R.id.SQswipeRefreshView);

    mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
      @Override
      public void onRefresh() {
        mMessagePresenter.onRefresh();
        Log.i("myRefresh", "onRefresh: do1");

        mSwipeRefreshLayout.postDelayed(new Runnable() {
          @Override
          public void run(){
            mSwipeRefreshLayout.setRefreshing(false);
          }
        }, 1000);
      }
    });
    mMessagePresenter.onCreate();
  }

  public void show(List<PersonalPic> pics) {
    List<MessageAdapter> messageAdapters = mMap2(pics);
    mRecyclerView.setAdapter(new MainListAdapter(messageAdapters));
    Log.i("myRefresh", "onRefresh: over1");
  }
}
