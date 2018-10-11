package com.richard.buttonview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * <br>Description : 多样式按钮
 * <br>Author : Richard
 * <br>Date : 2017/12/29 11:33
 * <br>Changelog:
 * <br>Version            Date            Author              Detail
 * <br> ----------------------------------------------------------------------
 * <br>1.0        2017/12/29 11:33       Richard      new file.
 */
public class ButtonView extends LinearLayout {

    private ProgressBar loadingView;
    private ImageView iconImage;
    private TextView textView;

    private CharSequence originText;

    //控件属性参数
    private float radius;
    private float topLeftRadius;
    private float topRightRadius;
    private float bottomLeftRadius;
    private float bottomRightRadius;
    private int backColor;


    public ButtonView(Context context) {
        super(context);
        init(null);
    }

    public ButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER);
        int size = getResources().getDimensionPixelSize(R.dimen.button_icon_and_loading_size);

        //加载中视图
        loadingView = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleSmallInverse);
        loadingView.setLayoutParams(new LayoutParams(size, size));
        loadingView.setVisibility(GONE);

        //icon imageView
        iconImage = new ImageView(getContext());
        iconImage.setLayoutParams(new LayoutParams(size, size));
        iconImage.setVisibility(GONE);

        //textView
        textView = new TextView(getContext());
        LayoutParams textViewLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewLayoutParams.setMargins(0, 0, 0, 0);
        textView.setLayoutParams(textViewLayoutParams);

        this.addView(loadingView);
        this.addView(iconImage);
        this.addView(textView);


        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ButtonView);

            //按钮小图标
            int icon = typedArray.getResourceId(R.styleable.ButtonView_bv_icon, -1);
            if (icon != -1) {
                iconImage.setVisibility(VISIBLE);
                this.setImageIcon(icon);
            } else {
                iconImage.setVisibility(GONE);
            }

            //设置文本内容
            originText = typedArray.getText(R.styleable.ButtonView_bv_text);
            this.setText(originText);
            //设置字体大小
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, typedArray.getDimension(R.styleable.ButtonView_bv_text_size, getResources().getDimensionPixelSize(R.dimen.textSize_15)));
            //设置字体颜色
            this.setTextColor(typedArray.getColor(R.styleable.ButtonView_bv_text_color, Color.WHITE));
            //获取按钮背景颜色
            backColor = typedArray.getColor(R.styleable.ButtonView_bv_backColor, Color.GRAY);

            //获取控件边角弧度属性参数
            radius = typedArray.getDimension(R.styleable.ButtonView_bv_radius, 0);
            if (radius == 0) {
                topLeftRadius = typedArray.getDimension(R.styleable.ButtonView_bv_topLeftRadius, 0);
                topRightRadius = typedArray.getDimension(R.styleable.ButtonView_bv_topRightRadius, 0);
                bottomRightRadius = typedArray.getDimension(R.styleable.ButtonView_bv_bottomRightRadius, 0);
                bottomLeftRadius = typedArray.getDimension(R.styleable.ButtonView_bv_bottomLeftRadius, 0);
            }
            //初始化控件的可用性
            this.setEnabled(typedArray.getBoolean(R.styleable.ButtonView_bv_enabled, true));

            typedArray.recycle();
            typedArray = null;
        }
    }


    /**
     * 生成未按下和按下后的状态的Drawable
     *
     * @param backColor         背景颜色
     * @param topLeftRadius     左上角圆角度
     * @param topRightRadius    右上角圆角度
     * @param bottomLeftRadius  左下角圆角度
     * @param bottomRightRadius 右上角圆角度
     */
    private StateListDrawable generatorSelector(int backColor, float topLeftRadius, float topRightRadius, float bottomLeftRadius, float bottomRightRadius) {
        StateListDrawable stateListDrawable = new StateListDrawable();

        //1、2两个参数表示左上角，3、4表示右上角，5、6表示右下角，7、8表示左下角
        //topLeftRadius, topLeftRadius, topRightRadius, topRightRadius, bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius
        float[] radius = new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius, bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius};

        //未按下的状态
        GradientDrawable unPressDrawable = this.generatorGradientDrawable(backColor, radius);

        //按下的状态
        GradientDrawable pressDrawable = this.generatorGradientDrawable(backColor, radius);
        pressDrawable.setAlpha(0xdf);

        stateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, unPressDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressDrawable);

        return stateListDrawable;
    }

    /**
     * 生成shape
     *
     * @param backColor 背景颜色
     * @param radius    四个边角弧度
     */
    private GradientDrawable generatorGradientDrawable(int backColor, float[] radius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(radius);
        gradientDrawable.setColor(backColor);
        return gradientDrawable;
    }


    /**
     * 设置按钮Selector
     *
     * @param backColor         背景颜色
     * @param topLeftRadius     左上角圆角度
     * @param topRightRadius    右上角圆角度
     * @param bottomLeftRadius  左下角圆角度
     * @param bottomRightRadius 右上角圆角度
     */
    public void setSelector(int backColor, float topLeftRadius, float topRightRadius, float bottomLeftRadius, float bottomRightRadius) {
        this.setBackgroundDrawable(generatorSelector(backColor, topLeftRadius, topRightRadius, bottomLeftRadius, bottomRightRadius));
    }


    /**
     * 设置按钮Selector
     *
     * @param backColor 背景颜色
     * @param radius    圆角角度
     */
    public void setSelector(int backColor, float radius) {
        this.setBackgroundDrawable(generatorSelector(backColor, radius, radius, radius, radius));
    }

    /**
     * 设置小图标
     *
     * @param imgRes 图片资源ID
     */
    public void setImageIcon(@DrawableRes int imgRes) {
        iconImage.setImageResource(imgRes);
        iconImage.setVisibility(VISIBLE);
    }


    /**
     * 获取文本
     *
     * @return
     */
    public CharSequence getText() {
        return textView.getText();
    }

    /**
     * 设置文本
     *
     * @param text 文本内容
     */
    public void setText(CharSequence text) {
        textView.setText(text);
    }

    /**
     * 设置文本字体大小
     *
     * @param textSize 字体大小
     */
    public void setTextSize(int textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    /**
     * 设置文本颜色
     *
     * @param textColor 字体颜色
     */
    public void setTextColor(int textColor) {
        textView.setTextColor(textColor);
    }

    /**
     * 显示加载视图
     */
    public void showLoading(String text) {
        this.setText(text);
        loadingView.setVisibility(VISIBLE);
        loadingView.setIndeterminate(true);
    }

    /**
     * 隐藏加载视图
     */
    public void hideLoading() {
        this.setText(originText);
        loadingView.setVisibility(GONE);
        loadingView.setIndeterminate(false);
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (radius == 0) {
            if (enabled) {
                this.setSelector(
                        backColor
                        , topLeftRadius
                        , topRightRadius
                        , bottomLeftRadius
                        , bottomRightRadius
                );
            } else {
                this.setBackgroundDrawable(this.generatorGradientDrawable(Color.parseColor("#C1C1C1"), new float[]{
                        topLeftRadius
                        , topLeftRadius
                        , topRightRadius
                        , topRightRadius
                        , bottomRightRadius
                        , bottomRightRadius
                        , bottomLeftRadius
                        , bottomLeftRadius
                }));
            }
        } else {
            if (enabled) {
                this.setSelector(backColor, radius);
            } else {
                this.setBackgroundDrawable(this.generatorGradientDrawable(Color.parseColor("#C1C1C1"), new float[]{
                        radius
                        , radius
                        , radius
                        , radius
                        , radius
                        , radius
                        , radius
                        , radius
                }));
            }
        }
        super.setEnabled(enabled);
    }
}
