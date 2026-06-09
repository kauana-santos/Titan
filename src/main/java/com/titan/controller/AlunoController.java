package com.titan.controller;

import com.titan.model.Aluno;
import com.titan.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Endpoints de gerenciamento de alunos da academia")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    @Operation(summary = "Listar todos os alunos", description = "Retorna uma lista completa de alunos cadastrados")
    public List<Aluno> listarTodos(){
        return alunoService.listarTodos();
    }

    @PostMapping
    @Operation(summary = "Cadastrar novos alunos", description = "Cadastra um novo aluno na academia")
    @ApiResponse(responseCode = "200", description = "Retorna o aluno salvo")
    @ApiResponse(responseCode = "409", description = "CPF já cadastrado" +
            "")
    public ResponseEntity<Aluno> criarAluno(@Valid @RequestBody Aluno aluno){
        Aluno salvo = alunoService.criarAluno(aluno);
        return ResponseEntity.status(201).body(salvo);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aluno", description = "Atualiza todos os dados de um aluno existente")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id, @Valid @RequestBody Aluno aluno){

        Aluno atualizado = alunoService.atualizar(id, aluno);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar aluno", description = "Deletar um aluno por id")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar aluno por id", description = "Busca um usuario existente por id")
    public ResponseEntity<Aluno> buscar(@PathVariable Long id){
        return ResponseEntity.ok(alunoService.buscarPorId(id));

    }

    @GetMapping("/{id}/buscar")
    @Operation(summary = "Verificar status da matricula", description = "Verifica se a matricula esta ativa")
    public ResponseEntity<String> verificarMatricula(@PathVariable Long id){
        Aluno aluno = alunoService.buscarPorId(id);

        if (aluno.getMatriculaAtiva()){
            return ResponseEntity.ok("Matricula_ativa");
        }

        return ResponseEntity.ok("Matricula_Inativa");
    }

}


