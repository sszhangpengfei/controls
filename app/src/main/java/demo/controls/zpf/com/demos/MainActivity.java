package demo.controls.zpf.com.demos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import demo.controls.zpf.com.library.CustomSeekBar;
import demo.controls.zpf.com.library.MultiStateToggleButton;

public class MainActivity extends AppCompatActivity {

    private MultiStateToggleButton toggle_btn,toggle_btn1;

    private CustomSeekBar seekbar_brightness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggle_btn = (MultiStateToggleButton)findViewById(R.id.toggle_btn);
        CharSequence[] texts = new CharSequence[]{"OFF","LOW","MED","HIGH","BOOST"};
        toggle_btn.setResource(texts);
        toggle_btn.setListener(new MultiStateToggleButton.onBtnClick() {
            @Override
            public void onClick(Button btn) {
                Toast.makeText(MainActivity.this,btn.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        toggle_btn1 =  (MultiStateToggleButton)findViewById(R.id.toggle_btn1);
        CharSequence[] text1 = new CharSequence[]{"A","B","C","D"};
        toggle_btn1.setResource(text1);
        toggle_btn1.setListener(new MultiStateToggleButton.onBtnClick() {
            @Override
            public void onClick(Button btn) {
                Toast.makeText(MainActivity.this,btn.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        seekbar_brightness = (CustomSeekBar)findViewById(R.id.seekbar_brightness);
        seekbar_brightness.setImgBg(R.mipmap.brightness);
        seekbar_brightness.setSeekValuePercent(0.7f);
    }
}
