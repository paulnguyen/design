
=begin 

Hashes are sort of like JavaScript objects or Python dictionaries. 
If you haven't studied those languages, all you need to know that 
a hash is a collection of key-value pairs. 

Hash syntax looks like this:

hash = {
  key1 => value1,
  key2 => value2,
  key3 => value3
}

Values are assigned to keys using =>. 

You can use any Ruby object for a 
key or value.

=end

my_hash = { "name" => "Eric",
  "age" => 26,
  "hungry?" => true
}

puts my_hash["name"]
puts my_hash["age"]
puts my_hash["hungry?"]