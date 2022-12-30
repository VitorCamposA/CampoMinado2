package cm.modelo;

import cm.excecao.ExplosaoException;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.*;

class CampoTest {


    private Campo campo = new Campo(3, 3);

    @Test
    void testeVizinhoDist1() {
        Campo vizinho = new Campo(4, 3);
        boolean result1 = campo.adicionarVizinho(vizinho);

        vizinho = new Campo(2, 3);
        boolean result2 = campo.adicionarVizinho(vizinho);

        vizinho = new Campo(3, 2);
        boolean result3 = campo.adicionarVizinho(vizinho);

        vizinho = new Campo(3, 4);
        boolean result4 = campo.adicionarVizinho(vizinho);

        assertTrue(result1 && result2 && result3 && result4);
    }

    @Test
    void testeVizinhoDiag(){
        Campo vizinho = new Campo(4, 4);
        boolean result1 = campo.adicionarVizinho(vizinho);

        vizinho = new Campo(2, 4);
        boolean result2 = campo.adicionarVizinho(vizinho);

        vizinho = new Campo(2, 2);
        boolean result3 = campo.adicionarVizinho(vizinho);

        vizinho = new Campo(4, 2);
        boolean result4 = campo.adicionarVizinho(vizinho);

        assertTrue(result1 && result2 && result3 && result4);

    }
    @Test
    void testeVizinhoD1f() {
        Campo vizinho = new Campo(4, 1);
        boolean result1 = campo.adicionarVizinho(vizinho);

        assertFalse(result1);
    }
    @Test
    void testeVizinhoDiagf() {
        Campo vizinho = new Campo(1, 1);
        boolean result1 = campo.adicionarVizinho(vizinho);

        assertFalse(result1);

    }
    @Test
    void testeValorPadraoAtributoMarcado(){
        assertFalse(campo.isMarcado());
    }
    @Test
    void testeAlternarMarcacao(){
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }
    @Test
    void testeAlternarMarcacao2(){
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }
    @Test
    void testeAlternarMarcacao3(){
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado(){

        assertTrue(campo.abrir());
    }
    @Test
    void testeAbrirNaoMinadoMarcado(){
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }
    @Test
    void testeAbrirMinadoMarcado(){
        campo.minar();
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }
    @Test
    void testeAbrirMinadoNaoMarcado(){
        campo.minar();
        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }
    @Test
    void testeAbrirComVizinhos1(){
        Campo vizinho1 = new Campo(2,2);
        Campo vizinhoDoVizinho1 = new Campo(1,2);
        campo.adicionarVizinho(vizinho1);
        vizinho1.adicionarVizinho(vizinhoDoVizinho1);
        campo.abrir();
        assertTrue(campo.isAberto() && vizinho1.isAberto() && vizinhoDoVizinho1.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2(){
        Campo campo22 = new Campo(2,2);

        Campo campo12 = new Campo(1,2);
        Campo campo11 = new Campo(1,1);
        campo11.minar();


        campo.adicionarVizinho(campo22);

        campo22.adicionarVizinho(campo12);
        campo22.adicionarVizinho(campo11);

        campo.abrir();

        assertTrue(campo22.isAberto() && campo12.isFechado());

    }
    @Test
    void testeToString(){
        Campo campo2 = new Campo(3,2);
        campo2.minar();
        System.out.println(campo);
        campo.abrir();
        System.out.println(campo);
        campo.adicionarVizinho(campo2);
        System.out.println(campo);
        campo.minar();
        System.out.println(campo);
        campo.reiniciar();
        campo.alternarMarcacao();
        System.out.println(campo);
    }

    @Test
    void testeResto(){
        assertEquals(campo.getColuna(), 3);
        assertEquals(campo.getLinha(), 3);

        campo.abrir();
        campo.objetivoAlcancado();
        campo.reiniciar();
        campo.minar();
        campo.alternarMarcacao();
        campo.objetivoAlcancado();
    }

}