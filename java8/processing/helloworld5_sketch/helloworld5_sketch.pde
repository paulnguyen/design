
size(800, 800) ;

// Examples of loading a text file and a JPEG image
// from the data folder of a sketch.

String[] lines = loadStrings("helloworld.txt");
PImage image = loadImage("gumball.jpg");

printArray( lines ) ;

// Displays the image at its actual size at point (0,0)
image(image, 0, 0);