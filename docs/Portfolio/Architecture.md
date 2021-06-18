# ARCHITECTURE

The requirements outline the need for a handheld device that is convenient for end-users. Therefore system will consist of an application that runs on the Android mobile OS. This will mean that the solution will be used on a portable device with a camera for the AR functionality. The system will first be designed for android devices, which are used by a large majority of consumers and we can port to iOS later in development.

This will require the design to be created using Android Studio, which includes an emulator and can connect to android devices for direct testing. The app will be programmed in Java rather than Kotlin due to everyone having previous experience with Java. The object-oriented format may prove useful for classifying the points in 3d space.

Another essential feature included in the requirements is an AR-based solution. ARCore can be used as it provides an SDK with lots of resources and documentation that can aid in the implementation of AR for our app. It includes 3D scanning, tracking and will be useful in the calculations to render the Voronoi tessellations.

The points/tessellation is the main aspect of the solution as mentioned in the requirements and must be displayed as a 3D model. Unity can satisfy the requirements of creating a 3D model of the points necessary for the structure, and creating a 3D model ahead of time to be downloaded with the app at the price of a smaller increase in storage space provided will massively speed up the processing time required to render the images, as well as freeing up RAM for the tracking of points on the sculpture which will be necessary in order to achieve the AR effect.

The app will be available on the Google Play store, as it is the default solution for android apps to be downloaded, and will be convenient for students to use, as is required by the project brief.
