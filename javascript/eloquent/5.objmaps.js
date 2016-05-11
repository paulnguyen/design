
/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/04_data.html

Objects as Maps:

One possible way is to store all the correlations in an array, using objects with name 
and value properties. But that makes looking up the correlation for a given event somewhat 
cumbersome: you’d have to loop over the whole array to find the object with the right name. 
We could wrap this lookup process in a function, but we would still be writing more code, 
and the computer would be doing more work than necessary.

Use object properties named after the event types. We can use the square bracket access 
notation to create and read the properties and can use the in operator to test whether 
a given property exists.


**/


var map = {};
function storePhi(event, phi) {
  map[event] = phi;
}

storePhi("pizza", 0.069);
storePhi("touched tree", -0.081);
console.log("pizza" in map);		// → true
console.log(map["touched tree"]);	// → -0.081
 
 
/**

What if we want to find all the events for which we have stored a coefficient? 
The properties don’t form a predictable series, like they would in an array, 
so we cannot use a normal for loop. JavaScript provides a loop construct specifically 
for going over the properties of an object. It looks a little like a normal for 
loop but distinguishes itself by the use of the word in.

**/

for (var event in map)
  console.log("The correlation for '" + event +
              "' is " + map[event]);

// → The correlation for 'pizza' is 0.069
// → The correlation for 'touched tree' is -0.081  