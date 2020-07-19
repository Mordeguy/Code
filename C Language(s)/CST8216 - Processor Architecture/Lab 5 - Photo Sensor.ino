

  // Pin to out from the TEENSY
  int analogPin = 22;
  
  // analogRead returns (0-1023); this is opposite in the code as noted below
  int sensorMin = 1023;
  int sensorMax = 0;

  // Used for map function
  int val = 0;

  // Reads the analog value into it
  int adc;
  
  // Calculated the voltage using formula: adc * 3.3/1024.0
  float vCalc;



  void setup() {

    // Turns the light on the TEENSY during calibration
    pinMode(13, OUTPUT);
    digitalWrite(13, HIGH);
  
    // Sets the pin for input 
    pinMode(analogPin, INPUT);

    // calibrate during the first five seconds 
    while (millis() < 10000) {
      val = analogRead(analogPin);

      // record the maximum sensor value
      if (val > sensorMax) {
          sensorMax = val;
      }

      // record the minimum sensor value
      if (val < sensorMin) {
          sensorMin = val;
      } 
    }

    // Turns off the onboard LED to indicate calibration is over
    digitalWrite(13, LOW);

 Serial.print("MAX: ");
 Serial.println(sensorMax);
 Serial.print("MIN: ");
 Serial.println(sensorMin);
} // End of setup()

void loop() {
  
  // Map used to set a new HIGH and LOW range
  val = map(val, sensorMin, sensorMax, 0, 255);

  // Read in from the lgh sensor
  adc = analogRead(analogPin);

  // Calculate the voltage
  vCalc = adc * 3.3/1024.0;

  // Print out the results
  Serial.print(adc);
  Serial.print("   ");
  Serial.println(vCalc);
  delay(1000);  
}

