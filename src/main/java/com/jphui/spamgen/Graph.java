package com.jphui.spamgen;

import java.util.List;

public interface Graph<E>
{
	/**
     *	Add a node to the graph
     *
     *	@param node the data to add to the graph. Duplicate data will not be added.
     *	@return true if the data was successfully added to graph
     */
	public boolean add(E node);
	
	/**
     *	Add an edge between two nodes in the graph
     *
     *	Only add an edge if both nodes exist AND if an edge does not already exist
     *
     *	@param from the node to add the edge from
     *	@param to the node to add the edge to
     *	@return true if the edge was successfully added to the graph
     */
	public boolean addEdge(E from, E to);
	
	/**
	 *	Check if an edge exists between from and to
	 *
	 *	@param from the node to check from
	 *	@param to the node to check to
	 *	@return true if an edge exists between from and to
	 */
	public boolean hasEdge(E from, E to);
	
	/**
	 *	Return a list of all nodes in the graph
	 *
	 *	@return a list of all nodes in the graph
	 */
	public List<E> getNodes();
	
	/**
	 *	Return a list of nodes that node is connected to by an edge
	 *
	 *	@param node the node to check for neighbors
	 *	@return the list of neighbors
	 */
	public List<E> getNeighbors(E node);
	
	/**
	 *	Check if graph contains specified node
	 *
	 *	@param node the node to check for
	 *	@return true if node exists in graph
	 */
	public boolean contains(E node);
	
	/**
	 *	Return the number of nodes in the graph
	 *
	 *	@return the number of nodes in the graph
	 */
	public int size();
    
}