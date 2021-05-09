package com.step.lclib.widget.spinner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.step.lclib.R;
import com.step.lclib.util.DisplayHelper;
import com.step.lclib.util.ResUtils;

import java.util.Arrays;
import java.util.List;

public class DropDownMenu extends LinearLayout {

    // 顶部菜单布局
    private LinearLayout mTabMenuView;
    // 底部容器
    private FrameLayout mContainerView;
    // 弹出菜单父布局
    private FrameLayout mPopupMenuViews;
    // 遮罩半透明View  点击可关闭 DropDownMenu
    private View mMaskView;

    // tabMenuView 里面选中的tab位置， -1 表示未选中
    private int mCurrentTabPosition = -1;

    private int mContentLayoutId;

    private View mContentView;

    private int mDividerColor;

    private int mDividerWidth;

    private int mDividerMargin;

    private int mMenuTextSelectedColor;

    private int mMenuTextUnselectedColor;

    private int mMenuTextPaddingHorizontal;

    private int mMenuTextPaddingVertical;

    private int mMaskColor;

    private int mMenuTextSize;

    private Drawable mMenuSelectedIcon;

    private Drawable mMenuUnselectedIcon;

    private float mMenuHeightPercent = 0.5f;


    public DropDownMenu(Context context) {
        super(context, null);
    }

    public DropDownMenu(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.DropDownMenuStyle);
    }

    public DropDownMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DropDownMenu);
        mContentLayoutId = typedArray.getResourceId(R.styleable.DropDownMenu_ddm_contentLayoutId, -1);
        mDividerColor = typedArray.getColor(R.styleable.DropDownMenu_ddm_dividerColor, getResources().getColor(android.R.color.black));

        mDividerWidth = typedArray.getDimensionPixelSize(R.styleable.DropDownMenu_ddm_dividerWidth, DisplayHelper.dp2px(1));
        mDividerMargin = typedArray.getDimensionPixelSize(R.styleable.DropDownMenu_ddm_dividerMargin, DisplayHelper.dp2px(10));
        int underlineColor = typedArray.getColor(R.styleable.DropDownMenu_ddm_underlineColor, getResources().getColor(android.R.color.holo_blue_bright));
        int underlineHeight = typedArray.getDimensionPixelSize(R.styleable.DropDownMenu_ddm_underlineHeight, DisplayHelper.dp2px(10));
        int menuBackgroundColor = typedArray.getColor(R.styleable.DropDownMenu_ddm_menuBackgroundColor, Color.WHITE);
        mMaskColor = typedArray.getColor(R.styleable.DropDownMenu_ddm_maskColor, getResources().getColor(android.R.color.darker_gray));
        mMenuTextSelectedColor = typedArray.getColor(R.styleable.DropDownMenu_ddm_menuTextSelectedColor, getResources().getColor(android.R.color.black));
        mMenuTextUnselectedColor = typedArray.getColor(R.styleable.DropDownMenu_ddm_menuTextUnselectedColor, getResources().getColor(android.R.color.holo_red_dark));
        mMenuTextPaddingHorizontal = typedArray.getDimensionPixelSize(R.styleable.DropDownMenu_ddm_menuTextPaddingHorizontal, DisplayHelper.dp2px(5));
        mMenuTextPaddingVertical = typedArray.getDimensionPixelSize(R.styleable.DropDownMenu_ddm_menuTextPaddingVertical, DisplayHelper.dp2px(5));
        mMenuTextSize = typedArray.getDimensionPixelSize(R.styleable.DropDownMenu_ddm_menuTextSize, DisplayHelper.dp2px(5));
        mMenuUnselectedIcon = ResUtils.getDrawableAttrRes(getContext(), typedArray, R.styleable.DropDownMenu_ddm_menuUnselectedIcon);
        if (mMenuUnselectedIcon == null) {
            mMenuUnselectedIcon = ResUtils.getVectorDrawable(getContext(), R.drawable.ddm_ic_arrow_down);
        }
        mMenuSelectedIcon = ResUtils.getDrawableAttrRes(getContext(), typedArray, R.styleable.DropDownMenu_ddm_menuSelectedIcon);
        if (mMenuSelectedIcon == null) {
            mMenuSelectedIcon = ResUtils.getVectorDrawable(getContext(), R.drawable.ddm_ic_arrow_up);
        }
        mMenuHeightPercent = typedArray.getFloat(R.styleable.DropDownMenu_ddm_menuHeightPercent, mMenuHeightPercent);

        typedArray.recycle();

        mTabMenuView = new LinearLayout(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTabMenuView.setOrientation(HORIZONTAL);
        mTabMenuView.setBackgroundColor(menuBackgroundColor);
        mTabMenuView.setLayoutParams(params);
        addView(mTabMenuView, 0);

        View underLine = new View(getContext());
        underLine.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, underlineHeight));
        underLine.setBackgroundColor(underlineColor);
        addView(underLine, 1);

        mContainerView = new FrameLayout(context);
        mContainerView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mContainerView, 2);
    }

    public void setDropDownMenu(String[] tabTexts, List<View> popupViews) {
        setDropDownMenu(Arrays.asList(tabTexts), popupViews);
    }

    public void setDropDownMenu(List<String> tabTexts, List<View> popupViews) {
        if (mContentLayoutId == -1) {
            throw new IllegalArgumentException("先设置初始化布局参数.");
        }

        setDropDownMenu(tabTexts, popupViews, View.inflate(getContext(), mContentLayoutId, null));
    }

    public void setDropDownMenu(String[] tabTexts, List<View> popupViews, View contentView) {
        setDropDownMenu(Arrays.asList(tabTexts), popupViews, contentView);
    }

    public void setDropDownMenu(List<String> tabTexts, List<View> popupViews, View contentView) {
        if (tabTexts.size() != popupViews.size()) {
            throw new IllegalArgumentException("参数错误");
        }

        for (int i = 0; i < tabTexts.size(); i++) {
            addTab(tabTexts, i);
        }

        mContentView = contentView;
        mContainerView.addView(contentView, 0);

        mMaskView = new View(getContext());
        mMaskView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mMaskView.setBackgroundColor(mMaskColor);
        mMaskView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
            }
        });

        mContainerView.addView(mMaskView, 1);
        mMaskView.setVisibility(GONE);

        if (mContainerView.getChildAt(2) != null) {
            mContainerView.removeViewAt(2);
        }

        mPopupMenuViews = new FrameLayout(getContext());
        mPopupMenuViews.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (DisplayHelper.getDisplaySize(getContext(), true).y * mMenuHeightPercent)));
        mPopupMenuViews.setVisibility(GONE);
        mContainerView.addView(mPopupMenuViews, 2);

        for (int i = 0; i < popupViews.size(); i++) {
            popupViews.get(i).setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mPopupMenuViews.addView(popupViews.get(i), i);
        }
    }

    private void addTab(List<String> tabTexts, int index) {
        final TextView tab = new TextView(getContext());
        tab.setSingleLine();
        tab.setEllipsize(TextUtils.TruncateAt.END);
        tab.setGravity(Gravity.CENTER);
        tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, mMenuTextSize);
        tab.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        tab.setTextColor(mMenuTextUnselectedColor);
        setArrowIconEnd(tab, mMenuUnselectedIcon);
        tab.setText(tabTexts.get(index));
        tab.setPadding(mMenuTextPaddingHorizontal, mMenuTextPaddingVertical, mMenuTextPaddingHorizontal, mMenuTextPaddingVertical);
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchMenu(tab);
            }
        });
        mTabMenuView.addView(tab);
        if (index < tabTexts.size() - 1) {
            View view = new View(getContext());
            LayoutParams params = new LayoutParams(mDividerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
            params.topMargin = mDividerMargin;
            params.bottomMargin = mDividerMargin;
            view.setLayoutParams(params);
            view.setBackgroundColor(mDividerColor);
            mTabMenuView.addView(view);
        }


    }


    public void setTabMenuText(String text) {
        if (mCurrentTabPosition != -1) {
            ((TextView) mTabMenuView.getChildAt(mCurrentTabPosition)).setText(text);
        }
    }


    public void setTabMenuClickable(boolean clickable) {
        for (int i = 0; i < mTabMenuView.getChildCount(); i = i + 2) {
            mTabMenuView.getChildAt(i).setClickable(clickable);
        }
    }

    public void closeMenu() {
        if (mCurrentTabPosition != -1) {
            TextView tvTab = ((TextView) mTabMenuView.getChildAt(mCurrentTabPosition));
            tvTab.setTextColor(mMenuTextUnselectedColor);
            setArrowIconEnd(tvTab, mMenuUnselectedIcon);
            mPopupMenuViews.setVisibility(GONE);
            mPopupMenuViews.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.ddm_menu_out));
            mMaskView.setVisibility(GONE);
            mMaskView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.ddm_mask_out));
            mCurrentTabPosition = -1;
        }
    }

    public boolean isShowing() {
        return mCurrentTabPosition != -1;
    }


    public View getContentView() {
        return mContentView;
    }

    private void switchMenu(View target) {
        for (int i = 0; i < mTabMenuView.getChildCount(); i = i + 2) {
            TextView menuViewChildAt = (TextView) mTabMenuView.getChildAt(i);
            if (target == mTabMenuView.getChildAt(i)) {
                if (mCurrentTabPosition == i) {
                    closeMenu();
                } else {
                    if (mCurrentTabPosition == -1) {
                        mPopupMenuViews.setVisibility(VISIBLE);
                        mPopupMenuViews.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.ddm_menu_in));
                        mMaskView.setVisibility(VISIBLE);
                        mMaskView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.ddm_mask_in));
                        mPopupMenuViews.getChildAt(i / 2).setVisibility(VISIBLE);
                    } else {
                        mPopupMenuViews.getChildAt(i / 2).setVisibility(VISIBLE);
                    }
                    mCurrentTabPosition = i;
                    menuViewChildAt.setTextColor(mMenuTextSelectedColor);
                    setArrowIconEnd((TextView) mTabMenuView.getChildAt(i), mMenuSelectedIcon);
                }
            } else {
                menuViewChildAt.setTextColor(mMenuTextUnselectedColor);
                setArrowIconEnd(menuViewChildAt, mMenuUnselectedIcon);
                mPopupMenuViews.getChildAt(i / 2).setVisibility(GONE);
            }
        }
    }

    private void setArrowIconEnd(TextView view, Drawable arrowIcon) {
        if (view == null) return;
        view.setCompoundDrawables(null, null, arrowIcon, null);
    }
}
