package levels;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Snail;
import entities.Bee;
import entities.Boar;
import entities.Boss;
import main.Game;
import objects.Cannon;
import objects.GameContainer;
import objects.Potion;
import objects.Spike;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.ObjectConstants.*;

public class Level {

	private BufferedImage img;
	private int[][] lvlData;

	private ArrayList<Snail> snails = new ArrayList<>();
	private ArrayList<Bee> bees = new ArrayList<>();
	private ArrayList<Boar> boars = new ArrayList<>();
	private ArrayList<Boss> bosses = new ArrayList<>();
	private ArrayList<Potion> potions = new ArrayList<>();
	private ArrayList<Spike> spikes = new ArrayList<>();
	private ArrayList<GameContainer> containers = new ArrayList<>();
	private ArrayList<Cannon> cannons = new ArrayList<>();
	
	private int lvlTilesWide;
	private int maxTilesOffset;
	private int maxLvlOffsetX;
	private Point playerSpawn;
	
	
	public Level(BufferedImage img) {
		this.img = img;
		lvlData = new int[img.getHeight()][img.getWidth()];
		loadLevel();
		calcLvlOffsets();
	}

	private void loadLevel() {

		for (int y = 0; y < img.getHeight(); y++)
			for (int x = 0; x < img.getWidth(); x++) {
				Color c = new Color(img.getRGB(x, y));
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();

				loadLevelData(red, x, y);
				loadEntities(green, x, y);
				loadObjects(blue, x, y);
			}
	}
	
	private void loadLevelData(int redValue, int x, int y) {
		if (redValue >= 48)
			lvlData[y][x] = 0;
		else
			lvlData[y][x] = redValue;
	}
	
	private void loadEntities(int greenValue, int x, int y) {
		switch (greenValue) {
		case SNAIL: 
			snails.add(new Snail(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
			break;
		case BEE: 
			bees.add(new Bee(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
			break;
		case BOAR:
			boars.add(new Boar(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
			break;
		case BOSS:
			bosses.add(new Boss(x * Game.TILES_SIZE, y * Game.TILES_SIZE));
			break;
		case 100: 
			playerSpawn = new Point(x * Game.TILES_SIZE, y * Game.TILES_SIZE);
			break;
		}
	}
	
	private void loadObjects(int blueValue, int x, int y) {
		switch (blueValue) {
		case RED_POTION:  
		case BLUE_POTION: potions.add(new Potion(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
			break;
		case BOX: 
		case BARREL: containers.add(new GameContainer(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
			break;
		case SPIKE:
			spikes.add(new Spike(x * Game.TILES_SIZE, y * Game.TILES_SIZE, SPIKE));
			break;
		case CANNON_LEFT:
		case CANNON_RIGHT: 
			cannons.add(new Cannon(x * Game.TILES_SIZE, y * Game.TILES_SIZE, blueValue));
			break;
		}
	}
	
	private void calcLvlOffsets() {
		lvlTilesWide = img.getWidth();
		maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
		maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
	}
	
	public Level(int[][] lvlData) {
		this.lvlData = lvlData;
	}

	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}
	
	public int[][] getLevelData() {
		return lvlData;
	}
	
	public Point getPlayerSpawn() {
		return playerSpawn;
	}
	
	public ArrayList<Boss> getBosses() {
		return bosses;
	}
	
	public int getLvlOffset() {
		return maxLvlOffsetX;
	}

	public ArrayList<Snail> getSnails() {
		return snails;
	}
	
	public ArrayList<Bee> getBees() {
		return bees;
	}
	
	public ArrayList<Boar> getBoars() {
		return boars;
	}
	
	public ArrayList<Potion> getPotions() {
		return potions;
	}

	public ArrayList<GameContainer> getContainers() {
		return containers;
	}
	
	public ArrayList<Spike> getSpikes() {
		return spikes;
	}
	
	public ArrayList<Cannon> getCannons(){
		return cannons;
	}
	
}