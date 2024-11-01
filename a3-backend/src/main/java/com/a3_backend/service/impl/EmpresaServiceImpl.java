package com.a3_backend.service.impl;

import com.a3_backend.TAD.ListaEncadeada;
import java.util.stream.Collectors;
import com.a3_backend.dto.CreateEmpresaRequest;
import com.a3_backend.dto.CreateEntityResponse;
import com.a3_backend.dto.EmpresaResponse;
import com.a3_backend.model.Empresa;
import com.a3_backend.model.Usuario;
import com.a3_backend.repository.EmpresaRepository;
import com.a3_backend.repository.UsuarioRepository;
import com.a3_backend.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public EmpresaResponse getById(Long id) {
        Empresa empresa = empresaRepository.findByIdWithFuncionarios(id).orElseThrow();
        System.out.println(empresa);
        return new EmpresaResponse(empresa);
    }

    @Override
    public CreateEntityResponse create(CreateEmpresaRequest empresaRequest, Long id) {
        Empresa empresa = new Empresa(empresaRequest);

        var administrador = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));
        empresa.setAdministrador(administrador);

        // Primeiro salvamos a empresa
        empresa = empresaRepository.save(empresa);

        System.out.println(empresa);

        // Depois adicionamos os funcionários
        List<Usuario> funcionarios = new ArrayList<>();
        for (Long usuarioId : empresaRequest.getFuncionariosIds()) {
            Usuario funcionario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + usuarioId));

            funcionario.setEmpresa(empresa);
            funcionarios.add(funcionario);
            usuarioRepository.save(funcionario); // Salva a atualização do funcionário
        }

        //ADD OS USUARIOS DEPOIS DE CRIAR A EMPRESA PQ TA BUGANDO ESSA MERDA TODA
        System.out.println(funcionarios);

        empresa.setFuncionarios(funcionarios);

        System.out.println("Funcionários após salvar: " + empresa);

        empresa = empresaRepository.save(empresa); // Salva a empresa novamente com os funcionários


        return new CreateEntityResponse(empresa);
    }
}
