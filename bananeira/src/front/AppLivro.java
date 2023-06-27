package front;

import java.util.List;

import entities.Livro;
import persistencia.LivroPersistencia;

public class AppLivro {
  public AppLivro() {
    int opc;
    do {
      System.out.println("\n\n*****Livro*****");
      System.out.println("1- Cadastrar");
      System.out.println("2- Buscar");
      System.out.println("3- Listar");
      System.out.println("4- Listar livros emprestados");
      System.out.println("5- Atualizar");
      System.out.println("6- Deletar");
      System.out.println("7- Voltar");
      opc = Console.readInt("Informe a opção: ");
      switch (opc) {
        case 1:
          incluirLivro();
          break;
        case 2:
          buscarLivro();
          break;
        case 3:
          listarLivros();
          break;
        case 4:
          // listarLivrosEmprestados();
          break;
        case 5:
          atualizarLivro();
          break;
        case 6:
          deletarLivro();
          break;
        case 7:
          break;
        default:
          System.out.println("Valor inválido!");
          break;
      }

    } while (opc != 7);

  }

  public void incluirLivro() {
    System.out.println("\n\n*****Cadastro de livro*****");
    Livro objLivro = new Livro();

    objLivro.setTitulo(Console.readString("Informe o titulo: "));
    if (LivroPersistencia.procurarPorTitulo(objLivro) == null) {
      objLivro.setAutor(Console.readString("Informe o autor: "));
      if (LivroPersistencia.incluir(objLivro)) {
        System.out.println("Cadastro realizado com sucesso!");
      } else {
        System.out.println("Algo deu errado na hora de cadastrar o livro!");
      }
    } else {
      System.out.println("Livro já cadastrado!");
    }
  }

  // -------------------------------------------------------------------------------------------------

  public void buscarLivro() {
    System.out.println("\n\n*****Buscar livro*****");
    Livro objLivro = new Livro();

    objLivro.setTitulo(Console.readString("Informe o titulo: "));
    objLivro = LivroPersistencia.procurarPorTitulo(objLivro);
    if (objLivro != null) {
      System.out.println("--------------------");
      System.out.println("Id: " + objLivro.getId());
      System.out.println("Titulo: " + objLivro.getTitulo());
      System.out.println("Autor: " + objLivro.getAutor());
      System.out.println("--------------------");
    } else {
      System.out.println("Livro não encontrado!");
    }
  }

  // -------------------------------------------------------------------------------------------------

  public void deletarLivro() {
    System.out.println("\n\n*****Remoção de livro*****");
    Livro objLivro = new Livro();

    objLivro.setTitulo(Console.readString("Informe o titulo: "));
    objLivro = LivroPersistencia.procurarPorTitulo(objLivro);
    if (objLivro != null) {
      if (LivroPersistencia.excluir(objLivro)) {
        LivroPersistencia.excluir(objLivro);
        System.out.println("Livro removido com sucesso!");
      } else {
        System.out.println("Algo deu errado na hora de remover o livro!");
      }
    } else {
      System.out.println("Livro não encontrado!");
    }
  }

  // -------------------------------------------------------------------------------------------------

  public void listarLivros() {
    System.out.println("*****Livros*****\n");
    List<Livro> livros = LivroPersistencia.listar();

    if (livros != null) {
      System.out.println("--------------------");
      for (Livro itemLivro : livros) {
        System.out.println("Id: " + itemLivro.getId());
        System.out.println("Titulo: " + itemLivro.getTitulo());
        System.out.println("Autor: " + itemLivro.getAutor());
        System.out.println("--------------------");
      }
    } else {
      System.out.println("Sem clientes cadastrados!");
    }

  }

  // -------------------------------------------------------------------------------------------------

  // public void atualizarLivro() {
  //   System.out.println("\n\n*****Atualizar livro*****");
  //   Livro objLivro = new Livro();
  //   String novoTitulo;
  //   objLivro.setTitulo(Console.readString("Informe o titulo: "));
  //   if (LivroPersistencia.procurarPorTitulo(objLivro) != null) {
  //     novoTitulo = Console.readString("Informe o novo titulo: ");
  //     objLivro.setAutor(Console.readString("Informe o autor: "));
  //     LivroPersistencia.atualizar(objLivro, novoTitulo);
  //     System.out.println("Livro atualizado com sucesso!");
  //   } else {
  //     System.out.println("Livro não encontrado!");
  //   }
  // }

  public void atualizarLivro() {
    System.out.println("\n\n*****Atualizar livro*****");
    Livro objLivro = new Livro();
    objLivro.setId(Console.readInt("Informe o id: "));
    if (LivroPersistencia.procurarPorId(objLivro) != null) {
      objLivro.setTitulo(Console.readString("Informe o titulo: "));
      objLivro.setAutor(Console.readString("Informe o autor: "));
      LivroPersistencia.atualizar(objLivro);
      System.out.println("Livro atualizado com sucesso!");
    } else {
      System.out.println("Livro não encontrado!");
    }
  }

  
}
