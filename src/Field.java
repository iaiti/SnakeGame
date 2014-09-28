import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Field extends Frame {
	
	public static final int row = 30;
	public static final int column = 30;
	public static final int gridwidth = 15;
	public boolean gameover = false;
	Snake s = new Snake(this);
	Redraw redraw = new Redraw();
	public int score = 0;
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	Egg e = new Egg(22,22);
	Image cushion = null;
	
	public static void main(String[] args) {
		new Field().Struct();
	}
	
	public void Struct(){
		this.setBounds(400, 100, row*gridwidth, column*gridwidth);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}});
		this.addKeyListener(new Monitor());
		new Thread(redraw).start();
	}
	
	public void changedeath(){
		gameover = true;
	}
	public void update(Graphics g) {
		if(cushion == null){
			cushion = this.createImage(row*gridwidth, column*gridwidth);
		}
		Graphics getcushion = cushion.getGraphics();
		paint(getcushion);
		g.drawImage(cushion, 0,0,null);
	}
	
	public void paint(Graphics g){
	
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, column*gridwidth,row*gridwidth-5);
		
		
		g.setColor(c);g.setColor(Color.green);
		for (int i = 1; i < row; i++) {
			g.drawLine(0, gridwidth*i, column*gridwidth, gridwidth*i);
		}
		for (int i = 1; i < column; i++) {
			g.drawLine(gridwidth*i, 0,  gridwidth*i,column*gridwidth);
		}
		g.setColor(Color.red);
		g.drawString("Score = "+ score, 30, 70);
		
		if(gameover){
			g.setFont(new Font("ËÎÌו",Font.BOLD| Font.HANGING_BASELINE,40));
			g.drawString("press F2 to restart", 20,250);
			g.drawString("Fail ", 170, 200);
			redraw.pause();
		}
		g.setColor(c);
		s.draw(g);
		s.eat(e);
		e.draw(g);
	}
	
	public class Monitor extends KeyAdapter{
		public void keyPressed(KeyEvent k){
			int e = k.getKeyCode();
			switch(e){
				case KeyEvent.VK_F2:
					if(redraw.death){
					restart();}
			}
			s.keyPressed(k);
		}

		
	}
	
	private void restart() {
		new Thread(redraw).start();
			redraw.death = false;
			gameover = false;
			 s = new Snake(Field.this);
	}
		
	public class Redraw implements Runnable{
		private  boolean death = false;
		public void run() {
			while(!death){
				repaint();
				try{
					Thread.sleep(100);
				} catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		public void pause(){
			death = true;
		}
	}	
}
	
	