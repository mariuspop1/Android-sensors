# Android-sensors


This project shows how to use android phone's build in sensors and read their values.
To this project I added the 7 most important sensors : 
- Light sensor 
- Gravity 
- Stepcounter
- Motion sensor
- Accelerometer
- Magnetic Field
- Pressure sensor 
In order to exemplify one use of the these sensors, I have set the app's background to change in respect to the light intensity level.
Furthermore, not all phones contain all these sensors, the app firstly checks if the phone has the sensor and if it does not have it, returns a toast message to alert the user and fills the respective textfield with the message: "Sensor not found"
