# SafeWait

### About the application:
SafeWait is an app that we created to tackle some issues surrounding the SafeRide service that is offered by the UNB Student Union. This service is available to all UNB students. It is essentially a free taxi service for the UNB student body. A SafeRide vehicle can drop a student off anywhere in the city of Fredericton.

We have used Kotlin and Python to develop our application. 
We created a custom API for our app which incorporates the Google Maps API. To test the API we made, we used Postman. 

The source code of our application uses API Level 30

### Problem the app tackles:
1. SafeRide vehicles are stationed at 4 locations on campus. Everyone each vehicle returns to the same location after dropping students off at their        respective destinations. At the moment, the only way to know when the SafeRide will be back is using a whiteboard that is present at each of the four locations. The SafeRide driver has to get out of the car, walk into the building and write the expected return time, which is usually not accurate. Students, including members of our group have wasted a lot of time waiting for the SafeRide cars to come back. 

2. Because SafeRide is only available for UNB students, people making use of the service have to manually enter their information into an excel sheet that is open on an iPad, or they have to write it out physically on a piece of paper. Not only is it time consuming, but there is no way to verify that a student is a UNB student unless they have their studentID. Many students often don't happen to have their studentID. Our app will have a unique QR Code for each student which stores their information such as Name, StudentID, and Address.
