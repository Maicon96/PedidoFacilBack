package br.com.pedidofacil.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedidofacil.ws.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	Endereco findById(long id);
	
	
}
