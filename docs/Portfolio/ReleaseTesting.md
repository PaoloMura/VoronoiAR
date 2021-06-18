# RELEASE TESTING

## Strategy

Integration Testing will occur at a later stage of development, when different systems, such as tracking and displaying, will come together to accomplish one task. Fortunately, the space in which our app will operate is a finite one (Fry Building), as such complex system results will be tested by hand on the field by the team under different real-world conditions.


## Example User Story: User Story 1 (Viewing the 3D Model in the Fry Building)

The chosen example is the process of displaying the points in 3D space. As stated before, these are imaginary pre-calculated points floating inside the Fry Building. They represent the backbone of the whole tessellation the slice represented by the sculpture is based on. 

For our app to be able to accurately portray the tessellation the points must be tracked perfectly. 	

Test | Description	| Expected Result
-----|--------------|----------------
Normal slow movement | Simulating the normal situation where a user is gently moving around the designated area in order to admire the 3D points. | A perfect match to our perception of reality
Faster movement	| Simulating a more frantic movement around the designated area. The speed may be increased for testing the limits of our app	| A perfect match to our perception of reality
Temporarily obstructed | During some normal slow movement test we will deliberately cover one or more usually visible points for a short duration. The number of obstructed points may be increased later on for further limit testing. | Perfect tracking, despite interruptions in displaying
Poor lighting conditions | Simulating the rare situation in which a user will attempt using the app in the designated are at night or during poor weather or other natural conditions which lead to poor light levels. Initially the torch will be light on, but upon further testing it will be toggled off | A good match to our perception of reality while the torch is on and a potentially acceptable match otherwise (the expected use does involve turning the torch on)
