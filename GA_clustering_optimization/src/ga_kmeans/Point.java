package ga_kmeans;

public class Point {
	private int x,y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public double getDistance(Point p1, Point p2){
		return Math.sqrt((p2.getX()-p2.getX())*(p2.getX()-p2.getX())+(p2.getY()-p1.getY())*(p2.getY()-p1.getY()));
	}
	
	@Override
	public String toString(){
		return "("+this.x+","+this.y+")";
	}
}