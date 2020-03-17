

// constants won't change. They're used here to set pin numbers:
const int SENSOR_PIN0 = 2; // the Arduino's input pin that connects to the sensor's SIGNAL pin 
const int SENSOR_PIN1 = 3;
const int SENSOR_PIN2 = 4;
const int OUTPUT_PIN0 = 11;
const int OUTPUT_PIN1 = 12;
// Variables will change:
int lastState = LOW;      // the previous state from the input pin
int currentState;         // the current reading from the input pin
int count=0;

void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600);
  // initialize the Arduino's pin as aninput
  pinMode(SENSOR_PIN0, INPUT);
  pinMode(SENSOR_PIN1, INPUT);
  pinMode(SENSOR_PIN2, INPUT);
  pinMode(OUTPUT_PIN0, OUTPUT);
  pinMode(OUTPUT_PIN1, OUTPUT);
  
}
void loop() {
  int touchValue0 = digitalRead(SENSOR_PIN0);
  int touchValue1 = digitalRead(SENSOR_PIN1);
  int touchValue2 = digitalRead(SENSOR_PIN2);
 
  if(touchValue0 == 1 && touchValue1 == 1 && touchValue2 == 1){
    if(count < 4){
      digitalWrite(OUTPUT_PIN0, HIGH);
      Serial.println("Sensor is touched");
    }
    if(count >= 4){
      digitalWrite(OUTPUT_PIN1, HIGH);
      Serial.println("LED");
    }
    count++;
  }
  else if(touchValue0 == 0 || touchValue1 == 0 || touchValue2 == 0){
    count = 0;
      digitalWrite(OUTPUT_PIN0, LOW);
      digitalWrite(OUTPUT_PIN1, LOW);
      Serial.println("Sensor is not touched"); 
  }
   delay(1000);
}
