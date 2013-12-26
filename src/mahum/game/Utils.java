/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

import org.jbox2d.collision.shapes.PolygonShape;

/**
 *
 * @author Kalast
 */
public class Utils {
    public static void display_array(Object[] array){
        System.out.println(array.length);
        for (Object array1 : array) {
            System.out.println(array1);
        }
    }
    
    public static PolygonShape createPolygon(){
        PolygonShape ps = new PolygonShape();
        
        return ps;
    }
    
    public float[] getSplineValues(float number, int precision){
        Spline s;
        return null;
    }
}
