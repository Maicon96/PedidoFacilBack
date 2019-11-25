package br.com.pedidofacil.ws.resource;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.pedidofacil.ws.dto.CampoErroDTO;
import br.com.pedidofacil.ws.dto.ListRegistroDTO;
import br.com.pedidofacil.ws.dto.RegistroDTO;
import br.com.pedidofacil.ws.dto.ResponseDTO;
import br.com.pedidofacil.ws.entity.Usuario;
import br.com.pedidofacil.ws.repository.UsuarioRepository;
import br.com.pedidofacil.ws.service.UsuarioService;


@RestController
@RequestMapping(value="/service/usuarios")
public class UsuarioResource extends ResponseEntityExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioResource.class);
		
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@GetMapping("/exists/{id}")
	public ResponseEntity<?> existsUsuario(@PathVariable(value="id") long id) {
		ResponseDTO<Usuario> response = new ResponseDTO<Usuario>();
				
		try {
			Usuario usuario = this.usuarioRepository.findById(id);

			if (usuario == null) {
				response.success = false;
				response.msg = "Usuário não cadastrado!";
				LOG.info("Exists - Usuário não cadastrado!");
			} else {
				response.registro = usuario;
				response.msg = "Sucesso ao buscar Usuário!";
				LOG.info("Exists - Sucesso ao buscar Usuário!");
			}
		} catch (Exception e) {
			response.success = false;
			response.msg = "Falha ao buscar Usuário: " + e.getMessage();
			LOG.error("Exists - Falha ao buscar Usuário: " + e.getMessage());
		}

		return response.success ? new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.ACCEPTED)
				: new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody RegistroDTO<Usuario> request, @RequestHeader(value="idEmpresa") String empresaUsuario) {			
		ResponseDTO<Usuario> response = new ResponseDTO<Usuario>();		
		
		try {						
			List<CampoErroDTO> erros = this.usuarioService.salvar(request.registro);			
			
			if (erros.isEmpty()) {		
				response.registro = request.registro;
				response.msg = "Sucesso ao cadastrar Usuário!";
				LOG.info("Save - Sucesso ao cadastrar Usuário!");
			} else {
				response.success = false;				
				response.msg = "Erro ao cadastrar Usuário!";				
				response.errors.addAll(erros);
				LOG.info("Save - Erro ao cadastrar Usuário!");
			}			
		} catch (Exception e) {
			response.success = false;
			response.msg = "Falha ao salvar Usuário: " + e.getMessage();
			LOG.error("Save - Falha ao salvar Usuário: " + e.getMessage());
		}

		return response.success ? new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.ACCEPTED)
				: new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody RegistroDTO<Usuario> request) {			
		ResponseDTO<Usuario> response = new ResponseDTO<Usuario>();		
		
		try {
			List<CampoErroDTO> erros = this.usuarioService.salvar(request.registro);			
			
			if (erros.isEmpty()) {		
				response.registro = request.registro;
				response.msg = "Sucesso ao atualizar Usuário!";
				LOG.info("Update - Sucesso ao atualizar Usuário!");
			} else {
				response.success = false;				
				response.msg = "Erro ao atualizar Usuário!";				
				response.errors.addAll(erros);
				LOG.info("Update - Erro ao atualizar Usuário!");
			}			
		} catch (Exception e) {
			response.success = false;
			response.msg = "Falha ao atualizar Usuário: " + e.getMessage();
			LOG.error("Update - Falha ao atualizar Usuário: " + e.getMessage());
		}

		return response.success ? new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.ACCEPTED)
				: new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	
	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody ListRegistroDTO<Usuario> request) {		
		ResponseDTO<Usuario> response = new ResponseDTO<Usuario>();		
		
		try {					
			this.usuarioRepository.deleteAll(request.registros);				
			response.msg = "Sucesso ao deletar Usuários!";					
			LOG.info("Delete - Sucesso ao deletar Usuários!");
		} catch (Exception e) {
			response.success = false;
			response.msg = "Falha ao deletar Usuários: " + e.getMessage();
			LOG.error("Delete - Falha ao deletar Usuários: " + e.getMessage());
		}

		return response.success ? new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.ACCEPTED)
				: new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
//	@PostMapping(value = "/relatorio", produces = { "application/pdf" })
//	public ResponseEntity<?> reportPDF(@RequestBody ListRegistroDTO<Usuario> request) {		
//		LoadResponseDTO<Usuario> dto = new LoadResponseDTO<Usuario>();
//		byte[] relato = null;
//		try {			
//			relato = this.managerBean.generatePdf(request.registro, Integer.parseInt(empresaUsuario),
//					usuario, nomeUsuario);
//			
//			if (relato == null) {
//				dto.success=false;	
//				dto.msg = "Erro ao gerar relatório de vasilhames a entregar itens baixas cupons - PDF null";
//			}
//		} catch (Exception e) {
//			dto.success = false;
//			dto.msg = "Falha ao gerar relatório de vasilhames a entregar itens baixas cupons: " + e.getMessage();
//			LOG.error("Falha ao gerar relatório de vasilhames a entregar itens baixas cupons", e);
//		}
//			
//		if (dto.success) {
//			return Response.ok(relato, "application/pdf").build();		
//		} else {
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//					.entity(dto).type(MediaType.APPLICATION_JSON).build();		
//		}
//		
//	}
	
	
}
