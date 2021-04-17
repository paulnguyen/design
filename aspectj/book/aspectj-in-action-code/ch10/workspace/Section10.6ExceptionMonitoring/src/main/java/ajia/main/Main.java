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

//Listing 10.17 TestException: a program that tests exception logging
package ajia.main;

public class Main {
    public static void main(String[] args) {
        try {
            perform();
        } catch (Throwable ex) {
            System.out.println("Error occurred during execution");
        }
    }

    public static void perform() {
        nestedPerform();
    }

    public static void nestedPerform() {
        nestedNestedPerform();
    }

    public static void nestedNestedPerform() {
        throw new IllegalStateException("Simulated exception");
    }
}