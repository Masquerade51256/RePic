package com.rpgroup.bn.view.navigation;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.rpgroup.bn.R;
import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity {
  private NavigationFragment mCurrentFragment;
  private NavigationFragmentAdapter mAdapter;
  private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
  private boolean useMenuResource = true;
  private final Handler handler = new Handler();

  //*****UI*******
  private AHBottomNavigationViewPager viewPager;
  private AHBottomNavigation bottomNavigation;
  private FloatingActionButton floatingActionButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    boolean enabledTranslucentNavigation = getSharedPreferences("shared", Context.MODE_PRIVATE)
        .getBoolean("translucentNavigation", false);
    setTheme(enabledTranslucentNavigation
        ? R.style.AppTheme_TranslucentNavigation : R.style.AppTheme);
    setContentView(R.layout.activity_navigation);
    Intent intent = getIntent();
    initUI();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    handler.removeCallbacksAndMessages(null);
  }

  //init ui
  private void initUI() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    bottomNavigation = findViewById(R.id.bottom_navigation);
    viewPager = findViewById(R.id.view_pager);
    floatingActionButton = findViewById(R.id.floating_action_button);
    int[] tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
    AHBottomNavigationAdapter navigationAdapter = new AHBottomNavigationAdapter(this,
        R.menu.bottom_navigation_menu_3);
    navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);
    bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);
    bottomNavigation.setTranslucentNavigationEnabled(true);
    bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
      @Override
      public boolean onTabSelected(int position, boolean wasSelected) {
        if (mCurrentFragment == null) {
          mCurrentFragment = mAdapter.getCurrentFragment();
        }

        //刷新当前碎片
        if (wasSelected) {
          return true;
        }
				if (mCurrentFragment != null) {
          mCurrentFragment.willBeHidden();
        }

        viewPager.setCurrentItem(position, false);

				if (mCurrentFragment == null) {
					return true;
				}

				mCurrentFragment = mAdapter.getCurrentFragment();
				mCurrentFragment.willBeDisplayed();

				if (position == 1) {
					bottomNavigation.setNotification("", 1);
					//floatingActionButton.setVisibility(View.VISIBLE);
					floatingActionButton.setAlpha(0f);
					floatingActionButton.setScaleX(0f);
					floatingActionButton.setScaleY(0f);
					floatingActionButton.animate()
							.alpha(1)
							.scaleX(1)
							.scaleY(1)
							.setDuration(300)
							.setInterpolator(new OvershootInterpolator())
							.setListener(new Animator.AnimatorListener() {
							  @Override
                public void onAnimationStart(Animator animation) {

								}

								@Override
								public void onAnimationEnd(Animator animation) {
									floatingActionButton.animate()
											.setInterpolator(new LinearOutSlowInInterpolator())
											.start();
								}

								@Override
								public void onAnimationCancel(Animator animation) {

								}

								@Override
								public void onAnimationRepeat(Animator animation) {

								}
							})
							.start();

				} else {
					if (floatingActionButton.getVisibility() == View.VISIBLE) {
						floatingActionButton.animate()
								.alpha(0)
								.scaleX(0)
								.scaleY(0)
								.setDuration(300)
								.setInterpolator(new LinearOutSlowInInterpolator())
								.setListener(new Animator.AnimatorListener() {
									@Override
									public void onAnimationStart(Animator animation) {

									}

									@Override
									public void onAnimationEnd(Animator animation) {

									}

									@Override
									public void onAnimationCancel(Animator animation) {

									}

									@Override
									public void onAnimationRepeat(Animator animation) {

									}

								})
								.start();
					}
				}

				return true;
			}
		});

		viewPager.setOffscreenPageLimit(3);
		mAdapter = new NavigationFragmentAdapter(getSupportFragmentManager());
		viewPager.setAdapter(mAdapter);
		mCurrentFragment = mAdapter.getCurrentFragment();
  }
}
