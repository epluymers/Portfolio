//==============================================================================
/**
 * File Name:   imageInterface.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package dataStorage;

/*
 * Import Statements
 */
import java.awt.Canvas;
import java.awt.Graphics;

/*
 * Class: imageInterface
 * Package: dataStorage
 * Extends: Canvas
 * Description: Abstract Class for the data image. Allows use of image in window without needing to know the type of image being generated
 */
public abstract class imageInterface extends Canvas
{
	/*
	 * Abstract Function: paint
	 * 
	 * Input - g: Graphics object that image information will be added to
	 */
	abstract public void paint(Graphics g);
}
