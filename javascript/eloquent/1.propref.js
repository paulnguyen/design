
/**

http://eloquentjavascript.net/code/
http://eloquentjavascript.net/04_data.html

obj.property		- variable named "property" of object
obj[property]		- property named by variable in object (value expression)
obj["property]		- property named "property" in object (value literal)

**/

var object = {
  length: 10,
  name: "Hello World" 
} ;

var variable = "name" ;

console.log(object.length) ;
console.log(object["length"]) ;

console.log(object[variable]) ;
console.log(object.variable) ; // Undefined! Why?



/**

The two most common ways to access properties in JavaScript are with 
a dot and with square brackets. Both value.x and value[x] access 
a property on value—but not necessarily the same property. 

The difference is in how x is interpreted. 

When using a dot, the part after the dot must be a valid variable name, 
and it directly names the property. 

When using square brackets, the expression between the brackets 
is evaluated to get the property name. 

Whereas value.x fetches the property of value named “x”, 
value[x] tries to evaluate the expression x and uses 
the result as the property name.

So if you know that the property you are interested in is 
called “length”, you say value.length. If you want to extract 
the property named by the value held in the variable i, 
you say value[i]. 

And because property names can be any string, 
if you want to access a property named “2” or “John Doe”, 
you must use square brackets: value[2] or value["John Doe"]. 
This is the case even though you know the precise name 
of the property in advance, because neither “2” nor “John Doe” 
is a valid variable name and so cannot be accessed through 
dot notation.

**/


