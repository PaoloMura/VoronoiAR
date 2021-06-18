### Notes made during refactoring/cleanup

* Check out the conditions of the example app's apache license and ensure we comply
* Change the default icon image 'ic_launcher.png'
* Consider different language setting for foreign visitors to the uni
* Consider alternative/adaptive displays for different devices:
  1. different-sized/version Android smartphones
  2. android tablets
  3. IOS devices
* Investigate best practices RE Android software development, e.g:
  * dependency injection
  * automated UI unit tests
  * continuous integration servers
* Unused Classes
  * DepthSettings - may be useful if we want the tessellation to not pass through walls/people/etc.
  * InstantPlacementSettings - remove?
  * TapHelper - may be useful if we want users to be able to interact with the tessellation by tapping
  * Plane Renderer - probably don't need...?
  * PointCloudRenderer - probably don't need...?
