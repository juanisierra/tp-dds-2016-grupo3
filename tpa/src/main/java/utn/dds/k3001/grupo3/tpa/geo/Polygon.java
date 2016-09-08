package utn.dds.k3001.grupo3.tpa.geo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.hibernate.annotations.Cascade;
import org.uqbar.geodds.NumberUtils;

/**
 * Define una zona conformada por vértices (un conjunto de puntos)<br>
 * <br>
 * <b>Importante:</b> cuando se define una zona no hay que definir el vértice final igual al inicial<br>
 * <br>
 * @author DDS
 */
@SuppressWarnings("all")
@Entity

public class Polygon {
	@Id @GeneratedValue
	private int id;
	@OneToMany
	@JoinColumn(name = "poligono_id")
	@Cascade(value={org.hibernate.annotations.CascadeType.PERSIST})
  private List<Point> surface;
  /**
   * Constructor default, obliga luego a agregar los puntos manualmente mediante el mensaje add(Point point). <br><br>
   * Para trabajar con un polígono inmutable, no debe usarse este constructor ni el add posterior.<br>
   */
  public Polygon() {
    ArrayList<Point> _arrayList = new ArrayList<Point>();
    this.surface = _arrayList;
  }
  
  public boolean add(final Point point) {
    return this.surface.add(point);
  }
  
  /**
   * Constructor que le pasa un conjunto de puntos que definen el polígono
   */
  public Polygon(final List<Point> points) {
    this.surface = points;
  }
  
  /**
   * Convierte un punto a un polígono conformado inicialmente por este punto. <br>
   * No compatible con la idea de un polígono inmutable.<br>
   */
  public static Polygon asPolygon(final Point point) {
    return new Polygon(((List<Point>) Collections.<Point>unmodifiableList(CollectionLiterals.<Point>newArrayList(point))));
  }
  
  /**
   * Determina si un punto está dentro de un polígono
   * Para la explicación véase http://erich.realtimerendering.com/ptinpoly/
   * @Deprecated Usar isInside
   */
  public boolean isInsideOld(final Point point) {
    boolean _xblockexpression = false;
    {
      int counter = 0;
      Point point1 = this.surface.get(0);
      final int N = this.surface.size();
      for (int i = 1; (i <= N); i++) {
        {
          Point point2 = this.surface.get((i % N));
          boolean _intersects = point.intersects(point1, point2);
          if (_intersects) {
            counter++;
          }
          point1 = point2;
        }
      }
      _xblockexpression = NumberUtils.even(counter);
    }
    return _xblockexpression;
  }
  
  /**
   * Función mejorada para determinar si un punto está en el polígono
   */
  public boolean isInside(final Point point) {
    boolean _xblockexpression = false;
    {
      final int N = this.surface.size();
      int j = (N - 1);
      boolean oddNodes = false;
      double x = point.longitude();
      double y = point.latitude();
      for (int i = 0; (i < N); i++) {
        {
          Point _get = this.surface.get(i);
          final double verticeIY = _get.latitude();
          Point _get_1 = this.surface.get(i);
          final double verticeIX = _get_1.longitude();
          Point _get_2 = this.surface.get(j);
          final double verticeJY = _get_2.latitude();
          Point _get_3 = this.surface.get(j);
          final double verticeJX = _get_3.longitude();
          if (((((verticeIY < y) && (verticeJY >= y)) || ((verticeJY < y) && (verticeIY >= y))) && ((verticeIX <= x) || (verticeJX <= x)))) {
            if (((verticeIX + (((y - verticeIY) / (verticeJY - verticeIY)) * (verticeJX - verticeIX))) < x)) {
              oddNodes = (!oddNodes);
            }
          }
          j = i;
        }
      }
      _xblockexpression = oddNodes;
    }
    return _xblockexpression;
  }
  
  /**
   * Indica si un punto es alguno de los vértices del polígono
   */
  public boolean pointOnVertex(final Point point) {
    return this.surface.contains(point);
  }

public List<Point> getSurface() {
	return surface;
}

public void setSurface(List<Point> surface) {
	this.surface = surface;
}
}
