package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entities.Livro;

public class LivroPersistencia {
  public static boolean incluir(Livro livro) {
    try {
      EntityManager manager = EntityManagerFactory.getInstance();
      manager.getTransaction().begin();
      manager.persist(livro);
      manager.getTransaction().commit();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean excluir(Livro livro) {
    try {
      EntityManager manager = EntityManagerFactory.getInstance();
      EntityTransaction transaction = manager.getTransaction();
      transaction.begin();
      Livro livroExistente = manager.find(Livro.class, livro.getId());

      if (livroExistente != null) {
        manager.remove(livroExistente);
        transaction.commit();
        return true;
      }

      transaction.rollback();
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static Livro procurarPorTitulo(Livro livro) {
    EntityManager manager = EntityManagerFactory.getInstance();
    Query consulta = manager.createQuery("from Livro where titulo = :param");
    consulta.setParameter("param", livro.getTitulo());
    List<Livro> livros = consulta.getResultList();
    if (!livros.isEmpty()) {
      return livros.get(0);
    }
    return null;
  }

  public static List<Livro> listar() {
    EntityManager manager = EntityManagerFactory.getInstance();
    Query consulta = manager.createQuery("from Livro");
    List<Livro> livros = consulta.getResultList();
    return livros;
  }

  public static List<Livro> listarEmprestados() {
    EntityManager manager = EntityManagerFactory.getInstance();
    Query consulta = manager.createQuery("SELECT emprestimo.livro FROM Emprestimo emprestimo");
    List<Livro> livros = consulta.getResultList();
    return livros;
  }

  public static void atualizar(Livro livro) {
    try {
      EntityManager manager = EntityManagerFactory.getInstance();
      EntityTransaction transaction = manager.getTransaction();
      transaction.begin();
      Livro livroExistente = manager.find(Livro.class, livro.getId());

      if (livroExistente != null) {
        livroExistente.setTitulo(livro.getTitulo());
        livroExistente.setAutor(livro.getAutor());
      }

      transaction.commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Livro procurarPorId(Livro livro) {
    EntityManager manager = EntityManagerFactory.getInstance();
    Query consulta = manager.createQuery("from Livro where id = :param");
    consulta.setParameter("param", livro.getId());
    List<Livro> livros = consulta.getResultList();
    if (!livros.isEmpty()) {
      return livros.get(0);
    }
    return null;
  }

  public static boolean verificarEmprestado(Livro livro) {
    try {
      EntityManager manager = EntityManagerFactory.getInstance();
      Query consulta = manager
          .createQuery("SELECT COUNT(*) FROM Emprestimo emprestimo WHERE emprestimo.livro = :livro");
      consulta.setParameter("livro", livro);
      Long count = (Long) consulta.getSingleResult();
      return count > 0;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

}
