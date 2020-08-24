package com.jphui.spamgen;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class AdjacencyListGraph<E> implements Graph<E>
{
	//INSTANCE VARIABLES
	/**
	 *	An Adjacency List that maps Node -> List of neighbor Nodes
	 */
	private Map<E, List<E>> adjacencyList;
	
	//CONSTRUCTORS
    public AdjacencyListGraph() 
    {
    	//initialize instance variables
    	adjacencyList = new HashMap<E, List<E>>();
    }
    
    //METHODS
    /**
     *	Add a node to the graph
     *
     *	@param node the data to add to the graph. Duplicate data will not be added.
     *	@return true if the data was successfully added to graph
     */
    public boolean add(E node)
    {
    	//TODO: check if this graph already contains *node*. If so, return false.
    	//TODO: add *node* to the Adjacency List, mapping it to an empty ArrayList then return true
		return (adjacencyList.putIfAbsent(node, new ArrayList<>()) == null);
    }
    
    /**
     *	Add an edge between two nodes in the graph
     *
     *	Only add an edge if both nodes exist AND if an edge does not already exist
     *
     *	@param from the node to add the edge from
     *	@param to the node to add the edge to
     *	@return true if the edge was successfully added to the graph
     */
	public boolean addEdge(E from, E to)
	{
		//TODO: check if this graph contains *from* and *to*. If not, return false.
		//TODO: Check if *to* is a neighbor of *from*. If so, return false.
		if (!this.contains(from) || !this.contains(to) || this.getNeighbors(from).contains(to))
			return false;
		
		//TODO: add *to* as a neighbor of *from*, then return true.
		else
			return adjacencyList.get(from).add(to);
	}
	
	/**
	 *	Check if an edge exists between from and to
	 *
	 *	@param from the node to check from
	 *	@param to the node to check to
	 *	@return true if an edge exists between from and to
	 */
	public boolean hasEdge(E from, E to)
	{
		//TODO: check if this graph contains both *from* and *to*. If not, return false.
		//TODO: check if *to* is a neighbor of *from*. If so, return true. Otherwise, return false.
		return (this.contains(from) &&
				this.contains(to) &&
				this.getNeighbors(from).contains(to));
	}
	
	/**
	 *	Check if graph contains specified node
	 *
	 *	@param node the node to check for
	 *	@return true if node exists in graph
	 */
	public boolean contains(E node)
	{
		//if the graph contains *node*, then it will have been put in the adjacencyList as a key.
		return null != adjacencyList.get(node);
	}
	
	/**
	 *	Return a list of all nodes in the graph
	 *
	 *	@return a list of all nodes in the graph
	 */
	public List<E> getNodes()
	{
		List<E> ret = new ArrayList<E>();

		for(E node : adjacencyList.keySet())
			ret.add(node);
			
		return ret;
	}
	
	/**
	 *	Return a list of nodes that node is connected to by an edge
	 *
	 *	@param node the node to check for neighbors
	 *	@return the list of neighbors
	 */
	public List<E> getNeighbors(E node)
	{
		//create a List object to return
		List<E> ret = new ArrayList<E>();		
			
		//get list of neighbor nodes
		List<E> neighbors = adjacencyList.get(node); 

		//if node has neighbors, add them all to the List ret
		if(null != neighbors)
			ret.addAll(neighbors);
		
		return ret;
	}
	
	/**
	 *	Return the number of nodes in the graph
	 *
	 *	@return the number of nodes in the graph
	 */
	public int size()
	{
		//the size of the graph is the number of nodes that have been added
		return adjacencyList.size();
	}
	
	/**
	 *	Return a string representation of the graph data
	 *
	 *	@return a string representation of the graph
	 */
	@Override
	public String toString()
	{
		List<String> ret = new ArrayList<String>();
		
		for(E node : getNodes())
			ret.add(node + "->" + getNeighbors(node).toString());

		return ret.toString();
	}
}