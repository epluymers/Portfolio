//==============================================================================
/**
 * File Name:   graphFactory.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package dataStorage;

/*
 * Class: graphFactory
 * Package: dataStorage
 * Extends: dataFactory
 * Description: Factory parses input and generates data for Graph Data
 */
public class graphFactory extends dataFactory{

	/*
	 * Local Class Variables
	 */
	private graphData data;
	private String connectionType = "Bidirectional";
	
	/*
	 * Constructor
	 */
	public graphFactory()
	{
		data = new graphData();
	}
	
	/*
	 * Function: parseLine
	 * Details: Extracts data from a line of input text and calls functions in graphData to generate the data it holds
	 * 
	 * Input - nextLine: Line of text in String format
	 */
	public void parseLine(String nextLine)
	{
		if(nextLine.contains("Connection:"))
		{
			if(nextLine.contains("Directed"))
			{
				connectionType = "Directed";
			}
		}
		else if(nextLine.contains("Node:"))
		{
			int loc = nextLine.indexOf("Node:");
			nextLine = (nextLine.substring(loc+6)).trim();
			loc = nextLine.indexOf(" ");
			int nodeID = Integer.parseInt((nextLine.substring(0, loc)).trim());
			nextLine = (nextLine.substring(loc+1)).trim();
			loc = nextLine.indexOf(" ");
			int nodeHeuristic = Integer.parseInt((nextLine.substring(0, loc)).trim());
			String nodeName = nextLine.substring(loc+1).trim();
			createNode(nodeID, nodeName, nodeHeuristic);
		}
		else if(nextLine.contains("Link:"))
		{
			int loc = nextLine.indexOf("Link");
			nextLine = (nextLine.substring(loc+6)).trim();
			loc = nextLine.indexOf(" ");
			int nodeID = Integer.parseInt(nextLine.substring(0, loc).trim());
			nextLine = nextLine.substring(loc+1).trim();
			loc = nextLine.indexOf(" ");
			int nodeLink = Integer.parseInt(nextLine.substring(0, loc).trim());
			int linkWeight = Integer.parseInt(nextLine.substring(loc+1).trim());
			createLink(nodeID, nodeLink, linkWeight);
		}
		else if(nextLine.contains("Start:"))
		{
			int loc = nextLine.indexOf("Start:");
			nextLine = (nextLine.substring(loc+7)).trim();
			int nodeID = Integer.parseInt(nextLine.trim());
			data.setStart(nodeID);
		}
		else if(nextLine.contains("End:"))
		{
			int loc = nextLine.indexOf("End:");
			nextLine = (nextLine.substring(loc+5)).trim();
			int nodeID = Integer.parseInt(nextLine.trim());
			data.setEnd(nodeID);
		}
		else if(nextLine.contains("DepthLimit:"))
		{
			int loc = nextLine.indexOf("DepthLimit:");
			nextLine = (nextLine.substring(loc+12)).trim();
			int depth = Integer.parseInt(nextLine.trim());
			data.setDepthLimit(depth);
		}
	}
	
	/*
	 * Private Function: createNode
	 * Description: Sends data to graphData to create a new node
	 * 
	 * Input - id: unique NodeID number
	 * Input - name: name associated with NodeID
	 * Input - heuristic: Heuristic value associated with node
	 */
	private void createNode(int id, String name, int heuristic)
	{
		data.addNode(id, name, heuristic);
	}
	
	/*
	 * Private Function: createLink
	 * Description: Sends data to graphData to create a new link. If graph is BiDirectional it will automatically create two links
	 * 
	 * Input - id: unique NodeID number of first node
	 * Input - connectedID: unique NodeID number of second node
	 * Input - weight: cost value associated with the connection
	 */
	private void createLink(int id, int connectedID, int weight)
	{
		data.addLink(id, connectedID, weight);
		if(connectionType.equals("Bidirectional"))
		{
			data.addLink(connectedID, id, weight);
		}
	}
	
	/*
	 * Function: getData
	 * Description: Returns the generated graphData object
	 * 
	 * Return - abstractData: Generated graphData object
	 */
	public abstractData getData() throws dataException
	{
		if(!data.validateData())
		{
			throw new dataException("The graph data entered is not valid. Please review guidlines and on example data.");
		}
		return data;
	}
	
	/*
	 * Function: getImage
	 * Description: Returns the generated image object
	 * 
	 * Return - imageInterface: Generated image object
	 */
	public imageInterface getImage()
	{
		imageInterface image = new graphImage(data);
		return image;
	}
}
