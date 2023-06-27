package front;

import entities.Cliente;
import entities.Emprestimo;
import entities.Livro;
import persistencia.ClientePersistencia;
import persistencia.EmprestimoPersistencia;
import persistencia.LivroPersistencia;

public class AppEmprestimo {
    public AppEmprestimo() {
    int opc;
    do {
      System.out.println("\n\n*****Empréstimo*****");
      System.out.println("1- Cadastrar");
      System.out.println("2- Buscar");
      System.out.println("3- Listar");
      System.out.println("4- Renovar");
      System.out.println("5- Deletar");
      System.out.println("6- Voltar");
      opc = Console.readInt("Informe a opção: ");
      switch (opc) {
        case 1:
          incluirEmprestimo();
          break;
        case 2:
          //buscarEmprestimo();
          break;
        case 3:
          //listarEmprestimos();
          break;
        case 4:
          //atualizarEmprestimo();
          break;
        case 5:
          deletarEmprestimo();
          break;
        case 6:
          break;
        default:
          System.out.println("Valor inválido!");
          break;
      }

    } while (opc != 6);
  }

    public void incluirEmprestimo(){
    System.out.println("\n\n*****Cadastro de empréstimo*****");
    Emprestimo objEmprestimo = new Emprestimo();
    Cliente objCliente = new Cliente();
    Livro objLivro = new Livro();

    objCliente.setCpf(Console.readString("Informe o cpf do cliente: "));
    objLivro.setId(Console.readInt("Informe o id do livro: "));

    objCliente = ClientePersistencia.procurarPorCPF(objCliente);
    objLivro = LivroPersistencia.procurarPorId(objLivro);

    
    if(objCliente != null && objLivro != null){
        objEmprestimo.setCliente(objCliente);
        if(EmprestimoPersistencia.procurarPorCliente(objEmprestimo) == null){
            objEmprestimo.setCliente(objCliente);
            objEmprestimo.setLivro(objLivro);
            if(EmprestimoPersistencia.incluir(objEmprestimo)){
                System.out.println("Emprestimo cadastrado!");
            }else{
                System.out.println("Cliente ou livro não encontrado!");
            }
        }else{
            System.out.println("Emprestimo ja existe!");
        }
    }
    }

    public void deletarEmprestimo(){
        System.out.println("\n\n*****Devolução de empréstimo*****");
        Emprestimo objEmprestimo = new Emprestimo();
        Cliente objCliente = new Cliente();

        objCliente.setCpf(Console.readString("Informe o cpf do cliente: "));
        objCliente = ClientePersistencia.procurarPorCPF(objCliente);

        objEmprestimo.setCliente(objCliente);
        if(EmprestimoPersistencia.procurarPorCliente(objEmprestimo) != null){
            objEmprestimo = EmprestimoPersistencia.procurarPorCliente(objEmprestimo);
            if(EmprestimoPersistencia.excluir(objEmprestimo)){
                System.out.println("Empréstimo deletado com sucesso!");
            }else{
                System.out.println("Algo deu errado na hora de deletar o emprestimo!");
            }
        }else{
            System.out.println("Emprestimo não encontrado!");
        }
    }
}