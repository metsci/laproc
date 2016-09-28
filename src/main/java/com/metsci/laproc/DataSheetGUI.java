package com.metsci.laproc;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DataSheetGUI{
	
	public static JPanel GetDataSheet(){
        JPanel dataPanel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        dataPanel.setLayout(gbl);
        JLabel tempLabel;
        GridBagConstraints c;
        for(int i = 0; i < 5; i++){
        	tempLabel = new JLabel("Header " + Integer.toString(i));
        	tempLabel.setBorder(BorderFactory.createCompoundBorder(
        			BorderFactory.createLineBorder(Color.BLACK,1,false), 
        			BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        	c = new GridBagConstraints();
        	c.fill = GridBagConstraints.HORIZONTAL;
        	c.gridx = i;
        	c.gridy = 0;
        	dataPanel.add(tempLabel, c);
        }
        for(int i = 1; i < 7; i++){
            for(int j = 0; j < 5; j++){
            	tempLabel = new JLabel("Entry " + Integer.toString(i) + " value " + Integer.toString(j));
            	tempLabel.setBorder(BorderFactory.createCompoundBorder(
            			BorderFactory.createLineBorder(Color.BLACK,1,false), 
            			BorderFactory.createEmptyBorder(10, 10, 10, 10)));
            	c = new GridBagConstraints();
            	c.fill = GridBagConstraints.HORIZONTAL;
            	c.gridx = j;
            	c.gridy = i;
            	dataPanel.add(tempLabel, c);
            }
        }
        return dataPanel;
	}
	
}
