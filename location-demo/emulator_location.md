# Using Location in the Emulator

Apps using location can be challenging to debug in the emulator, since you're not actually moving. 
But, you can have the emulator, well, emulate location by providing it a file with some locations 
in it. 

I've provided a file [locations.gpx](locations.gpx) that has a series of locations marked up in a 
special flavor of xml called gpx. To use it, in your emulator,  click the "Extended Controls" button 
(the three dots). In the Location tab, click the "Import GPX/KML" button. Then, load the locations.gpx 
file. You can then click the "Play Route" button, and these locations will be fed to the emulator as 
where the phone is currently located. 

You can see this in action by pulling up Google Maps on your emulator, then hitting play. The map 
should update, reflecting the change in location over time. 

You'll see that this file is basically a path traveling around NE Seattle (near Green Lake) and 
in the International District. 

