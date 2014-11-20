package javaapplication1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Editor extends JFrame {
    

    public Editor() {
        
        this.initialize();
        this.setListeners();
    }
    
    public static Grafo grafo = new Grafo();

    private static final int LARGURA = 800;
    private static final int ALTURA = 600;

    private TelaGrafo tela;
    
    private JButton btnLimparCanvas;
    private JButton btnPrintar;
    private JButton btnProfundidade;
    private JButton btnCamadas;

    private void initialize() {

        tela = new TelaGrafo();
        tela.setPreferredSize(new Dimension(LARGURA, ALTURA));

        btnLimparCanvas = new JButton("Limpar");
 
        btnProfundidade=new JButton("Profundidade");
        btnCamadas=new JButton("Camadas");
        
        JPanel pnlBotoes = new JPanel(new FlowLayout());
        pnlBotoes.add(btnLimparCanvas);

        pnlBotoes.add(btnProfundidade);
        pnlBotoes.add(btnCamadas);

        Container organizacao = getContentPane();
        organizacao.setLayout(new BorderLayout());
        organizacao.add(tela, BorderLayout.CENTER);
        organizacao.add(pnlBotoes, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Editor de Grafos");
        pack();
        setVisible(true);
        requestFocus();
    }
    
    private void setListeners() {
        
        MoveListener nl = new MoveListener();
        tela.addMouseListener(nl);
        tela.addMouseMotionListener(nl);
        
        AddVerticeListener avl = new AddVerticeListener();
        tela.addMouseListener(avl);

        RemoveDesenhavelListener rdl = new RemoveDesenhavelListener();
        tela.addMouseListener(rdl);
        
        AddArestaListener aal = new AddArestaListener();
        tela.addMouseListener(aal);
        tela.addMouseMotionListener(aal);

        btnLimparCanvas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grafo.limpar();
                tela.repaint();
            }
        });
        btnProfundidade.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(grafo.getVertices().size()<2){
                JOptionPane.showMessageDialog(null, "Adicionar mais vertices");
                }else{
                selecionarVerticeListener svl = new selecionarVerticeListener();
                tela.addMouseListener(svl);
                grafo.profudindade();
                tela.removeMouseListener(svl);
                
                }
                    
            }
        });
         btnCamadas.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(grafo.getVertices().size()<2){
                JOptionPane.showMessageDialog(null, "Adicionar mais vertices");
                }else{
                selecionarVerticeListener svl = new selecionarVerticeListener();
                tela.addMouseListener(svl);
                grafo.camadas();
                tela.removeMouseListener(svl);
                
                }
                    
            }
        });

        
    }
  
    

    private class AddVerticeListener extends MouseInputAdapter {
        
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 2 && !e.isConsumed() && e.getButton() == 1) {
                e.consume();
                Ponto2D p = new Ponto2D(e.getX(), e.getY());
                Vertice v = grafo.getVertice(p);
                if (v == null) {
                    String nome = JOptionPane.showInputDialog("Digite o nome do Vértice");
                    if (nome != null) {
                        v = new Vertice(p, nome);
                        grafo.setVertice(v);
                        tela.repaint();
                    }
                }
            }
        }
    }
   private class selecionarVerticeListener extends MouseInputAdapter {
        
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 1 && !e.isConsumed()) {
                e.consume();
                Vertice v=null;
                
                 v = grafo.getVertice(new Ponto2D(e.getX(), e.getY()));
               
                if(v!=null){
                    if(grafo.getSelecionado1()==null){
                        grafo.setSelecionado1(v);
                    }else{
                        grafo.setSelecionado2(v);
                    }
                }
        }
    }
   }
    private class RemoveDesenhavelListener extends MouseInputAdapter {
        
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 2 && !e.isConsumed() && e.getButton() == 3) {
                e.consume();
                Vertice v = grafo.getVertice(new Ponto2D(e.getX(), e.getY()));
                Aresta a = grafo.getAresta(new Ponto2D(e.getX(), e.getY()));
                if (v != null) {
                    grafo.removeVertice(v);
                }
                if (a != null) {
                    grafo.removeAresta(a);
                }
                tela.repaint();
            }
        }
    }
    
    

    private class AddArestaListener extends MouseInputAdapter {
            
        private Aresta a;
        
        public void mousePressed(MouseEvent e) {
            a = null;
            if (e.getButton() == 2) {
                Vertice v = grafo.getVertice(new Ponto2D(e.getX(), e.getY()));
                if (v != null) {
                    a = new Aresta(v, null);
                    tela.addDesenhavel(a);
                }
            }
        }

        public void mouseDragged(MouseEvent e) {
            if (a != null) {
                a.setV2(new Vertice(new Ponto2D(e.getX(), e.getY()), ""));
                tela.repaint();
            }
        }

        public void mouseReleased(MouseEvent e) {
            if (a != null) {
                Vertice v = grafo.getVertice(new Ponto2D(e.getX(), e.getY()));
                if (v != null && v != a.getV1()) {
                    a.setV2(v);
                    grafo.setAresta(a);
                }
                tela.removeDesenhavel(a);
                tela.repaint();
            }
        }
    }

    private class MoveListener extends MouseInputAdapter {
            
        private Vertice v;
        
        public void mousePressed(MouseEvent e) {
            v = null;
            if (e.getButton() == 1) {
                v = grafo.getVertice(new Ponto2D(e.getX(), e.getY()));
            }
        }

        public void mouseDragged(MouseEvent e) {
            if (v != null) {
                v.setPosicao(new Ponto2D(e.getX(), e.getY()));
                tela.repaint();
            }
        }

        public void mouseReleased(MouseEvent e) {
            if (v != null) {
                v.setPosicao(new Ponto2D(e.getX(), e.getY()));
                tela.repaint();
            }
        }
    }
}
