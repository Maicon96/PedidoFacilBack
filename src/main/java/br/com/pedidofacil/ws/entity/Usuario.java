package br.com.pedidofacil.ws.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@Column(name = "id_empresa")
	@JsonInclude(value = Include.NON_NULL)
	private Long idEmpresa;
	
	@NotNull
	@Column(name = "nome")
	@JsonInclude(value = Include.NON_NULL)
	private String nomeCompleto;
	
	@NotNull
	@Column(name = "cpf")
	@JsonInclude(value = Include.NON_NULL)
	private String cpf;
	
	@NotNull
	@Column(name = "email")
	@JsonInclude(value = Include.NON_NULL)
	private String email;
		
	@Column(name = "login")
	@JsonInclude(value = Include.NON_NULL)
	private String login;
		
	@NotNull
	@Column(name = "senha")
	@JsonInclude(value = Include.NON_NULL)
	private String senha;
				
	@Column(name = "avatar")
	@JsonInclude(value = Include.NON_NULL)
	private byte[] avatar;	
	
	
	@Column(name = "id_endereco")
	@JsonInclude(value = Include.NON_NULL)
	private Long idEndereco;
		
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_endereco", insertable=false, updatable=false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Endereco endereco;
	
	
	public Usuario() {
		
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
		
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
