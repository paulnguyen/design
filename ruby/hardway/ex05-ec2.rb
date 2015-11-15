my_name = 'Zed A. Shaw'
my_age = 35 # not a lie
my_height = 74 # inches
my_weight = 180 # lbs
my_eyes = 'Blue'
my_teeth = 'White'
my_hair = 'Brown'

puts "Let's talk about %r." % my_name
puts "He's %i inches tall." % my_height
puts "He's %i pounds heavy." % my_weight
puts "Actually that's not too heavy."
puts "He's got %r eyes and %r hair." % [my_eyes, my_hair]
puts "His teeth are usually %r depending on the coffee." % my_teeth

# this line is tricky, try to get it exactly right
puts "If I add %i, %i, and %i I get %i." % [
  my_age, my_height, my_weight, my_age + my_height + my_weight]
