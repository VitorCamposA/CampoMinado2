package cm.modelo;

import cm.excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    //cordenada
    private final int linha;
    private final int coluna;
    //vizinho
    private List<Campo> vizinhos = new ArrayList<Campo>();
    //mina
    private boolean minado = false;

    public boolean isMinado() {
        return minado;
    }

    //aberto
    private boolean aberto = false;
    //marcado
    private boolean marcado = false;

    Campo(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    boolean adicionarVizinho(Campo vizinho) {
        boolean xDiferente = this.linha != vizinho.linha;
        boolean yDiferente = this.coluna != vizinho.coluna;

        boolean diagonal = xDiferente && yDiferente;

        int deltaX = Math.abs(this.linha - vizinho.linha);
        int deltaY = Math.abs(this.coluna - vizinho.coluna);

        int deltaGeral = deltaX + deltaY;

        if (deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        } else {
            return false;
        }
    }

    void alternarMarcacao() {
        if (!aberto) {
            marcado = !marcado;
        }
    }

    boolean abrir() {
        if (!aberto && !marcado) {
            aberto = true;
            if (minado) {
                throw new ExplosaoException();
            }

            if (vizinhancaSegura()) {
                vizinhos.forEach(v -> v.abrir());
            }
            return true;
        } else {
            return false;
        }
    }

    boolean vizinhancaSegura() {
        return vizinhos.stream().noneMatch(v -> v.minado);

    }

    public boolean isMarcado() {
        return marcado;
    }

    void minar() {
        minado = true;
    }

    void setAberto(boolean aberto) {
        this.aberto = aberto;
    }

    public boolean isAberto() {
        return aberto;
    }

    public boolean isFechado() {
        return !isAberto();
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    boolean objetivoAlcancado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    long minasVizinhas(){
        return vizinhos.stream()
                .filter(m -> m.minado)
                .count();
    }

    void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
    }

    public String toString(){
        if(marcado){
            return "x";
        }else if (aberto && minado){
            return "*";
        }else if (aberto && minasVizinhas() > 0){
            return Long.toString(minasVizinhas());
        }else if (aberto){
            return " ";
        }else {
            return "?";
        }
    }
}
