package br.com.pedidofacil.ws.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedidofacil.ws.dto.CampoErroDTO;
import br.com.pedidofacil.ws.entity.Usuario;
import br.com.pedidofacil.ws.repository.UsuarioRepository;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
		
	public List<CampoErroDTO> salvar(Usuario usuario) {			
		
		if (usuario.getId() != null && usuario.getSenha() == null) {			
			usuario.setSenha(this.usuarioRepository.findSenha(usuario.getId()));			
		}
		
		List<CampoErroDTO> erros = this.validaSalvar(usuario);
		if (erros.isEmpty()) {
			this.usuarioRepository.save(usuario);
		}	
		
		return erros;		
	}
	
	public List<CampoErroDTO> validaSalvar(Usuario usuario) {			
		
		List<CampoErroDTO> erros = new ArrayList<CampoErroDTO>();

//		this.validaNome(usuario, erros);
		this.validaLogin(usuario, erros);
		this.validaSenha(usuario, erros);
//		this.validaUnique(usuario, erros);

		return erros;		
	}
	

//	public void validaNome(Usuario usuario, List<CampoErroDTO> erros) {
//		if (usuario.getNome() == null || usuario.getNome() == "") {		
//			erros.add(new CampoErroDTO("nome", "Nome é campo obrigatório"));
//		}
//	}
	
	public void validaLogin(Usuario usuario, List<CampoErroDTO> erros) {
		if (usuario.getLogin() == "") {		
			erros.add(new CampoErroDTO("login", "Login é campo obrigatório"));
		}
	}
	
	public void validaSenha(Usuario usuario, List<CampoErroDTO> erros) {
		if (usuario.getSenha() == null || usuario.getSenha() == "") {		
			erros.add(new CampoErroDTO("senha", "Senha é campo obrigatório"));
		} 
	}
	
		
//	public void validaUnique(Usuario usuario, List<CampoErroDTO> erros) {
//		if (usuario.getIdEmpresa() != null && usuario.getLogin() != null) {
//			
//			if (usuario.getId() != null) {
//				Usuario usuarioAux = this.usuarioRepository.existsEmpresaUsuario(usuario.getId(), 
//						usuario.getIdEmpresa(), usuario.getLogin());
//				
//				if (usuarioAux != null) {
//					erros.add(new CampoErroDTO("login", "Login já cadastrado para essa empresa. Escolha outro!"));	
//				}	
//			} else {
//				Usuario usuarioAux = this.usuarioRepository.existsEmpresaUsuario(usuario.getIdEmpresa(), 
//						usuario.getLogin());
//				
//				if (usuarioAux != null) {
//					erros.add(new CampoErroDTO("login", "Login já cadastrado para essa empresa. Escolha outro!"));	
//				}
//			}				
//		}
//	}
	
	
	public byte[] generatePdf(Integer idEmpresa) throws Exception {		
		HashMap<String, Object> param = new HashMap<>();		
		InputStream relatoIS = null;
		InputStream relatoSubReport = null;
		InputStream logo = null;
		byte[] pdf = null;
		
		try {		
			
			List<Usuario> vasilhamesEntregar = this.usuarioRepository.findAll();
			      
			relatoIS = this.getClass().getClassLoader().getResourceAsStream("/relato/vasilhame/Usuario.jasper");
	        JasperReport relato = (JasperReport) JRLoader.loadObject(relatoIS);              
	        
//			logo = new ByteArrayInputStream(new byte[0]);
//			Empresa empresa = this.empresaCadManagerBean.findLogo(idEmpresa);
//			if (empresa != null) {
//				if (empresa.getLogo() != null) {
//					logo = new ByteArrayInputStream(empresa.getLogo());
//				} else {
//					logo = this.getClass().getClassLoader().getResourceAsStream("/relato/images/noimage.jpg");
//				}				
//
//			} else {
//				logo = this.getClass().getClassLoader().getResourceAsStream("/relato/images/noimage.jpg");
//			}
		        
//	        param.put("logo", logo);    
	        
	        pdf = JasperRunManager.runReportToPdf(relato, param, new JRBeanCollectionDataSource(vasilhamesEntregar));
        
		} finally {			
			if (relatoIS != null) relatoIS.close();
			if (relatoSubReport != null) relatoSubReport.close();
			if (logo != null) logo.close();			
		}
		
		return pdf;
	}
	
}
