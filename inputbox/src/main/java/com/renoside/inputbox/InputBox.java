package com.renoside.inputbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class InputBox extends ConstraintLayout implements View.OnClickListener {

    /**
     * 定义控价
     */
    private LinearLayout ico;
    private TextView title;
    private EditText input;
    private ImageView clear;
    private ImageView visibility;
    /**
     * 新建控件
     */
    private ImageView imageView;
    /**
     * 密码当前是否可见
     */
    private boolean isVisible = false;

    public InputBox(Context context) {
        super(context);
        initView();
        initListener();
    }

    public InputBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.input_box, this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputBox);
        initView();
        if (typedArray != null) {
            /**
             * 设置图标和图标大小
             */
            Drawable icoBackground = typedArray.getDrawable(R.styleable.InputBox_ico_background);
            if (icoBackground != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                imageView = new ImageView(getContext());
                imageView.setBackground(icoBackground);
                int icoSize = typedArray.getInt(R.styleable.InputBox_ico_size, 0);
                if (icoSize != 0)
                    imageView.setLayoutParams(new LayoutParams(icoSize * 2, icoSize * 2));
                ico.addView(imageView);
            }
            /**
             * 设置标题属性
             */
            String titleContent = typedArray.getString(R.styleable.InputBox_title_content);     //标题内容
            if (titleContent != null)
                title.setText(titleContent);
            int titleSize = typedArray.getInt(R.styleable.InputBox_title_size, 0);      //标题字体大小
            if (titleSize != 0)
                title.setTextSize(titleSize);
            int titleColor = typedArray.getColor(R.styleable.InputBox_title_color, Color.rgb(192, 192, 192));      //标题字体颜色
            if (titleColor != Color.rgb(255, 255, 255))
                title.setTextColor(titleColor);
            /**
             * 输入框字体属性
             */
            int inputSize = typedArray.getInt(R.styleable.InputBox_input_size, 0);
            if (inputSize != 0)
                input.setTextSize(inputSize);
            int inputColor = typedArray.getColor(R.styleable.InputBox_input_color, Color.rgb(192, 192, 192));      //标题字体颜色
            if (inputColor != Color.rgb(255, 255, 255))
                input.setTextColor(inputColor);
            /**
             * 输入框提示
             */
            String inputHint = typedArray.getString(R.styleable.InputBox_input_hint);
            if (inputHint != null) {
                SpannableString spannableString = new SpannableString(inputHint);   //定义hint的值
                AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(14, true);     //设置字体大小 true表示单位是sp
                spannableString.setSpan(absoluteSizeSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                input.setHint(new SpannedString(spannableString));
            }
            /**
             * 清空功能框和密码功能框二选一
             */
            boolean isClear = typedArray.getBoolean(R.styleable.InputBox_is_clear, false);
            boolean isPassword = typedArray.getBoolean(R.styleable.InputBox_is_password, false);
            if (isClear && !isPassword) {       //选择了清空框
                clear.setVisibility(View.VISIBLE);
                visibility.setVisibility(View.GONE);
            } else if (isClear && isPassword) {     //两者都选，显示清空框
                clear.setVisibility(View.VISIBLE);
                visibility.setVisibility(View.GONE);
            } else if (isPassword) {        //选择了密码框
                clear.setVisibility(View.GONE);
                visibility.setVisibility(View.VISIBLE);
                input.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
        /**
         * 设置功能监听
         */
        initListener();
        /**
         * 释放资源
         */
        typedArray.recycle();
    }

    public InputBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initListener();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        ico = findViewById(R.id.ico);
        title = findViewById(R.id.title);
        input = findViewById(R.id.input);
        clear = findViewById(R.id.clear);
        visibility = findViewById(R.id.visibility);
    }

    /**
     * 设置监听
     */
    private void initListener() {
        clear.setOnClickListener(this);
        visibility.setOnClickListener(this);
    }

    /**
     * 获取输入框文本
     * @return
     */
    public String getText() {
        return input.getText().toString();
    }

    /**
     * 监听器
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.clear) {
            input.setText("");      //清空输入框
        } else if (view.getId() == R.id.visibility) {
            if (!isVisible) {
                visibility.setSelected(true);       //更换成可见图标
                input.setTransformationMethod(HideReturnsTransformationMethod.getInstance());       //设置密码可见
                input.setSelection(input.getText().length());        //光标移到最后
                isVisible = true;
            } else {
                visibility.setSelected(false);      //更换成不可见图标
                input.setTransformationMethod(PasswordTransformationMethod.getInstance());          //设置密码不可见
                input.setSelection(input.getText().length());
                isVisible = false;
            }
        }
    }

}
