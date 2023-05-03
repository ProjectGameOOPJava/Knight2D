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
	public static final String SNAIL_SPRITE = "snailsheet.png";
	public static final String STATUS_BAR = "health_power_bar.png";
	public static final String COMPLETED_IMG = "completed_sprite.png";

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

		List<String> levelNames = Arrays.asList("1.png", "2.png", "3.png");
	
		BufferedImage[] imgs = new BufferedImage[levelNames.size()];
	
		for(int i = 0; i < imgs.length; i++) {
			
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

