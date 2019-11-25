package br.com.pedidofacil.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pedidofacil.ws.entity.Auditoria;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {
	
	Auditoria findById(long id);
	
	
}
