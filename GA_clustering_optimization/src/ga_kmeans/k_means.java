package ga_kmeans;

import java.util.ArrayList;
import java.util.Random;

public class k_means {
	private static int K=10;//The number of clusters
	private static int bound=50;
	private static ArrayList<Point> originCentres = new ArrayList<>();//the origin center of all points
	private static ArrayList<Point> allPoints = new ArrayList<>();//the points we want to cluster
	private static ArrayList<ArrayList<Point>> allIndexes = new ArrayList<ArrayList<Point>>();
	private static int maxtimes = 100;//the number of iteration
	
	public static double getDistance(Point p,Point q){
		return Math.sqrt(Math.abs(Math.pow(p.getX()-q.getX(), 2)+Math.pow(p.getY()-q.getY(), 2)));
	}
	
	public static void generatePoints(){
		//generate 100 points from 0 to 500
		for(int i=0;i<100;i++){
			Random rx = new Random();
			int x = rx.nextInt(bound);
			Random ry = new Random();
			int y = ry.nextInt(bound);
			Point p = new Point(x,y);
			allPoints.add(p);
		}
		System.out.print(allPoints);
		System.out.println();
	}
	
	public static void generateOriginCentre(ArrayList<Point> allpoints){
		for(int i=0;i<K;i++){
			int index = 3*i+1;
			if(index<50){
				originCentres.add(allpoints.get(index));
			}else{
				index = index-2;
				originCentres.add(allpoints.get(index));
			}
		}
		System.out.println(originCentres);
		//initiate the allIndexes:
		for(int k=0;k<originCentres.size();k++){
			ArrayList<Point> p = new ArrayList<Point>();
			p.add(originCentres.get(k));
			allIndexes.add(p);
		}
	}
	
	public static double calculateCluster(ArrayList<Point> centres){
		double sumDis = 0;
		for(int l=0;l<allPoints.size();l++){
			double[] diss = new double[K];
			for(int i=0;i<centres.size();i++){
				double dis = getDistance(allPoints.get(l),centres.get(i));
				diss[i]=dis;
			}
			//find the closest cluster and it's index
			double mindis = bound;
			int index = -1;
			for(int j=0;j<diss.length;j++){
				if(diss[j]<mindis){
					mindis = diss[j];
					index = j;
				}
			}
			allIndexes.get(index).remove(0);
			allIndexes.get(index).add(allPoints.get(l));
			sumDis += mindis;
		}
		System.out.println("The origin sum of all dis is: "+ sumDis);
		return sumDis;
	}
	
	public static ArrayList<Point> findNewCenter(ArrayList<ArrayList<Point>> allIndexe,ArrayList<Point> Centres){
		for(int i=0;i<allIndexe.size();i++){
			int xSum = 0,ySum = 0;
			for(int j=0;j<allIndexe.get(i).size();j++){
				xSum+=allIndexe.get(i).get(j).getX();
				ySum+=allIndexe.get(i).get(j).getY();
			}
			Centres.get(i).setX(xSum/allIndexe.get(i).size());
			Centres.get(i).setY(ySum/allIndexe.get(i).size());
		}
		System.out.println("Now the centers are: "+Centres);
		return Centres;
	}
	
	public static void recursionTillBreak(){
		double[] storeDis = new double[2];
		ArrayList<Point> newCentres = new ArrayList<Point>();
		storeDis[0] = calculateCluster(originCentres);
		for(int i=0;i<maxtimes;i++){
			newCentres = findNewCenter(allIndexes,originCentres);
			double newDis = calculateCluster(newCentres);
			storeDis[1] = storeDis[0];
			storeDis[0] = newDis;
			if(storeDis[0]==storeDis[1]){
				break;
			}
		}
		System.out.println(newCentres);
		System.out.println(allIndexes);
	}
	
	public static void main(String[] args){
		generatePoints();
		//get the origin center randomly;
		generateOriginCentre(allPoints);
		//recursion till it break
		recursionTillBreak();
	}
}
