package jogodacobra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.random.*;

public class GamePanel extends JPanel implements ActionListener {

    static final int LARGURA_TELA = 600;
    static final int ALTURA_TELA = 600;
    static final int TAMANHO_DA_UNIDADE = 25;
    static final int UNIDADES_DO_JOGO = (LARGURA_TELA * ALTURA_TELA) / TAMANHO_DA_UNIDADE;
    static final int DELAY = 75;
    final int x[] = new int[UNIDADES_DO_JOGO];
    final int y[] = new int[UNIDADES_DO_JOGO];
    int partesDoCorpo = 6;
    int macasComidas;
    int macaX;
    int macaY;
    char direcao = 'R';
    boolean andando = false;
    Timer tempo;
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new AdaptadorDeTecla());
        iniciarjogo();
    }

    public void iniciarjogo() {
        novaMaca();
        andando = true;
        tempo = new Timer(DELAY, this);
        tempo.start();
    }

    public void componenteDaPintura(Graphics g) {

    }

    public void desenho(Graphics g) {

    }

    public void novaMaca() {

    }
    public void movimento() {

    }

    public void checarMaca() {

    }

    public void checarColisao() {

    }

    public void gameOver(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public class AdaptadorDeTecla extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

        }
    }
}
