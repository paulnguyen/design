
=begin 


Lambda Syntax

Lambdas are defined using the following syntax:

lambda { |param| block }

Lambdas are useful in the same situations in which 
you'd use a proc. We'll cover the differences between 
lambdas and procs in the next exercise; in the meantime, 
let's get a little practice in with the lambda syntax.


We have an array of strings in the editor, but we want an array of symbols.

    1. On line 4, create a new variable called symbolize.
    2. Store a lambda that takes one parameter and calls .to_sym on that parameter.

We will then use symbolize with the .collect method to convert the items in strings to symbols!

strings = ["leonardo", "donatello", "raphael", "michaelangelo"]

# Write your code here!

symbols = strings.collect(&symbolize)

=end


strings = ["leonardo", "donatello", "raphael", "michaelangelo"]

symbolize = lambda { |p| p.to_sym }

symbols = strings.collect(&symbolize)