
##
##  http://groovy-lang.org/closures.html
##

def greeting = 'Hello'
//clos.call( 'World' )

def demo() {
    def clo = { p -> print "${greeting} ${p}\n" }
    return clo
}

c = demo()
c.call( 'Ken' )