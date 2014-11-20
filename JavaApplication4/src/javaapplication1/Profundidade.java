package javaapplication1;


import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.*;
import java.awt.Color;

public class Profundidade {

    public Profundidade(Grafo g, Vertice inicio, Vertice fim) {
        this.g = g;
        this.inicio = inicio;
        this.fim = fim;
        this.visitados = new ArrayList<Vertice>();
        cont = 0;
        pilha = new Stack<Vertice>();
        pilha.push(inicio);
        achou = false;
        terminar = false;
    }
    
    public void proximo() {
        if (!pilha.empty() && !achou) {
            Vertice v = pilha.pop();
            List <Aresta> arestas=g.pegarConexoes(v);
            List<Vertice> lista = new VirtualFlow.ArrayLinkedList<>();
            if (v != inicio) v.setCor(Color.BLUE);
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
        //    return new StringDesenhavel(""+cont, v.getPosicao());
        } else {
            if (achou && !terminar) {
                terminar = true;
                cont++;
                //return new StringDesenhavel(""+cont, fim.getPosicao());
            }
           // return null;
        }
    }
    
    private Stack<Vertice> pilha;
    
    private List<Vertice> visitados;
    private Grafo g;
    private Vertice inicio;
    private Vertice fim;
    private int cont;
    private boolean achou;
    private boolean terminar;
}
