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
@Table(name="enderecos")
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@NotNull
	@Column(name = "cidade")
	@JsonInclude(value = Include.NON_NULL)
	private String cidade;
		
	@NotNull
	@Column(name = "cep")
	@JsonInclude(value = Include.NON_NULL)
	private Integer cep;
	
	@NotNull
	@Column(name = "rua")
	@JsonInclude(value = Include.NON_NULL)
	private String rua;
	
	@NotNull
	@Column(name = "bairro")
	@JsonInclude(value = Include.NON_NULL)
	private String bairro;
	
	@NotNull
	@Column(name = "numero")
	@JsonInclude(value = Include.NON_NULL)
	private Integer numero;
	
	@Column(name = "complemento")
	@JsonInclude(value = Include.NON_NULL)
	private String complemento;	
		
	
	public Endereco() {
		
	}	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
