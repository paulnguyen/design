/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/06_object.html

Methods are simply properties that hold function values. This is a simple method:

**/

var rabbit = {};
rabbit.speak = function(line) {
  console.log("The rabbit says '" + line + "'");
};

rabbit.speak("I'm alive.");		// → The rabbit says 'I'm alive.'
