package blockgame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import blockgame.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			
			// Play button
			if(mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemies();
	            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));	
			}
			
			// Help button
			if(mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}
			
			// Exit button
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}
		}

		// Back button (from Help)
				if(game.gameState == STATE.Help) {
					if(mouseOver(mx, my, 210, 350, 200, 64)) {
						game.gameState = STATE.Menu;
						return;
					}
				}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my  > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.DARK_GRAY);
			g.drawString("Menu", 240, 70);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 275, 190);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 275, 290);
		
			g.drawRect(210, 350, 200, 64);
			g.drawString("Exit", 275, 390);
		}else if(game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.DARK_GRAY);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt3);
			g.drawString("Use W, S, A and D buttons to move the player (the blue square).", 20, 150);
			g.drawString("Dodge the enemies. You get points as long as you stay alive.", 30, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 275, 390);
		}
		
		
	}

}
