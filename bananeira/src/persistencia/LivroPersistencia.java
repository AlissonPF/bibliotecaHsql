package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
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
      manager.getTransaction().begin();
      manager.remove(livro);
      manager.getTransaction().commit();
      return true;

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

  public static void atualizar(Livro livro, String novoTitulo) {
    EntityManager manager = EntityManagerFactory.getInstance();

    Query consulta = manager.createQuery("UPDATE Livro SET titulo = :titulo, autor = :autor WHERE titulo = :titulo");
    consulta.setParameter("titulo", novoTitulo);
    consulta.setParameter("autor", livro.getAutor());
    consulta.setParameter("titulo", livro.getTitulo());

    int linhasAtualizadas = consulta.executeUpdate();

    if (linhasAtualizadas > 0) {
      manager.getTransaction().commit();
    } else {
      manager.getTransaction().rollback();
    }
  }
}
