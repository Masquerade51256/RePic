package com.rpgroup.bn;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.rpgroup.bn.view.fragment.EditFragment;
import com.rpgroup.bn.view.fragment.MessageFragment;
import com.rpgroup.bn.view.fragment.PersonalFragment;
import java.util.ArrayList;

/**
 *
 */
public class DemoViewPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<DemoFragment> fragments = new ArrayList<>();
	private DemoFragment currentFragment;

	public DemoViewPagerAdapter(FragmentManager fm) {
		super(fm);

		fragments.clear();
		//fragments.add(new MessageFragment());
		fragments.add(DemoFragment.newInstance(0));
		fragments.add(new EditFragment());
		fragments.add(new PersonalFragment());
	}

	@Override
	public DemoFragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		if (getCurrentFragment() != object) {
			currentFragment = ((DemoFragment) object);
		}
		super.setPrimaryItem(container, position, object);
	}

	/**
	 * Get the current fragment
	 */
	public DemoFragment getCurrentFragment() {
		return currentFragment;
	}
}