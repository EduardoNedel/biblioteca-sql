public class Biblioteca {

    private final ItemBiblioteca[] acervo;
    // mesma posição do acervo: guarda com quem o item está (null = ninguém)
    private final Usuario[] emprestadoPara;
    private int totalItens;

    public Biblioteca(int capacidade) {
        this.acervo = new ItemBiblioteca[capacidade];
        this.emprestadoPara = new Usuario[capacidade];
        this.totalItens = 0;
    }

    public boolean adicionarItem(ItemBiblioteca item) {
        if (totalItens == acervo.length) {
            System.out.println("Acervo cheio, não foi possível cadastrar: " + item.getTitulo());
            return false;
        }
        acervo[totalItens] = item;
        totalItens++;
        return true;
    }

    public boolean emprestar(ItemBiblioteca item, Usuario usuario) {
        int posicao = buscarPosicao(item);
        if (posicao == -1) {
            System.out.println("Item não pertence ao acervo: " + item.getTitulo());
            return false;
        }
        if (!item.isDisponivel()) {
            System.out.println("Empréstimo recusado: \"" + item.getTitulo() + "\" já está emprestado.");
            return false;
        }
        if (usuario.atingiuLimite()) {
            System.out.println("Empréstimo recusado: " + usuario.getNome()
                    + " já atingiu o limite de " + usuario.getLimiteItens() + " itens.");
            return false;
        }

        item.marcarComoEmprestado();
        usuario.registrarEmprestimo();
        emprestadoPara[posicao] = usuario;

        System.out.println("Empréstimo realizado: \"" + item.getTitulo() + "\" para " + usuario.getNome()
                + " (devolver em até " + item.getPrazoDias() + " dias).");
        return true;
    }

    // devolve o item e retorna o valor da multa (0 se não houve atraso)
    // diasDeAtraso = quantos dias passaram do prazo
    public double devolver(ItemBiblioteca item, Usuario usuario, int diasDeAtraso) {
        int posicao = buscarPosicao(item);
        if (posicao == -1 || emprestadoPara[posicao] != usuario) {
            System.out.println("Devolução inválida: \"" + item.getTitulo() + "\" não está emprestado para "
                    + usuario.getNome() + ".");
            return 0;
        }

        item.marcarComoDisponivel();
        usuario.registrarDevolucao();
        emprestadoPara[posicao] = null;

        double multa = item.calcularMulta(diasDeAtraso);
        System.out.println("Devolução registrada: \"" + item.getTitulo() + "\" por " + usuario.getNome()
                + String.format(" - multa: R$ %.2f", multa));
        return multa;
    }

    public void listarAcervo() {
        System.out.println("---- ACERVO ----");
        for (int i = 0; i < totalItens; i++) {
            System.out.println(acervo[i]);
        }
        System.out.println("----------------");
    }

    private int buscarPosicao(ItemBiblioteca item) {
        for (int i = 0; i < totalItens; i++) {
            if (acervo[i] == item) {
                return i;
            }
        }
        return -1;
    }
}
