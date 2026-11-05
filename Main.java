public class Main {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(10);

        // cadastro do acervo
        Livro l1 = new Livro("L001", "Java: Como Programar", "Paul Deitel");
        Livro l2 = new Livro("L002", "Código Limpo", "Robert C. Martin");
        Livro l3 = new Livro("L003", "Dom Casmurro", "Machado de Assis");
        Revista r1 = new Revista("R001", "Superinteressante", 450);
        Revista r2 = new Revista("R002", "Mundo Estranho", 210);

        biblioteca.adicionarItem(l1);
        biblioteca.adicionarItem(l2);
        biblioteca.adicionarItem(l3);
        biblioteca.adicionarItem(r1);
        biblioteca.adicionarItem(r2);

        // cadastro de usuários
        Aluno ana = new Aluno("Ana Souza");
        Professor carlos = new Professor("Carlos Lima");

        biblioteca.listarAcervo();

        // a Ana (aluna, limite 3) pega 3 itens sem problema
        System.out.println("\n>> Empréstimos da Ana");
        biblioteca.emprestar(l1, ana);
        biblioteca.emprestar(l2, ana);
        biblioteca.emprestar(r1, ana);

        // o quarto deve ser recusado por limite atingido
        System.out.println("\n>> Quarto empréstimo da Ana (deve ser recusado)");
        biblioteca.emprestar(l3, ana);

        // o professor pega um item que a Ana não pegou
        System.out.println("\n>> Empréstimo do professor");
        biblioteca.emprestar(l3, carlos);

        // tentar pegar item que já está emprestado
        System.out.println("\n>> Item já emprestado");
        biblioteca.emprestar(l1, carlos);

        System.out.println();
        biblioteca.listarAcervo();

        // devolução da revista com 3 dias de atraso: 3 x R$ 1,00
        System.out.println("\n>> Devolução com atraso");
        double multa = biblioteca.devolver(r1, ana, 3);
        System.out.println("Multa cobrada da Ana: R$ " + String.format("%.2f", multa));

        // agora a Ana tem uma vaga de novo
        System.out.println("\n>> Ana tenta de novo depois da devolução");
        biblioteca.emprestar(r2, ana);

        System.out.println();
        System.out.println(ana);
        System.out.println(carlos);
        biblioteca.listarAcervo();
    }
}
