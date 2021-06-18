# REQUIREMENTS

## User Stakeholders

### Visitors

A visitor is anyone who passes through the Fry Building and has the intention of using the app to view the 3D tessellation. This includes students, staff and open day visitors of the University.
												

## Other Stakeholders

### Passers-by

These are also visitors such as students, staff and open day visitors who are passing through the Fry Building. However, they do not have the intention of using the app and may be indirectly affected by its use. This could be due to app users unknowingly blocking their path while distracted by the app. The use of the app may cause bystanders concern or confusion if they feel that they are being filmed.


### School of Mathematics/University of Bristol

The School of Mathematics is the client. The School is interested in the success of the app as a way of enhancing the experience of viewing the Fry Building’s Voronoi sculpture. The app should help to showcase a part of the School and its success would reflect well on the client.


### Development Team

The team is comprised of the five members stated at the top of this portfolio. Their involvement with the app includes development, testing, documentation and potentially maintenance. The development team intends to create an app that meets the client’s needs and is effective for end users. This will reflect well on them for their future careers and success in the project is desirable since it contributes to their academic grade.


### Maintenance Team

This is anyone who is involved with maintaining the app after its completion. It could potentially be one or several of those team members currently involved in the development of the app. Alternatively a third party or even the client itself may be responsible for keeping the app functional and up-to-date. The maintenance team will be interested in a well-documented app that is clearly constructed. This will allow easier updates and fixes.

## User Stories

### Story 1

> As a visitor I want to be able to view the 3D Voronoi tessellation to better visualise and understand how the sculpture was designed.

n | Normal Flow	| Alternative Flow	| Exceptional Flow
--|-------------|-------------------|-----------------
1 |	Download and open the app | Download and open the app | Download and open the app
2	| Select the option to view the 3D points | Select the option to view the 3D points | Select the option to view the 3D points
3	| Aim the camera at a reference anchor | Aim the camera at a reference anchor | Aim the camera at a reference anchor
4	| Aim the camera at the sculpture | Aim the camera at the sculpture | Swivel the device around frantically
5	| View the 3D points overlaid on the live view | View the 3D points overlaid on the live view | Notice the warning that tracking has failed
6	| Exit the app | Toggle the option to view the 3D tessellation | Select the option to reset
7	| | View the 3D tessellation overlaid on the live view | 
8	| | Exit the app | 


### Story 2

> As a visitor I want to be able to learn more about Voronoi patterns, the mathematical relevance and the Fry Building’s sculpture in particular.

n | Normal Flow |	Alternative Flow | Exceptional Flow
--|-------------|------------------|-----------------
1	| Download and open the app | Download and open the app | Download and open the app
2	| Select the information option | Select the information option | Select the information option
3	| Select the Voronoi pattern option |	Select the Voronoi sculpture option |	Select the return option
4 |	Read information about Voronoi patterns	| Read information about the Fry Building sculpture	| Arrive at the home screen


### Story 3

> As a non-mathematical/computer science visitor I want to be able to learn about the Voronoi sculpture in an intuitive way and use the app without difficulty.

The flow steps for this user story are the same as above. In order to address the needs of this user story, consideration will be made during development to provide a user-friendly interface as well as clear, simple instructions and information.
												

## Requirements

### User Story 1

This user story reflects the main purpose of the app. The flow steps have been broken down into atomic requirements below. Additional key features have also been included for completeness.

* Download the app
  * App must exist in the Google Play store
*	Open the app
  *	User taps the app icon
  *	Disclaimer displayed, including:
    *	Warning that imagery shown is rendered and not real
    *	Take care of yourself and others when using the app
    *	Use of the rear camera is essential for use of the app
  *	Home screen is displayed, including:
    *	Button to view Fry Building’s 3D points
    *	Button to navigate to information page
*	Select button to view 3D points
  *	Display live view from the rear camera
  *	Overlay a target box on this view
  *	Display message asking user to scan the reference anchor1, aligning it within the target box
  *	Display button to activate torch (for use at night or in dark conditions)
    *	User presses this button
    *	Device’s torch is toggled on/off
  *	Display help option
    *	User presses this button
    *	Display a box containing information on:
      *	Where to find a reference anchor
      *	How to scan a reference anchor
  *	Display cancel button
*	Aim the camera at the reference anchor
  *	User aligns the reference anchor within the target box
  *	App recognises the reference anchor
  *	Load the stored positional data for this particular anchor
  *	Determine the distance of the device from the anchor
  *	Determine the device’s orientation relative to the anchor
  *	Determine where to display the 3D model of the points based on the previous three bullet points
  *	Load the 3D model
  *	Render the 3D model
    *	This should be fast
  *	Hide the following:
    *	Target box
    *	Help button
    *	Message asking the user to scan the anchor
  *	Display a button to toggle between 3D points and 3D tessellation
  *	Display a button to toggle between day and night mode
    *	User toggles day mode on
      *	Change the colour of the points to be a dark colour to contrast against the light background
    *	User toggles night mode on
      *	Change the colour of the points to be a light colour to contrast against the dark background
  *	Display a button to reset the anchor
*	Aim the camera at the sculpture
  *	Detect and register changes in…
    *	The device’s position
    *	The device’s orientation
  *	3D scan the Fry Building
    *	Detect any known key features such as:
      *	The sculpture’s frame
      *	The sculpture’s cell pattern
      *	The windows
      *	The ceiling lamps
    *	Determine relative distance and orientation of the device from these features
  *	Use the combined above information to update…
    *	The position of the 3D points
    *	The orientation of the 3D points
  *	Position and orientation of the points should match up to that of the sculpture
  *	Updates should render quickly
*	Warn that tracking has failed
  *	User moves device around erratically
  *	Detect if likely to be miscalculating relative position and orientation
    *	If there are no detected key features in-frame
    *	If there have been no detected key features in-frame for more than a set amount of time
  *	Display warning message
*	Option to reset
  *	User selects button to reset anchor
  *	Hide the following:
    *	3D model
    *	Toggle button
    *	Reset anchor button
  *	Return to anchor scanning screen
*	Toggle view 3D tessellation
  *	User toggles from 3D points to 3D tessellation
  *	Hide 3D points model
  *	Use same positional and rotational data for the points to determine where to position and how to rotate the 3D tessellation model
  *	Load 3D tessellation model
  *	Render 3D tessellation model
  *	Continue to update this model in the same way as the 3D points above
  *	Follow the same process when toggling from the 3D tessellation back to the 3D points 




1)	A ‘reference anchor’ is one of several physical markers that will be displayed below eye level on the walls in the Fry Building atrium and around the courtyard outside. It is used as a way of determining the device’s relative position and orientation within the Fry Building.
