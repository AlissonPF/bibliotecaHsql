package front;

import java.util.List;

import entities.Cliente;
import negocio.ValidaCPF;
import persistencia.ClientePersistencia;

public class AppCliente {
  public AppCliente() {
    int opc = 0;
    do {
      System.out.println("\n\n*****Cliente*****");
      System.out.println("1- Cadastrar");
      System.out.println("2- Buscar");
      System.out.println("3- Listar");
      System.out.println("4- Listar clientes com empréstimo ativo");
      System.out.println("5- Atualizar");
      System.out.println("6- Deletar");
      System.out.println("7- Voltar");
      opc = Console.readInt("Informe a opção: ");
      switch (opc) {
        case 1:
          incluirCliente();
          break;
        case 2:
          buscarCliente();
          break;
        case 3:
          listarClientes();
          break;
        case 4:
          listarClientesEmprestados();
          break;
        case 5:
          atualizarCliente();
          break;
        case 6:
          deletarCliente();
          break;
        case 7:
          break;
        default:
          System.out.println("Valor inválido!");
          break;
      }

    } while (opc != 7);
  }

  public void incluirCliente() {
    System.out.println("\n\n*****Cadastro de cliente*****");
    Cliente objCliente = new Cliente();

    objCliente.setCpf(Console.readString("Informe o cpf: "));
    if (ValidaCPF.isCPF(objCliente.getCpf())) {
      if (ClientePersistencia.procurarPorCPF(objCliente) == null) {
        objCliente.setNome(Console.readString("Informe o nome: "));
        objCliente.setIdade(Console.readInt("Informe a idade:"));
        if (ClientePersistencia.incluir(objCliente)) {
          System.out.println("Cadastro realizado com sucesso!");
        } else {
          System.out.println("Algo deu errado na hora de cadastrar o cliente!");
        }
      } else {
        System.out.println("Cpf já cadastrado!");
      }
    } else {
      System.out.println("CPF inválido!");
    }
  }

  // -------------------------------------------------------------------------------------------------

  public void buscarCliente() {
    System.out.println("\n\n*****Buscar cliente*****");
    Cliente objCliente = new Cliente();

    objCliente.setCpf(Console.readString("Informe o cpf: "));
    objCliente = ClientePersistencia.procurarPorCPF(objCliente);
    if (objCliente != null) {
      System.out.println("--------------------");
      System.out.println("Id: " + objCliente.getId());
      System.out.println("Cpf: " + objCliente.getCpf());
      System.out.println("Nome: " + objCliente.getNome());
      System.out.println("Idade: " + objCliente.getIdade());
      System.out.println("--------------------");
    } else {
      System.out.println("Cliente não encontrado!");
    }
  }

  // -------------------------------------------------------------------------------------------------

  public void deletarCliente() {
    System.out.println("\n\n*****Remoção de cliente*****");
    Cliente objCliente = new Cliente();

    objCliente.setCpf(Console.readString("Informe o cpf: "));
    objCliente = ClientePersistencia.procurarPorCPF(objCliente);
    if (objCliente != null && !ClientePersistencia.verificarEmprestado(objCliente)) {
      if (ClientePersistencia.excluir(objCliente)) {
        ClientePersistencia.excluir(objCliente);
        System.out.println("Cliente removido com sucesso!");
      } else {
        System.out.println("Algo deu errado na hora de remover o cliente!");
      }
    } else {
      System.out.println("Cliente não encontrado ou com empréstimo ativo!");
    }
  }

  // -------------------------------------------------------------------------------------------------

  public void listarClientes() {
    System.out.println("*****Clientes*****\n");
    List<Cliente> clientes = ClientePersistencia.listar();

    if (clientes != null) {
      System.out.println("--------------------");
      for (Cliente itemCliente : clientes) {
        System.out.println("Id: " + itemCliente.getId());
        System.out.println("Cpf: " + itemCliente.getCpf());
        System.out.println("Nome: " + itemCliente.getNome());
        System.out.println("Idade: " + itemCliente.getIdade());
        System.out.println("--------------------");
      }
    } else {
      System.out.println("Sem clientes cadastrados!");
    }

  }

  // -------------------------------------------------------------------------------------------------

  public void atualizarCliente() {
    System.out.println("\n\n*****Atualizar cliente*****");
    Cliente objCliente = new Cliente();

    objCliente.setCpf(Console.readString("Informe o cpf: "));
    objCliente = ClientePersistencia.procurarPorCPF(objCliente);

    if (objCliente != null
        && !ClientePersistencia.verificarEmprestado(objCliente)) {
      objCliente.setNome(Console.readString("Informe o nome: "));
      objCliente.setIdade(Console.readInt("Informe a idade: "));
      ClientePersistencia.atualizar(objCliente);
      System.out.println("Cliente atualizado com sucesso!");
    } else {
      System.out.println("Cliente não encontrado ou com empréstimo ativo!");
    }
  }

  public void listarClientesEmprestados() {
    System.out.println("*****Clientes*****\n");
    List<Cliente> clientes = ClientePersistencia.listarEmprestados();

    if (clientes != null) {
      System.out.println("--------------------");
      for (Cliente itemCliente : clientes) {
        System.out.println("Id: " + itemCliente.getId());
        System.out.println("Cpf: " + itemCliente.getCpf());
        System.out.println("Nome: " + itemCliente.getNome());
        System.out.println("Idade: " + itemCliente.getIdade());
        System.out.println("--------------------");
      }
    } else {
      System.out.println("Sem Clientes emprestados!");
    }
  }
}
