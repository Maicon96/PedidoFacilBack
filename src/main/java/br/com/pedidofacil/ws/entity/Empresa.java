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
@Table(name="empresas")
public class Empresa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	
	@NotNull
	@Column(name = "nome")
	@JsonInclude(value = Include.NON_NULL)
	private String nome;
	
	@NotNull
	@Column(name = "cpfcnpj")
	@JsonInclude(value = Include.NON_NULL)
	private String cpfcnpj;	
	
	
	public Empresa() {
		
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

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
	

}
