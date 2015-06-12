package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JPanel;

/*
 * Class Coordinate is responsible for drawing the graph.
 */

public class Coordinate extends JPanel{
	private static final long serialVersionUID = 1L;
	List<Integer> coord = new ArrayList<Integer>();
	List<Edge> edgecoord = new ArrayList<Edge>();
	XMLGraph graph1 = new XMLGraph();
	Network net = new Network();
	
	// Here graph is drawn
	private void doDrawing(Graphics g) {
			
		Graphics2D g2d = (Graphics2D) g;
		// Reading the coordinates of country for drawing the shape
		readFile();
        g2d.setColor(Color.blue);
       
        // Drawing of shape of Switzerland
        int j=0;
        for(int i=0; i<coord.size()-2; i=i+2) {
        	j = i + 1;
            int x = coord.get(i);
            int x1 = coord.get(i+2);
            int y = coord.get(j);
            int y1 = coord.get(j+2);
            g2d.drawLine(x, y, x1, y1);
        }
        
       // Drawing of cities on a map 
       for(int a=0; a<graph1.nodes.size(); a++){
    	   	g2d.setColor(Color.red);
        	int x = graph1.nodes.get(a).getX();
        	int y = graph1.nodes.get(a).getY();
     
        	g2d.fillOval(x, y, 10, 10);  
        }
        
       // Drawing connections between cities
        for(int z=0; z<net.linkList.size()/2; z++){
        	int x = net.linkList.get(z).getSource().getX();
        	int y = net.linkList.get(z).getSource().getY();
        	for(int b=z; b<net.linkList.size(); b++){
        	int x1 = net.linkList.get(b).getDestination().getX();
        	int y1 = net.linkList.get(b).getDestination().getY();
        	
        	if(net.linkList.get(z).getSource().getName() != net.linkList.get(z).getDestination().getName())
        		g2d.drawLine(x, y, x1, y1);
        	}    	
        }  
	}
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
        doDrawing(g);
    }
	
	// Reading coordinates for country shape
	public void readFile(){
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream("suisse.txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine = "";
		StringTokenizer st = new StringTokenizer(strLine);
		
		try {
			while((strLine = br.readLine()) != null)      
			{
				  st = new StringTokenizer(strLine);
				while (st.hasMoreTokens()) {
				    int x = Integer.parseInt(st.nextToken());
				    coord.add(x);						
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
