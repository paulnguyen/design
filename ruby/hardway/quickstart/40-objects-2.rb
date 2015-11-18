
# Hash
mystuff = {'apple' => "I AM APPLES!"}
puts mystuff['apple']

# Module
require "./40-module.rb"
MyStuff.apple()
puts MyStuff::TANGERINE

# Hash vs. Module
mystuff['apple'] # get apple from dict
MyStuff.apple() # get apple from the module
MyStuff::TANGERINE # same thing, it's just a variable

# Class
class MyStuff

    def initialize()
        @tangerine = "And now a thousand years between"
    end

    attr_reader :tangerine

    def apple()
        puts "I AM CLASSY APPLES!"
    end

end

# Object
thing = MyStuff.new()
thing.apple()
puts thing.tangerine



# dict style
mystuff['apples']

# module style
MyStuff.apples()
puts MyStuff::TANGERINE

# class style
thing = MyStuff.new()
thing.apples()
puts thing.tangerine

