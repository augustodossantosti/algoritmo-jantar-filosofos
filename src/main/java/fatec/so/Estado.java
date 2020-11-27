package fatec.so;

public enum Estado {

    PENSANDO(0, "PENSANDO"),
    COM_FOME(1, "COM FOME"),
    COMENDO(2, "COMENDO");

    private final int codigo;
    private final String descricao;

    Estado(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
