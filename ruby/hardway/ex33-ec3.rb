def array_loop(count, inc)
  i = 0
  numbers = []

  while i < count
    puts "At the top i is #{i}"
    numbers.push(i)

    i = i + inc
    puts "Numbers now: #{numbers}"
    puts "At the bottom i is #{i}"
  end

  puts "The numbers: "

  for num in numbers
    puts num
  end
end

array_loop(5, 1)
array_loop(10, 2)
array_loop(15, 5)
array_loop(20, 10)
