package com.colezz.mysafekeyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;

import com.colezz.safekeyboard.KeyboardEditTextTouchListener;
import com.colezz.safekeyboard.KeyboardState;
import com.colezz.safekeyboard.KeyboardType;
import com.colezz.safekeyboard.SafeKeyboard;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {


    private SafeKeyboard mSafeKeyboard;
    private TextInputLayout layoutName;
    private TextInputLayout layoutPhone;
    private TextInputLayout layoutIDCard;
    private TextInputLayout layoutMoney;
    private TextInputLayout layoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutName = findViewById(R.id.text_input_layout_name);
        layoutPhone = findViewById(R.id.text_input_layout_phone);
        layoutIDCard = findViewById(R.id.text_input_layout_idcard);
        layoutMoney = findViewById(R.id.text_input_layout_money);
        layoutPassword = findViewById(R.id.text_input_layout_password);
        KeyboardView keyboardView = findViewById(R.id.keyboardview);
        mSafeKeyboard = SafeKeyboard.newBuilder(this)
                .setRandom(false)                               // 设置是否为随机键盘
                .setUpperLetter(false)                          // 设置是否为大写字母(只有默认为字母键盘时才有效)
                .setEditText(layoutPhone.getEditText())         // 设置{@link EditText}
                .setKeyboardView(keyboardView)                  // 设置{@link KeyboardView}
                .setKeyboardType(KeyboardType.TYPE_LETTER)      // 设置键盘类型
                .setKeyboardState(KeyboardState.STATE_SHOW)     // 设置键盘状态
                .build();                                       // 构建SafeKeyboard
        setOnTouchListener();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setOnTouchListener() {
        layoutName.getEditText().setOnTouchListener(new KeyboardEditTextTouchListener(mSafeKeyboard, KeyboardType.TYPE_LETTER));
        layoutPhone.getEditText().setOnTouchListener(new KeyboardEditTextTouchListener(mSafeKeyboard, KeyboardType.TYPE_NUMBER));
        layoutIDCard.getEditText().setOnTouchListener(new KeyboardEditTextTouchListener(mSafeKeyboard, KeyboardType.TYPE_NUMBER_X));
        layoutMoney.getEditText().setOnTouchListener(new KeyboardEditTextTouchListener(mSafeKeyboard, KeyboardType.TYPE_NUMBER_POINT));
        layoutPassword.getEditText().setOnTouchListener(new KeyboardEditTextTouchListener(mSafeKeyboard, KeyboardType.TYPE_LETTER_NUMBER));
    }
}