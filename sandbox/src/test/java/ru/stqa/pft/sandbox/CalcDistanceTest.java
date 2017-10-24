package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalcDistanceTest {

   @Test
    public void testDistance(){
       Point p1 = new Point(0,0);
       Point p2 = new Point(1,1);

       Assert.assertEquals(p1.distance(p2),(p2.distance(p1)));
   }
}
