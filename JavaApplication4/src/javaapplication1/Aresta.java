package javaapplication1;
import java.awt.*;
import java.awt.geom.*;

public class Aresta implements Desenhavel {

    public Aresta(Vertice v1, Vertice v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    public void desenhar(Graphics2D g2d){
        if (v1 != null && v2 != null) {
            double x1 = v1.getPosicao().getX();
            double y1 = v1.getPosicao().getY();
            
            double x2 = v2.getPosicao().getX();
            double y2 = v2.getPosicao().getY();
            
            this.line = new Line2D.Double(x1, y1, x2, y2);
            g2d.draw(this.line); 
        }
    }
    
    public boolean contem(Ponto2D p) {
        return this.line.intersects(p.getX()-5, p.getY()-5, 10, 10);
    }
    
    public Vertice getV1(){
        return this.v1;
    }
    
    public void setV1(Vertice v1){
        this.v1 = v1;
    }
    
    public Vertice getV2(){
        return this.v2;
    }

    public void setV2(Vertice v2){
        this.v2 = v2;
    }
    
    public boolean equals(Aresta a) {
        if (this.v1.equals(a.getV1()) && this.v2.equals(a.getV2())) return true;
        if (this.v1.equals(a.getV2()) && this.v2.equals(a.getV1())) return true;
        return false;
    }
    
    private Vertice v1;
    private Vertice v2;
    
    private Line2D line;
}
