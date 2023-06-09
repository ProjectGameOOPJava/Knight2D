package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

public class Menu extends State implements Statemethods {
	
	private MenuButton[] buttons = new MenuButton[3];
	private BufferedImage backgroundImg;
	private int menuX, menuY, menuWidth, menuHeight;
	
	public Menu(Game game) {
		super(game);
		loadButtons();
		loadBackground();

	}
	
	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
		menuWidth = Game.GAME_WIDTH;
		menuHeight = Game.GAME_HEIGHT ;
		menuX = 0;
		menuY = 0;

	}
	
	private void loadButtons() {
		buttons[0] = new MenuButton((int)Game.GAME_WIDTH / 2  , (int) (100 * Game.SCALE), 0, Gamestate.PLAYING);
		buttons[1] = new MenuButton((int)Game.GAME_WIDTH / 2  , (int) (170 * Game.SCALE), 1, Gamestate.OPTIONS);
		buttons[2] = new MenuButton((int)Game.GAME_WIDTH / 2  , (int) (240 * Game.SCALE), 2, Gamestate.QUIT);
	}
	
	@Override
	public void update() {
		
		for (MenuButton mb : buttons)
			mb.update();
	}

	@Override
	public void draw(Graphics g) {

		g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);

		for (MenuButton mb : buttons)
			mb.draw(g);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				mb.setMousePressed(true);
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		for (MenuButton mb : buttons) {
			if (isIn(e, mb)) {
				if (mb.isMousePressed())
					mb.applyGamestate();
				if (mb.getState() == Gamestate.PLAYING)
					game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLevelIndex());
				break;
			}
		}

		resetButtons();

	}
	
	private void resetButtons() {
		for (MenuButton mb : buttons)
			mb.resetBools();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for (MenuButton mb : buttons)
			mb.setMouseOver(false);

		for (MenuButton mb : buttons)
			if (isIn(e, mb)) {
				mb.setMouseOver(true);
				break;
			}

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		

	}

}