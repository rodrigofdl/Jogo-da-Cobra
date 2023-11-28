package jogodacobra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int LARGURA_TELA = 600;
    static final int ALTURA_TELA = 600;
    static final int TAMANHO_DA_UNIDADE = 25;
    static final int UNIDADES_DO_JOGO = (LARGURA_TELA * ALTURA_TELA) / TAMANHO_DA_UNIDADE;
    static final int DELAY = 75;
    final int[] x = new int[UNIDADES_DO_JOGO];
    final int[] y = new int[UNIDADES_DO_JOGO];
    int partesDoCorpo = 6;
    int macasComidas;
    int macaX;
    int macaY;
    char direcao = 'D';
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenho(g);
    }

    public void desenho(Graphics g) {

        if (andando) {
            /*for (int i = 0; i < ALTURA_TELA / TAMANHO_DA_UNIDADE; i++) {
                g.drawLine(i * TAMANHO_DA_UNIDADE, 0, i * TAMANHO_DA_UNIDADE, ALTURA_TELA);
                g.drawLine(0, i * TAMANHO_DA_UNIDADE, LARGURA_TELA, i * TAMANHO_DA_UNIDADE);
            }
            */
            g.setColor(Color.red);
            g.fillOval(macaX, macaY, TAMANHO_DA_UNIDADE, TAMANHO_DA_UNIDADE);

            for (int i = 0; i < partesDoCorpo; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], TAMANHO_DA_UNIDADE, TAMANHO_DA_UNIDADE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    g.fillRect(x[i], y[i], TAMANHO_DA_UNIDADE, TAMANHO_DA_UNIDADE);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Serif", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Pontuação: " + macasComidas, (LARGURA_TELA - metrics.stringWidth("Pontuação: " + macasComidas)) / 2, g.getFont().getSize());
        } else {
            FimDeJogo(g);
        }
    }

    public void novaMaca() {
        macaX = random.nextInt(LARGURA_TELA / TAMANHO_DA_UNIDADE) * TAMANHO_DA_UNIDADE;
        macaY = random.nextInt(ALTURA_TELA / TAMANHO_DA_UNIDADE) * TAMANHO_DA_UNIDADE;
    }

    public void movimento() {
        for (int i = partesDoCorpo; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direcao) {
            case 'C':
                y[0] = y[0] - TAMANHO_DA_UNIDADE;
                break;
            case 'B':
                y[0] = y[0] + TAMANHO_DA_UNIDADE;
                break;
            case 'E':
                x[0] = x[0] - TAMANHO_DA_UNIDADE;
                break;
            case 'D':
                x[0] = x[0] + TAMANHO_DA_UNIDADE;
                break;
        }
    }

    public void checarMaca() {
        if ((x[0] == macaX) && (y[0] == macaY)) {
            partesDoCorpo++;
            macasComidas++;
            novaMaca();
        }
    }

    public void checarColisao() {
        // checa se a cabeça colidiu com o corpo
        for (int i = partesDoCorpo; i > 0; i--) {
            if ((x[0] == x[i] && y[0] == y[i])) {
                andando = false;
                break;
            }
        }
        // checa se a cabeça tocou na borda esquerda
        if (x[0] < 0) {
            andando = false;
        }
        // checa se a cabeça tocou na borda direita
        if (x[0] > LARGURA_TELA) {
            andando = false;
        }
        // checa se a cabeça tocou a borda de cima
        if (y[0] < 0) {
            andando = false;
        }
        // checa se a cabeça tocou a borda de baixo
        if (y[0] > ALTURA_TELA) {
            andando = false;
        }
        if (!andando) {
            tempo.stop();
        }
    }

    public void FimDeJogo(Graphics g) {
        // texto de Fim de Jogo
        g.setColor(Color.red);
        g.setFont(new Font("Serif", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Fim de Jogo", (LARGURA_TELA - metrics.stringWidth("Fim de Jogo")) / 2, ALTURA_TELA / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (andando) {
            movimento();
            checarMaca();
            checarColisao();
        }
        repaint();
    }

    public class AdaptadorDeTecla extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direcao != 'D') {
                        direcao = 'E';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direcao != 'E') {
                        direcao = 'D';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direcao != 'B') {
                        direcao = 'C';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direcao != 'C') {
                        direcao = 'B';
                    }
                    break;
            }
        }
    }
}
