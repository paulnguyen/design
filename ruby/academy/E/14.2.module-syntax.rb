
=begin 


Module Syntax

You can pull in pre-existing modules (we'll show you how soon!), but you can also make your own. Modules are super easy to make! You just use the module keyword, like so:

module ModuleName
  # Bits 'n pieces
end

Like class names, module names are written in CapitalizedCamelCase, rather than lowercasewithunderscores.

It doesn't make sense to include variables in modules, since variables (by definition) change (or vary). Constants, however, are supposed to always stay the same, so including helpful constants in modules is a great idea.

Ruby doesn't make you keep the same value for a constant once it's initialized, but it will warn you if you try to change it. Ruby constants are written in ALL_CAPS and are separated with underscores if there's more than one word.

An example of a Ruby constant is PI, which lives in the Math module and is approximately equal to 3.141592653589793. We created our own PI in the previous exercise, but don't worry: because they're in separate modules, Ruby knows to keep them separate.
Instructions

Create your own module called MyLibrary in the editor to the right. Include a constant called FAVE_BOOK and set it equal to a string naming your favorite book!

=end

module MyLibrary

	FAVE_BOOK = "Design Patterns"

end
