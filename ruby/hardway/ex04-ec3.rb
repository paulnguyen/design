# Cars.
cars = 100

# Space in a car. Presumably an average.
space_in_a_car = 4.0

# Drivers.
drivers = 30

# Passengers.
passengers = 90

# Cars not driven.
cars_not_driven = cars - drivers

# Cars driven.
cars_driven = drivers

# Carpool capacity.
carpool_capacity = cars_driven * space_in_a_car

# Average passengers per car.
average_passengers_per_car = passengers / cars_driven

puts "There are #{cars} cars available."
puts "There are only #{drivers} drivers available."
puts "There will be #{cars_not_driven} empty cars today."
puts "We can transport #{carpool_capacity} people today."
puts "We have #{passengers} passengers to carpool today."
puts "We need to put about #{average_passengers_per_car} in each car."
