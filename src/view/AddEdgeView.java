package view;

import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Edge;
//import model.BFS;
import model.Graph;
import model.Vertex;

public class AddEdgeView extends BorderPane implements Observer {

	// model variable(s)
//	private BFS bfs;
	private Graph theGraph;
	// view variable(s)
	private GridPane gp;
	private Button button;
	private TextField textField1;
	private TextField textField2;
	private Label responseText;
	private Label startLabel;
	private Label endLabel;
	private Label functionHeader;
	// static variable(s)
	public static final int width = 800;
	public static final int height = 400;
	
	@Override
	public void update(Observable o, Object arg) {
		// update the graph
		theGraph = (Graph) o;
	}
	
	// constructor 
	public AddEdgeView (Graph g) {
		// set the graph
		theGraph = g;
		// init the BFS
//		bfs = new BFS(theGraph);
		// init grid pane
		gp = new GridPane();
		// set grid pane to center
		this.setCenter(gp);
		// init the pane
		initializePane();
	}
	
	// grid pane initializer
	private void initializePane() {
		// TODO: init grid pane variables like text fields, labels, buttons, etc.
		// create button
		button = new Button("Add");
		// create input text field
		textField1 = new TextField();
		textField2 = new TextField();
		// make the input text field editable
		textField1.setEditable(true);
		textField2.setEditable(true);
		// create response text
		responseText = new Label("Enter an Edge to add to the Graph");
		startLabel = new Label("Starting Vertex");
		endLabel = new Label("End Vertex");
		functionHeader = new Label("Add Edge");
		// set grid pane width & height
		gp.setPrefSize(width, height);
		// add button listener
		ButtonListener handler = new ButtonListener();
		// set the listener
		button.setOnAction(handler);
		// set position
		GridPane.setConstraints(functionHeader, 1, 1);
		GridPane.setConstraints(startLabel, 0, 2);
		GridPane.setConstraints(textField1, 1, 2);
		GridPane.setConstraints(endLabel, 2, 2);
		GridPane.setConstraints(textField2, 3, 2);
		GridPane.setConstraints(button, 4, 2);
		GridPane.setConstraints(responseText, 1, 3);
		gp.setHgap(10);
		gp.setVgap(10);
		gp.getChildren().addAll(functionHeader, startLabel, textField1, endLabel, textField2, button, responseText);
		
		// TODO: add any listeners
		
		
	}
	
	public class ButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			String text1 = textField1.getText();
			String text2 = textField2.getText();
			
			if (!text1.isEmpty() && !text2.isEmpty()) { // && (Integer.parseInt(text) < 11 && Integer.parseInt(text) > 0)
				Vertex startV = theGraph.find(Integer.parseInt(text1));
				Vertex endV = theGraph.find(Integer.parseInt(text2));
				if (startV.getVertexNumber() != -9999 && endV.getVertexNumber() != -9999) {
					Edge edge = theGraph.findEdge(startV,endV);
					if (edge.getWeight() == -9999) {
						edge.setWeight(1);
						theGraph.addEdge(edge);
						responseText.setText("Success! Edge ("+text1+"-->"+text2+") added!");
					}
					else 
						responseText.setText("Error! Edge already exists!");
				}
				else {
					if (startV.getVertexNumber() != -9999)
						responseText.setText("Error! Vertex (" + text1 + ") does not exist!");
					else if (endV.getVertexNumber() != -9999)
						responseText.setText("Error! Vertex (" + text2 + ") does not exist!");
				}
			} else {
				responseText.setText("Please enter a valid edge");
			}
			textField1.setText("");
			textField2.setText("");
		}
		
	}
	
}
