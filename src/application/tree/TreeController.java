package application.tree;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TreeController implements Initializable {
	
	@FXML
	private TreeView<String> treeView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		treeView.setRoot(new TreeItem<String>("Root"));
		TreeItem<String> parent1 = new TreeItem<String>("Parent1");
		TreeItem<String> parent2 = new TreeItem<String>("Parent2");
		TreeItem<String> parent3 = new TreeItem<String>("Parent3");
		treeView.getRoot().getChildren().addAll(parent1,parent2,parent3);
		parent1.getChildren().addAll(new TreeItem<String>("Child1"),new TreeItem<String>("Child2"));
		parent2.getChildren().addAll(new TreeItem<String>("Child3"),new TreeItem<String>("Child4"));
		parent3.getChildren().addAll(new TreeItem<String>("Child5"),new TreeItem<String>("Child6"));
		treeView.getRoot().setExpanded(true);
		
		
	}

}
