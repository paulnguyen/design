
/*

    Use class followed by the class’s name to create a class. 
    A property declaration in a class is written the same way 
    as a constant or variable declaration, except that it is 
    in the context of a class. Likewise, method and function 
    declarations are written the same way.

*/

class Shape1 {
    var numberOfSides = 0
    func simpleDescription() -> String {
        return "A shape with \(numberOfSides) sides."
    }
}

/*
 
 Objects and Classes

 Use class followed by the class’s name to create a class. 
 A property declaration in a class is written the same way 
 as a constant or variable declaration, except that it is 
 in the context of a class. Likewise, method and function 
 declarations are written the same way.
 
*/

class Shape2 {
    var numberOfSides = 0
    let day = "Monday"
    func simpleDescription() -> String {
        return "A shape with \(numberOfSides) sides."
    }
    func greet(_ person: String) {
        print ( "Hello \(person), today is \(day)." )
    }
}

/*
 
 Create an instance of a class by putting parentheses 
 after the class name. Use dot syntax to access the 
 properties and methods of the instance.

*/

var shape = Shape2()
shape.numberOfSides = 7
var shapeDescription = shape.simpleDescription()
shape.greet( "John" )

/*
 
    This version of the Shape class is missing something important: 
    an initializer to set up the class when an instance is created. 
    Use init to create one.
 
    1.  Notice how self is used to distinguish the name property from
        the name argument to the initializer.
 
    2.  Use deinit to create a deinitializer if you need to perform 
        some cleanup before the object is deallocated.
 
*/
 
 class NamedShape {
    
    var numberOfSides: Int = 0
    var name: String
 
    init(name: String) {
        self.name = name
    }
 
    func simpleDescription() -> String {
        return "A shape with \(numberOfSides) sides."
    }
    
 }
 
/*

    Subclasses include their superclass name after their class name, 
    separated by a colon. There is no requirement for classes to 
    subclass any standard root class, so you can include or omit a 
    superclass as needed.
 
    Methods on a subclass that override the superclass’s implementation 
    are marked with override—overriding a method by accident, without override, 
    is detected by the compiler as an error. The compiler also detects methods 
    with override that don’t actually override any method in the superclass.
 
*/

class Square: NamedShape {
    var sideLength: Double
    
    init(sideLength: Double, name: String) {
        self.sideLength = sideLength
        super.init(name: name)
        numberOfSides = 4
    }
    
    func area() -> Double {
        return sideLength * sideLength
    }
    
    override func simpleDescription() -> String {
        return "A square with sides of length \(sideLength)."
    }
}

let test = Square(sideLength: 5.2, name: "my test square")
test.area()
test.simpleDescription()


/*

    In addition to simple properties that are stored, 
    properties can have a getter and a setter.
 
*/

class EquilateralTriangle: NamedShape {
    var sideLength: Double = 0.0
    
    init(sideLength: Double, name: String) {
        self.sideLength = sideLength
        super.init(name: name)
        numberOfSides = 3
    }
    
    var perimeter: Double {
        get {
            return 3.0 * sideLength
        }
        set {
            sideLength = newValue / 3.0
        }
    }
    
    override func simpleDescription() -> String {
        return "An equilateral triangle with sides of length \(sideLength)."
    }
}

var triangle = EquilateralTriangle(sideLength: 3.1, name: "a triangle")
print(triangle.perimeter)
triangle.perimeter = 9.9
print(triangle.sideLength)

/*
 
    If you don’t need to compute the property but still need to 
    provide code that is run before and after setting a new value, 
    use willSet and didSet. The code you provide is run any time 
    the value changes outside of an initializer. 
 
    For example, the class below ensures that the side length 
    of its triangle is always the same as the side length of 
    its square.
 
*/

class TriangleAndSquare {
    
    var triangle: EquilateralTriangle {
        willSet {
            square.sideLength = newValue.sideLength
        }
    }
    
    var square: Square {
        willSet {
            triangle.sideLength = newValue.sideLength
        }
    }
    
    init(size: Double, name: String) {
        square = Square(sideLength: size, name: name)
        triangle = EquilateralTriangle(sideLength: size, name: name)
    }
}

var triangleAndSquare = TriangleAndSquare(size: 10, name: "another test shape")
print(triangleAndSquare.square.sideLength)
print(triangleAndSquare.triangle.sideLength)
triangleAndSquare.square = Square(sideLength: 50, name: "larger square")
print(triangleAndSquare.triangle.sideLength)

/*

    When working with optional values, you can write ? before operations 
    like methods, properties, and subscripting. If the value before 
    the ? is nil, everything after the ? is ignored and the value of 
    the whole expression is nil. Otherwise, the optional value is 
    unwrapped, and everything after the ? acts on the unwrapped value. 
 
    In both cases, the value of the whole expression is an optional value.

*/
 
 let optionalSquare: Square? = Square(sideLength: 2.5, name: "optional square")
 let sideLength = optionalSquare?.sideLength

 var optionalSquare1: Square? = Square(sideLength: 2.5, name: "optional square")
 optionalSquare1 = nil
 let sideLength1 = optionalSquare1?.sideLength






