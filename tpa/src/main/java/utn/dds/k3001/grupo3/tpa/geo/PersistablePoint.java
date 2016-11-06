package utn.dds.k3001.grupo3.tpa.geo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbar.geodds.NumberUtils;
import org.uqbar.geodds.Point;

@SuppressWarnings("all")
@Entity
public class PersistablePoint extends Point implements Serializable{
	@Id @GeneratedValue
	private int id;
	private BigDecimal x;
	private BigDecimal y;
  
  public PersistablePoint(final int aX, final int aY) {
	super(aX,aY);
    BigDecimal _bigDecimal = new BigDecimal(aX);
    this.x = _bigDecimal;
    BigDecimal _bigDecimal_1 = new BigDecimal(aY);
    this.y = _bigDecimal_1;
  }
 
  public PersistablePoint(){
	  super(0,0);
  }
  
  public PersistablePoint(BigDecimal x,BigDecimal y) {
	  super(x.doubleValue(),y.doubleValue());
	  this.x=x;
	  this.y=y;
  }
  
  public PersistablePoint(final double aX, final double aY) {
	  super(aX,aY);
    BigDecimal _bigDecimal = new BigDecimal(aX);
    this.x = _bigDecimal;
    BigDecimal _bigDecimal_1 = new BigDecimal(aY);
    this.y = _bigDecimal_1;
  }

  public static PersistablePoint and(final double aX, final double aY) {
    return new PersistablePoint(aX, aY);
  }

  public double distance(final PersistablePoint anotherPoint) {
	 return super.distance(anotherPoint);
  }

  @Override
  public String toString() {
    return ((("x: " + this.x) + ", y: ") + this.y);
  }

public BigDecimal getX() {
	return x;
}

public void setX(BigDecimal x) {
	this.x = x;
}

public BigDecimal getY() {
	return y;
}

public void setY(BigDecimal y) {
	this.y = y;
}
}
