package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {

	public static final String PLAYER_ATLAS = "playersheet.png";
	public static final String LEVEL_ATLAS = "tiles.png";
	public static final String level1 = "level1.png";

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
		int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
		BufferedImage img = GetSpriteAtlas(level1);

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