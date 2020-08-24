package com.jphui.spamgen;

import java.util.Map;

public interface WeightedGraph<E> extends Graph<E>
{
	/**
     *	Set the weight between two nodes
     *
     *	@param from the node to set the weight from
     *	@param to the node to set the weight to
     *	@param weight the weight to set
     */
	public void setWeight(E from, E to, int weight);
	
	/**
     *	Set the weight between two nodes
     *
     *	If there is no edge between the nodes, this will return 0.
     *	If no weight has been assigned to these nodes, then this will return 0.
     *	
     *	@param from the node to get the weight from
     *	@param to the node to get the weight to
     *	@return the weight between from and to or 0 if no edge or weight exists.
     */
	public int getWeight(E from, E to);
	
	/**
	 *	Return a Mapping of Node -> Weight for all neighbors of node
	 *
	 *	@param node the node to get weight mappings from
	 *	@return mappings from node to weight
	 */
	public Map<E, Integer> getNeighborWeights(E node);
}