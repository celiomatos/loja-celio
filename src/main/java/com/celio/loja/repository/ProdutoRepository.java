package com.celio.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celio.loja.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	Produto findByCodigo(Long codigo);
}
