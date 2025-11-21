 import java.util.Scanner;

public class ControledeEstoque {
    
    public static Scanner input = new Scanner(System.in);

/*produtos[total] = new Produto(); // cria produto
cadastrarProduto(produtos[total]);
total++;   OBS: COLOCAR NA OPÇÃO 1 */

    public static Produto[] produtos;
    public static int total = 0;

    public static void cadastrarProduto(Produto al){

        System.out.println("Digite o nome do produto: ");
        al.nome = input.nextLine();
        System.out.println("Digite a quantidade em estoque: ");
        al.qtd = input.nextInt();
        System.out.println("Digite o preço do produto: ");
        al.preco = input.nextDouble();

        input.nextLine();
        System.out.println("Digite a categoria: ");
        al.categoria = input.nextLine();
        System.out.println("Digite a quantidade mínima: ");
        al.qtdminima = input.nextInt();
        input.nextLine();

       System.out.println("Produto cadastrado com sucesso!");

    }

    public static void imprimir(Produto al){

        System.out.println("Nome: " + al.nome);
        System.out.println("Quantidade: " + al.qtd);
        System.out.println("Preço unitário: " + al.preco);
        System.out.println("Categoria: " + al.categoria);
        System.out.println("Quantidade Mínima: " + al.qtdminima);

    }

    public static void listar(Produto[] v) {

    if (total == 0) {
        System.out.println("Nenhum produto cadastrado!");
    } else {
        for (int i = 0; i < total; i++) {
            imprimir(produtos[i]);
            System.out.println("------------------");
        }
    }
}

    public static void filtrarPorCategoria(){

        System.out.println("Qual a categoria que você deseja filtrar?");
        String categoriaEscolhida = input.nextLine();
        for(int i = 0; i < total; i++){

            if (mesmaString(produtos[i].categoria, categoriaEscolhida)){

                imprimir(produtos[i]);
            }

        }

    }

    public static boolean nomeMaior(String a, String b) {

    char[] A = a.toCharArray();
    char[] B = b.toCharArray();

    int tam = (A.length < B.length) ? A.length : B.length;

    for (int i = 0; i < tam; i++) {

        if (A[i] > B[i]) {
            return true;
        }

        if (A[i] < B[i]) {
            return false;
        }
    }
    return A.length > B.length;
}

        public static void bubbleSortNome(Produto[] v, int total) {

    for (int i = 0; i < total - 1; i++) {
        for (int j = 0; j < total - 1 - i; j++) {

            // usando a comparação manual
            if (nomeMaior(v[j].nome, v[j + 1].nome)) {

                Produto aux = v[j];
                v[j] = v[j + 1];
                v[j + 1] = aux;
            }
        }
    }
}

    public static void removerElemento(Produto[] v){

        System.out.println("Digite o nome do produto  que você quer remover");
        String produtoRemovido = input.nextLine();
        for(int i = 0; i < total; i++){

            if(mesmaString(produtos[i].nome, produtoRemovido)){
            for (int j = i; j < total - 1; j++) {
                v[j] = v[j + 1];
            }
            total--; 

            System.out.println("Produto removido com sucesso!");
            return;
        }
    }

    System.out.println("Produto não encontrado.");
}

   public static void atualizarPreco(Produto[] v){

    System.out.println("Qual o produto que você irá trocar o preço?");
    String produtotrocado = input.nextLine();
    for(int i = 0; i < total; i++){
        if(mesmaString(produtos[i].nome, produtotrocado)){

            System.out.println("Digite o novo preço:");
            double novoPreco = input.nextDouble();

            v[i].preco = novoPreco;
            input.nextLine();

            System.out.println("Preço atualizado com sucesso!");
            return;
        }
    }
        System.out.println("Produto não encontrado");
        }

    public static boolean mesmaString(String a, String b){
    if (a.length() != b.length()) {
        return false;
    }

    for (int i = 0; i < a.length(); i++) {
        if (a.charAt(i) != b.charAt(i)) {
            return false;
        }
    }
    return true;
}

    public static double listarPorCategoria(Produto[] produtos) {

        System.out.println("Digite a categoria:");
        String categoriaBuscada = input.nextLine();

        double subtotal = 0;
        boolean achou = false;

        for (int i = 0; i < total; i++) {

            if (mesmaString(produtos[i].categoria, categoriaBuscada)) {

                System.out.println("Nome: " + produtos[i].nome);
                System.out.println("Preço: " + produtos[i].preco);
                System.out.println("------------------------");

                subtotal += produtos[i].preco * produtos[i].qtd;
                achou = true;
            }
        }

        if (!achou) {
            System.out.println("Nenhum produto encontrado.");
        }

        return subtotal;
    }


    public static void main(String[] args) {
     
        System.out.println("Quantos produtos deseja armazenar?");
        int n = input.nextInt();

        produtos = new Produto[n];
        input.nextLine();

        int opcao;
        do{
        
        System.out.println("----MENU----");
        System.out.println();
        System.out.println("OPÇÃO 1: CADASTRAR UM PRODUTO");
        System.out.println("OPÇÃO 2: LISTAR");
        System.out.println("OPÇÃO 3: FILTRAR POR CATEGORIA");
        System.out.println("OPÇÃO 4: ORDENAR");
        System.out.println("OPÇÃO 5: REMOVER ELEMENTO");
        System.out.println("OPÇÃO 6: ATUALIZAR PREÇO");
        System.out.println("OPÇÃO 7: LISTAGEM COM SUBTOTAL DO VALOR EM ESTOQUE POR CATEGORIA");
        
        System.out.println("Escolha sua opção:  ");
        opcao = input.nextInt();
        input.nextLine();

            switch (opcao) {
                case 1:
                if (total < produtos.length) {
                    produtos[total] = new Produto();
                    cadastrarProduto(produtos[total]);
                    total++;
                    } else {
                        System.out.println("Limite atingido!");
                    }
                    break;

                case 2: 
                listar(produtos);
                break;

                case 3:
                filtrarPorCategoria();
                break;

                case 4:

                bubbleSortNome(produtos, total);
                break;

                case 5:

                removerElemento(produtos);
                break;

                case 6:
                atualizarPreco(produtos);
                break;

                case 7:
                double subtotal = listarPorCategoria(produtos);
                System.out.println("Subtotal da categoria = " + subtotal);
                break;
            
                default:
                System.out.println("Nenhuma opção, tente novamente!");
                    break;
            }

        } while(opcao != 0);
    }

}
