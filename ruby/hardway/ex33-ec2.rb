def array_loop(count)
  i = 0
  numbers = []

  while i < count
    puts "At the top i is #{i}"
    numbers.push(i)

    i = i + 1
    puts "Numbers now: #{numbers}"
    puts "At the bottom i is #{i}"
  end

  puts "The numbers: "

  for num in numbers
    puts num
  end
end

array_loop(5)
array_loop(10)
array_loop(15)
array_loop(20)
