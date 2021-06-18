# DEVELOPMENT TESTING

## Strategy

Development testing will occur at every stage of implementation. Each function will be tested using unit tests where a return value is expected. Most aspects of the solution are visual. These will be tested using an emulator or Android device connected to a laptop running Android Studio. Components such as scanning a reference anchor or displaying the 3D model do not require a presence in the Fry Building to test the core features. These will be tested by the developers individually in their own homes or study spaces.


## Example Component: Scan the reference anchor

The chosen component is the process of scanning a reference anchor. As mentioned in the requirements section, this anchor is a marker (such as a QR code or distinct image displayed on a plaque) that will be located at a fixed, known point within the Fry Building. When detecting the anchor, the app should be able to determine the device’s position and orientation relative to the anchor.

It is an essential feature as it is used as a way of ‘anchoring’ the 3D model into position for the first time. The positioning of a 3D model to match up to the sculpture is one of the fundamental requirements of the app.


Test | Description | Expected Result
-----|-------------|----------------
Normal vertical position | Simulating the normal situation in which a user attempts to scan the reference: | Orientation = 0.0
_ | *	The device is held vertically with screen parallel to the reference. | Distance = 1.0
_ | *	The device is held 1m from the reference (distance measured manually). | Reference = ref_id
Alternative horizontal position	| Simulating an alternative situation in which: | Orientation = 90.0
_ | *	The device is held horizontally with the screen parallel to the reference. | Distance = 1.0
_ | *	The device is held 1m from the reference. | Reference = ref_id
Alternative Angled position	| Simulating an alternative situation in which: | Orientation = 45.0
_ | *	The device is held at a 45-degree angle to the reference (orientation measured manually). | Distance = 1.0
_ | * The device is held 1m from the reference. | Reference = ref_id
Reference partially obscured | The reference is partially covered up. The device is held in the ‘normal’ position. | Error: no reference detected
Reference obscured | The reference is completely covered up. The device is held in the ‘normal’ position. | Error: no reference detected
