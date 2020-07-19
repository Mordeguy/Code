
  // Loads in data
  int shiftRegister = 16;
  
  // Pushes data out
  int storageRegister = 15;
  
  // Where the data comes in
  int serialData = 14;



void setup() {
  
     pinMode(serialData, OUTPUT);
     pinMode(storageRegister, OUTPUT);
     pinMode(shiftRegister, OUTPUT);

     digitalWrite(serialData, 0);
     digitalWrite(shiftRegister, 0);
     digitalWrite(storageRegister, 0);

     delay(1000);
}

void loop() {

/** PART 1 - 8 Bit Bit-Wise Shift **********************************
  int number = 1;

    digitalWrite(storageRegister, LOW);
    shiftOut(serialData, shiftRegister, MSBFIRST, 0);
    digitalWrite(storageRegister, HIGH);
    delay(300);

  for(byte x = 0; x<8; x++){

    digitalWrite(storageRegister, LOW);
    shiftOut(serialData, shiftRegister, MSBFIRST, number);
    digitalWrite(storageRegister, HIGH);

    delay(300);
    number <<= 1;
  }

   number = 1;

    digitalWrite(storageRegister, LOW);
    shiftOut(serialData, shiftRegister, MSBFIRST, 0);
    digitalWrite(storageRegister, HIGH);
    delay(300);

  for(byte x = 0; x<8; x++){

    digitalWrite(storageRegister, LOW);
    shiftOut(serialData, shiftRegister, LSBFIRST, number);
    digitalWrite(storageRegister, HIGH);

    delay(300);
    number <<= 1;
  }
  *******************************************************************/








/** PART 2 - 16 LED Light Shift *************************************
    int numArray[]= {0, 1, 3, 7, 15, 31, 63, 127, 255};

    // 1. ~~~~~~~~~~~~~~~~~~~~~~~~
    for (int i = 0; i < 9; i++){

       // Shifts 0 to second register and slowly adds lihts right-to-left
        shiftOut(serialData, shiftRegister, LSBFIRST, 0);
        shiftOut(serialData, shiftRegister, MSBFIRST, numArray[i]);

      // Shifts out data to both REGISTERS
        digitalWrite(storageRegister, 1);
        delay(200);
        digitalWrite(storageRegister, 0);
      }


    // 2. ~~~~~~~~~~~~~~~~~~~~~~~~
      for (int i = 0; i < 9; i++){

     // Shifts slowly adds data from -right-to-left and REGISTER 1 is all lit
       shiftOut(serialData, shiftRegister, MSBFIRST, numArray[i]);
       shiftOut(serialData, shiftRegister, MSBFIRST, 255);

    // Shifts out data to both REGISTERS
       digitalWrite(storageRegister, 1);
       delay(200);
       digitalWrite(storageRegister, 0);
      }



    // 3. ~~~~~~~~~~~~~~~~~~~~~~~~
       for (int i = 8; i > 0; i--){

      // Shifts slowly takes away data from left-to-right and REGISTER 1 is all lit
         shiftOut(serialData, shiftRegister, MSBFIRST, numArray[i]);
         shiftOut(serialData, shiftRegister, MSBFIRST, 255);
  
     // Shifts out data to both REGISTERS
         digitalWrite(storageRegister, 1);
         delay(200);
         digitalWrite(storageRegister, 0);
    }

'
    // 4. ~~~~~~~~~~~~~~~~~~~~~~~~
    for (int i = 8; i > 0; i--){

     // Shifts slowly takes away data from left-to-right and REGISTER 2 is all not lit
       shiftOut(serialData, shiftRegister, MSBFIRST, 0);
       shiftOut(serialData, shiftRegister, MSBFIRST, numArray[i]);
   
     // Shifts out data to both REGISTERS
         digitalWrite(storageRegister, 1);
         delay(200);
         digitalWrite(storageRegister, 0);
    }
     *******************************************************************/








    /** Part 3 - Seven Segment Display *********************************
    // Int i and j is used to cycle through the array
    int i;
    int j;
          //     0           1          2         3           4          5         6            7           8           9
    byte a[10] = {0b00000010,0b10011111,0b00100101,0b00001100,0b10011000,0b01001001,0b01000001, 0b00011111, 0b00000000, 0b00001000};

    // 0b00000100 - 0
    // 0b10011111 - 1
    // 0b00100101 - 2
    // 0b00001100 - 3
    // 0b10011000 - 4
    // 0b01001001 - 5
    // 0b01000001 - 6
    // 0b00011111 = 7
    // 0b00000000 - 8
    // 0b00001000 - 9
   

    // Outer FOR loop handles the counting of the 10s place while counting
    for (i = 0; i < 10; i++){

      digitalWrite (storageRegister, 0);
      shiftOut (serialData, shiftRegister, LSBFIRST, 0b00000010);
      shiftOut (serialData, shiftRegister, LSBFIRST, a[i]);
      digitalWrite (storageRegister, 1);

      // Inner FOR loop handles the 1s place while counting
      for (j = 0; j < 10; j++){
        
         digitalWrite (storageRegister, 0);
         shiftOut (serialData, shiftRegister, LSBFIRST, a[j]);
         shiftOut (serialData, shiftRegister, LSBFIRST, a[i]);
         digitalWrite (storageRegister, 1);
         delay (300);
      }
    }

    *******************************************************************/

    

}
