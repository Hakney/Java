/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
     
      if(usuario.getId() == null){
        entityManager.persist(usuario);
      } else {
        usuario = entityManager.merge(usuario);
      }
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
  
 
  public void excluir(Integer id) {
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

   public List<Usuario> listar(){
    EntityManager entityManager = getEntityManager();

    String queryString = "select x from Usuario x";

    Query query = entityManager.createQuery(queryString);

    List<Usuario> usuario = query.getResultList();
    
    return usuario;
  }
  
}
