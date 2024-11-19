package com.example.exam_mobile_interfaces;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TopFragment extends Fragment {

    private EditText inputText;
    private TextView textSizeLabel;

    private int textSize = 16;

    public interface OnTextSendListener {
        void onTextSend(String text, int size);
    }

    private OnTextSendListener textSendListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);

        inputText = view.findViewById(R.id.inputText);
        Button sendButton = view.findViewById(R.id.sendButton);
        SeekBar sizeSeekBar = view.findViewById(R.id.sizeSeekBar);
        textSizeLabel = view.findViewById(R.id.textSizeLabel);

        // Text size seekbar
        sizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSize = 10 + progress;
                textSizeLabel.setText("Text size: " + textSize + "sp");
            }

            // This is mandatory to use whit the OnSeekBarChangeListener.
            // This sis used to do something when you start to drag the seekbar or when you drop it.
            // This not have use in my app.

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Button to update the text size.
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputText.getText().toString();
                if (textSendListener != null) {
                    textSendListener.onTextSend(text, textSize);
                }
            }
        });

        return view;
    }

    public void setOnTextSendListener(OnTextSendListener listener) {
        this.textSendListener = listener;
    }
}