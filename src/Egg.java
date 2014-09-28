import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Egg {
	private static Random r = new Random();
	int l = Field.gridwidth;
	int w = Field.gridwidth;
	int row, clo;
	Snake s;
	private Color co =  Color.blue;
	
	public Egg(int row, int clo) {
			this.row = row;
			this.clo = clo;
	}
	
	public Egg(){
		this(r.nextInt(Field.row-3)+3,r.nextInt(Field.column-1)+1);
	}
	
	 public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(co);
		g.fillOval(l*clo, w*row, l, w);
		g.setColor(c);
		if(co == Color.blue) co =  Color.white;
		else co =  Color.blue;
		
	}
	
	public void reappear(){
		this.row = r.nextInt(Field.row-3)+3;
		this.clo = r.nextInt(Field.column-1)+1;
		//s.addtohead();
	}

	public Rectangle touch(){
		return new Rectangle(l*clo, w*row, l, w);
	}
}
