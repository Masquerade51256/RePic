package com.rpgroup.bn.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rpgroup.bn.DemoFragment;
import com.rpgroup.bn.R;

public class blankFragment extends DemoFragment{
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.blank_layout, container, false);
    return view;
  }

}
