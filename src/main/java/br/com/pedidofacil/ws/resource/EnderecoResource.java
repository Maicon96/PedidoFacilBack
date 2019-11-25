package br.com.pedidofacil.ws.resource;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.pedidofacil.ws.dto.CampoErroDTO;
import br.com.pedidofacil.ws.dto.ListRegistroDTO;
import br.com.pedidofacil.ws.dto.LoadResponseDTO;
import br.com.pedidofacil.ws.dto.RegistroDTO;
import br.com.pedidofacil.ws.dto.ResponseDTO;
import br.com.pedidofacil.ws.entity.Endereco;
import br.com.pedidofacil.ws.repository.EnderecoRepository;
import br.com.pedidofacil.ws.service.EnderecoService;


@RestController
@RequestMapping(value="/service/enderecos")
public class EnderecoResource extends ResponseEntityExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(EnderecoResource.class);
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	@PostMapping("/load")
	public ResponseEntity<?> loadClientes() {
		LoadResponseDTO<Endereco> dto = new LoadResponseDTO<Endereco>();

		try {
			List<Endereco> enderecos = this.enderecoRepository.findAll();

			if (enderecos == null) {
				dto.success = false;
				dto.msg = "Nenhum Endereço encontrado!";
				LOG.info("Load - Nenhum Endereço encontrado!");
			} else {
				dto.registros = enderecos;
				dto.msg = "Sucesso ao buscar Endereços";
				LOG.info("Load - Sucesso ao buscar Endereços!");
			}
		} catch (Exception e) {
			dto.success = false;
			dto.msg = "Falha ao buscar Endereços: " + e.getMessage();
			LOG.error("Load - Falha ao buscar Endereços: " + e.getMessage());
		}

		return dto.success ? new ResponseEntity<Object>(dto, new HttpHeaders(), HttpStatus.ACCEPTED)
				: new ResponseEntity<Object>(dto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/exists/{id}")
	public ResponseEntity<?> existsCliente(@PathVariable(value="id") long id) {
		ResponseDTO<Endereco> response = new ResponseDTO<Endereco>();
				
		try {
			Endereco endereco = this.enderecoRepository.findById(id);

			if (endereco == null) {
				response.success = false;
				response.msg = "Endereço não cadastrado!";
				LOG.info("Exists - Endereço não cadastrado!");
			} else {
				response.registro = endereco;
				response.msg = "Sucesso ao buscar Endereço!";
				LOG.info("Exists - Sucesso ao buscar Endereço!");
			}
		} catch (Exception e) {
			response.success = false;
			response.msg = "Falha ao buscar Endereço: " + e.getMessage();
			LOG.error("Exists - Falha ao buscar Endereço: " + e.getMessage());
		}

		return response.success ? new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.ACCEPTED)
				: new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody RegistroDTO<Endereco> request) {			
		ResponseDTO<Endereco> response = new ResponseDTO<Endereco>();		
		
		try {
			List<CampoErroDTO> erros = this.enderecoService.salvar(request.registro);			
			
			if (erros.isEmpty()) {		
				response.registro = request.registro;
				response.msg = "Sucesso ao cadastrar Endereço!";
				LOG.info("Save - Sucesso ao cadastrar Endereço!");
			} else {
				response.success = false;				
				response.msg = "Erro ao cadastrar Endereço!";
				response.errors.addAll(erros);
				LOG.info("Save - Erro ao cadastrar Endereço!");
			}			
		} catch (Exception e) {
			response.success = false;
			response.msg = "Falha ao salvar Endereço: " + e.getMessage();
			LOG.error("Save - Falha ao salvar Endereço: " + e.getMessage());
		}

		return response.success ? new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.ACCEPTED)
				: new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody RegistroDTO<Endereco> request) {			
		ResponseDTO<Endereco> response = new ResponseDTO<Endereco>();		
		
		try {
			List<CampoErroDTO> erros = this.enderecoService.salvar(request.registro);			
			
			if (erros.isEmpty()) {		
				response.registro = request.registro;
				response.msg = "Sucesso ao atualizar Endereço!";
				LOG.info("Update - Sucesso ao atualizar Endereço!");
			} else {
				response.success = false;				
				response.msg = "Erro ao atualizar Endereço!";
				response.errors.addAll(erros);
				LOG.info("Update - Erro ao atualizar Endereço!");
			}			
		} catch (Exception e) {
			response.success = false;
			response.msg = "Falha ao atualizar Endereço: " + e.getMessage();
			LOG.error("Update - Falha ao atualizar Endereço: " + e.getMessage());
		}

		return response.success ? new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.ACCEPTED)
				: new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	
	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody ListRegistroDTO<Endereco> request) {		
		ResponseDTO<Endereco> response = new ResponseDTO<Endereco>();		
		
		try {					
			this.enderecoRepository.deleteAll(request.registros);				
			response.msg = "Sucesso ao deletar Endereço!";
			LOG.info("Delete - Sucesso ao deletar Endereço!");
		} catch (Exception e) {
			response.success = false;
			response.msg = "Falha ao deletar Endereço: " + e.getMessage();
			LOG.error("Delete - Falha ao deletar Endereço: " + e.getMessage());
		}

		return response.success ? new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.ACCEPTED)
				: new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
}
