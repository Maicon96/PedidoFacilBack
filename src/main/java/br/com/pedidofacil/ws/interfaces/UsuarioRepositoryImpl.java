package br.com.pedidofacil.ws.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import br.com.pedidofacil.ws.entity.Usuario;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	
//	@Override
	public List<Usuario> filtrar(Usuario usuario) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		
		Root<Usuario> root = criteria.from(Usuario.class);
		
		//CRIAR AS RESTRIÇOES
		Predicate[] predicates = criarRestricoes(usuario, builder, root);
		
		
		
		criteria.where(predicates);
		
		TypedQuery<Usuario> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}


	private Predicate[] criarRestricoes(Usuario usuario, CriteriaBuilder builder, Root<Usuario> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		
		//where descricao like 
		
//		if (!StringUtils.isEmpty(usuario.getNome())) {
//			predicates.add(builder.like(
//					builder.lower(root.get("nome")), "%" + usuario.getNome().toLowerCase() + "%"));
//		}
		
		
//		return predicates;
		return null;
	}

}
