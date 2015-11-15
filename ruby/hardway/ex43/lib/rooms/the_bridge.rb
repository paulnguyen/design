  def the_bridge
    puts "You burst onto the Bridge with the netron destruct bomb"
    puts "under your arm and surprise 5 Gothons who are trying to"
    puts "take control of the ship. Each of them has an even uglier"
    puts "clown costume than the last. They haven't pulled their"
    puts "weapons out yet, as they see the active bomb under your"
    puts "arm and don't want to set it off."

    prompt()
    action = gets.chomp()

    if action == "throw the bomb"
      puts "In a panic you throw the bomb at the group of Gothons"
      puts "and make a leap for the door. Right as you drip it a"
      puts "Gothon shoots you right in the back killing you."
      puts "As you die you see another Gothon frantically try to disarm"
      puts "the bomb. You die knowing they will probably blow up when"
      puts "it goes off."
      raise DeathException

    elsif action == "slowly place the bomb"
      puts "You point your blaser at the bomb under your arm"
      puts "and the Gothons put their hands up and start to sweat."
      puts "You inch backward to the door, open it, and then carefully"
      puts "place the bomb on the floor, pointing your blaser at it."
      puts "You then jump back through the door, punch the close button"
      puts "and blast the lock so the Gothons can't get out."
      puts "Now that the bomb is placed you run to the escape pod to"
      puts "get off this tin can."
      return :escape_pod
    else
      puts "DOES NOT COMPUTE!"
      return :the_bridge
    end
  end
