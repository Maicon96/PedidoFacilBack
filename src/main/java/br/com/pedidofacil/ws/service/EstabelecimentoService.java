package br.com.pedidofacil.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedidofacil.ws.dto.CampoErroDTO;
import br.com.pedidofacil.ws.entity.Estabelecimento;
import br.com.pedidofacil.ws.repository.EstabelecimentoRepository;

@Service
public class EstabelecimentoService {
	
	@Autowired
	EstabelecimentoRepository estabelecimentoRepository;
		
	
	public List<CampoErroDTO> salvar(Estabelecimento estabelecimento) {			
		
		List<CampoErroDTO> erros = this.validaSalvar(estabelecimento);

		if (erros.isEmpty()) {
			this.estabelecimentoRepository.save(estabelecimento);			
		}	
		
		return erros;
		
	}
	
	public Long salvarRetorno(Estabelecimento estabelecimento) {			
		
		List<CampoErroDTO> erros = this.validaSalvar(estabelecimento);

		if (erros.isEmpty()) {
			this.estabelecimentoRepository.save(estabelecimento);			
		}	
		
		return estabelecimento.getId();
		
	}
	
	public List<CampoErroDTO> validaSalvar(Estabelecimento estabelecimento) {			

		List<CampoErroDTO> erros = new ArrayList<CampoErroDTO>();
	
//		this.validaCidade(endereco, erros);
//		this.validaCep(endereco, erros);
//		this.validaRua(endereco, erros);
//		this.validaBairro(endereco, erros);
//		this.validaNumero(endereco, erros);

		return erros;		
	}
	
	
		
//	public void validaCidade(Estabelecimento endereco, List<CampoErroDTO> erros) {
//		if (endereco.getCidade() == null || endereco.getCidade() == "") {		
//			erros.add(new CampoErroDTO("cidade", "Cidade é campo obrigatório"));
//		}
//	}
//	
//	public void validaCep(Estabelecimento endereco, List<CampoErroDTO> erros) {
//		if (endereco.getCep() == null) {		
//			erros.add(new CampoErroDTO("cep", "CEP é campo obrigatório"));
//		}
//	}
//	
//	public void validaRua(Estabelecimento endereco, List<CampoErroDTO> erros) {
//		if (endereco.getRua() == null || endereco.getRua() == "") {		
//			erros.add(new CampoErroDTO("rua", "Rua é campo obrigatório"));
//		}
//	}
//	
//	public void validaBairro(Estabelecimento endereco, List<CampoErroDTO> erros) {
//		if (endereco.getBairro() == null || endereco.getBairro() == "") {		
//			erros.add(new CampoErroDTO("bairro", "Bairro é campo obrigatório"));
//		}
//	}
//	
//	public void validaNumero(Estabelecimento endereco, List<CampoErroDTO> erros) {
//		if (endereco.getNumero() == null) {		
//			erros.add(new CampoErroDTO("numero", "Número é campo obrigatório"));
//		}
//	}
	
	
	
}
