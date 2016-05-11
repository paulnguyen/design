/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/06_object.html

**/

// Watch closely.

var empty = {};
console.log(empty.toString);	// → function toString(){…}
console.log(empty.toString());	// → [object Object]

/** 

I just pulled a property out of an empty object. Magic!

Well, not really. I have simply been withholding 
information about the way JavaScript objects work. 
In addition to their set of properties, almost all 
objects also have a prototype. A prototype is another 
object that is used as a fallback source of properties. 

When an object gets a request for a property that it 
does not have, its prototype will be searched for the 
property, then the prototype’s prototype, and so on.

**/