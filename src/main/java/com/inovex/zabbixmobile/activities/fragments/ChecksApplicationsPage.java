/*
This file is part of ZAX.

	ZAX is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	ZAX is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with ZAX.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.inovex.zabbixmobile.activities.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.inovex.zabbixmobile.R;
import com.inovex.zabbixmobile.adapters.ChecksItemsListAdapter;
import com.inovex.zabbixmobile.listeners.OnChecksItemSelectedListener;

/**
 * A page representing one particular application and thus containing a list of
 * all items in this application.
 * 
 */
public class ChecksApplicationsPage extends BaseServiceConnectedListFragment {

	private String mTitle = "";

	public static String TAG = ChecksApplicationsPage.class.getSimpleName();

	private OnChecksItemSelectedListener mCallbackMain;
	private ChecksItemsListAdapter mListAdapter;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try {
			mCallbackMain = (OnChecksItemSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnChecksItemSelectedListener.");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate: " + this.toString());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.page_base_list, null);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// setEmptyText(getResources().getString(R.string.empty_list_checks));
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		TextView emptyView = (TextView) getView().findViewById(
				android.R.id.empty);
		if (emptyView != null)
			emptyView.setText(R.string.empty_list_checks);

	}

	/**
	 * Selects an item in this page's list.
	 * 
	 * @param position
	 *            the item's position
	 */
	public void selectItem(int position) {
		if (getListAdapter() != null && getListView() != null) {
			getListView().setItemChecked(position, true);
			getListView().setSelection(position);
		}
		if (mListAdapter != null && mListAdapter.getCount() > position) {
			mListAdapter.setCurrentPosition(position);
		}
	}

	/**
	 * Restores the item selection using the list adapter's state.
	 */
	public void restoreItemSelection() {
		if (mListAdapter == null || mListAdapter.getCount() <= 0)
			return;
		int position = mListAdapter.getCurrentPosition();
		if (position >= mListAdapter.getCount())
			position = 0;
		// selectItem(mCurrentPosition);
		mCallbackMain.onItemSelected(position, mListAdapter.getItem(position),
				false);
	}

	@Override
	protected void setupListAdapter() {
		this.mListAdapter = mZabbixDataService.getChecksItemsListAdapter();
		setListAdapter(mListAdapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		mCallbackMain.onItemSelected(position, mListAdapter.getItem(position),
				true);
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public String getTitle() {
		return mTitle;
	}

	public void uncheckCurrentItem() {
		if (getView() != null && getListView() != null)
			getListView().setItemChecked(mListAdapter.getCurrentPosition(),
					false);
	}

}
