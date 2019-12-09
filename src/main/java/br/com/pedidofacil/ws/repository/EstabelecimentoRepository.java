package br.com.pedidofacil.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedidofacil.ws.entity.Estabelecimento;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
	
	Estabelecimento findById(long id);
	
	
}
