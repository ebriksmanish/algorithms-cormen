
import java.util.List;
import java.util.Set;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {
		if (graph == null) {
			return -1;
		}
		
		if (!graph.containsElement(src) || !graph.containsElement(dest)) {
			return -1;
		}
	
		if (src.equals(dest)) {
			return 0;
		}

		
		return (new DepthFirstSearch(graph)).dfs2(graph.getNode(src), dest);
	}
	

	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {


		
		return null;
	}


	public static boolean isHamiltonianPath(Graph g, List<String> values) {
		if (g == null || values == null || values.isEmpty()) {
			return false;
		}
		
		String firstValue = values.get(0);
		Node firstNode = g.getNode(firstValue);
		
		if (!g.containsNode(firstNode)) {
			return false;
		}
		
		if (values.size()-1 != g.getAllNodes().size()) {
			return false;
		}
		
		Node lastNode = firstNode;
		for (int i = 1; i < values.size(); i++) {
			String value = values.get(i);
			Set<Node> nodes = g.getNodeNeighbors(lastNode);
			Node selectedNode = getNextNodeWithValue(nodes, value);
			if (selectedNode == null) {
				return false;
			} else {
				lastNode = selectedNode;
			} 
		}

		return (lastNode).equals(firstNode);
	}


	private static Node getNextNodeWithValue(Set<Node> nodes, String value) {
		Node result = null;
		
		for (Node node : nodes) {
			if (node.element.equals(value)) {
				result = node;
			}
		}
		
		return result;
	}
	
}
