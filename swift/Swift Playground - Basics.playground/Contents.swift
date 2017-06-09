
// Hello World

print ( "Hello World" )

// Constants and Variables

let const = 10
var variable = 10
print ( const + variable )

// Type Inference

let implicitInteger = 70
let implicitDouble = 70.0

// Explicit Type

let explicitDouble: Double = 70

// Type Conversion

let label = "The width is "
let width = 94
let widthLabel2 = label + String(width)

// Arrays

var shoppingList = ["catfish", "water", "tulips", "blue paint"]
shoppingList[1] = "bottle of water"
shoppingList

// Maps

var occupations = [
    "Malcolm": "Captain",
    "Kaylee": "Mechanic",
]
occupations["Jayne"] = "Public Relations"
occupations["Malcolm"]

// Using Initializer to create empty Array or Map

let emptyArray = [String]()
let emptyDictionary = [String: Float]()

// Alternative Empay Array/Map Notation

shoppingList = []
occupations = [:]

// if-else, for-in

let individualScores = [75, 43, 103, 87, 12]
var teamScore = 0
for score in individualScores {
    if score > 50 {
        teamScore += 3
    } else {
        teamScore += 1
    }
}
print(teamScore)

// optional values & if let

var optionalString: String? = "Hello"
print(optionalString == nil)
var optionalName: String? = "John Appleseed"
//optionalName = nil
var greeting = "Hello!"
if let name = optionalName {
    greeting = "Hello, \(name)"
}

// ?? default value operator

let nickName: String? = nil
let fullName: String = "John Appleseed"
let informalGreeting = "Hi \(nickName ?? fullName)"

/*
    Switches support any kind of data and a wide variety
    of comparison operations—they aren’t limited to integers
    and tests for equality.

    Note: there is no need to explicitly break out of the
          switch at the end of each case’s code
*/

let vegetable = "red pepper"
switch vegetable {
case "celery":
    print("Add some raisins and make ants on a log.")
case "cucumber", "watercress":
    print("That would make a good tea sandwich.")
case let x where x.hasSuffix("pepper"):
    print("Is it a spicy \(x)?")
default:
    print("Everything tastes good in soup.")
}

/*
 
    You use for-in to iterate over items in a dictionary 
    by providing a pair of names to use for each key-value 
    pair. Dictionaries are an unordered collection, so their 
    keys and values are iterated over in an arbitrary order.
 
*/

let interestingNumbers = [
    "Prime": [2, 3, 5, 7, 11, 13],
    "Fibonacci": [1, 1, 2, 3, 5, 8],
    "Square": [1, 4, 9, 16, 25],
]
var largest = 0
for (kind, numbers) in interestingNumbers {
    print(kind)
    for number in numbers {
        print (number)
        if number > largest {
            largest = number
        }
    }
}
print("largest = ",largest)


// while loop

var n = 2
while n < 100 {
    n *= 2
}
print(n)

var m = 2
repeat {
    m *= 2
} while m < 100
print(m)

// looping over index ranges

var total = 0
for i in 0..<4 {
    total += i
}
print(total)




