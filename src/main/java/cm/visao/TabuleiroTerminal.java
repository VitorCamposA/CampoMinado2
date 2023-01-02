package cm.visao;

import cm.excecao.ExplosaoException;
import cm.excecao.SairException;
import cm.modelo.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroTerminal {

    private Tabuleiro tabuleiro;
    private Scanner sc = new Scanner(System.in);

    public TabuleiroTerminal(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo(){
        try {
            boolean continuar = true;

            while (continuar){
                cicloDoJogo();



                System.out.println("Outra Partida? (S/n) ");
                String resposta = sc.nextLine();
                if ("n".equalsIgnoreCase(resposta)){
                    continuar = false;
                } else{
                    tabuleiro.reiniciar();
                }
            }
        }catch (SairException e){
            System.out.println("Tchau");
        }finally {
            sc.close();
        }
    }

    private void cicloDoJogo(){
        try {
            while (!tabuleiro.objetivoAlcancado()){
                System.out.println(tabuleiro);

                String digitado =
                        capturarValorDigitado("Digite (x, y): ");

                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(e -> Integer.parseInt(e.trim())).iterator();

                digitado = capturarValorDigitado("1 - para abrir 2 - para (Des)Marcar");

                if ("1".equals(digitado)){
                    tabuleiro.abrir(xy.next(), xy.next());
                } else if ("2".equals(digitado)){
                    tabuleiro.alternarMarcacao(xy.next(), xy.next());
                }



            }

            System.out.println(tabuleiro);
            System.out.println("Voce Ganhou!");

        }catch (ExplosaoException e){
            System.out.println(tabuleiro);
            System.out.println("Voce perdeu!");
        }
    }

    private String capturarValorDigitado (String texto){
        System.out.println(texto);
        String digitado = sc.nextLine();

        if ("Sair".equalsIgnoreCase(digitado)){
            throw new SairException();
        }
        return digitado;
    }
}
