=begin


What's a Symbol?

You can think of a Ruby symbol as a sort of name. 
It's important to remember that symbols aren't strings:

"string" == :string # false

Above and beyond the different syntax, there's a key 
behavior of symbols that makes them different from strings: 
while there can be multiple different strings that all 
have the same value, there's only one copy of any particular 
symbol at a given time.



Symbol Syntax

Symbols always start with a colon (:). They must be valid 
Ruby variable names, so the first character after the colon 
has to be a letter or underscore (_); after that, any 
combination of letters, numbers, and underscores is allowed.


=end

puts "string".object_id
puts "string".object_id

puts :symbol.object_id
puts :symbol.object_id

