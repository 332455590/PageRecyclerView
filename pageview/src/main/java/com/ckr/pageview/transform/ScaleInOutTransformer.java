package com.ckr.pageview.transform;

import android.view.View;

public class ScaleInOutTransformer extends BaseTransformer {

	@Override
	protected void onTransform(View view, float position, boolean forwardDirection) {
		view.setPivotX(position < 0 ? 0 : view.getWidth());
		view.setPivotY(view.getHeight() / 2f);
		float scale = position < 0 ? 1f + position : 1f - position;
		view.setScaleX(scale);
		view.setScaleY(scale);
	}

	@Override
	protected void onPreTransform(View view, float position) {
		super.onPreTransform(view, position);
		view.setTranslationX(-view.getWidth() * position);
	}
}
