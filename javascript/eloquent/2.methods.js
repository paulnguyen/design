
/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/04_data.html

Properties that contain functions are generally 
called methods of the value they belong to. 
As in, “toUpperCase is a method of a string”.


**/


// typeof returns the Object Type of value/expression
var doh = "Doh";
console.log(typeof doh.toUpperCase);	// → function
console.log(doh.toUpperCase());			// → DOH


// Array Methods: push() and pop()
var mack = [];
mack.push("Mack");
mack.push("the", "Knife");
console.log(mack);				// → ["Mack", "the", "Knife"]
console.log(mack.join(" "));	// → Mack the Knife
console.log(mack.pop());		// → Knife
console.log(mack);				// → ["Mack", "the"]