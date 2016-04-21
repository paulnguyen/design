
#include <iostream>
using namespace std;


// old way of doing things

struct sBalloon { 
    string colour;
};
void speak (const sBalloon &b) ;

int main ()
{

    sBalloon b1;					// On stack
    b1.colour = "red"; 				
    
    sBalloon b2;					// On stack
    b2.colour = "red";
    				
    sBalloon* pb3 = new sBalloon; 	// On heap
    pb3->colour = "green";
    
    speak( b1 );
    speak( b2 ) ;
    speak(*pb3);
    
    delete pb3;
    
}

void speak (const sBalloon &b) {
    cout << "I’m a " << b.colour << " balloon\n";
}
