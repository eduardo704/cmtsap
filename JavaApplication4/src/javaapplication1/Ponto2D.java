package javaapplication1;
public class Ponto2D {

    Ponto2D(){
        this(-1,-1);
    }

    Ponto2D(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
      return x;
    }

    public void setX(int x){
      this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
    
    public Ponto2D clonar() {
        return new Ponto2D(x, y);
    }

    public boolean equals(Ponto2D p) {
        return ((x == p.getX()) && (y == p.getY()));
    }

    private int x, y;
}
