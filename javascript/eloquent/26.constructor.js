/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/06_object.html

A more convenient way to create objects that derive 
from some shared prototype is to use a constructor. 

In JavaScript, calling a function with the 
new keyword in front of it causes it to be 
treated as a constructor. The constructor 
will have its this variable bound to a 
fresh object, and unless it explicitly 
returns another object value, 
this new object will be returned from the call.

An object created with new is said to be 
an instance of its constructor.

Here is a simple constructor for rabbits. 
It is a convention to capitalize the names 
of constructors so that they are easily 
distinguished from other functions.

**/

function Rabbit(type) {
  this.type = type;
}

var killerRabbit = new Rabbit("killer");
var blackRabbit = new Rabbit("black");

console.log(blackRabbit.type);
// → black


/**

Constructors (in fact, all functions) automatically get 
a property named prototype, which by default holds 
a plain, empty object that derives from Object.prototype. 

Every instance created with this constructor will have 
this object as its prototype. So to add a speak method 
to rabbits created with the Rabbit constructor, 
we can simply do this:

**/

Rabbit.prototype.speak = function(line) {
  console.log("The " + this.type + " rabbit says '" +
              line + "'");
};
blackRabbit.speak("Doom...");
// → The black rabbit says 'Doom...'

/** 

It is important to note the distinction between the way 
a prototype is associated with a constructor (through 
its prototype property) and the way objects have a 
prototype (which can be retrieved with Object.getPrototypeOf). 

The actual prototype of a constructor is Function.prototype 
since constructors are functions. Its prototype property 
will be the prototype of instances created through it 
but is not its own prototype.

**/