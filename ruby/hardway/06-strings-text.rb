# 10 is 2 in binary.
x = "There are #{10} types of people."

# You could have made this more cryptic.
binary = "binary"

# Yep.
do_not = "don't"

# Har har.
y = "Those who know #{binary} and those who #{do_not}."

# Print the previous lines...
puts x

# ...on separate lines.
puts y

# One too many periods.
puts "I said: #{x}."

# Interpolation still occurs. Who knew?
puts "I also said: '#{y}'."

# Is this actually false?
hilarious = false

# Yep.
joke_evaluation = "Isn't that joke so funny?! #{hilarious}"

# Yep yep.
puts joke_evaluation

# x, y, w...e?
w = "This is the left side of..."
e = "a string with a right side."

# This is annoying.
puts w + e
