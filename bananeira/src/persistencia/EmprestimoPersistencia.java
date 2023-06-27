package persistencia;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Cliente;
import entities.Emprestimo;
import entities.Livro;

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
}
