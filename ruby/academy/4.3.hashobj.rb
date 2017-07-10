
# You can create a hash using Hash.new, like so:

my_hash1 = Hash.new
my_hash2 = {}

puts my_hash1
puts my_hash2


# Setting a variable equal to Hash.new creates a new, 
# empty hash; it's the same as setting the variable 
# equal to empty curly braces ({}).


# Adding to a Hash
# We can add to a hash two ways: if we created it using literal 
# notation, we can simply add a new key-value pair directly 
# between the curly braces. If we used Hash.new, we can add 
# to the hash using bracket notation:

pets = Hash.new
pets["Stevie"] = "cat"
puts pets

# Adds the key "Stevie" with the
# value "cat" to the hash


# You can access values in a hash just like an array.

pets = {
  "Stevie" => "cat",
  "Bowser" => "hamster",
  "Kevin Sorbo" => "fish"
}

puts pets["Stevie"]

# will print "cat"
