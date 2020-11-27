package fatec.so;

import javax.swing.JFrame;

public class JantarDosFilosofos extends JFrame
{

    public JantarDosFilosofos ()
    {
        add(new Grade());

        setTitle("Jantar dos Filósofos - SO");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(820, 820);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args)
    {
        new JantarDosFilosofos();
    }

}
