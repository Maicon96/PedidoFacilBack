package br.com.pedidofacil.ws.resource.security;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.pedidofacil.ws.dto.CampoErroDTO;
import br.com.pedidofacil.ws.dto.RegistroDTO;
import br.com.pedidofacil.ws.dto.ResponseAutenticacaoDTO;
import br.com.pedidofacil.ws.dto.ResponseDTO;
import br.com.pedidofacil.ws.entity.Usuario;
import br.com.pedidofacil.ws.repository.UsuarioRepository;
import br.com.pedidofacil.ws.resource.UsuarioResource;
import br.com.pedidofacil.ws.service.EnderecoService;
import br.com.pedidofacil.ws.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@RequestMapping(value="/login")
public class LoginController extends ResponseEntityExceptionHandler {		
	
	private static final long EXPIRATIONTIME = 240 * 60 * 1000;
    private static final String SECRET = "pedidoFacilPermissaoLogin";  
    
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioResource.class);
    
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	
	@PostMapping("/exists/usuario")
	public ResponseEntity<?> buscarUsuario(@RequestBody Usuario usuario) {		
		ResponseAutenticacaoDTO<Usuario> dto = new ResponseAutenticacaoDTO<Usuario>();

		try {			
			Usuario usu = this.usuarioRepository.findByLogin(usuario.getLogin());
			
			if (usu != null) {			
				dto.registro = usu;
				dto.msg = "Sucesso ao buscar Usuário!";								
			} else {
				dto.success = false;
				dto.msg = "Usuário não encontrado!";				
			}						
			
		} catch (Exception e) {
			dto.success = false;
			dto.msg = "Falha ao autenticar Usuário: " + e.getMessage();
		}

		return dto.success ? new ResponseEntity<Object>(dto, new HttpHeaders(), HttpStatus.ACCEPTED)
				: new ResponseEntity<Object>(dto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/autenticar/usuario")
	public ResponseEntity<?> autenticar(@RequestBody Usuario usuario) {		
		ResponseAutenticacaoDTO<Usuario> dto = new ResponseAutenticacaoDTO<Usuario>();

		try {			
			Usuario usu = this.usuarioRepository.findUsuarioSenha(usuario.getLogin(), usuario.getSenha());
			
			if (usu != null) {		
				usu.setSenha(null);
				dto.registro = usu;
				dto.msg = "Sucesso ao autenticar Usuário!";
				dto.token = this.geraToken(usu);				
			} else {
				dto.success = false;
				dto.msg = "Usuário ou Senha inválido!";				
			}						
			
		} catch (Exception e) {
			dto.success = false;
			dto.msg = "Falha ao autenticar Usuário: " + e.getMessage();
		}

		return dto.success ? new ResponseEntity<Object>(dto, new HttpHeaders(), HttpStatus.ACCEPTED)
				: new ResponseEntity<Object>(dto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/usuario/save")
	public ResponseEntity<?> save(@RequestBody RegistroDTO<Usuario> request) {			
		ResponseDTO<Usuario> response = new ResponseDTO<Usuario>();		
		Long idEndereco = new Long(0);
		
		try {		
			if (request.registro.getEndereco() != null) {
				idEndereco = this.enderecoService.salvarRetorno(request.registro.getEndereco());	
			}
			
			if (idEndereco > 0) {
				request.registro.setIdEndereco(idEndereco);
				
				List<CampoErroDTO> erros = this.usuarioService.salvar(request.registro);			
				
				if (erros.isEmpty()) {		
					response.registro = request.registro;
					response.msg = "Sucesso ao cadastrar Usuário!";
					LOG.info("Save - Sucesso ao cadastrar Usuário!");
					
					//this.geraToken(request.registro);
					
				} else {
					response.success = false;				
					response.msg = "Erro ao cadastrar Usuário!";				
					response.errors.addAll(erros);
					LOG.info("Save - Erro ao cadastrar Usuário!");
				}	
			} else {
				response.success = false;				
				response.msg = "Erro ao cadastrar Usuário Endereço!";	
				LOG.info("Save - Erro ao cadastrar Usuário Endereço!");
			}
					
		} catch (Exception e) {
			response.success = false;
			response.msg = "Falha ao salvar Usuário: " + e.getMessage();
			LOG.error("Save - Falha ao salvar Usuário: " + e.getMessage());
		}

		return response.success ? new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.ACCEPTED)
				: new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	public String geraToken(Usuario usuario) throws ServletException {		
		
		String token = "";
		
		try {			
			token = Jwts.builder()
					.setSubject(usuario.getLogin())
					.signWith(SignatureAlgorithm.HS512, usuario.getLogin() + SECRET)
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
					.compact();			
			
		} catch (Exception e) {
			throw new ServletException("Falha ao gerar token!"); 
		}	
		
		return token;
	}	
	
}
