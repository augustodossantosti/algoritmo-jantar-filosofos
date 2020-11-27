package fatec.so;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Grade extends JPanel implements Runnable
{
    private final static int QUANTIDADE_FILOSOFOS = 5;

    String mensagem = "";

    Thread animador;

    public static Semaforo mutex = new Semaforo(1);
    public static Semaforo[] semaforos = new Semaforo[QUANTIDADE_FILOSOFOS];
    public static int[] estado = new int[QUANTIDADE_FILOSOFOS];
    static Filosofo[] filosofo = new Filosofo[QUANTIDADE_FILOSOFOS];

    public Grade ()
    {
        setFocusable(true);
        setSize(400, 400);
        setBackground(Color.white);
        init();
    }

    public void init ()
    {
        Arrays.fill(estado, 0);

        if(animador == null)
        {
            animador = new Thread(this);
            animador.start();
        }

        Thread.currentThread().setPriority(1);

        filosofo[0] = new Filosofo("Platao", 0);
        filosofo[1] = new Filosofo("Socrates", 1);
        filosofo[2] = new Filosofo("Aristoteles", 2);
        filosofo[3] = new Filosofo("Tales", 3);
        filosofo[4] = new Filosofo("Sofocles", 4);

        semaforos[0] = new Semaforo();
        semaforos[1] = new Semaforo();
        semaforos[2] = new Semaforo();
        semaforos[3] = new Semaforo();
        semaforos[4] = new Semaforo();

        filosofo[0].start();
        filosofo[1].start();
        filosofo[2].start();
        filosofo[3].start();
        filosofo[4].start();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        g.setColor(Color.blue);
        g.drawOval(50, 50, 300, 300);

        for(int i = 0; i < QUANTIDADE_FILOSOFOS; i++)
        {
            if(estado[i] == Estado.PENSANDO.getCodigo())
            {
                g.setColor(Color.gray);
                mensagem = Estado.PENSANDO.getDescricao();
            }
            if(estado[i] == Estado.COM_FOME.getCodigo())
            {
                g.setColor(Color.yellow);
                mensagem = Estado.COM_FOME.getDescricao();
            }
            if(estado[i] == Estado.COMENDO.getCodigo())
            {
                g.setColor(Color.green);
                mensagem = Estado.COMENDO.getDescricao();
            }

            g.fillOval((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 15, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 15, 30, 30);
            g.setColor(Color.black);
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 5, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) + 5, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 5, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) + 5);
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 2, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 3, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 2, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)));
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 2, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)), (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 2, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)));
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 8, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 8, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 3, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 8);
            g.drawLine((int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 3, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 8, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) + 8, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) - 8);
            g.drawString(filosofo[i].getName(), (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 15, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) + 25);
            g.drawString(mensagem, (int)(200D - 100D * Math.cos(1.2566370614359172D * (double)i)) - 15, (int)(200D - 100D * Math.sin(1.2566370614359172D * (double)i)) + 40);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void run ()
    {
        do
        {
            repaint();
            try
            {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex)
            {
                System.out.println("ERROR>" + ex.getMessage());
            }
        }
        while (true);
    }

}
