package br.com.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.model.Produto;

public class DAO<E> {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("produtos-jpa");
		}catch(Exception e) {
			System.out.println("Erro" + e.getMessage());
		}
	}
	
	public DAO() {
		this(null);
	}
	
	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	}
	
	public DAO<E> abrirT() {
		em.getTransaction().begin();
		return this;
	}
	
	public DAO<E> fecharT() {
		em.getTransaction().commit();
		return this;
	}
	
	public DAO<E> inserirAtomico(E entidade) {
		this.abrirT();
		em.persist(entidade);
		this.fecharT();
		return this;
	}
	
	public DAO<E> inserir(E entidade) {
		em.persist(entidade);
		return this;
	}
	
	public List<E> obterTodos() {
		if(classe == null) {
			throw new UnsupportedOperationException("Classe nula");
		}
		String jpql = "select e from " + classe.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql,classe);
		return query.getResultList();
	}
	
	public DAO<E> excluirRegistro(E entidade) {
		em.remove(entidade);
		return this;
	}
	
	public void fechar() {
		em.close();
	}
}
