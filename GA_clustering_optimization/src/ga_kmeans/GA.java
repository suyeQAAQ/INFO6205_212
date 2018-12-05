package ga_kmeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class GA {
	private static ArrayList<Point> allpoints = generatePoints();;
	private static ArrayList<Point[]> popList = genePopulation();
	private static ArrayList<int[]> chromos = initPopulation(popList);
	
	public static double getDistance(Point p1, Point p2){
		return Math.sqrt((p2.getX()-p2.getX())*(p2.getX()-p2.getX())+(p2.getY()-p1.getY())*(p2.getY()-p1.getY()));
	}

	public static void main(String[] args){
		GA Tryer = new GA();
		//Iteration:
		for(int i=0;i<100000;i++){
			System.out.println(Tryer.calculateFitnessValueOfEachChromo(chromos.get(0), popList.get(0)));
			Tryer.select();
			Tryer.cross();
			Tryer.mutation();
			Tryer.decode(chromos);
		}
	}
	
	private double calculateFitnessValueOfEachChromo(int[] chromo, Point[] pop){
		double sumDis = 0;
		for(int i=0;i<allpoints.size();i++){
				double dis = getDistance(allpoints.get(i),pop[chromo[i]]);
				sumDis += dis;
		}
		return sumDis;
	}
	
	private int[] getPopIndex(){
		Map<Integer,Double> valuesort = new HashMap<>();
		for(int i=0;i<popList.size();i++){
			Point[] pop = popList.get(i);
			int[] chromo = chromos.get(i);
			double value = calculateFitnessValueOfEachChromo(chromo,pop);
			valuesort.put(i, value);
		}
		List<Map.Entry<Integer,Double>> list = new ArrayList<Map.Entry<Integer,Double>>(valuesort.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer,Double>>(){
			public int compare(Entry<Integer,Double> o1, Entry<Integer,Double> o2){
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		int[] newindex = new int[100];
		for(int k=0;k<popList.size();k++){
			double v = valuesort.get(k);
			for(Map.Entry<Integer,Double> mapping:list){
				if(mapping.getValue()==v){
					int newIndex = list.indexOf(mapping);
					newindex[k] = newIndex;
				}
			}
		}
//		for(Map.Entry<Integer,Double> mapping:list){
//			System.out.println(mapping);
//		}
//		for(int i:newindex){
//			System.out.println(i);
//		}
		
		return newindex;
	}
	
	private double getFitness(int index){
		double a = 0.001;
		return a*Math.pow((1-a), index);
	}

	private double[] calculateFitness() {
		// TODO Auto-generated method stub
		double[] shufflefits = new double[100];
		for(int i=0;i<100;i++){
			double fit = getFitness(i);
			shufflefits[i] = fit;
		}
		double S = 0.0;
		for(int j=0;j<shufflefits.length;j++){
			S += shufflefits[j];
		}
		
		double[] shufflecFits = new double[100];
		for(int k=0;k<100;k++){
			double cFit = 0;
			for(int l=0;l<=k;l++){
				cFit+=shufflefits[l];
			}
			cFit = cFit/S;
			shufflecFits[k] = cFit;
		}
		double[] cFits = new double[100];
		int[] newindex = getPopIndex();
		for(int i=0;i<100;i++){
			cFits[i] = shufflecFits[newindex[i]];
		}
		return cFits;
	}
	
	public void select(){
		double[] cFits = this.calculateFitness();
		ArrayList<int[]> midchromo = new ArrayList<int[]>();
		int count = 0;
		while(count<100){
			for(int i=0;i<100;i++){
				if(Math.random()<cFits[i]){
					midchromo.add(chromos.get(i));
					count++;
				}
			}
		}
		chromos = midchromo;
		this.decode(chromos);
		chromos = this.initPopulation(popList);
	}
	
	private void cross() {
		// TODO Auto-generated method stub
		ArrayList<Integer> label = new ArrayList<>();
		for(int i=0;i<100;i++){
			label.add(i);
		}
		Random r = new Random();
		while(label.size()!=0){
			int i1 = r.nextInt(label.size());
			int i2 = r.nextInt(label.size()); 
			if(i1!=i2){
				int label1 = label.get(i1);
				int label2 = label.get(i2);
				if(i1>i2){
					label.remove(i1);
					label.remove(i2);
				}else{
					label.remove(i2);
					label.remove(i1);
				}
				if(Math.random()<0.06){
					int crosspoint = (int)(Math.random()*100);
//					for(int k=crosspoint;k<100;k++){
//						int temp = chromos.get(label1)[k];
//						chromos.get(label1)[k] = chromos.get(label2)[k];
//						chromos.get(label2)[k] = temp;
//					}
					int temp = chromos.get(label1)[crosspoint];
					chromos.get(label1)[crosspoint] = chromos.get(label2)[crosspoint];
					chromos.get(label2)[crosspoint] = temp;
				}	
			}
		}
		this.decode(chromos);
		chromos = this.initPopulation(popList);//very important
	}

	private void mutation() {
		Random r = new Random();
		for(int[] a:chromos){
			for(int i=0;i<100;i++){
				if(Math.random()<0.01) {
//					if(a[i] == 9){
//						a[i]=0;
//					}else{
//						a[i]=a[i]+1;
//					}
					a[i] = r.nextInt(10);
					}
				}
			}
		this.decode(chromos);
		chromos = this.initPopulation(popList);
	}
	
	private static int getIndex(Point p, Point[] pop){
		double[] diss = new double[10];
		for(int i=0;i<10;i++){
			double dis = getDistance(p,pop[i]);
			diss[i] = dis;
		}
		double minDis = 100;
		int index = -1;
		for(int j=0;j<10;j++){
			if(diss[j]<minDis){
				minDis = diss[j];
				index = j;
			}
		}
		return index;
	}
	
	private static ArrayList<int[]> initPopulation(ArrayList<Point[]> pops){
		ArrayList<int[]> chromos = new ArrayList<>();
		for(int j=0;j<100;j++){
			int[] chromo= new int[100];
			for(int i=0;i<allpoints.size();i++){
				int index = getIndex(allpoints.get(i), pops.get(j));
				chromo[i]=index;
			}
			chromos.add(chromo);
		}
		return chromos;
	}
	
	private void decode(ArrayList<int[]> chromos){
		for(int i=0;i<100;i++){
			int[] chromo = chromos.get(i);
			double[] clusterx = new double[10];
			int[] size = new int[10];
			double[] clustery = new double[10];
			for(int j=0;j<100;j++){
				clusterx[chromo[j]] += allpoints.get(j).getX();
				clustery[chromo[j]] += allpoints.get(j).getY();
				size[chromo[j]]++;
			}
			for(int a = 0;a<10;a++){
				double x = clusterx[a]/size[a];
				double y = clustery[a]/size[a];
				popList.get(i)[a] = new Point(x,y);
			}
		}
//		for(int[] p:chromos){
//			System.out.println("---------------------------------------");
//		for(int q:p){
//			System.out.print(q+",");
//		}
//		}
	}
	
	private  static ArrayList<Point[]> genePopulation(){
		ArrayList<Point[]> popList = new ArrayList<>();
		for(int i=0;i<100;i++){
			Point[] pop = new Point[10];
			for(int j=0;j<10;j++){
				double x = Math.random()*100;
				double y = Math.random()*100;
				Point p = new Point(x,y);
				pop[j] = p;
			}
			popList.add(pop);
		}
		return popList;
	}

	public static ArrayList<Point> generatePoints(){
		//generate 100 points from 0 to 50
		ArrayList<Point> allPoint = new ArrayList<>();
//		ArrayList<Double> allx = new ArrayList<>();
//		ArrayList<Double> ally = new ArrayList<>();
//		for(int i=0;i<100;i++){
//			double x = Math.random()*100;
//			double y = Math.random()*100;
//			Point p = new Point(x,y);
//			allPoint.add(p);
//			allx.add(x);
//			ally.add(y);
//		}
//		System.out.println(allx);
//		System.out.println(ally);
		double[] allx = {87.38314103498737, 82.7362548380563, 45.430181110485655, 80.17982387170449, 50.67126880246142, 89.20610004156562, 13.835104264249287, 76.90211198821288, 98.05577067399399, 42.9096872116606, 89.09658951981376, 38.23966171357134, 44.435719821089556, 16.605316201724683, 38.22088576863902, 28.4954053861862, 34.43277698229592, 3.8516190672940875, 1.5535548906701169, 20.484926003741887, 28.619086858963495, 22.259667188434673, 14.740402698826326, 33.59389989283085, 82.01202362591702, 14.968987059051319, 5.181803061126178, 17.91093776556698, 60.53889091195435, 27.30056038885391, 71.183580346272, 23.889023674809472, 66.17473115144722, 60.53994712111045, 77.00752481396368, 21.988632542863062, 66.45636083783015, 29.803682262775556, 22.024700880358793, 61.75565052441764, 49.075726230549165, 77.94202180018968, 67.17466981860632, 84.78725412945724, 47.2557638465398, 22.43121467396939, 26.93373690598134, 31.038840329404692, 6.611829740426112, 37.20838279180671, 59.995358733651194, 2.9251523963612636, 89.20185433736141, 96.40216742634618, 16.865839317051524, 76.66871381527497, 35.46836356368303, 31.91224409342528, 27.578424645871035, 75.34871278372279, 29.775437357624657, 81.7908411610474, 37.72893995784254, 43.10343995338577, 40.24379175380041, 31.3793858910561, 26.03607863028079, 84.93941820261178, 28.5870667995749, 74.15340943858992, 61.629475797694155, 61.600356151409464, 50.65246700895643, 44.22880992437063, 84.26869570597809, 53.9490247201907, 93.36887013974021, 97.48263863251653, 15.696242814717554, 36.31521452063744, 18.805526412622807, 43.241504723643665, 35.327736833977184, 23.262868164603777, 11.044493015278945, 53.490722537753065, 78.48237288297757, 79.2295430442548, 46.56989397262531, 1.5573557362537738, 40.35678088000392, 5.491296434179061, 33.87716885271419, 7.22024286954257, 93.94543589832625, 43.90614623350191, 93.00541007523606, 90.20016085466419, 55.110698579748494, 61.88921931530436};
		double[] ally = {36.83396666766683, 56.253148236051786, 95.64786856825864, 23.83055655812193, 36.45891208712769, 84.49691520813721, 33.77696550643488, 54.463566146143016, 89.42836126522428, 97.36833614697535, 72.92177563637911, 57.86102185322123, 28.280336349295155, 58.97720663674466, 72.18433704647319, 0.4982608894819385, 68.13218853883556, 88.1712055776058, 11.815518867651253, 56.96284917798556, 4.017660601554418, 25.4484520686176, 19.08296323190757, 32.98589909737001, 99.36774198215153, 66.50214921991055, 19.07188058020527, 79.5195569362102, 64.46343662019237, 42.47540455624712, 71.74684246428549, 40.438732026094826, 48.688602709005735, 52.00442582510344, 81.60267463615448, 1.4452307900327588, 95.41094353378826, 75.12813195251165, 91.23259233457397, 32.7524116316149, 33.31931085601539, 24.142357843229934, 95.14170216901607, 43.12847845300198, 3.045427549491053, 11.476778450156322, 35.1339715322148, 87.8765324524548, 42.89815742752261, 39.67263469706354, 63.60083539031702, 98.32953798743806, 82.53845653370183, 43.539439578693326, 86.0395153047329, 25.438279685705034, 18.423112229094883, 39.568753727810005, 19.12921531928221, 15.030489687194414, 69.84541403407644, 62.68316183459387, 25.693301604233156, 57.31921549362327, 39.400870753249784, 95.3985861466322, 12.875585042288373, 78.23860358331784, 26.706100705352632, 19.478061729365525, 4.85884866584374, 65.18518640450142, 69.19902374269378, 45.15506724693788, 26.453738385516324, 44.74281288685171, 8.848472196265345, 98.00458884243088, 58.88612837188683, 54.96935553308915, 8.601360606946539, 68.54000119167526, 54.111107097601185, 79.82329921267952, 67.39550828214422, 54.613897861078605, 18.981703597415045, 38.308355895283185, 7.566289556014782, 51.70101092301692, 5.6004589751326606, 45.914218641673024, 33.47518269581254, 71.141340783358, 69.18200369321723, 90.9315804633019, 85.18838413687101, 91.42159519647119, 79.7547285376888, 93.21954701446103};
		for(int i=0;i<100;i++){
			allPoint.add(new Point(allx[i],ally[i]));
		}
		return allPoint;
	}
}
