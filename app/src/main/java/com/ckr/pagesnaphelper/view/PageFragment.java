package com.ckr.pagesnaphelper.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.ckr.pagesnaphelper.R;
import com.ckr.pagesnaphelper.adapter.MainAdapter;
import com.ckr.pagesnaphelper.model.Item;
import com.ckr.pageview.view.PageRecyclerView;
import com.ckr.pageview.view.PageView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends BaseFragment implements PageRecyclerView.OnPageChangeListener {
	private static final String TAG = "PageFragment";
	private static final String ID_LAYOUT = "layoutId";
	private static final String ID_LAYOUT_ITEM = "itemLayoutId";
	@BindView(R.id.pageView)
	PageView pageView;
	private MainAdapter mainAdapter;
	private ArrayList<Item> items;
	private final static int CAPACITY = 10;
	private int layoutId;
	private int itemLayoutId;
	private int startCount = 100;

	public static PageFragment newInstance(@LayoutRes int layoutId, @LayoutRes int itemLayoutId) {
		Bundle args = new Bundle();
		args.putInt(ID_LAYOUT, layoutId);
		args.putInt(ID_LAYOUT_ITEM, itemLayoutId);
		PageFragment fragment = new PageFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Bundle arguments = getArguments();
		if (arguments != null) {
			layoutId = arguments.getInt(ID_LAYOUT, R.layout.fragment_horizontal_grid);
			itemLayoutId = arguments.getInt(ID_LAYOUT_ITEM, R.layout.item_horizontal_grid);
		}
	}

	@Override
	protected int getContentLayoutId() {
		return layoutId;
	}

	@Override
	protected void init() {
		initData();
		initView();
	}

	private void initView() {
		pageView.addOnPageChangeListener(this);
		mainAdapter = new MainAdapter(getContext(), itemLayoutId);
		pageView.setAdapter(mainAdapter);
		pageView.updateAll(items);
	}

	private void initData() {
		items = new ArrayList<>(CAPACITY);
		Item item = new Item();
		try {
			for (int i = 0; i < CAPACITY; i++) {
				Item clone = (Item) item.clone();
				clone.setName("item  " + i);
				items.add(clone);
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void addData(int index) {
		int itemCount = mainAdapter.getRawItemCount();
		Item item = new Item();
		item.setName("item  "+startCount);
		startCount++;
		if (index == -1 || index >= itemCount) {
			mainAdapter.updateItem(item);
		} else {
			mainAdapter.updateItem(index, item);
		}
	}

	@Override
	protected void jumpToPage(int page) {
		int pageCount = mainAdapter.getPageCount();
		if (page>pageCount-1){
			page=pageCount-1;
		}
		pageView.setCurrentItem(page);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		Log.d(TAG, "onPageScrolled() called with: position = [" + position + "], positionOffset = [" + positionOffset + "], positionOffsetPixels = [" + positionOffsetPixels + "]");
	}

	@Override
	public void onPageSelected(int position) {
		Log.d(TAG, "onPageSelected() called with: position = [" + position + "]");
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		Log.d(TAG, "onPageScrollStateChanged() called with: state = [" + state + "]");
	}
}
