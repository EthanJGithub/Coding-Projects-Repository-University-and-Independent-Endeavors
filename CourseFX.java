package assignment;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class CourseFX extends Application {
	private Scene scene = new Scene(new Pane(), 800, 600);
	
    @Override
    public void start(Stage stage) {
    	Course course = generateCourse();
    	//creating a label for the title
    	showStudents(course);
        stage.setScene(scene);
        //window title
        stage.show();
    }
    
    /**
     * This method should set the scene that displays all student and course information
	 * @param course course
	 */
    public void showStudents(Course course) {
    	Text text = new Text(230, 55, "Course Students");
    	text.setFont(Font.font("Tahoma", 50));
    	text.setFill(Color.DARKBLUE);
    	DropShadow ds = new DropShadow();
    	ds.setOffsetY(.5f);
    	ds.setColor(Color.color(0.1f, 0.1f, 0.1f));
    	text.setEffect(ds);
    	Pane canvas = new Pane();
    	canvas.setStyle("-fx-background-color: lightblue");
    	double total = 0;
    	for(int i = 0; i < course.getAllStudents().size(); i++) { 
    	Student student = course.getAllStudents().get(i);
    	total += student.computeMyAverage();
    	Button stu1 = new Button(student.getStuName() + " | Average Score: " + Math.round(student.computeMyAverage()*100)/100.00);
    	stu1.setOnAction(value ->  
		{
			showScores(student, course);
	    });
    	stu1.setTextFill(Color.CADETBLUE);
    	stu1.setScaleX(1.5);
    	stu1.setScaleY(2);
    	stu1.setTextAlignment(TextAlignment.CENTER);
    	canvas.getChildren().add(stu1);
    	stu1.setTranslateX(330);
    	// to translate each button you must use * i 
    	stu1.setTranslateY(120 + (80 * i));
    		}
     
    	Text text1 = new Text(240, 580, "Average Course Score: " + Math.round((total  / course.getAllStudents().size()) *100)/100.00);
    	text1.setFont(Font.font("Impact", 30));
    	text1.setEffect(ds);
    	text1.setFill(Color.CORNFLOWERBLUE);
    	
    	GridPane grid = new GridPane();
    	grid.setAlignment(Pos.CENTER_LEFT);
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(100,25,100,25));
    	Button submit = new Button ("Submit");
    	submit.setTextFill(Color.BLACK);
    	submit.setFont(Font.font("Elephant"));
    	submit.setTranslateX(100);
    	submit.setTranslateY(180);
    	canvas.getChildren().addAll(text, text1, grid, submit);
    	
    	Label Name = new Label("Name:");
    	Name.setTextFill(Color.DARKCYAN);
    	grid.add(Name, 0, 1);
    	TextField userTextField = new TextField();
    	grid.add(userTextField, 1, 1);
    	Label pw = new Label("ID:");
    	pw.setTextFill(Color.MAGENTA);
    	grid.add(pw, 0, 2);
    	TextField ID = new TextField();
    	grid.add(ID, 1, 2);
    	
    	
    	submit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {	
				if (!userTextField.getText().equals("") && !ID.getText().equals("")) {
				String name = userTextField.getText();
				int id = Integer.parseInt(ID.getText());
				Student newstudent = new Student(name, id);

	        	Score[][] scores = {{new Score("Quiz 1", 80), new Score("Homework 1", 95), new Score("Test 1", 67)},
						{new Score("Quiz 1", 0), new Score("Homework 1", 0), new Score("Test 1", 0)},
						{new Score("Quiz 1", 0), new Score("Homework 1", 0), new Score("Test 1", 0)},
						{new Score("Quiz 1", 0), new Score("Homework 1", 0), new Score("Test 1", 0)}};
	        	
	        	for (int i = 0; i < 4; i++) {
	        		newstudent.setAllMyScores(scores[i]);
	        	}
				course.addStudent(newstudent);
				double total = 0;
		    	for(int i = 0; i < course.getAllStudents().size(); i++) { 
		    	Student student = course.getAllStudents().get(i);
		    	total += student.computeMyAverage();
		    	Button stu1 = new Button(student.getStuName() + " | Average Score: " + Math.round(student.computeMyAverage()*100)/100.00);
		    	stu1.setScaleX(1.5);
		    	stu1.setScaleY(2);
		    	stu1.setTextAlignment(TextAlignment.CENTER);
		    	canvas.getChildren().add(stu1);
		    	stu1.setTranslateX(330);
		    	stu1.setTranslateY(120 + (80 * i));
		    	Text text1 = new Text(270, 500, "Average Course Score: " + Math.round((total  / course.getAllStudents().size()) *100)/100.00);
		    	text1.setFont(Font.font("Impact", 25));
		    	text1.setFill(Color.CORNFLOWERBLUE);
		    	stu1.setOnAction(value ->  
				{
					showScores(student, course);
			    });
		    		}
		    	
				}
				
		    	
		    	
			}
    	});
    	
        scene.setRoot(canvas);
        
        
    }
    
    /**
     * This method should set the scene that displays a student and their score information
	 * @param student student in the course
	 * @param course course
	 */
    public void showScores(Student stu, Course course) {
    	GridPane grid = new GridPane();
    	grid.setAlignment(Pos.BOTTOM_LEFT);
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(240,25,240,25));
    	Button submit = new Button ("Submit");
    	submit.setFont(Font.font("Elephant"));
    	submit.setTranslateX(110);
    	submit.setTranslateY(320);	
    	Label Name = new Label("Name:");
    	Name.setTextFill(Color.DARKCYAN);
    	grid.add(Name, 0, 1);
    	TextField userTextField = new TextField();
    	grid.add(userTextField, 1, 1);
    	Label pw = new Label("Value:");
    	pw.setTextFill(Color.MAGENTA);
    	grid.add(pw, 0, 2);
    	TextField Value = new TextField();
    	grid.add(Value, 1, 2);
    	
    	
    	Pane canvas = new Pane();
    	canvas.setStyle("-fx-background-color: skyblue");
    	scene.setRoot(canvas);
        Text title = new Text(stu.getStuName() + " | ID: " + stu.getId());
        title.setFill(Color.DARKBLUE);
        title.relocate(350, 20);
        title.setScaleX(2);
        title.setScaleY(2);
        for(int i = 0; i< stu.getAllMyScores().length; i++) {
        	Score score = stu.getAllMyScores()[i];
        	Text Score = new Text(score.getScoreName() + " : " +score.getScoreValue() + "%");
        	Score.setFill(Color.ORANGERED);
        	Score.setScaleX(2);
        	Score.setScaleY(2);
        	Score.setTranslateX(350);
        	Score.setTranslateY(180 + (50 * i));
        	canvas.getChildren().add(Score);
        	TextField update = new TextField();
            update.setScaleX(.5);
            update.setScaleY(.5);
            update.setTranslateX(330);
            update.setTranslateY(185 + (50 * i));
            Button updatepress = new Button("Update");
            updatepress.setTextFill(Color.DEEPSKYBLUE);
            updatepress.setScaleX(.8);
            updatepress.setScaleY(.8);
            updatepress.setTranslateX(310);
            updatepress.setTranslateY(185+ (50 * i));

            canvas.getChildren().addAll(update,updatepress);
            updatepress.setOnAction(new EventHandler<ActionEvent>() {
         	   public void handle(ActionEvent ae) {
         		   if(!update.getText().equals("")){
         			   score.setScoreValue(Float.parseFloat(update.getText()));
         			   Score.setText(score.getScoreName() + " : " + score.getScoreValue() + "%");
         			  
         		   }
         	   }
            });
        	 
        }
      Button back = new Button("Back");
      back.setScaleX(1.5);
      back.setScaleY(1.5);
      back.relocate(370, 550);
      back.setOnAction(new EventHandler<ActionEvent>() {
    	  public void handle(ActionEvent ae) {
    		  showStudents(course);
    	  }
      });
        canvas.getChildren().addAll(title, grid, submit, back);
        
        submit.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent ae) {
				String assignment = userTextField.getText();
				int grade = Integer.parseInt(Value.getText());
				Text newgrade = new Text(assignment + " : " + grade + "%");
				Score score = new Score(assignment, grade);
				stu.AddScore(score);
				TextField update = new TextField();
	            update.setScaleX(.5);
	            update.setScaleY(.5);
	            update.setTranslateX(330);
	            update.setTranslateY(185 + (50 * 3));
	            Button updatepress = new Button("Update");
	            updatepress.setScaleX(.8);
	            updatepress.setScaleY(.8);
	            updatepress.setTranslateX(310);
	            updatepress.setTranslateY(185+ (50 * 3));
				
				newgrade.setScaleX(2);
				newgrade.setScaleY(2);
				newgrade.relocate (350, 310);
				canvas.getChildren().addAll(newgrade, update, updatepress);
    		}
    	});
      
      
    }
    
    /**
	 * @return course to be displayed
	 */
    public Course generateCourse() {
    	Score[][] scores = {{new Score("Quiz 1", 80), new Score("Homework 1", 95), new Score("Test 1", 67)},
    						{new Score("Quiz 1", 80), new Score("Homework 1", 95), new Score("Test 1", 67)},
    						{new Score("Quiz 1", 80), new Score("Homework 1", 95), new Score("Test 1", 67)},
    						{new Score("Quiz 1", 80), new Score("Homework 1", 95), new Score("Test 1", 67)}};
    	ArrayList<Student> students = new ArrayList<Student>(Arrays.asList(new Student("Leonardo", 1), new Student("Michelangelo", 2), new Student("Donatello", 3), new Student("Raphael", 4)));
    	
    	for (int i = 0; i < 4; i++) {
    		students.get(i).setAllMyScores(scores[i]);
    	}
    	
    	Course course = new Course();
    	course.setAllStudents(students);

    	return course;
    }

    public static void main(String[] args) {
        launch();
    }
}