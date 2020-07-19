
  // Analog PIN w/ Touch
  int analogPin = 22;

  // Value used to setup adn calibrate
  int val = 0;

  // Sensor min and max
  int sensorMin = 65535;
  int sensorMax = 0;
  
  // Values used for the reading and calculation
  int adc;
  float vCalc;




void setup() {
  
    // Turns on the LED on the TEENSY during calibration
    pinMode(13, OUTPUT);
    digitalWrite(13, HIGH);
  
    // Sets up your touch pin for analogRead 
    pinMode(analogPin, INPUT);

    // calibrate during the first five seconds 
     while (millis() < 10000) {
        val = touchRead(analogPin);

    // record the maximum sensor value
        if (val > sensorMax) {
              sensorMax = val;
        }
        
    // record the minimum sensor value
        if (val < sensorMin) {
              sensorMin = val;
        }
      }
          // Turn off the TEENSY LED
          digitalWrite(13, LOW);

          // Write out the values
          Serial.print("MAX: ");
          Serial.println(sensorMax);
          Serial.print("MIN: ");
          Serial.println(sensorMin);
}

void loop() {

  adc = touchRead(analogPin);
  val = map(val, sensorMin, sensorMax, 0, 255);
  Serial.println(adc);
 
  delay(1000);
}
