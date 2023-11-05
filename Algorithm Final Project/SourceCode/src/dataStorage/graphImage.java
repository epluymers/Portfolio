//==============================================================================
/**
 * File Name:   graphImage.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package dataStorage;

/*
 * Import Statements
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
//import java.awt.*;

/*
 * Class: graphImage
 * Package: dataStorage
 * Extends: imageInterface
 * Description: Generates and image of data based on it's current solution set. Repainted each time the window refreshes.
 */
public class graphImage extends imageInterface
{
	/*
	 * Local Class Variables
	 */
	private graphData currentData;
	
	/*
	 * Constructor
	 * 
	 * Input - data - dataObject that will be used for image generation
	 */
	public graphImage(graphData data)
	{
		currentData = data;
	}

	/*
	 * Function: paint
	 * 
	 * Input - g: Graphics object that image information will be added to
	 */
	public void paint(Graphics g)
	{
		ArrayList<ArrayList<Integer>> solution = currentData.getSolutionSet();
		int lastSolLocX = 300;
		int lastSolLocY = 25;
		Color green = new Color(0, 128, 0);
		Color grey = new Color(128,128,128);
		Color black = new Color(0, 0, 0);
		g.setColor(green);
		
		int solutionDisplayRowStart = 0;
		if(solution.size() > 7)
		{
			solutionDisplayRowStart = solution.size() - 7;
		}
		else
		{
			g.drawString(currentData.getNodeName(solution.get(0).get(0)),lastSolLocX,lastSolLocY);
		}
		lastSolLocY = 0;
		
		for(int i = solutionDisplayRowStart; i < solution.size(); i++)
		{
			int iter = i - solutionDisplayRowStart;
			ArrayList<Integer> links = currentData.getNodeLinks(solution.get(i).get(0));
			int distance = 800/links.size();
			int rowGap = 100;
			int newX = 0;
			int newY = 0;
			if(!currentData.goalTest() || i != solution.size() - 1)
			{
				for(int l = 0; l < links.size(); l++)
				{
					if(i < solution.size() - 1 && solution.get(i+1).get(0) == links.get(l))
					{
						g.setColor(green);
						newX = l*distance+25;
						newY = rowGap*iter+75;
						g.drawLine(lastSolLocX, lastSolLocY+25, l*distance+25, rowGap*iter+50);
						int cost = currentData.getLinkCost(solution.get(i).get(0), links.get(l));
						g.drawString("Cost: " + cost, l*distance+35, rowGap*iter+60);
						g.drawString(currentData.getNodeName(links.get(l)), l*distance+25, rowGap*iter+75);
						int heur = currentData.getHeuristic(links.get(l));
						g.drawString("Heuristic: " + heur, l*distance+35, rowGap*iter+90);
						cost = cost + solution.get(i).get(2);
						g.drawString("Cum Cost: " + cost, l*distance+35, rowGap*iter+105);
					}
					else if(currentData.isInExploredSet(links.get(l), solution.get(i).get(0)))
					{
						g.setColor(grey);
						g.drawLine(lastSolLocX, lastSolLocY+25, l*distance+25, rowGap*iter+50);
						int cost = currentData.getLinkCost(solution.get(i).get(0), links.get(l));
						g.drawString("Cost: " + cost, l*distance+35, rowGap*iter+60);
						g.drawString(currentData.getNodeName(links.get(l)), l*distance+25, rowGap*iter+75);
						int heur = currentData.getHeuristic(links.get(l));
						g.drawString("Heuristic: " + heur, l*distance+35, rowGap*iter+90);
					}
					else if(currentData.isInFrontierSet(links.get(l), solution.get(i).get(0)))
					{
						g.setColor(black);
						g.drawLine(lastSolLocX, lastSolLocY+25, l*distance+25, rowGap*iter+50);
						int cost = currentData.getLinkCost(solution.get(i).get(0), links.get(l));
						g.drawString("Cost: " + cost, l*distance+35, rowGap*iter+60);
						g.drawString(currentData.getNodeName(links.get(l)), l*distance+25, rowGap*iter+75);
						int heur = currentData.getHeuristic(links.get(l));
						g.drawString("Heuristic: " + heur, l*distance+35, rowGap*iter+90);
					}
				}
				lastSolLocX = newX;
				lastSolLocY = newY;
			}
		}
	}
}
