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

//Listing 13.1: A test program showing incorrect usage of the UI update call 
package ajia.main;

//import ...
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Main {
    public static void main(String[] args) {
        JFrame appFrame = new JFrame();
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DefaultTableModel tableModel = new DefaultTableModel(4, 2);
        JTable table = new JTable(tableModel);
        
        appFrame.getContentPane().add(table);
        
        appFrame.pack();
        appFrame.setVisible(true);
        
        String value = "[0,0]";
        
        tableModel.setValueAt(value, 0, 0);
        
        JOptionPane.showMessageDialog(appFrame, "Press OK to continue");
        
        int rowCount = tableModel.getRowCount();
        System.out.println("Row count = " + rowCount);
        
        Color gridColor = table.getGridColor();
        System.out.println("Grid color = " + gridColor);
    }
}