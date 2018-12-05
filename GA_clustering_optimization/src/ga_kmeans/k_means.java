package ga_kmeans;

import java.util.ArrayList;
import java.util.Random;

public class k_means {
	private int K=10;//The number of clusters
	
	public static double getDistance(Point p,Point q){
		return Math.sqrt(Math.abs(Math.pow(p.getX()-q.getX(), 2)+Math.pow(p.getY()-q.getY(), 2)));
	}
	
	public ArrayList<Point> generatePoints(int bound){
		//generate 100 points from 0 to 500
		ArrayList<Point> allPoint = new ArrayList<>();
		for(int i=0;i<100;i++){
			Random rx = new Random();
			int x = rx.nextInt(bound);
			Random ry = new Random();
			int y = ry.nextInt(bound);
			Point p = new Point(x,y);
			allPoint.add(p);
		}
		System.out.print(allPoint);
		System.out.println();
		return allPoint;
	}
	
	public ArrayList<Point> generateOriginCentre(ArrayList<Point> allpoints){
		ArrayList<Point> originCenters = new ArrayList<Point>();
		for(int i=0;i<K;i++){
			int index = 3*i+1;
			if(index<50){
				originCenters.add(allpoints.get(index));
			}else{
				index = index-2;
				originCenters.add(allpoints.get(index));
			}
		}
		System.out.println(originCenters);
		return originCenters;
	}
	
	public ArrayList<ArrayList<Point>> calculateCluster(ArrayList<Point> centres, ArrayList<Point> allpoints){
		ArrayList<ArrayList<Point>> allIndexes = new ArrayList<ArrayList<Point>>();
		for(int s = 0;s<10;s++){
			ArrayList<Point> p = new ArrayList<>();
			allIndexes.add(p);
		}
		double sumDis = 0;
		for(int l=0;l<allpoints.size();l++){
			double[] diss = new double[K];
			for(int i=0;i<centres.size();i++){
				double dis = getDistance(allpoints.get(l),centres.get(i));
				diss[i]=dis;
			}
			//find the closest cluster and it's index
			double mindis = 50;
			int index = -1;
			for(int j=0;j<diss.length;j++){
				if(diss[j]<mindis){
					mindis = diss[j];
					index = j;
				}
			}
			allIndexes.get(index).add(allpoints.get(l));
			sumDis += mindis;
		}
		System.out.println(allIndexes);
		System.out.println("The sum of all dis is: "+ sumDis);
		return allIndexes;
	}
	
	public ArrayList<Point> findNewCenter(ArrayList<ArrayList<Point>> allIndexe,ArrayList<Point> Centres){
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
	
	public ArrayList<Point> recursionTillBreak(){
		k_means k = new k_means();
		ArrayList<Point> allPoint = k.generatePoints(50);
		ArrayList<Point> originCenter = k.generateOriginCentre(allPoint);
		//first iteration:
		ArrayList<ArrayList<Point>> allIndexes = calculateCluster(originCenter,allPoint);
		ArrayList<Point> newCentres = findNewCenter(allIndexes,originCenter);
		//end of first iteration
		int i;
		ArrayList<Point> temp = new ArrayList<>();
		ArrayList<Point> newtemp = new ArrayList<>();
		for(i=0;i<100;i++){
			allIndexes = calculateCluster(newCentres,allPoint);
			newCentres = findNewCenter(allIndexes,newCentres);
			newtemp = temp;
			temp = newCentres;
			if(temp.equals(newtemp)){
				break;
			}
		}
		System.out.println("End at No."+(i+2));
		System.out.println(newCentres);
		System.out.println(allIndexes);
		return newCentres;
	}
	
	public static void main(String[] args){
		k_means k = new k_means();
		k.recursionTillBreak();
	}
}
