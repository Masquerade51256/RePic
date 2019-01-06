package com.rpgroup.bn.view.navigation;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.rpgroup.bn.view.fragment.EditFragment;
import com.rpgroup.bn.view.fragment.MessageFragment;
import com.rpgroup.bn.view.fragment.PersonalFragment;
import com.rpgroup.bn.view.navigation.NavigationFragment;
import java.util.ArrayList;

public class NavigationFragmentAdapter extends FragmentPagerAdapter {

	private final ArrayList<NavigationFragment> fragments = new ArrayList<>();
	private NavigationFragment currentFragment;
	//加载三个主要Fragment
	public NavigationFragmentAdapter(FragmentManager fm) {
		super(fm);
		fragments.clear();
		fragments.add(new MessageFragment());
		fragments.add(new EditFragment());
		fragments.add(new PersonalFragment());
	}

	@Override
	public NavigationFragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		if (getCurrentFragment() != object) {
			currentFragment = ((NavigationFragment) object);
		}
		super.setPrimaryItem(container, position, object);
	}
	public NavigationFragment getCurrentFragment() {
		return currentFragment;
	}
}