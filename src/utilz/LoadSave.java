package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class LoadSave {

	public static final String PLAYER_ATLAS = "playersheet.png";
	public static final String LEVEL_ATLAS = "tiles.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menuKnight3072x1024.jpg";
	public static final String PAUSE_BACKGROUND = "pause_menu.png";
	public static final String SOUND_BUTTONS = "sound_button.png";
	public static final String URM_BUTTONS = "urm_buttons.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
	public static final String PLAYING_BG_IMG = "background_playing.jpg";
	public static final String SNAIL_ATLAS = "snailsheet.png";
	public static final String BEE_ATLAS = "beesheet.png";
	public static final String BOAR_ATLAS = "boarsheet.png";
	public static final String BOSS_ATLAS = "bosssheet.png";
	public static final String STATUS_BAR = "health_power_bar.png";
	public static final String COMPLETED_IMG = "completed_sprite.png";
	public static final String POTION_ATLAS = "potions_sprites.png";
	public static final String CONTAINER_ATLAS = "objects_sprites.png";
	public static final String TRAP_ATLAS = "trap_atlas.png";
	public static final String CANNON_ATLAS = "cannon_atlas.png";
	public static final String CANNON_BALL = "ball.png";
	public static final String DEATH_SCREEN = "death_screen.png";
	public static final String OPTIONS_MENU = "options_background.png";
	public static final String GAME_COMPLETED = "game_completed.png";
	public static final String SLASH = "unti.png";

	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		File f = new File("res/" + fileName);
		try {
			img = ImageIO.read(f);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	public static BufferedImage[] GetAllLevels() {

		List<String> levelNames = Arrays.asList("1.png", "2.png", "3.png","4.png");

		BufferedImage[] imgs = new BufferedImage[levelNames.size()];

		for (int i = 0; i < imgs.length; i++) {

			File f = new File("res/lvls/" + levelNames.get(i));

			try {
				imgs[i] = ImageIO.read(f);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return imgs;
	}
}
