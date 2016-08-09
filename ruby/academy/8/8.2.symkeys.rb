=begin


What are Symbols Used For?

Symbols pop up in a lot of places in Ruby, but they're 
primarily used either as hash keys or for referencing method names. 
(We'll see how symbols can reference methods in a later lesson.)

Symbols make good hash keys for a few reasons:

1. They're immutable, meaning they can't be changed once they're created;
2. Only one copy of any symbol exists at a given time, so they save memory;
3. Symbol-as-keys are faster than strings-as-keys because of the above two reasons.


=end

sounds = {
  :cat => "meow",
  :dog => "woof",
  :computer => 10010110,
}

