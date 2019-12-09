package br.com.pedidofacil.ws.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="tbl_estabelecimentos")
public class Estabelecimento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@NotNull
	@Column(name = "nome")
	@JsonInclude(value = Include.NON_NULL)
	private String nome;
	
	@NotNull
	@Column(name = "cnpj")
	@JsonInclude(value = Include.NON_NULL)
	private String cnpj;
	
	@NotNull
	@Column(name = "cidade")
	@JsonInclude(value = Include.NON_NULL)
	private String cidade;
	
	@NotNull
	@Column(name = "estado")
	@JsonInclude(value = Include.NON_NULL)
	private String estado;
	
	
	@Column(name = "imagem")
	@JsonInclude(value = Include.NON_NULL)
	private byte[] imagem;	
	
	
	public Estabelecimento() {
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
		

}
