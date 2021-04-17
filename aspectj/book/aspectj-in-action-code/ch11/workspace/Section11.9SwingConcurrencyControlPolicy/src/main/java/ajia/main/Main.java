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

//Listing 11.25 Test code violating the policy
package ajia.main;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import ...;

public class Main {
    public static void main(String[] args) {
        JFrame appFrame = new JFrame();
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DefaultTableModel tableModel = new DefaultTableModel(4, 2);
        JTable table = new JTable(tableModel);
        
        appFrame.getContentPane().add(table);
        
        appFrame.pack();
        appFrame.setVisible(true);
        
        System.err.println("Frame is now visible");
        
        tableModel.setValueAt("[0,0]", 0, 0);
        tableModel.removeRow(2);
    }
}