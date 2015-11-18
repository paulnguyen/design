  def escape_pod
    puts "You rush through the ship desperately trying to make it to"
    puts "the escape pod before the whole ship explodes. It seems like"
    puts "hardly any Gothons are on the ship, so your run is clear of"
    puts "interference. You get to the chamber with the escape pods, and"
    puts "now need to pick one to take. Some of them could be damaged"
    puts "but you don't have time to look. There's 5 pods, which one"
    puts "do you take?"

    good_pod = rand(5)+1
    print "[pod #]>"
    guess = gets.chomp()

    if guess.to_i != good_pod
      puts "You jump into pod %s and hit the eject button." % guess
      puts "The pod escapes out into the void of space, then"
      puts "implodes as the hull ruptures, crushing your body"
      puts "into jam jelly."
      raise DeathException
    else
      puts "You jump into pod %s and hit the eject button." % guess
      puts "The pod easily slides out into space heading to"
      puts "the planet below. As it flies to the planet, you look"
      puts "back and see your ship implode then explode like a"
      puts "bright star, taking out the Gothon ship at the same"
      puts "time. You won!"
      Process.exit(0)
    end
