package fatec.so;

public class Filosofo extends Thread
{
    private final int id;

    public Filosofo (final String nome, final int id)
    {
        super(nome);
        this.id = id;
    }

    public void estouComFome()
    {
        Grade.estado[this.id] = Estado.COM_FOME.getCodigo();
        System.out.println("O Filósofo " + getName() + " está " + Estado.COM_FOME.getDescricao() + "!");
    }

    public void estouComendo()
    {
        Grade.estado[this.id] = Estado.COMENDO.getCodigo();
        System.out.println("O Filósofo " + getName() + " está " + Estado.COMENDO.getDescricao() + "!");

        try
        {
            Thread.sleep(1000L);
        }
        catch (InterruptedException ex)
        {
            System.out.println("ERROR>" + ex.getMessage());
        }
    }

    public void estouPensando()
    {
        Grade.estado[this.id] = Estado.PENSANDO.getCodigo();
        System.out.println("O Filósofo " + getName() + " está " + Estado.PENSANDO.getDescricao() + "!");

        try
        {
            Thread.sleep(1000L);
        }
        catch (InterruptedException ex)
        {
            System.out.println("ERROR>" + ex.getMessage());
        }
    }

    public void largarGarfo()
    {
        Grade.mutex.decrementar();

        estouPensando();

        Grade.filosofo[verVizinhoEsquerda()].tentarPegarGarfos();
        Grade.filosofo[verVizinhoDireita()].tentarPegarGarfos();

        Grade.mutex.incrementar();
    }

    public void pegarGarfo()
    {
        Grade.mutex.decrementar();

        estouComFome();

        tentarPegarGarfos();

        Grade.mutex.incrementar();
        Grade.semaforos[this.id].decrementar();
    }

    public void tentarPegarGarfos()
    {
        if (Grade.estado[this.id] == Estado.COM_FOME.getCodigo() &&
                Grade.estado[verVizinhoEsquerda()] != Estado.COMENDO.getCodigo() &&
                Grade.estado[verVizinhoDireita()] != Estado.COMENDO.getCodigo())
        {
            estouComendo();
            Grade.semaforos[this.id].incrementar();
        }
    }

    @Override
    public void run ()
    {
        try
        {
            estouPensando();
            do
            {
                pegarGarfo();
                Thread.sleep(1000L);
                largarGarfo();
            }
            while (true);
        }
        catch (InterruptedException ex)
        {
            System.out.println("ERROR>" + ex.getMessage());
            return;
        }

    }

    public int verVizinhoDireita()
    {
        // Rationa o valor em 5 posições, ou seja, se o ID deste filósofo acrescentado
        // de um for maior que quatro, passa a ser zero
        return (this.id + 1) % 5;
    }

    public int verVizinhoEsquerda()
    {
        if (this.id == 0)
        {
            // Retorna a ultima posição
            return 4;
        }
        else
        {
            // Rationa o valor em 5 posições, ou seja, se o ID deste filósofo decrescido
            // de um for menor que zero, passa a ser quatro
            return (this.id - 1) % 5;
        }
    }

}
