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

//Listing 13.3: Test.java: the conventional implementation
package ajia.main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import ...

public class Main {
    public static void main(String[] args) {
        final JFrame appFrame = new JFrame();
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final DefaultTableModel tableModel = new DefaultTableModel(4, 2);
        final JTable table = new JTable(tableModel);
        
        appFrame.getContentPane().add(table);
        
        appFrame.pack();
        appFrame.setVisible(true);
        final String value = "[0,0]";
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                tableModel.setValueAt(value, 0, 0);
            }
        });
        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    JOptionPane.showMessageDialog(appFrame,
                            "Press OK to continue");
                }
            });
        } catch (Exception ex) {
            // ignore...
        }
        
        final int[] rowCountValueArray = new int[1];
        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    rowCountValueArray[0] = tableModel.getRowCount();
                }
            });
        } catch (Exception ex) {
            // ignore...
        }
        int rowCount = rowCountValueArray[0];
        
        System.out.println("Row count = " + rowCount);
        
        final Color[] gridColorValueArray = new Color[1];
        try {
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    gridColorValueArray[0] = table.getGridColor();
                }
            });
        } catch (Exception ex) {
            // ignore...
        }
        Color gridColor = gridColorValueArray[0];
        
        System.out.println("Grid color = " + gridColor);
    }
}