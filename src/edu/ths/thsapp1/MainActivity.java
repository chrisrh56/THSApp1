package edu.ths.thsapp1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

//public class MainActivity extends Activity {
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
//}

public class MainActivity extends Activity implements SensorEventListener {
    /** Called when the activity is first created. */
   
   private SensorManager sensorManager;
   private TextView txtRawData;
   private TextView txtDirection;
   private float myAzimuth = 0;
   private float myPitch = 0;
   private float myRoll = 0;
   
   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        txtRawData = (TextView) findViewById(R.id.txt_info);
        txtDirection = (TextView) findViewById(R.id.txt_direction);
        txtRawData.setText("Compass");
        txtDirection.setText("");
        
        // Real sensor manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }
    
    /** Register for the updates when Activity is in foreground */
   @Override
    protected void onResume() {
      super.onResume();
      sensorManager.registerListener(this, 
            sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
            SensorManager.SENSOR_DELAY_NORMAL);
    }
    
    /** Stop the updates when Activity is paused */
   @Override
    protected void onPause() {
      super.onPause();
      sensorManager.unregisterListener(this);
    }
    

   public void onAccuracyChanged(Sensor sensor, int accuracy) {
      // TODO Auto-generated method stub
      
   }

   public void onSensorChanged(SensorEvent event) {
      // TODO Auto-generated method stub

           myAzimuth = Math.round(event.values[0]);
           myPitch = Math.round(event.values[1]);
           myRoll = Math.round(event.values[2]);
           

           String out = String.format("Azimuth: %.2f\n\nPitch:%.2f\n\nRoll:%.2f\n\n", 
                 myAzimuth, myPitch, myRoll);
           txtRawData.setText(out);
           
           printDirection();
   }
   
   private void printDirection() {
      
      if (myAzimuth < 22)
         txtDirection.setText("N");
      else if (myAzimuth >= 22 && myAzimuth < 67)
         txtDirection.setText("NE");
      else if (myAzimuth >= 67 && myAzimuth < 112)
         txtDirection.setText("E");
      else if (myAzimuth >= 112 && myAzimuth < 157)
         txtDirection.setText("SE");
      else if (myAzimuth >= 157 && myAzimuth < 202)
         txtDirection.setText("S");
      else if (myAzimuth >= 202 && myAzimuth < 247)
         txtDirection.setText("SW");
      else if (myAzimuth >= 247 && myAzimuth < 292)
         txtDirection.setText("W");
      else if (myAzimuth >= 292 && myAzimuth < 337)
         txtDirection.setText("NW");
      else if (myAzimuth >= 337)
         txtDirection.setText("N");
        else
           txtDirection.setText("");
      
   }
}