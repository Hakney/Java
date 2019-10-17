/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author hakney.lima
 */
public class UsuarioDAO {
  /**
   * Método utilizado para obter o entity manager.
   * @return
   */
  private EntityManager getEntityManager() {
    EntityManagerFactory factory;
    EntityManager entityManager;
      //Obtém o factory a partir da unidade de persistência.
      factory = Persistence.createEntityManagerFactory("funcionarios");
      //Cria um entity manager.
      entityManager = factory.createEntityManager();
      //Fecha o factory para liberar os recursos utilizado.
    
    return entityManager;
  }

  /**
   * Método utilizado para salvar ou atualizar as informações de uma pessoa.

   * @param usuario
   * @return
   * @throws java.lang.Exception
   */
  public Usuario cadastrar(Usuario usuario) throws Exception {
    EntityManager entityManager = getEntityManager();
    try {
      // Inicia uma transação com o banco de dados.
      entityManager.getTransaction().begin();
      System.out.println("Salvando usuario.");
      // Verifica se a pessoa ainda não está salva no banco de dados.
      if(usuario.getId() == null) {
        //Salva os dados da pessoa.
        entityManager.persist(usuario);
      } else {
        //Atualiza os dados da pessoa.
        usuario = entityManager.merge(usuario);
      }
      // Finaliza a transação.
      entityManager.getTransaction().commit();
    } finally {
      entityManager.close();
    }
    return usuario;
  }

  /**
   * Método que apaga a pessoa do banco de dados.
   * @param id
   */
  
  
  public void excluir(Long id) {
    EntityManager entityManager = getEntityManager();
    try {
      // Inicia uma transação com o banco de dados.
      entityManager.getTransaction().begin();
      // Consulta a pessoa na base de dados através do seu ID.
      Usuario usuario = entityManager.find(Usuario.class, id);
      System.out.println("Excluindo os dados de: " + usuario.getNome());
      // Remove a pessoa da base de dados.
      entityManager.remove(usuario);
      // Finaliza a transação.
      entityManager.getTransaction().commit();
    } finally {
      entityManager.close();
    }
  }

  /**
   * Consulta o pessoa pelo ID.
   * @param id
   * @return o objeto Pessoa.
   */
  public Usuario consultarPorId(Long id) {
    EntityManager entityManager = getEntityManager();
    Usuario usuario = null;
    try {
      //Consulta uma pessoa pelo seu ID.
      usuario = entityManager.find(Usuario.class, id);
    } finally {
      entityManager.close();
    }
    return usuario;
  }

    private Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
