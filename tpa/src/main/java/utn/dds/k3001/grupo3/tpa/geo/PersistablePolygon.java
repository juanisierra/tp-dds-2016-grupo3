package utn.dds.k3001.grupo3.tpa.geo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.hibernate.annotations.Cascade;
import org.uqbar.geodds.NumberUtils;
import org.uqbar.geodds.Polygon;

@SuppressWarnings("all")
@Entity

public class PersistablePolygon extends Polygon implements Serializable{
	@Id @GeneratedValue
	private int id;
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "poligono_id")
	private List<PersistablePoint> puntos;

  public PersistablePolygon() {
	super();
    ArrayList<PersistablePoint> _arrayList = new ArrayList<PersistablePoint>();
    this.puntos = _arrayList;
  }
  
  public boolean add(final PersistablePoint point) {
	super.add(point);
    return this.puntos.add(point);
  }
  
  public PersistablePolygon( List<PersistablePoint> points) {
	super();
    this.puntos = points;
    if(points!=null) points.forEach(punto -> super.add(punto));
  }
}
