# Define the cheese and crackers function.
def cheese_and_crackers(cheese_count, boxes_of_crackers)
  # How many cheeses?
  puts "You have #{cheese_count} cheeses!"

  # How many crackers?
  puts "You have #{boxes_of_crackers} boxes of crackers!"
  
  # Har har.
  puts "Man that's enough for a party!"

  # Random.
  puts "Get a blanket."

  # To separate the invocations.
  puts # a blank line
end

# We sure can.
puts "We can just give the function numbers directly:"
cheese_and_crackers(20, 30)

# I wonder if he'll ever mention scope.
puts "OR, we can use variables from our script:"
amount_of_cheese = 10
amount_of_crackers = 50
cheese_and_crackers(amount_of_cheese, amount_of_crackers)

# Bye-bye multiples of 10.
puts "We can even do math inside too:"
cheese_and_crackers(10 + 20, 5 + 6)

# That's a lot of crackers.
puts "And we can combine the two, variables and math:"
cheese_and_crackers(amount_of_cheese + 100, amount_of_crackers + 1000)
