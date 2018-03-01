

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/*
 * SD2x Homework #6
 * This is an implementation of Depth First Search (DFS) on a graph.
 * You may modify and submit this code if you'd like.
 */

public class DepthFirstSearch {
	protected Set<Node> marked;
	protected Graph graph;
	
	public DepthFirstSearch(Graph graphToSearch) {
		marked = new HashSet<Node>();
		graph = graphToSearch;
	}
	
	public boolean dfs(Node start, String elementToFind) {
		if (!graph.containsNode(start)) {
			return false;
		}	
	
		if (start.getElement().equals(elementToFind)) {
			return true;
		} else {
			marked.add(start);
			for (Node neighbor : graph.getNodeNeighbors(start)) {
				if (!marked.contains(neighbor) && 
				    dfs(neighbor, elementToFind))
	             return true;
			}
			return false;
		}
	}
	
	public int dfs(String from, String to) {
		if (!graph.containsElement(from) || !graph.containsElement(to)) {
				return -1;
		}
		
		if (from.equals(to)) {
			return 0;
		}

		return dfs2(graph.getNode(from), to);
	}

	private int dfs2(Node start, String elementToFind) {
		LinkedList<Integer> distances = new LinkedList<>();
		Set<Node> neighbors = graph.getNodeNeighbors(start);
		marked.add(start);

		for (Node neighbor : neighbors) {
			if (neighbor.getElement().equals(elementToFind)) {
				distances.add(1);
			} else {
				if (!marked.contains(neighbor)) {
					int intermediateDistance = dfs2(neighbor, elementToFind);	
					int distance = intermediateDistance > 0 ? intermediateDistance + 1 : -1;
					if (distance > 0) {						
						distances.add(distance);
					}
				}
			}
			
		}

		return distances.size() > 0 && neighbors.size() > 0 ?
				Collections.min(distances) : -1;
	}

	
	
	

}
