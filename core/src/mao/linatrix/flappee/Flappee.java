package mao.linatrix.flappee;

import com.badlogic.gdx.Game;

public class Flappee extends Game {
	
	@Override
	public void create() {
		setScreen(new GameScreen());
	}
	
}