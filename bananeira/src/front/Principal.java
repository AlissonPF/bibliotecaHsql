package front;

public class Principal {
    public static void main(String[] args) {
        int opc = 0;

        do {
            System.out.println("----------MENU----------");
            System.out.println("1- Cliente");
            System.out.println("2- Livro");
            System.out.println("3- Emprestimo");
            System.out.println("4- Sair");
            opc = Console.readInt("Informe a opção: ");
            switch (opc) {
                case 1:
                    new AppCliente();
                    break;
                case 2:
                    new AppLivro();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Valor inválido!");
                    break;
            }
        } while (opc != 4);
    }
}
