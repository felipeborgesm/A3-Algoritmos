package com.a3_backend.service.impl;

import com.a3_backend.dto.AddFuncionariosRequest;
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
        Empresa empresa = empresaRepository.findById(id).orElseThrow();
        return new EmpresaResponse(empresa);
    }

    @Override
    public CreateEntityResponse create(CreateEmpresaRequest empresaRequest, Long id) {
        Empresa empresa = new Empresa(empresaRequest);

        var administrador = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));
        empresa.setAdministrador(administrador);

        empresa = empresaRepository.save(empresa);

        administrador.setEmpresa(empresa);
        usuarioRepository.save(administrador);
        return new CreateEntityResponse(empresa);
    }

    @Override
    public void addFuncionarios(AddFuncionariosRequest funcionariosId, Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow();

        List<Usuario> funcionarios = new ArrayList<>();
        for (Long usuarioId : funcionariosId.getFuncionariosId()) {
            Usuario funcionario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + usuarioId));
            funcionario.setEmpresa(empresa);
            usuarioRepository.save(funcionario);
            funcionarios.add(funcionario);
        }
        empresa.getFuncionarios().addAll(funcionarios);
        empresaRepository.save(empresa);
    }
}
