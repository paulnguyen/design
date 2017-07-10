
# Block - Do End Syntax
[1, 2, 3].each do |num|
  puts num
end
# ==> Prints 1, 2, 3 on separate lines


# Block - Curly Brackets Syntax
[1, 2, 3].each { |num| puts num }
# ==> Prints 1, 2, 3 on separate lines


=begin 

Let's do a little review! 
Use .times and a block to 
puts the string "I'm a block!" 
five times.

=end

5.times { puts "I'm a block!" }