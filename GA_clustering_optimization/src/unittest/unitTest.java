package unittest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import ga_kmeans.GA;
import ga_kmeans.Point;

public class unitTest {
    /**
     */
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
        Point p1=new Point(100,100), p2=new Point(80,20);
        assertEquals(Math.sqrt(2)*80, GA.getDistance(p1,p2));
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

    /**
     */
    @Test
    public void testcalculateFitness(){
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

}
