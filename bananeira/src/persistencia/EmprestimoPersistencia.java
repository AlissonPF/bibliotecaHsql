package persistencia;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entities.Emprestimo;

public class EmprestimoPersistencia {
  public static boolean incluir(Emprestimo emprestimo) {
    try {
      LocalDate data1 = LocalDate.now();
      LocalDate data2 = data1.plusDays(14);

      emprestimo.setDataEmpréstimo(data1);
      emprestimo.setDataDevolução(data2);
      EntityManager manager = EntityManagerFactory.getInstance();
      manager.getTransaction().begin();
      manager.persist(emprestimo);
      manager.getTransaction().commit();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean excluir(Emprestimo emprestimo) {
    try {
      EntityManager manager = EntityManagerFactory.getInstance();
      manager.getTransaction().begin();
      manager.remove(emprestimo);
      manager.getTransaction().commit();
      return true;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static Emprestimo procurarPorCliente(Emprestimo emprestimo) {
    EntityManager manager = EntityManagerFactory.getInstance();
    Query consulta = manager.createQuery("from Emprestimo where cliente_id = :param");
    consulta.setParameter("param", emprestimo.getCliente().getId());
    List<Emprestimo> emprestimos = consulta.getResultList();
    if (!emprestimos.isEmpty()) {
      return emprestimos.get(0);
    }
    return null;
  }

  public static List<Emprestimo> listar() {
    EntityManager manager = EntityManagerFactory.getInstance();
    Query consulta = manager.createQuery("from Emprestimo");
    List<Emprestimo> emprestimos = consulta.getResultList();
    return emprestimos;
  }

  public static void atualizar(Emprestimo emprestimo) {
    EntityManager manager = EntityManagerFactory.getInstance();
    EntityTransaction transaction = manager.getTransaction();

    try {
      transaction.begin();
      Emprestimo emprestimoExistente = manager.find(Emprestimo.class, emprestimo.getId());

      if (emprestimoExistente != null) {
        LocalDate data = emprestimoExistente.getDataDevolução();
        LocalDate data2 = data.plusDays(14);

        emprestimoExistente.setDataDevolução(data2);
        transaction.commit();
      }
    } catch (Exception e) {
      if (transaction != null && transaction.isActive()) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }
}
