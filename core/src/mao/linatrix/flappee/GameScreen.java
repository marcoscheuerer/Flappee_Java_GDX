package mao.linatrix.flappee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class GameScreen extends ScreenAdapter {
	
	private static final int WORLD_WIDTH = 480;
	private static final int WORLD_HEIGHT = 640;
	
	private Viewport viewport;
	private Camera camera;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	
	private Flappee flappee = new Flappee();
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
	
	@Override
	public void show() {
		camera = new OrthographicCamera();
		camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
		camera.update();
		
		viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		
		flappee.setPosition(WORLD_WIDTH / 4, WORLD_HEIGHT / 2);
	}
	
	@Override
	public void render(float delta) {
		clearScreen();
		
		batch.setProjectionMatrix(camera.projection);
		batch.setTransformMatrix(camera.view);
		
		batch.begin();
		
		batch.end();
		
		shapeRenderer.setProjectionMatrix(camera.projection);
		shapeRenderer.setTransformMatrix(camera.view);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		flappee.drawDebug(shapeRenderer);
		shapeRenderer.end();
		
		update(delta);
	}
	
	private void clearScreen() {
		Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
		Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
	}
	
	private void update(float delta) {
		flappee.update();
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
			flappee.flyUp();
		blockFlappeeLeavingTheWorld();
	}
	
	private void blockFlappeeLeavingTheWorld() {
		flappee.setPosition(flappee.getX(), MathUtils.clamp(flappee.getY(), 0,  WORLD_HEIGHT));
	}
	
}