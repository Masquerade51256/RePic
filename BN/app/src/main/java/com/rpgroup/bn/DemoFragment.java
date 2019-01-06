package com.rpgroup.bn;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;


/**
 *
 */
public class DemoFragment extends Fragment {
	
	private View fragmentContainer;
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;
	
	/**
	 * Create a new instance of the fragment
	 */
	public static DemoFragment newInstance(int index) {
		DemoFragment fragment = new DemoFragment();
		Bundle b = new Bundle();
		b.putInt("index", index);
		fragment.setArguments(b);
		return fragment;
	}
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (getArguments().getInt("index", 0) == 0) {
			/*recycler   View view = inflater.inflate(R.layout.square_page, container, false);*/
			View view = inflater.inflate(R.layout.message_info, container, false);
			initSquarePG(view);
			return view;
		}else {
			return null;
		}
	}

	private void initSquarePG(View view) {
		fragmentContainer = view.findViewById(R.id.characterView);


//		final ImageButton btn_favorite=view.findViewById((R.id.btn_favorite));
//		btn_favorite.setOnClickListener(new View.OnClickListener() {
//			boolean f=false;
//			@Override
//			public void onClick(View v) {
//				if(f){
//					btn_favorite.setImageResource(R.drawable.ic_favorite);
//					f=false;
//				}
//				else {
//					btn_favorite.setImageResource(R.drawable.ic_favorited);
//					f=true;
//				}
//
//			}
//		});
//
//		ImageButton btn_comment = view.findViewById(R.id.btn_comment);
//		btn_comment.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v)  {
//				final PopupWindow mPopWindow;
//				//设置contentView
//				View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popup_comment, null);
//
//				mPopWindow = new PopupWindow(contentView,
//						ActionBar.LayoutParams.MATCH_PARENT,
//						ActionBar.LayoutParams.WRAP_CONTENT, true);
//				mPopWindow.setContentView(contentView);
//
//				//防止PopupWindow被软件盘挡住（可能只要下面一句，可能需要这两句）
////        mPopWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
//				mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//
//				//设置软键盘弹出
//				InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//				inputMethodManager.toggleSoftInput(1000, InputMethodManager.HIDE_NOT_ALWAYS);//这里给它设置了弹出的时间
//
//				//设置各个控件的点击响应
//				final EditText editText = contentView.findViewById(R.id.pop_editText);
//				Button btn = contentView.findViewById(R.id.pop_btn);
//
//				btn.setOnClickListener(new View.OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						String inputString = editText.getText().toString();
//						Toast.makeText(getContext(), inputString, Toast.LENGTH_SHORT).show();
//						mPopWindow.dismiss();//让PopupWindow消失
//					}
//				});
//				//是否具有获取焦点的能力
//				mPopWindow.setFocusable(true);
//				//显示PopupWindow
//				View rootView = LayoutInflater.from(getContext()).inflate(R.layout.message_info, null);
//				mPopWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
//			}
//		});

	}


//	public void refresh() {
//		if (getArguments().getInt("index", 0) > 0 && recyclerView != null) {
//			recyclerView.smoothScrollToPosition(0);
//		}
//	}

	/**
	 * Called when a fragment will be displayed
	 */
	public void willBeDisplayed() {
		// Do what you want here, for example animate the content
		if (fragmentContainer != null) {
			Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
			fragmentContainer.startAnimation(fadeIn);
		}
	}

	/**
	 * Called when a fragment will be hidden
	 */
	public void willBeHidden() {
		if (fragmentContainer != null) {
			Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
			fragmentContainer.startAnimation(fadeOut);
		}
	}

}
