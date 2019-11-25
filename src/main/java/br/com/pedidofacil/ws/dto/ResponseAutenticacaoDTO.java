package br.com.pedidofacil.ws.dto;

public class ResponseAutenticacaoDTO<T> {
	
	public boolean success = true;
	public T registro = null;	
	public String msg;
	public String token;

}