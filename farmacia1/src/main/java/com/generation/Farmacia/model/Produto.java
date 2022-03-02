package com.generation.Farmacia.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_produtos")
public class Produto {

	@Id  //esse atributo vai ser a chave primária
	@GeneratedValue (strategy = GenerationType.IDENTITY) //auto increment
	private Long id;
	
	/*A anotação @NotBlank é utilizada para validar se o campo não está vazio.
	 * logo ela não permite nulo ou vazio*/
	 
	@NotBlank(message = "Nome é obrigatório!") //forçar a pessoa digitar algo
	private String nome;
	
	@NotBlank (message = "Descrição é obrigatório")
	private String descricao;
	
	private int quantidade; //checar a quantidade que existe no estoque
	
	private String laboratorio;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING) //exibe dois zeros, faz a conversão, formatação
	@NotNull(message = "Preço é obrigatório!") //forçando a pessoa digitar com o notNull
	@Positive(message = "Digite um valor maior do que zero") //obrgatoriamente precisa ser um valor maior que zero
	private BigDecimal preco;
	
	private String foto; //deixar livre que pode existe a foto ou não
	
	@ManyToOne  //representa a chave estrangeira 
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	} 
	
	
}