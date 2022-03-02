package com.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")  //tudo o que vier para /postagens essa classe controladora que vai respponder
@CrossOrigin(origins = "*", allowedHeaders = "*")  //se não configurar , o frnot-end não vai conseguir se comunicar com o back-end
//se estiverem na mesma porta roda normalmente. No mercado depois de estar tuido funcionando coloca o enedereço do front-end
public class PostagemController {
	
	@Autowired
	private PostagemRepository postagemRepository; //injvestão de idependecia está criada. Minha interface 
	
	@GetMapping
	public ResponseEntity<List <Postagem >> getAll() {  //devolver um objeto de reposta e no corpo dessa reporesta
		//vai trazer uma lista e vários objetivos da classe postagem
		return ResponseEntity.ok(postagemRepository.findAll());
		/* select * from tb_postagens;   (findAll) */
	}

	
	@GetMapping("/{id}") // valor que dentr´da chave é uma váriavel de caminho
	public ResponseEntity <Postagem > getById(@PathVariable Long id) { //não usaremos o list porque vamos usar apenas o postagem. Um objeto da classe postagem
	//por ser um parâmetro (váriavel caminho) precisa receber dentro do meu método para poder utilziar 
		// com o @PathVariable vai entender que o que eu digitei "id"  será um parâmetro. E
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta)) //faça a execução do responentity e do método ok e no corpo você coloca a resposta que encontrou no "resposta"
				.orElse(ResponseEntity.notFound().build()); //aqui é a resposta negativa
		
		
	}
	@GetMapping("/titulo/{titulo}") //para saber o que digitar no http...
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));

	/*terceiro método debusc
	 primeiro criar no repository depois chamar no Controller
	primerio método get*/
			
	}
	
	@PostMapping
	public ResponseEntity <Postagem> postPostagem(@Valid @RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> putPostagem (@Valid @RequestBody Postagem postagem) {
		return postagemRepository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.ok().body(postagemRepository.save(postagem)))
						.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable long id) {
		return postagemRepository.findById(id)
				.map(resposta -> {
					postagemRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
					})
				.orElse(ResponseEntity.notFound().build());
	}
}