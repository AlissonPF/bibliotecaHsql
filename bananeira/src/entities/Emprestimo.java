package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Emprestimo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToOne
  private Livro livro;

  @OneToOne
  private Cliente cliente;
  private LocalDate dataEmpréstimo;
  private LocalDate dataDevolução;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Livro getLivro() {
    return livro;
  }

  public void setLivro(Livro livro) {
    this.livro = livro;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public LocalDate getDataEmpréstimo() {
    return dataEmpréstimo;
  }

  public void setDataEmpréstimo(LocalDate dataEmpréstimo) {
    this.dataEmpréstimo = dataEmpréstimo;
  }

  public LocalDate getDataDevolução() {
    return dataDevolução;
  }

  public void setDataDevolução(LocalDate dataDevolução) {
    this.dataDevolução = dataDevolução;
  }

}
