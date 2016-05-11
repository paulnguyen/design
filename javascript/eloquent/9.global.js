
/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/04_data.html

The global scope, the space in which global variables live, 
can also be approached as an object in JavaScript. Each global 
variable is present as a property of this object. In browsers, 
the global scope object is stored in the window variable.

	svar myVar = 10;
	console.log("myVar" in window);	// → true
	console.log(window.myVar);	// → 10

https://nodejs.org/api/globals.html
http://www.hacksparrow.com/global-variables-in-node-js.html


**/


global ;

global.myVar = 0 ;
console.log("myVar" in global);	// → true
console.log(global.myVar);	// → 10