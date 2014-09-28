import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


public class Snake{
	Field f;
	private Node head = null;
	private Node tail = null;
	private int size = 0;
	private Node n = new Node(10,11,Direction.r);
	
	public Snake(Field f){
		head = n;
		tail = n;
		size = 1;
		this.f = f;
	}
	
	
	public void draw(Graphics g){
		if(size <= 0) return;
		move();
		checkdeath();
		for(Node n= head; n!= null; n = n.next){
			n.draw(g);
		}
		
	}
	
	private void checkdeath() {
		if(head.clo<1|| head.row<4|| head.row>Field.row||head.clo>Field.column-2){
			f.changedeath();
		}
		for(Node n= head.next; n!= null; n = n.next){
			if(n.clo == head.clo&&n.row== head.row)
				f.changedeath();
		}
		
	}


	public void move(){
		addtohead();
		deletetail();
	}
	private void deletetail() {
		if(size == 0) return;
		tail = tail.before;
		tail.next =null;
	}
	public class Node{
		int l = Field.gridwidth;
		int w = Field.gridwidth;
		int row, clo;
		Direction dir;
		Node next = null;
		Node before = null;
		
		public Node(int row, int clo, Direction dir) {
			this.row = row;
			this.clo = clo;
			this.dir = dir;
		}

		void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.blue);
			g.fillRect(l*clo, w*row, l, w);
			g.setColor(c);
		}
		
	}
	
	public void eat(Egg e){
		if(this.touch().intersects(e.touch())){
			e.reappear();
			this.addtohead();
			f.setScore(f.getScore()+5);
		}
	}
	
	
	public Rectangle touch(){
		return new Rectangle(head.l*head.clo, head.w*head.row, head.l, head.w);
	}
	/*public void addtotail(){
		Node n = null;
		switch(tail.dir){
			case l:
				n = new Node(tail.row,tail.clo +1,tail.dir);
				break;
			case r:
				n = new Node(tail.row,tail.clo - 1 ,tail.dir);
				break;
			case u:
				n = new Node(tail.row + 1,tail.clo,tail.dir);
				break;
			case d:
				n = new Node(tail.row - 1,tail.clo,tail.dir);
				break;
		}
		tail.next = n;
		n.before = tail;
		tail = n;
		size ++;
	}*/
	
	public void addtohead(){
		Node n = null;
		switch(head.dir){
			case l:
				n = new Node(head.row,head.clo - 1,head.dir);
				break;
			case r:
				n = new Node(head.row,head.clo + 1 ,head.dir);
				break;
			case u:
				n = new Node(head.row - 1,head.clo,head.dir);
				break;
			case d:
				n = new Node(head.row + 1,head.clo,head.dir);
				break;
		}
		n.next = head;
		head.before = n;
		head = n;
		size ++;
	}
	
	public void keyPressed(KeyEvent k){
		int e = k.getKeyCode();
		switch(e){
			case KeyEvent.VK_LEFT:
				if(head.dir != Direction.r)
				head.dir = Direction.l;
				break;
			case KeyEvent.VK_RIGHT:
				if(head.dir != Direction.l)
				head.dir = Direction.r;
				break;
			case KeyEvent.VK_UP:
				if(head.dir != Direction.d)
				head.dir = Direction.u;
				break;
			case KeyEvent.VK_DOWN:
				if(head.dir != Direction.u)
				head.dir = Direction.d;
				break;
		}
	}
}

























