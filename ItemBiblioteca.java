public abstract class ItemBiblioteca {

    private final String codigo;
    private final String titulo;
    private boolean disponivel;

    public ItemBiblioteca(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.disponivel = true; // todo item novo entra disponível no acervo
    }

    // prazo e multa mudam de acordo com o tipo do item
    public abstract int getPrazoDias();

    public abstract double getMultaPorDia();

    public double calcularMulta(int diasDeAtraso) {
        if (diasDeAtraso <= 0) {
            return 0;
        }
        return diasDeAtraso * getMultaPorDia();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    // sem modificador de acesso de propósito: só a classe Biblioteca (mesmo pacote)
    // mexe na disponibilidade, como consequência de emprestar() e devolver()
    void marcarComoEmprestado() {
        this.disponivel = false;
    }

    void marcarComoDisponivel() {
        this.disponivel = true;
    }

    @Override
    public String toString() {
        String situacao = disponivel ? "disponível" : "emprestado";
        return String.format("[%s] %s - %s (prazo: %d dias, multa: R$ %.2f/dia) - %s",
                codigo, getClass().getSimpleName(), titulo, getPrazoDias(), getMultaPorDia(), situacao);
    }
}
