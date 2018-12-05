package ga_kmeans;

public class Point {
	private double x,y;
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setY(double y){
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
