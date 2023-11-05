//==============================================================================
/**
 * File Name:   graphData.java
 *
 * Written By: Elicia Pluymers
 * 2023 CS Capstone Project AiAlgorithms
 **/
//==============================================================================

package dataStorage;

/*
 * Import Statements
 */
import java.util.ArrayList;

/*
 * Class: graphData
 * Package: dataStorage
 * Extends: abstractData
 * Description: Class for Graph Data Storage and Manipulation. Handles data maintenance tasks for algorithm.
 */
public class graphData extends abstractData
{
	/*
	 * Local Class Variables
	 */
	private ArrayList<Integer> nodeID;
	private ArrayList<String> nodeName;
	private ArrayList<Integer> nodeHeuristic;
	private ArrayList<ArrayList<Integer>> linkAndCost;
	private int end;
	private int start;
	private ArrayList<ArrayList<Integer>> solutionSet;
	private ArrayList<ArrayList<Integer>> frontierSet;
	private ArrayList<ArrayList<Integer>> exploredSet;
	private int depthLim;

	/*
	 * Constructor
	 */
	public graphData()
	{
		nodeID = new ArrayList<Integer>();
		nodeName = new ArrayList<String>();
		nodeHeuristic = new ArrayList<Integer>();
		linkAndCost = new ArrayList<ArrayList<Integer>>();
		frontierSet = new ArrayList<ArrayList<Integer>>();
		exploredSet = new ArrayList<ArrayList<Integer>>();
		solutionSet = new ArrayList<ArrayList<Integer>>();
		end = -1;
		start = -1;
		depthLim = 100;
	}
	
	public void addNode(int id, String name, int heuristic)
	{
		nodeID.add(id);
		nodeName.add(name);
		nodeHeuristic.add(heuristic);
	}
	
	public void addLink(int fromLink, int toLink, int cost)
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(fromLink);
		temp.add(toLink);
		temp.add(cost);
		linkAndCost.add(temp);
	}
	
	public void addFrontier(int id, int rootID, int cost)
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(id);
		temp.add(rootID);
		temp.add(cost);
		if(!frontierSet.contains(temp) && !exploredSet.contains(temp))
		{
			frontierSet.add(temp);
		}
	}
	
	public void addExplored(int id, int rootID, int cost)
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(id);
		temp.add(rootID);
		temp.add(cost);
		if(!exploredSet.contains(temp))
		{
			exploredSet.add(temp);
		}
	}
	
	public void setStart(int id) //todo exception
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(id);
		temp.add(-1);
		temp.add(0);
		solutionSet.add(temp);
		addExplored(id, -1, 0);
		addFrontierNodes(temp);
		start = id;
	}
	
	public void setEnd(int id) //todo exception
	{
		end = id;
	}
	
	public int getDataSize()
	{
		return nodeID.size();
	}
	
	public String getNodeName(int id)
	{
		String name = "";
		for(int i = 0; i < nodeID.size(); i++)
		{
			if(nodeID.get(i) == id)
			{
				name = nodeName.get(i);
			}
		}
		return name;
	}
	
	public int getNode(int arrayLoc)
	{
		return nodeID.get(arrayLoc);
	}
	
	public ArrayList<Integer> getNodeLinks(int id)
	{
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i = 0; i < linkAndCost.size(); i++)
		{
			if(linkAndCost.get(i).get(0) == id)
			{
				temp.add(linkAndCost.get(i).get(1));
			}
		}
		return temp;
	}
	
	public int getLinkCost(int fromID, int toID)
	{
		int cost = 0;
		for(int i = 0; i < linkAndCost.size(); i++)
		{
			if(linkAndCost.get(i).get(0) == fromID && linkAndCost.get(i).get(1) == toID)
			{
				cost = linkAndCost.get(i).get(2);
			}
		}
		return cost;
	}
	
	public int getHeuristic(int id)
	{
		for(int i = 0; i < nodeID.size(); i++)
		{
			if(nodeID.get(i) == id)
			{
				return nodeHeuristic.get(i);
			}
		}
		return -1; //todo exception
	}
	
 	public boolean goalTest()
	{
		if(solutionSet.get(solutionSet.size()-1).get(0) == end)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void addSolution(ArrayList<Integer> frontier)
	{
		removeFrontier(frontier);
		addExplored(frontier.get(0), frontier.get(1), frontier.get(2));
		int costCalc = solutionSet.get(solutionSet.size()-1).get(2) + getLinkCost(solutionSet.get(solutionSet.size()-1).get(0), frontier.get(0));
		if(frontier.get(1) != solutionSet.get(solutionSet.size()-1).get(0) || frontier.get(2) != (solutionSet.get(solutionSet.size()-1).get(2) + getLinkCost(solutionSet.get(solutionSet.size()-1).get(0), frontier.get(0))))
		{
			updateSolutionSet(frontier);
		}
		solutionSet.add(frontier);
		if(depthLim >= solutionSet.size())
		{
			addFrontierNodes(frontier);
		}
	}
	
	public void popSolutionSet()
	{
		solutionSet.remove(solutionSet.size()-1);
	}
	
	public ArrayList<ArrayList<Integer>> getSolutionSet()
	{
		return solutionSet;
	}
	
	public void addFrontierNodes(ArrayList<Integer> solutionNode)
	{
		ArrayList<Integer> temp = getNodeLinks(solutionNode.get(0));
		for(int i = 0; i < temp.size(); i++)
		{
			if(!isInSolutionSet(temp.get(i)))
			{
				int cost = solutionNode.get(2) + getLinkCost(solutionNode.get(0), temp.get(i));
				addFrontier(temp.get(i), solutionNode.get(0), cost);
			}
		}
	}
	
	public int getFrontierSize()
	{
		return frontierSet.size();
	}
	
	public int getFrontierSize(int id)
	{
		int size = 0;
		for(int i = 0; i < frontierSet.size(); i++)
		{
			if(frontierSet.get(i).get(1) == id)
			{
				size++;
			}
		}
		return size;
	}
	
	public ArrayList<Integer> getFirstFrontier()
	{
		return frontierSet.get(0);
	}
	
	public ArrayList<Integer> getFirstFrontier(int id)
	{
		ArrayList<Integer> frontierFirst;
		for(int i = 0; i <  frontierSet.size(); i++)
		{
			if(frontierSet.get(i).get(1) == id)
			{
				frontierFirst = frontierSet.get(i);
				return frontierFirst;
			}
		}
		return null; //todo exception
	}
	
	public ArrayList<Integer> getCheapestFrontier()
	{
		int lowCost = frontierSet.get(0).get(2);
		int lowID = 0;
		for(int i = 1; i < frontierSet.size(); i++)
		{
			if(frontierSet.get(i).get(2) < lowCost)
			{
				lowCost = frontierSet.get(i).get(2);
				lowID = i;
			}
		}
		return frontierSet.get(lowID);
	}
	
	public ArrayList<Integer> getLowestHeuristicFrontier()
	{
		int lowHeur = getHeuristic(frontierSet.get(0).get(0));
		int lowID = 0;
		for(int i = 1; i < frontierSet.size(); i++)
		{
			if(getHeuristic(frontierSet.get(i).get(0)) < lowHeur)
			{
				lowHeur = getHeuristic(frontierSet.get(i).get(0));
				lowID = i;
			}
		}
		return frontierSet.get(lowID);
	}
	
	public ArrayList<Integer> getLowHeurCostFrontier()
	{
		int lowHeurCost = getHeuristic(frontierSet.get(0).get(0)) + frontierSet.get(0).get(2);
		int lowID = 0;
		for(int i = 1; i < frontierSet.size(); i++)
		{
			int curHeurCost = getHeuristic(frontierSet.get(i).get(0)) + frontierSet.get(i).get(2);
			if(curHeurCost < lowHeurCost)
			{
				lowHeurCost = frontierSet.get(i).get(0);
				lowID = i;
			}
		}
		return frontierSet.get(lowID);
	}
	
	public int getCurrentLocation()
	{
		return solutionSet.get(solutionSet.size()-1).get(0);
	}

	
	public void removeFrontier(ArrayList<Integer> frontier)
	{
		for(int i = 0; i < frontierSet.size(); i++)
		{
			if(frontierSet.get(i).equals(frontier))
			{
				frontierSet.remove(i);
			}
		}
	}
	
	public boolean isInSolutionSet(int id)
	{
		for(int i = 0; i < solutionSet.size(); i++)
		{
			if(solutionSet.get(i).get(0) == id)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isInExploredSet(int id, int rootID)
	{
		for(int i = 0; i < exploredSet.size(); i++)
		{
			if(exploredSet.get(i).get(0) == id && exploredSet.get(i).get(1) == rootID)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isInFrontierSet(int id, int rootID)
	{
		for(int i = 0; i < frontierSet.size(); i++)
		{
			if(frontierSet.get(i).get(0) == id && frontierSet.get(i).get(1) == rootID)
			{
				return true;
			}
		}
		return false;
	}
	
	public void updateSolutionSet(ArrayList<Integer> frontier) //may need to add extra logic for other algorithms
	{
		while(isInSolutionSet(frontier.get(1)) && (frontier.get(1) != solutionSet.get(solutionSet.size()-1).get(0) || frontier.get(2) != solutionSet.get(solutionSet.size()-1).get(2) + getLinkCost(frontier.get(1), frontier.get(0))))
		{
			popSolutionSet();
		}
		if(!isInSolutionSet(frontier.get(1)))
		{
			for(int i = exploredSet.size() - 1; i >= 0; i--)
			{
				if(exploredSet.get(i).get(0) == frontier.get(1) && frontier.get(2) == (exploredSet.get(i).get(2) + getLinkCost(frontier.get(1), frontier.get(0))))
				{
					addSolution(exploredSet.get(i));
				}
			}
		}
	}
	
	public void setDepthLimit(int d)
	{
		depthLim = d;
	}
	
	public int getDepthLimit()
	{
		return depthLim;
	}
	
	public int getSolutionSize()
	{
		return solutionSet.size();
	}
	
	public void resetSolution()
	{
		solutionSet.clear();
		frontierSet.clear();
		exploredSet.clear();
		setStart(start);
	}
	
	public boolean validateData()
	{
		if(start == -1 || end == -1)
		{
			return false;
		}
		for(int i = 0; i < linkAndCost.size(); i++)
		{
			String nullName = "";
			if(getNodeName(linkAndCost.get(i).get(0)).equals(nullName) || getNodeName(linkAndCost.get(i).get(1)).equals(nullName))
			{
				return false;
			}
		}
		return true;
	}
}
