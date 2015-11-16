print "How old are you? "
age = gets.chomp()
print "How tall are you? "
height = gets.chomp()
print "How much do you weigh? "
weight = gets.chomp()

puts "So, you're #{age} old, #{height} tall and #{weight} heavy."

print "What is your name? "
name = gets.chomp()
print "Where were you born? "
birthplace = gets.chomp()
print "Where do you live now? "
town = gets.chomp()

puts "Hi #{name}! You were born in #{birthplace}, and you currently live in #{town}. Cool."

print "Give me a number: "
number = gets.chomp.to_i

bigger = number * 100
puts "A bigger number is #{bigger}."

print "Give me another number: "
another = gets.chomp
number = another.to_i

smaller = number / 100
puts "A smaller number is #{smaller}."

