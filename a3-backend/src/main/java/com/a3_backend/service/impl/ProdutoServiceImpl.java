package com.a3_backend.service.impl;

import com.a3_backend.dto.CreateEntityResponse;
import com.a3_backend.dto.CreateProdutoRequest;
import com.a3_backend.dto.ProdutoResponse;
import com.a3_backend.model.Empresa;
import com.a3_backend.model.Produto;
import com.a3_backend.repository.EmpresaRepository;
import com.a3_backend.repository.ProdutoRepository;
import com.a3_backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    EmpresaRepository empresaRepository;

    @Override
    public ProdutoResponse getByCodigo(Integer codigo) {
        Produto produto = produtoRepository.findByCodigo(codigo);
        return new ProdutoResponse(produto);
    }

    @Override
    public List<ProdutoResponse> getAllByEmpresaId(Long empresaId) {
        return produtoRepository.getAllByEmpresaId(empresaId);
    }

    @Override
    public void addProduto(CreateProdutoRequest produtoRequest, Long empresaId) {
        Produto produto = new Produto(produtoRequest);

        var empresa = empresaRepository.findById(empresaId).orElseThrow();

        produto.setCodigo(gerarNumeroUnico());
        produto.setEmpresa(empresa);
        produtoRepository.save(produto);

        return;
    }
    private Integer gerarNumeroUnico() {
        Random random = new Random();
        Integer numeroAleatorio;

        do {
            numeroAleatorio = random.nextInt(900000) + 100000;
        } while (!numeroEhUnico(numeroAleatorio));

        return numeroAleatorio;
    }

    private boolean numeroEhUnico(Integer codigo) {
        return produtoRepository.findByCodigo(codigo) == null;
    }
}
