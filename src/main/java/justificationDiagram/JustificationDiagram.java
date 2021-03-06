package justificationDiagram;

import export.*;
import models.ActionNode;
import models.Node;
import models.Relation;
import models.Visitable;

import java.util.*;
import java.util.Map.Entry;

public class JustificationDiagram implements Visitable {
    private Map<String, Node> nodes;
    private Set<Relation> relations;

    public JustificationDiagram() {
        setNodes(new HashMap<>());
        setRelations(new HashSet<>());
    }
    
    public JustificationDiagram(Map<String,Node> nodes, Set<Relation> relations) {
        this.setNodes(nodes);
        this.setRelations(relations);
    }

    @Override
    public void accept(JDVisitor visitor) {
        visitor.visitDiagram(this);
    }
    
    /**
     * Used to set the ActionNodes to the corresponding node.
     * 
     * @param actionNodes Map of information found in the action file with the label of the node and the ActionNodes
     */
    public void setActionNode(Map<String, ActionNode> actionNodes) {
   
    	if(actionNodes != null) {
    		    		
    		for (Entry<String, Node> node : getNodes().entrySet()) {
    			
    			String realLabel = node.getValue().getLabel();
    			realLabel = realLabel.substring(1,realLabel.length()-1);

    			if(actionNodes.containsKey(realLabel)) {
    	    	 	node.getValue().setActionNode(actionNodes.get(realLabel));
    			}	
            }
    	}
    	else {
    		System.err.println("ActionNodes is null, there is no information in this file.");
    	}
    }
     
    
    /**
     * Change the state of the node in fonction of there childrens.
     * Uses a TopologicalSort but does not change the order of the nodes in the diagram (to avoid problems).
     * call Node.analyseRelation();
     */
    public void analysesDiagrammeRelation(List<String> labelList) {
    	
    	TopologicalSort sort = new TopologicalSort(this);
                
        for (Node node : sort.getOrder()) {
            node.prerequisiteAnalysis(labelList);
        } 


    }

	public Set<Relation> getRelations() {
		return relations;
	}

	public void setRelations(Set<Relation> relations) {
		this.relations = relations;
	}

	public Map<String, Node> getNodes() {
		return nodes;
	}

	public void setNodes(Map<String, Node> nodes) {
		this.nodes = nodes;
	}

}
