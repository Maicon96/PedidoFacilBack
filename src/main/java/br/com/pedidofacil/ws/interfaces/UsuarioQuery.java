package br.com.pedidofacil.ws.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedidofacil.ws.entity.Usuario;

public interface UsuarioQuery extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {
	
	
	
	
}
