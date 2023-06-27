package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Cliente;

public class ClientePersistencia {
  public static boolean incluir(Cliente cliente) {
    try {
      EntityManager manager = EntityManagerFactory.getInstance();
      manager.getTransaction().begin();
      manager.persist(cliente);
      manager.getTransaction().commit();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean excluir(Cliente cliente) {
    try {
      EntityManager manager = EntityManagerFactory.getInstance();
      manager.getTransaction().begin();
      manager.remove(cliente);
      manager.getTransaction().commit();
      return true;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static Cliente procurarPorCPF(Cliente cliente) {
    EntityManager manager = EntityManagerFactory.getInstance();
    Query consulta = manager.createQuery("from Cliente where cpf = :param");
    consulta.setParameter("param", cliente.getCpf());
    List<Cliente> clientes = consulta.getResultList();
    if (!clientes.isEmpty()) {
      return clientes.get(0);
    }
    return null;
  }

  public static List<Cliente> listar() {
    EntityManager manager = EntityManagerFactory.getInstance();
    Query consulta = manager.createQuery("from Cliente");
    List<Cliente> clientes = consulta.getResultList();
    return clientes;
  }

  public static void atualizar(Cliente cliente) {
    EntityManager manager = EntityManagerFactory.getInstance();

    Query consulta = manager.createQuery("UPDATE Cliente SET nome = :nome, idade = :idade WHERE cpf = :cpf");
    consulta.setParameter("nome", cliente.getNome());
    consulta.setParameter("idade", cliente.getIdade());
    consulta.setParameter("cpf", cliente.getCpf());

    int linhasAtualizadas = consulta.executeUpdate();

    if (linhasAtualizadas > 0) {
      manager.getTransaction().commit();
    } else {
      manager.getTransaction().rollback();
    }
  }
}
