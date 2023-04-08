package mao.linatrix.flappee;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Flappee {

	private static final float COLLISION_RADIUS = 24f;
	private final Circle collisionCircle;
	
	private float x = 0, y = 0;
	
	public Flappee() {
		collisionCircle = new Circle(x, y, COLLISION_RADIUS);
	}
	
	public void drawDebug(ShapeRenderer shapeRenderer) {
		shapeRenderer.circle(collisionCircle.x, collisionCircle.y, collisionCircle.radius);
	}
	
}
