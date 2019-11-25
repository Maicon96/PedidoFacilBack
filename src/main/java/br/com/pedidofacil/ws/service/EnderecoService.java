package br.com.pedidofacil.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedidofacil.ws.dto.CampoErroDTO;
import br.com.pedidofacil.ws.entity.Endereco;
import br.com.pedidofacil.ws.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository enderecoRepository;
		
	
	public List<CampoErroDTO> salvar(Endereco endereco) {			
		
		List<CampoErroDTO> erros = this.validaSalvar(endereco);

		if (erros.isEmpty()) {
			this.enderecoRepository.save(endereco);			
		}	
		
		return erros;
		
	}
	
	public Long salvarRetorno(Endereco endereco) {			
		
		List<CampoErroDTO> erros = this.validaSalvar(endereco);

		if (erros.isEmpty()) {
			this.enderecoRepository.save(endereco);			
		}	
		
		return endereco.getId();
		
	}
	
	public List<CampoErroDTO> validaSalvar(Endereco endereco) {			

		List<CampoErroDTO> erros = new ArrayList<CampoErroDTO>();
	
		this.validaCidade(endereco, erros);
		this.validaCep(endereco, erros);
		this.validaRua(endereco, erros);
		this.validaBairro(endereco, erros);
		this.validaNumero(endereco, erros);

		return erros;		
	}
	
	
		
	public void validaCidade(Endereco endereco, List<CampoErroDTO> erros) {
		if (endereco.getCidade() == null || endereco.getCidade() == "") {		
			erros.add(new CampoErroDTO("cidade", "Cidade é campo obrigatório"));
		}
	}
	
	public void validaCep(Endereco endereco, List<CampoErroDTO> erros) {
		if (endereco.getCep() == null) {		
			erros.add(new CampoErroDTO("cep", "CEP é campo obrigatório"));
		}
	}
	
	public void validaRua(Endereco endereco, List<CampoErroDTO> erros) {
		if (endereco.getRua() == null || endereco.getRua() == "") {		
			erros.add(new CampoErroDTO("rua", "Rua é campo obrigatório"));
		}
	}
	
	public void validaBairro(Endereco endereco, List<CampoErroDTO> erros) {
		if (endereco.getBairro() == null || endereco.getBairro() == "") {		
			erros.add(new CampoErroDTO("bairro", "Bairro é campo obrigatório"));
		}
	}
	
	public void validaNumero(Endereco endereco, List<CampoErroDTO> erros) {
		if (endereco.getNumero() == null) {		
			erros.add(new CampoErroDTO("numero", "Número é campo obrigatório"));
		}
	}
	
	
	
}
