package com.titan.service;

import com.titan.exception.AlunoNotFoundException;
import com.titan.exception.CpfJaCadastradoException;
import com.titan.model.Aluno;
import com.titan.repository.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listarTodos(){
        return alunoRepository.findAll();
    }

    public Aluno criarAluno( Aluno aluno){
        if( alunoRepository.existsByCpf(aluno.getCpf())){
            throw new CpfJaCadastradoException(aluno.getCpf());
        }

        return alunoRepository.save(aluno);
    }

    public Aluno atualizar( Long id,  Aluno aluno){
        Aluno alunoExist = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        alunoExist.setNome(aluno.getNome());
        alunoExist.setCpf(aluno.getCpf());
        alunoExist.setDtNascimento(aluno.getDtNascimento());
        alunoExist.setPlano(aluno.getPlano());
        alunoExist.setMatriculaAtiva(aluno.getMatriculaAtiva());

            return alunoRepository.save(alunoExist);

    }

    public Aluno buscarPorId(Long id){
        return alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNotFoundException(id));
    }

    public void deletar(Long id){
        alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNotFoundException(id));

        alunoRepository.deleteById(id);
    }

}
