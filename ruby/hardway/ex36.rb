module RoomPuzzle
  module Util
    def self.invalid()
      puts "wtf are you doing?"
      puts
      Process.exit(1)
    end

    def self.die(message)
      puts message
      puts "Sorry, try again."
      puts
      Process.exit(0)
    end
    
    def self.win
      puts "You win, nice job!"
      puts
      Process.exit(0)
    end

    def self.prompt()
      print "> "
    end
  end

  module Room
    def self.croc()
      options = ['jump', 'feed']
      puts "There's a hungry crocodile in here!"
      puts "You can either 'feed' him a box of marsala burgers from Trader Joe's, or you can 'jump' over him using a trampoline."
      Util::prompt()
      choice = gets.chomp()
      if choice.include? options[0]
        Util::die("The trampoline broke and you were eaten by the crocodile.")
      elsif choice.include? options[1]
        Util::win()
      else
        Util::invalid()
      end
    end

    def self.bear()
      choices = ['feed', 'spray']
      puts "Nice work!"
      puts "There's a hungry bear in here!"
      puts "You can either 'feed' him a tasty Pacific salmon or 'spray' him with mace."
      Util::prompt()
      choice = gets.chomp()
      if choice.downcase.include? choices[0]
        Util::win()
      elsif choice.downcase.include? choices[1]
        Util::die("He gets mad and rips you to shreds.")
      else
        Util::invalid()
      end
    end

    def self.python()
      choices = ['feed', 'shoot']
      puts "There's a hungry python in here!"
      puts "You can either 'feed' her a deer mouse or 'shoot' her with a tranqulizer gun."
      Util::prompt()
      choice = gets.chomp()
      if choice.downcase.include? choices[0]
        Room::bear()
      elsif choice.downcase.include? choices[1]
        Util::die("The tranquilizer needle broke on impact and you were eaten.")
      else
        Util::invalid()
      end
    end

    def self.cheetahs()
      choices = ['feed', 'catnip']
      puts "There are several hungry cheetahs in here!"
      puts "You can either 'feed' them a Thomson's gazelle or give them some 'catnip.'"
      Util::prompt()
      choice = gets.chomp()
      if choice.downcase.include? choices[0]
        Util::die("That wasn't enough food for these hungry cheetahs! They ate you too.")
      elsif choice.downcase.include? choices[1]
        Util::win()
      else
        Util::invalid()
      end
    end
  end

  def self.start()
    doors = ['left', 'middle', 'right']

    puts "Welcome to Matt's game!"
    puts "You can enter one of three rooms: "
    doors.each do |door|
      puts door.capitalize()
    end

    Util::prompt; choice = gets.chomp()
    if choice.downcase == 'left'
      Room::croc()
    elsif choice.downcase == 'middle'
      Room::python()
    elsif choice.downcase == 'right'
      Room::cheetahs()
    else
      Util::invalid()
    end
  end
end
