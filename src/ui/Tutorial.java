package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import gamestates.Playing;
import main.Game;

public class Tutorial {
	
	private Playing playing;
	
	public Tutorial(Playing playing) {
		this.playing = playing;
		
	}
	
	public void drawText(Graphics g, int xLvlOffset) {
		
		g.setFont(new Font("Arial", Font.PLAIN, 20 * (int)Game.SCALE));
		g.setColor(Color.BLACK);
		g.drawString("Welcome Player",0 - xLvlOffset, 100 * (int)Game.SCALE);
		g.drawString("Find and defeat the boss", 0 - xLvlOffset, 120 * (int)Game.SCALE);
		g.drawString("A: left", 100 - xLvlOffset, 140 * (int)Game.SCALE);
		g.drawString("D: right", 100 - xLvlOffset, 160 * (int)Game.SCALE);
		g.drawString("W: Jump", 100 - xLvlOffset, 180 * (int)Game.SCALE);
		g.drawString("J: Attack", 100 - xLvlOffset, 200 * (int)Game.SCALE);
		g.drawString("Hold J: ComboAttack", 100 - xLvlOffset, 220 * (int)Game.SCALE);
		g.drawString("L:Rush", 100 - xLvlOffset, 240 * (int)Game.SCALE);
		g.drawString("I:UntiSlashhh", 100 - xLvlOffset, 260 * (int)Game.SCALE);
		
		
	}
	
	public void drawTextBoss(Graphics g, int xLvlOffset) {
		
		g.setFont(new Font("Arial", Font.PLAIN, 20 * (int)Game.SCALE));
		g.setColor(Color.BLACK);
		g.drawString("Cannot use unti on Boss",350 - xLvlOffset, 100 * (int)Game.SCALE);
		g.drawString("Boss can automatically heal when it not chasing",350 - xLvlOffset, 140 * (int)Game.SCALE);
	}

}
