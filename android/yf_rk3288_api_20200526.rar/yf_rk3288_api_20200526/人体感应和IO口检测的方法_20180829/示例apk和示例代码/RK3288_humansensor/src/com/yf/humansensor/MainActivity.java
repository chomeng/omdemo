package com.yf.humansensor;

import com.yf.humansensor.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10,bt11,bt12;
	int fd;
	int val;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt1 =(Button)findViewById(R.id.button1);
		bt2 =(Button)findViewById(R.id.button2);
		bt3 =(Button)findViewById(R.id.button3);
		bt4 =(Button)findViewById(R.id.button4);
		bt5 =(Button)findViewById(R.id.button5);
		bt6 =(Button)findViewById(R.id.button6);
		bt7 =(Button)findViewById(R.id.button7);
		bt8 =(Button)findViewById(R.id.button8);
		bt9 =(Button)findViewById(R.id.button9);
		bt10 =(Button)findViewById(R.id.button10);
		bt11 =(Button)findViewById(R.id.button11);
		bt12 =(Button)findViewById(R.id.button12);
		
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);
		bt5.setOnClickListener(this);
		bt6.setOnClickListener(this);
		bt7.setOnClickListener(this);
		bt8.setOnClickListener(this);
		bt9.setOnClickListener(this);
		bt10.setOnClickListener(this);
		bt11.setOnClickListener(this);
		bt12.setOnClickListener(this);
		fd = humansensor_manager.open();

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int ret = 0;
		if(v.getId() == R.id.button1){
			humansensor_manager.set_humansensor_time(fd, 10);
			Toast.makeText(getApplicationContext(), "set humansensor time = 10 second", Toast.LENGTH_SHORT).show();
		}else if(v.getId() == R.id.button2){
			ret = humansensor_manager.get_humansensor_value(fd);
			Toast.makeText(getApplicationContext(), "get humansensor gpio value = "+ret, Toast.LENGTH_SHORT).show();
		}else if(v.getId() == R.id.button3){
			val = ((val == 1) ? 0:1);
			humansensor_manager.set_gpio1_value(fd, val);
			Toast.makeText(getApplicationContext(), "set gpio1 value = "+val, Toast.LENGTH_SHORT).show();
		}else if(v.getId() == R.id.button4){
			ret = humansensor_manager.get_gpio1_value(fd);
			Toast.makeText(getApplicationContext(), "get gpio1 value = "+ret, Toast.LENGTH_SHORT).show();
		}else if(v.getId() == R.id.button5){
			val = ((val == 1) ? 0:1);
			humansensor_manager.set_gpio2_value(fd, val);
			Toast.makeText(getApplicationContext(), "set gpio2 value = "+val, Toast.LENGTH_SHORT).show();
		}else if(v.getId() == R.id.button6){
			ret = humansensor_manager.get_gpio2_value(fd);
			Toast.makeText(getApplicationContext(), "get gpio2 value = "+ret, Toast.LENGTH_SHORT).show();
		}else if(v.getId() == R.id.button7){
			val = ((val == 1) ? 0:1);
			humansensor_manager.set_gpio3_value(fd, val);
			Toast.makeText(getApplicationContext(), "set gpio3 value = "+val, Toast.LENGTH_SHORT).show();
		}else if(v.getId() == R.id.button8){
			ret = humansensor_manager.get_gpio3_value(fd);
			Toast.makeText(getApplicationContext(), "get gpio3 value = "+ret, Toast.LENGTH_SHORT).show();
		}else if(v.getId() == R.id.button9){
			val = ((val == 1) ? 0:1);
			humansensor_manager.set_gpio4_value(fd, val);
			Toast.makeText(getApplicationContext(), "set gpio4 value = "+val, Toast.LENGTH_SHORT).show();
		}else if(v.getId() == R.id.button10){
			ret = humansensor_manager.get_gpio4_value(fd);
			Toast.makeText(getApplicationContext(), "get sensor value = "+ret, Toast.LENGTH_SHORT).show();
		}else if(v.getId() == R.id.button11){
			val = ((val == 1) ? 0:1);
			humansensor_manager.set_sensor_value(fd, val);
			Toast.makeText(getApplicationContext(), "set sensor value = "+val, Toast.LENGTH_SHORT).show();
		}else if(v.getId() == R.id.button12){
			ret = humansensor_manager.get_sensor_value(fd);
			Toast.makeText(getApplicationContext(), "get sensor value = "+ret, Toast.LENGTH_SHORT).show();
		}
		
	}
}
