package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import gamestates.Playing;

public class Tutorial {
	
	private Playing playing;
	private Font arial_20;
	
	public Tutorial(Playing playing) {
		this.playing = playing;
		arial_20 = new Font("Arial", Font.PLAIN, 20);
	}
	
	public void drawText(Graphics g, int xLvlOffset) {
		
		g.setFont(arial_20);
		g.setColor(Color.BLACK);
		g.drawString("Welcome Player",0 - xLvlOffset, 100);
		g.drawString("Find and defeat the boss", 0 - xLvlOffset, 120);
		g.drawString("A: left", 100 - xLvlOffset, 140);
		g.drawString("D: right", 100 - xLvlOffset, 160);
		g.drawString("W: Jump", 100 - xLvlOffset, 180);
		g.drawString("J: Attack", 100 - xLvlOffset, 200);
		g.drawString("Hold J: ComboAttack", 100 - xLvlOffset, 220);
		g.drawString("L:Rush", 100 - xLvlOffset, 240);
		g.drawString("I:UntiSlashhh", 100 - xLvlOffset, 260);
		
		
	}
	
	public void drawTextBoss(Graphics g, int xLvlOffset) {
		
		g.setFont(arial_20);
		g.setColor(Color.BLACK);
		g.drawString("Cannot use unti on Boss",250 - xLvlOffset, 100);
		g.drawString("Boss can automatically heal when it not chasing",250 - xLvlOffset, 140);
	}

}
