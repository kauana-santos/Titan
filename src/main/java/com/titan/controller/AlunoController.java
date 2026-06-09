package com.titan.controller;

import com.titan.model.Aluno;
import com.titan.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> listarTodos(){
        return alunoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@Valid @RequestBody Aluno aluno){
        Aluno salvo = alunoService.criarAluno(aluno);
        return ResponseEntity.status(201).body(salvo);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @Valid @RequestBody Aluno aluno){

        Aluno atualizado = alunoService.atualizar(id, aluno);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable Long id){
        return ResponseEntity.ok(alunoService.buscarPorId(id));

    }

    @GetMapping("/{id}/buscar")
    public ResponseEntity<String> verificarMatricula(@PathVariable Long id){
        Aluno aluno = alunoService.buscarPorId(id);

        if (aluno.getMatriculaAtiva()){
            return ResponseEntity.ok("Matricula_ativa");
        }

        return ResponseEntity.ok("Matricula_Inativa");
    }

}


