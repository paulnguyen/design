/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/06_object.html

Usually a method needs to do something with the object 
it was called on. When a function is called as a 
method—looked up as a property and immediately called, 
as in object.method()

—the special variable "this" in its body will point to 
the object that it was called on.

**/

function speak(line) {
  console.log("The " + this.type + " rabbit says '" + line + "'");
}
var whiteRabbit = {type: "white", speak: speak};
var fatRabbit = {type: "fat", speak: speak};

whiteRabbit.speak("Oh my ears and whiskers, " +
                  "how late it's getting!");

// → The white rabbit says 'Oh my ears and whiskers, how
//   late it's getting!'

fatRabbit.speak("I could sure use a carrot right now.");

// → The fat rabbit says 'I could sure use a carrot
//   right now.'