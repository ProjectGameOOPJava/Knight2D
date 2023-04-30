package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {

	public static final String PLAYER_ATLAS = "playersheet.png";
	public static final String LEVEL_ATLAS = "tiles.png";
	public static final String level1 = "level_one_data_long.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menuKnight3072x1024.jpg";
	public static final String PAUSE_BACKGROUND = "pause_menu.png";
	public static final String SOUND_BUTTONS = "sound_button.png";
	public static final String URM_BUTTONS = "urm_buttons.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
	public static final String PLAYING_BG_IMG = "background_playing.jpg";
	
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

	public static int[][] GetLevelData() {
		
		BufferedImage img = GetSpriteAtlas(level1);
		int[][] lvlData = new int[img.getHeight()][img.getWidth()];

		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 48)
					value = 0;
				lvlData[j][i] = value;
			}
		return lvlData;

	}
}