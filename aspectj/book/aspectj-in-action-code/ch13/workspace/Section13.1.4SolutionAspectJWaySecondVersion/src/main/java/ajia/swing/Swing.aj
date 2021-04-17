/*
Copyright 2009 Ramnivas Laddad

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at 
    http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and 
limitations under the License. 
*/

//Listing 11.27 The Swing library aspect
package ajia.swing;

//import ...
import java.util.EventListener;
import java.awt.Component;
import javax.swing.JComponent;

public aspect Swing {
    public pointcut threadSafeCall()
        : call(void JComponent.revalidate())
          || call(void JComponent.repaint(..))
          || call(void Component+.invalidate(..))
          || call(void add*Listener(EventListener+))
          || call(void remove*Listener(EventListener+));

    public pointcut viewCall()
        : call(* javax..JComponent+.*(..))
          || call(* javax..JFrame+.*(..));

    public pointcut modelCall()
        : call(* javax..*Model+.*(..))
          || call(* javax.swing.text.Document+.*(..));

    public pointcut uiCall()
        : viewCall() || modelCall();
}