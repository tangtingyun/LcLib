/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.step.lclib.util;

import android.annotation.TargetApi;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.step.lclib.R;


public class LayoutHelper {
    public static final int RADIUS_ALL = 0;
    public static final int RADIUS_LEFT = 1;
    public static final int RADIUS_TOP = 2;
    public static final int RADIUS_RIGHT = 3;
    public static final int RADIUS_BOTTOM = 4;

    public static void setViewOutline(View view, AttributeSet attributes, int defStyleAttr, int defStyleRes) {
        TypedArray array = view.getContext().obtainStyledAttributes(attributes, R.styleable.viewOutLineStrategy, defStyleAttr, defStyleRes);
        int radius = array.getDimensionPixelSize(R.styleable.viewOutLineStrategy_clip_radius, 0);
        int hideSide = array.getInt(R.styleable.viewOutLineStrategy_clip_side, 0);
        array.recycle();
        setViewOutline(view, radius, hideSide);
    }

    public static void setViewOutline(View owner, final int radius, final int radiusSide) {
        //需要版本大于5.0
        if (!DeviceHelper.useFeature()) return;
        owner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            @TargetApi(21)
            public void getOutline(View view, Outline outline) {
                int w = view.getWidth(), h = view.getHeight();
                if (w == 0 || h == 0) {
                    return;
                }

                if (radiusSide != RADIUS_ALL) {
                    int left = 0, top = 0, right = w, bottom = h;
                    if (radiusSide == RADIUS_LEFT) {
                        right += radius;
                    } else if (radiusSide == RADIUS_TOP) {
                        bottom += radius;
                    } else if (radiusSide == RADIUS_RIGHT) {
                        left -= radius;
                    } else if (radiusSide == RADIUS_BOTTOM) {
                        top -= radius;
                    }
                    outline.setRoundRect(left, top, right, bottom, radius);
                    return;
                }

                int top = 0, bottom = h, left = 0, right = w;
                if (radius <= 0) {
                    outline.setRect(left, top, right, bottom);
                } else {
                    outline.setRoundRect(left, top, right, bottom, radius);
                }
            }
        });
        owner.setClipToOutline(radius > 0);
        owner.invalidate();
    }
}
