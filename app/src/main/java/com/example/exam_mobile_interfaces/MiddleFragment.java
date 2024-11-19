package com.example.exam_mobile_interfaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MiddleFragment extends Fragment {

    private TextView outputText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_middle, container, false);
        outputText = view.findViewById(R.id.outputText);
        return view;
    }

    // Updates the size of the text.
    public void updateText(String text, int textSize) {
        if (outputText != null) {
            outputText.setText(text);
            outputText.setTextSize(textSize);
        }
    }

    // Updates the color of the text.
    public void updateTextColor(int color) {
        if (outputText != null) {
            outputText.setTextColor(color);
        }
    }
}