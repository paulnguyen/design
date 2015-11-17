cities = {'CA' => 'San Francisco',
  'MI' => 'Detroit',
  'FL' => 'Jacksonville'}

cities['NY'] = 'New York'
cities['OR'] = 'Portland'

puts "For loop: "
for city in cities
  puts city
end
puts

puts "each method: "
cities.each do |city|
  puts city
end
