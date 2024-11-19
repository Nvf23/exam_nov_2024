package com.example.exam_mobile_interfaces;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BottomFragment extends Fragment {

    private SeekBar seekBarRed;
    private SeekBar seekBarGreen;
    private SeekBar seekBarBlue;
    private OnColorSendListener colorSendListener;

    public interface OnColorSendListener {
        void onColorSend(int color);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);

        seekBarRed = view.findViewById(R.id.seekBarRed);
        seekBarGreen = view.findViewById(R.id.seekBarGreen);
        seekBarBlue = view.findViewById(R.id.seekBarBlue);
        Button sendColorButton = view.findViewById(R.id.sendColorButton);

        // Set color dynamically for SeekBar and thumb.
        SeekBar.OnSeekBarChangeListener colorUpdateListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateSeekBarColors();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

        seekBarRed.setOnSeekBarChangeListener(colorUpdateListener);
        seekBarGreen.setOnSeekBarChangeListener(colorUpdateListener);
        seekBarBlue.setOnSeekBarChangeListener(colorUpdateListener);

        // Button to update the text with the selected color.
        sendColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int red = seekBarRed.getProgress();
                int green = seekBarGreen.getProgress();
                int blue = seekBarBlue.getProgress();

                // Create a color with the RGB values.
                int color = Color.rgb(red, green, blue);

                if (colorSendListener != null) {
                    colorSendListener.onColorSend(color);
                }
            }
        });

        return view;
    }

    // Update the colors of SeekBars dynamically.
    private void updateSeekBarColors() {
        int red = seekBarRed.getProgress();
        int green = seekBarGreen.getProgress();
        int blue = seekBarBlue.getProgress();

        // Update the color of each SeekBar dynamically.
        seekBarRed.setThumbTintList(android.content.res.ColorStateList.valueOf(Color.rgb(red, 0, 0)));
        seekBarRed.setProgressTintList(android.content.res.ColorStateList.valueOf(Color.rgb(red, 0, 0)));

        seekBarGreen.setThumbTintList(android.content.res.ColorStateList.valueOf(Color.rgb(0, green, 0)));
        seekBarGreen.setProgressTintList(android.content.res.ColorStateList.valueOf(Color.rgb(0, green, 0)));

        seekBarBlue.setThumbTintList(android.content.res.ColorStateList.valueOf(Color.rgb(0, 0, blue)));
        seekBarBlue.setProgressTintList(android.content.res.ColorStateList.valueOf(Color.rgb(0, 0, blue)));
    }

    // Communication listener.
    public void setOnColorSendListener(OnColorSendListener listener) {
        this.colorSendListener = listener;
    }
}