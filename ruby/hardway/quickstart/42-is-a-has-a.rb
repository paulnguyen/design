
## Animal is-a object look at the extra credit
class Animal
end

## Dog is an Animal
class Dog < Animal

  def initialize(name)
    @name = name
  end
end

## Cat is an Animal
class Cat < Animal

  def initialize(name)
    @name = name
  end
end

## Person is an Object
class Person

  def initialize(name)
    @name = name

    ## Person has-a pet of some kind
    @pet = nil
  end

  attr_accessor :pet
end

## Employee is a Person
class Employee < Person

  def initialize(name, salary)
    super(name)
    @salary = salary
  end

end

## Fish Object
class Fish
end

## Salmon is a Fish
class Salmon < Fish
end

## Halibut is a Fish
class Halibut < Fish
end


rover = Dog.new("Rover")
satan = Cat.new("Satan")

mary = Person.new("Mary")
mary.pet = satan
frank = Employee.new("Frank", 120000)
frank.pet = rover
flipper = Fish.new()
crouse = Salmon.new()
harry = Halibut.new()