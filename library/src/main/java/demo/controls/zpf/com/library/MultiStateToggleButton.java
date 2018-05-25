package demo.controls.zpf.com.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zpf on 2018/5/25.
 */

public class MultiStateToggleButton extends LinearLayout {


    public interface onBtnClick{

        void onClick(Button btn);

    }

    private onBtnClick l;

    /**
     * The specified texts
     */
    CharSequence[]   texts;
    /**
     * The layout containing all buttons
     */
    private LinearLayout mainLayout;
    /**
     * A list of rendered tv. Used to get state, among others
     */
    List<View> buttons;

    public MultiStateToggleButton(Context context){
        super(context);
    }

    public MultiStateToggleButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setResource(CharSequence[] texts){
        setElement(texts);
    }

    public void setListener(onBtnClick mListener){
        this.l = mListener;
    }


    private void setElement(CharSequence[] texts){
        this.texts = texts;
        final int textCount = texts != null ? texts.length : 0;
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (mainLayout == null) {
            mainLayout = (LinearLayout) inflater.inflate(R.layout.view_multi_state_toggle_button, this, true);
            setLayoutBG(mainLayout);
        }
        mainLayout.removeAllViews();
        this.buttons = new ArrayList<>(textCount);
        for (int i = 0; i < textCount; i++) {
            RelativeLayout layout= (RelativeLayout) inflater.inflate(R.layout.view_toggle_button, mainLayout, false);
            Button b = (Button)layout.findViewById(R.id.toggle_btn);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)b.getLayoutParams();
            if(i == 0){
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            }else if(i == textCount-1){
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            }
            else{
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
            }
            b.setLayoutParams(params);

            b.setText(texts != null ? texts[i] : "");
            final int position = i;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setValue(position);
                    if(l != null){
                        l.onClick((Button)v);
                    }
                }
            });
            mainLayout.addView(layout);
            this.buttons.add(b);
        }
        setValue(0);
    }


    private void setValue(int position) {
        for (int i = 0; i < this.buttons.size(); i++) {
            if (i == position) {
                setButtonState(buttons.get(i), true);
            } else {
                setButtonState(buttons.get(i), false);
            }
        }
    }

    private void setButtonState(View b, boolean selected) {
        if (b == null) {
            return;
        }
        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.OVAL);
        gd.setColor(selected ? Color.WHITE:Color.TRANSPARENT);
        b.setBackground(gd);
        ((Button)b).setTextColor(selected ? Color.BLACK:Color.WHITE);
    }


    private void setLayoutBG(LinearLayout layout){
        int roundRadius = 60;
        int fillColor = Color.parseColor("#d3d3d3");
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(fillColor);
        gd.setCornerRadius(roundRadius);
        layout.setBackground(gd);
    }
}
