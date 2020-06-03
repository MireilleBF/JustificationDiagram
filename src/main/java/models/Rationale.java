package models;

import java.util.ArrayList;

import export.*;

public class Rationale extends Node {

    public Rationale(String alias, String label,ArrayList<String> realizationResult) {
        super(alias, label,realizationResult);
        setState(); 
    }

    public void accept(JDVisitor JDVisitor) {
        JDVisitor.visitRationale(this);
    }
    
    /**
     * Verifies that all necessary files are present and 
     * analyse the list 'realizationList' and set the correct State for this node
     * 
     */
    public void setState() {    	
    	boolean isDone = true;
		// used to verify that the necessary files are present.  
		if(!checkFile.isEmpty() && isDone) {
			isDone = checkFileAnalyses();
		}
		// used to check the number of files in a repertory
		if(!checkFileWithNumber.isEmpty() && isDone) {
			isDone = CheckFileWithNumberAnalyses();
		}
	
		if(realizationList != null && realizationList.contains(label) && isDone) {
			this.state = State.DONE;
		}
		else {
			this.state = State.TODO;
		}
    }
}
