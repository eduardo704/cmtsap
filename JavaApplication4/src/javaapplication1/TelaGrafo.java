package javaapplication1;


import javax.swing.*;
import java.awt.*;
import java.util.*;

class TelaGrafo extends JPanel {

    public TelaGrafo() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.WHITE);
        this.desenhaveis = new ArrayList<Desenhavel>();
    }
    
    public void addDesenhavel(Desenhavel d) {
        this.desenhaveis.add(d);
    }
    
    public void removeDesenhavel(Desenhavel d) {
        this.desenhaveis.remove(d);
    }
    
    public void limparDesenaveis() {
        this.desenhaveis = new ArrayList<Desenhavel>();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;

        Dimension dim = getSize();
        StringBuilder txtDim = new StringBuilder("Dimensões da tela: ");
        txtDim.append(dim.width).append(" X ").append(dim.height); 
        g2d.drawString(txtDim.toString(), TAMANHO_MARGEM_TXT, TAMANHO_LINHA_TXT);
        
        
        for (Desenhavel d : desenhaveis) {
            d.desenhar(this.g2d);
        }
        
        for (Desenhavel d : Editor.grafo) {
            d.desenhar(this.g2d);
        }
    }

    private static final int TAMANHO_MARGEM_TXT = 10;
    private static final int TAMANHO_LINHA_TXT = 15;
    private static final int INICIO = 10;
    private static final float BASE = 0.98f;
    private static final int TAMANHO_VERTICE = 10;

    private Graphics2D g2d;
    private ArrayList<Desenhavel> desenhaveis;
}
