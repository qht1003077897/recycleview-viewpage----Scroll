package com.example.recycleviewtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends Fragment {

	private TextView textview;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment, null);
		textview = (TextView) view.findViewById(R.id.textview);
		int index = getArguments().getInt("index");
		switch (index) {
		case 0:
			textview.setText("第一页");
			break;
		case 1:
			textview.setText("第二页");
			break;
		case 2:
			textview.setText("第三页");
			break;
		case 3:
			textview.setText("第四页");
			break;
		case 4:
			textview.setText("第五页");
			break;
		case 5:
			textview.setText("第六页");
			break;
		case 6:
			textview.setText("第七页");
		case 7:
			textview.setText("第八页");
			break;
		default:
			break;
		}
		return view;
	}
}
