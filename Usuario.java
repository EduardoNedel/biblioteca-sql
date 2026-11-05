public abstract class Usuario {

    private final String nome;
    private int quantidadeEmprestada;

    public Usuario(String nome) {
        this.nome = nome;
        this.quantidadeEmprestada = 0;
    }

    // aluno e professor têm limites diferentes
    public abstract int getLimiteItens();

    public boolean atingiuLimite() {
        return quantidadeEmprestada >= getLimiteItens();
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeEmprestada() {
        return quantidadeEmprestada;
    }

    // controlados só pela Biblioteca
    void registrarEmprestimo() {
        quantidadeEmprestada++;
    }

    void registrarDevolucao() {
        if (quantidadeEmprestada > 0) {
            quantidadeEmprestada--;
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %d/%d itens",
                nome, getClass().getSimpleName(), quantidadeEmprestada, getLimiteItens());
    }
}
