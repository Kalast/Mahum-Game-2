/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mahum.game;

/**
 * An abstract class defining a general spline object.
 * 
 * @author <a href="mailto:info@geosoft.no">GeoSoft</a>
 */   
abstract class Spline
{
  protected double controlPoints_[];
  protected int    nParts_;

  abstract double[] generate();
}

