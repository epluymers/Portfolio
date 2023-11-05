//==============================================================================
/**
 * File Name:   queenImage.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package dataStorage;

/*
 * Import Statements
 */
import java.awt.Graphics;
import java.awt.Font;

/*
 * Class: queenImage
 * Package: dataStorage
 * Extends: imageInterface
 * Description: Generates and image of data based on it's current solution set. Repainted each time the window refreshes.
 */
public class queenImage extends imageInterface
{
	/*
	 * Local Class Variables
	 */
	private queensData currentData;
	private int boardSize;
	
	/*
	 * Constructor
	 * 
	 * Input - data - dataObject that will be used for image generation
	 */
	public queenImage(queensData data)
	{
		currentData = data;
		boardSize = currentData.getBoardSize();
	}
	
	/*
	 * Function: paint
	 * 
	 * Input - g: Graphics object that image information will be added to
	 */
	public void paint(Graphics g)
	{
		int gap = 750/boardSize;
		Font queenSize = new Font("SansSerif", Font.PLAIN, gap);
		g.drawLine(25, 25, 25, 775);
		g.drawLine(25, 25, 775, 25);
		g.drawLine(25, 775, 775, 775);
		g.drawLine(775, 25, 775, 775);
		for(int i = 1; i < boardSize; i++)
		{
			g.drawLine(25, gap*i+25, 775, gap*i+25);
			g.drawLine(gap*i+25, 25, gap*i+25, 775);
		}
		g.setFont(queenSize);
		for(int i = 1; i <= boardSize; i++)
		{
			int xSpace = currentData.getQueenX(i) - 1;
			int ySpace = currentData.getQueenY(i);
			g.drawString("Q", gap*xSpace+(200/boardSize), gap*ySpace);
		}
	}
}
