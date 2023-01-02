package cm;

import cm.modelo.Tabuleiro;
import cm.visao.TabuleiroTerminal;

public class Aplicacao {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro(6,6,6);

        new TabuleiroTerminal(tabuleiro);
    }
}
