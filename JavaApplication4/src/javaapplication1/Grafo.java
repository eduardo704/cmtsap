package javaapplication1;
import java.util.*;
import java.awt.*;
import javax.swing.JOptionPane;
import java.awt.Color;

public class Grafo implements Iterable<Desenhavel> {

    public Grafo() {
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
    }

    public void setVertice(Vertice v) {
        this.vertices.add(v);
    }
    
    public Vertice getVertice(Ponto2D p) {
        for (Vertice v : vertices) {
            if (v.contem(p)) return v;
        }
        return null;
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }
    
    public void removeVertice(Vertice v) {
        ArrayList<Aresta> remover = new ArrayList<Aresta>();
        for (Aresta a : this.arestas) {
            if (a.getV1().equals(v) || a.getV2().equals(v)) remover.add(a);
        }
        for (Aresta a : remover) {
            arestas.remove(a);
        }
        this.vertices.remove(v);
    }

    public void setAresta(Aresta a) {
        for (Aresta aresta : arestas) {
            if (aresta.equals(a)) return;
        }
        this.arestas.add(a);
    }
    
    public Aresta getAresta(Ponto2D p) {
        for (Aresta a: arestas) {
            if (a.contem(p)) return a;
        }
        return null;
    }
    
    public void removeAresta(Aresta a) {
        this.arestas.remove(a);
    }

    public void limpar(){
        arestas = new ArrayList<Aresta>();
        vertices = new ArrayList<Vertice>();
    }

    @Override
    public Iterator<Desenhavel> iterator() {
        ArrayList<Desenhavel> desenhaveis = new ArrayList<Desenhavel>();
        desenhaveis.addAll(arestas);
        desenhaveis.addAll(vertices);
        return desenhaveis.iterator();
    }
    public ArrayList<Aresta> pegarConexoes(Vertice v){
        ArrayList<Aresta> conexoes=new ArrayList<>();
        for(Aresta a : arestas){
               if(a.getV1()==v){
                   conexoes.add(a);
               }
               else if(a.getV2()==v){
                   Vertice aux=a.getV1();
                   Aresta are=new Aresta(v, aux);
                   conexoes.add(are);
               }            
           }
        return conexoes;
    }
    
      public ArrayList<Vertice> getVerticesVizinhos(Vertice v){
        ArrayList<Vertice> lista = new ArrayList<Vertice>();
        for (Aresta a : arestas) {
            if (a.getV1().equals(v)) lista.add(a.getV2());
        }
        return lista;
    }
    
    
    public void profudindade(){
       Vertice v=selecionado1;
       Vertice ultimaBifurcacao;
       Vertice inicio = selecionado1;
       Vertice  fim = selecionado2;
       Stack<Vertice> pilha;
       int cont = 0;
       pilha = new Stack<Vertice>();
       pilha.push(inicio);
       boolean achou = false;
       boolean terminar = false;
       ArrayList<Vertice> visitados=new ArrayList<>();

         if (!pilha.empty() && !achou) {
            Vertice ve = pilha.pop();
            java.util.List<Vertice> lista = getVerticesVizinhos(ve);
            if (ve != inicio) ve.setCor(Color.BLUE);
            for (int i = 0; i < lista.size(); i++) {
                if (!pilha.contains(lista.get(i)) && !visitados.contains(lista.get(i))) {
                    pilha.push(lista.get(i));
                    if (!lista.get(i).equals(fim)) {
                        lista.get(i).setCor(Color.YELLOW);
                    } else {
                        achou = true;
                        i = lista.size();
                    }
                }
            }
            visitados.add(v);
            cont++;
            
        } else {
            if (achou && !terminar) {
                terminar = true;
                cont++;
               
            }
           
        }
       
     
       
    }
    
    public void camadas(){
       Vertice v=selecionado1;
       ArrayList<Vertice> visitados=new ArrayList();
       ArrayList<Vertice> aVisitar=vertices;
       ArrayList<Aresta> conexoes=new ArrayList();
       while(aVisitar.size()<vertices.size()){
           conexoes=this.pegarConexoes(v);
           for(Aresta a: conexoes){
               if(selecionado2.equals(a.getV2())){
                   for(Vertice vert: visitados){
                       
                   }
               }else{
                   visitados.add(a.getV2());
               }
           }
           
       }
       
    }
    private Vertice selecionado1;
    private Vertice selecionado2;

    public Vertice getSelecionado1() {
        return selecionado1;
    }

    public void setSelecionado1(Vertice selecionado1) {
        this.selecionado1 = selecionado1;
        JOptionPane.showMessageDialog(null,"Vertice inicial selecionado");
    }

    public Vertice getSelecionado2() {
        return selecionado2;
       
    }

    public void setSelecionado2(Vertice selecionado2) {
        this.selecionado2 = selecionado2;
         JOptionPane.showMessageDialog(null,"Vertice final selecionado");
    }
    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestas;
}
