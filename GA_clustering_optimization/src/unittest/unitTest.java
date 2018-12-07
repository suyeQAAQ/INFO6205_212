package unittest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import ga_kmeans.GA;
import ga_kmeans.Point;

public class unitTest {

    @Test
    public void testDistance1() {
        GA tryer = new GA();
        Point p1=new Point(2,2), p2=new Point(1,1);
        assertEquals(Math.sqrt(2), GA.getDistance(p1,p2));
    }

    /**
     */
    @Test
    public void testDistance2() {
        GA tryer = new GA();
        Point p1=new Point(100,100), p2=new Point(80,90);
        assertEquals(Math.sqrt(5)*10, GA.getDistance(p1,p2));
    }

    /**
     */
    @Test
    public void testencode1() {
        GA tryer = new GA();
        for(int i=0;i<100;i++){
            tryer.popList.remove(i);
            tryer.chromos.remove(i);
        }
        GA.N1=5;
        GA.N2=1;
        GA.P=5;
        int[] a={1};
        Point x=new Point(1,1);
        Point[] b={x,x,x,x,x};
        for(int j=0;j<5;j++){
            GA.chromos.add(a);
            GA.popList.add(b);
        }

        assertEquals(-1, GA.encode(GA.popList).get(1)[1]);
    }
    /**
     */

    @Test
    public void testencode2() {
        GA tryer = new GA();
        for(int i=0;i<100;i++){
            tryer.popList.remove(i);
            tryer.chromos.remove(i);
        }
        GA.N1=10;
        GA.N2=1;
        GA.P=10;
        int[] a={1};
        Point x=new Point(2,2);
        Point[] b={x,x,x,x,x};
        for(int j=0;j<10;j++){
            GA.chromos.add(a);
            GA.popList.add(b);
        }

        assertEquals(-1, GA.encode(GA.popList).get(1)[2]);
    }

    @Test
    public void testencode3() {
        GA tryer = new GA();
        for(int i=0;i<100;i++){
            tryer.popList.remove(i);
            tryer.chromos.remove(i);
        }
        GA.N1=10;
        GA.N2=1;
        GA.P=10;
        int[] a={8};
        Point x=new Point(1,2);
        Point[] b={x,x,x,x,x};
        for(int j=0;j<10;j++){
            GA.chromos.add(a);
            GA.popList.add(b);
        }

        assertEquals(-1, GA.encode(GA.popList).get(1)[2]);
    }

//    @Test
//    public void testdecode1() {
//        GA tryer = new GA();
//        for(int i=0;i<100;i++){
//            tryer.popList.remove(i);
//            tryer.chromos.remove(i);
//        }
//        int a[]=new int [100];
//        for(int i=0;i<100;i++){
//            a[i]=1;
//        }
//        Point x=new Point(2,2);
//        Point[] b={x,x,x,x,x,x,x,x,x,x};
//        for(int j=0;j<100;j++){
//            GA.popList.add(b);
//            GA.popList.add(a);
//        }
//        GA.decode(GA.chromos);
//        assertEquals(93.3,GA.popList.get(1)[1].getX(),0.1);
//    }
    /**
     */


    @Test
    public void testgetFitness() {
        int index=1;
        GA tryer = new GA();
        assertEquals(9.999000000000001E-5, GA.getFitness(index));
    }

    /**
     */
    @Test
    public void calculateFitnessValueOfEachChromo(){
        GA tryer = new GA();
        int[] chromo={1,2,3};
        Point[] pop={new Point(0,0),new Point(0,0),};
    }
    /**
     */
    @Test
    public void testcalculateFitnessValueOfEachChromo(){
        GA tryer =new GA();

    }

    /**
     */
    @Test
    public void testgetPopIndex(){
        GA tryer =new GA();
    }

    /**
     */
    @Test
    public void testcalculateFitness(){
        GA tryer =new GA();
    }

    /**
     *
     * @Test
    public void testselect(){
    GA tryer =new GA();
    }
     */


    /**
     *
     * @Test
    public void testcross(){
    GA tryer =new GA();
    }
     */

    /**
     */
    @Test
    public void testgetIndex(){
        GA tryer =new GA();

    }
}
