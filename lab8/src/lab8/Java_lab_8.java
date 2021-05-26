package lab8;

import javafx.application.Application;
import javafx.event.EventHandler; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.canvas.Canvas; 
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle; 
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Java_lab_8 extends Application {

	double size_cleen = 40;
	
	public static void main(String[] args) { 
		 launch(args); 
	}
		 /** 
		 * Сбрасывает холст в его первоначальный вид, заполняя полотно 
		 */ 
	private void reset(Canvas canvas) { 
		GraphicsContext gc = canvas.getGraphicsContext2D();
		drawHeart(gc);
		drawRadialGradient(gc, Color.BLACK, Color.BLACK);
		drawLinearGradient(gc, Color.WHITE, Color.WHITE);
	}
	
	@Override 
	public void start(Stage primaryStage) {
		 primaryStage.setTitle("PaintApp");
		 Group root = new Group();


		 // Задний фон
		 Rectangle rect = new Rectangle(800, 800, Color.PINK);
		 root.getChildren().add(rect);

		 // Передний фон
		 final Canvas canvas = new Canvas(800, 800);
		 // canvas.setTranslateX(0);
		 // canvas.setTranslateY(0);

		 reset(canvas);

		 final GraphicsContext gc = canvas.getGraphicsContext2D();

		 // Удалние переднего фона при нажатии ЛКМ и перемещении по холсту
		 canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			 @Override
			 public void handle(MouseEvent e) {
				 canvas.setOnScroll((ScrollEvent event)->{
					 double clean_size = event.getDeltaY();

					 if(clean_size < 0) {
						 size_cleen += 5;
					 }
					 else {
						 size_cleen -= 5;
					 }

					 if(size_cleen < 5) {
						 size_cleen = 5;
					 }
					 else if(size_cleen > 100)
					 {
						 size_cleen = 100;
					 }

					 System.out.println(size_cleen);
				 });
				 gc.clearRect(e.getX() - 1, e.getY() - 1, size_cleen, size_cleen);
			 }
		 });

		 // Возвращение переднего фона при 3 клике
		 canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			 @Override
			 public void handle(MouseEvent t) {
				 if (t.getClickCount() == 3) {
					 reset(canvas);
				 }
			 }
		 });

		 // Добавление холста к сцене и отражение сцены
		 root.getChildren().add(canvas);
		 primaryStage.setScene(new Scene(root, 800, 800));
		 primaryStage.show();
	}

	private void drawHeart(GraphicsContext gc) {
		gc.beginPath();

		gc.moveTo(200, 600);
		gc.lineTo(500, 200);
		gc.lineTo(200, 200);

		gc.lineTo(600, 300);
		gc.lineTo(600, 600);
		gc.lineTo(500, 700);
		gc.lineTo(500, 600);
		gc.lineTo(300, 600);
		gc.lineTo(300, 700);

		gc.closePath();
}
	/**
	* Радиальный градиетн
	*/
	private void drawRadialGradient(GraphicsContext gc , Color firstColor, Color lastColor) {
		gc.setFill(new RadialGradient(0, 0, 0.5, 0.5, 0.1, true,
		CycleMethod.REFLECT,
		new Stop(0.0, firstColor),
		new Stop(1.0, lastColor)));
		gc.fill();
	}

	/**
	* Линейный градиент
	*/
	private void drawLinearGradient(GraphicsContext gc, Color firstColor, Color secondColor) {
		LinearGradient lg = new LinearGradient(0, 0, 1, 1, true,
		CycleMethod.REFLECT,
		new Stop(0.0, firstColor),
		new Stop(1.0, secondColor));
		gc.setStroke(lg);
		gc.setLineWidth(10);
		gc.stroke();
	}
}
