def add(first, second)
  puts first + second
end

# First.
add(2, 2)

first = 10
second = 20

# Second.
add(first, second)

# Third.
add(first + 2, second + 2)

# Fourth.
add(first * second, second / first)

# Fifth.
add(first ** 2, second ** 3)

# Sixth.
add(second % first, first % second)

# Seventh. Parentheses are not necessary.
add "first", "second"

# Eighth.
add "first", " second"

# Ninth.
add "third ", "fourth"

# Tenth. Why not.
add "8", "%d" % 7
