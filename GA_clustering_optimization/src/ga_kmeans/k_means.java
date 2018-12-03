package ga_kmeans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class k_means {
	private static int K=10;//The number of clusters
	private static int bound=50;
	private static ArrayList<Point> originCentres = new ArrayList<>();//the origin center of all points
	private static ArrayList<Point> allPoints = new ArrayList<>();//the points we want to cluster
	private static List<ArrayList<Point>> allIndexes = new ArrayList<ArrayList<Point>>();
	private static int maxtimes = 1000;//the number of iteration
	private double breakCondition;
	
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
	
	public static void calculateCluster(){
		double sumDis = 0;
		for(int l=0;l<allPoints.size();l++){
			double[] diss = new double[K];
			for(int i=0;i<originCentres.size();i++){
				double dis = getDistance(allPoints.get(l),originCentres.get(i));
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
	}
	
	public static void findNewCenter(){
		for(int i=0;i<allIndexes.size();i++){
			int xSum = 0,ySum = 0;
			for(int j=0;j<allIndexes.get(i).size();j++){
				xSum+=allIndexes.get(i).get(j).getX();
				ySum+=allIndexes.get(i).get(j).getY();
			}
			originCentres.get(i).setX(xSum/allIndexes.get(i).size());
			originCentres.get(i).setY(ySum/allIndexes.get(i).size());
		}
		System.out.println("Now the centers are: "+originCentres);
	}
	
	public static void main(String[] args){
		generatePoints();
		//get the origin center randomly;
		generateOriginCentre(allPoints);
		//calculate which cluster it should be in
		calculateCluster();
		//find the new Centres
		findNewCenter();
	}
}
