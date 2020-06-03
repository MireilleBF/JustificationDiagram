package justificationDiagram;

import export.*;
import models.Node;
import models.Relation;
import models.Visitable;

import java.util.*;

public class JustificationDiagram implements Visitable {
    public HashMap<String, Node> nodes;
    public Set<Relation> relations;

    public JustificationDiagram() {
        nodes = new HashMap<>();
        relations = new HashSet<>();
    }
    
    public JustificationDiagram(HashMap<String,Node> nodes, Set<Relation> relations) {
        this.nodes = nodes;
        this.relations = relations;
    }

    @Override
    public void accept(JDVisitor visitor) {
        visitor.visitDiagram(this);
    }
     
    /**
     * Change the state of the node in fonction of there childrens.
     * Uses a TopologicalSort but does not change the order of the nodes in the diagram (to avoid problems).
     * call Node.analyseRelation();
     */
    public void analysesDiagrammeRelation() {
    	
    	TopologicalSort sort = new TopologicalSort(this);
                
        for (Node node : sort.getOrder()) {
            node.analyseRelation();
        } 


    }

}
