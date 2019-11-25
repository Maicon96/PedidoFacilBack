package br.com.pedidofacil.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.pedidofacil.ws.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Usuario findById(long id);
	
	@Query(value="SELECT u.login FROM Usuario u WHERE u.login = :login")
	public String findByUsuario(@Param("login") String login);
	
	@Query(value="SELECT u FROM Usuario u WHERE u.login = :login")
	public Usuario findByLogin(@Param("login") String login);
				
	@Query(value="SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha")
	public Usuario findUsuarioSenha(@Param("login") String login, 
			@Param("senha") String senha);
	
	@Query(value="SELECT u FROM Usuario u WHERE u.idEmpresa = :idEmpresa AND u.login = :login")
	public Usuario existsEmpresaUsuario(@Param("idEmpresa") Long idEmpresa, 
			@Param("login") String login);
	
	@Query(value="SELECT u FROM Usuario u WHERE u.id != :id AND u.idEmpresa = :idEmpresa "
			+ "AND u.login = :login")
	public Usuario existsEmpresaUsuario(@Param("id") Long id, @Param("idEmpresa") Long idEmpresa, 
			@Param("login") String login);		
	
	@Query(value="SELECT u.senha FROM Usuario u WHERE u.id = :id")
	public String findSenha(@Param("id") Long id);
	
}
