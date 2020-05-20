package com.example.ati2clcd1602;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.nilhcem.androidthings.driver.lcdpcf8574.LcdPcf8574;

import java.io.IOException;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LcdPcf8574 lcd = null; // e.g. new LcdPcf8574("I2C1", 0x3f);
        try {
            lcd = new LcdPcf8574("I2C1", 0x3f);
            lcd.begin(16, 2);
            lcd.setBacklight(true);

            // load custom character to the LCD
            int[] heart = {0b00000, 0b01010, 0b11111, 0b11111, 0b11111, 0b01110, 0b00100, 0b00000};
            lcd.createChar(0, heart);

            int[] arrow = {0b00100, 0b01110, 0b11111, 0b00100, 0b00100, 0b00100, 0b00100, 0b00000};
            lcd.createChar(1, arrow);

            int[] android = {0b00100, 0b01110, 0b11111, 0b00000, 0b11111, 0b11111, 0b11111, 0b11111};
            lcd.createChar(2, android);

            lcd.clear();
            lcd.setCursor(0, 0);
            lcd.print("Hello,");
            lcd.setCursor(0, 1);
            lcd.print("Android Things!");
            lcd.write(2); // write :heart: custom character

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
