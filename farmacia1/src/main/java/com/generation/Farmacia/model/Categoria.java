package com.generation.Farmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categorias")
public class Categoria {
	
	@Id  //esse atributo vai ser a chave primária
	@GeneratedValue (strategy = GenerationType.IDENTITY) //auto increment
	private Long id;
	
	/*A anotação @NotBlank é utilizada para validar se o campo não está vazio.
	 * logo ela não permite nulo ou vazio*/
	 
	@NotBlank(message = "Categoria é obrigatório!") //forçar a pessoa digitar algo
	private String nome;
	
	private String descricao;
	
	//@OneToMany é a chave primária 
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL) //cascade = tudo que eu alterar em categoria irá se propagar em para produto 
	@JsonIgnoreProperties("categoria")
	private List<Produto>produto;  //private = manipular dentro da classe que está sendo manipulado.
	//por isso precisamos do métodos getters and setters, pois, iremos manipular apenas na classe criada
	// public pode acessar de qualquer classe
	//atributos deixa privado  outros métodos você deixa público.

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
	
	
}
