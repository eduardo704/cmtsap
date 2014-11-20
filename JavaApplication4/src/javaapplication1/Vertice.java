package javaapplication1;


import java.awt.*;
import java.awt.geom.*;

public class Vertice implements Desenhavel {

    public Vertice(Ponto2D p, String nome, Color c) {
        this.setPosicao(p.clonar());
        this.nome = nome;
        this.cor = c;
    }

    public Vertice(Ponto2D p, String nome) {
        this(p, nome, Color.BLACK);
    }

    public void desenhar(Graphics2D g2d) {
        int x = (int) posicao.getX() - (tamanho / 2);
        int y = (int) posicao.getY() - (tamanho / 2);
        this.oval = new Ellipse2D.Double(x, y, Vertice.tamanho, Vertice.tamanho);
        g2d.setColor(this.cor);
        g2d.fill(this.oval);
        g2d.setColor(Color.BLACK);
        int largura = g2d.getFontMetrics().stringWidth(nome);
        g2d.drawString(nome, posicao.getX() - (largura/2), posicao.getY() + (tamanho/2) + CORD_Y_TEXTO);
    }
    
    public boolean contem(Ponto2D p) {
        return this.oval.contains(p.getX(), p.getY());
    }
    
    public void setCor(Color cor) {
        this.cor = cor;
    }

    public Ponto2D getPosicao() {
        return this.posicao;
    }

    public void setPosicao(Ponto2D p) {
        posicao = p.clonar();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
        nome = n;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Vertice)) return false;
        Vertice v = (Vertice) obj;
        if (posicao.getX() == v.posicao.getX() && posicao.getY() == v.posicao.getY()) return true;
        return false;
    }

    public static void setTamanho(int tamanho) throws IllegalArgumentException {
        if (TAM_MINIMO <= tamanho && tamanho <= TAM_MAXIMO) {
            Vertice.tamanho = tamanho;
        } else {
            throw new IllegalArgumentException(String.format("O tamanho do Vértice deve estar entre %d e %d", TAM_MINIMO, TAM_MAXIMO));
        }
    }

    public static int getTamanho() {
        return Vertice.tamanho;
    }
    
    public static int getTamanhoMinimo() {
        return Vertice.TAM_MINIMO;
    }
    
    public static int getTamanhoMaximo() {
        return Vertice.TAM_MAXIMO;
    }

    private static int TAM_MINIMO = 20;
    private static int TAM_MAXIMO = 40;
    private static int CORD_Y_TEXTO = 20;
    private static int tamanho = 20;

    private Ponto2D posicao;
    private String nome;
    private Color cor;
    
    private Ellipse2D oval;
}
