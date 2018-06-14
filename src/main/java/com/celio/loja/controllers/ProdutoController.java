package com.celio.loja.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.celio.loja.models.Produto;
import com.celio.loja.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	@Autowired
	private ProdutoRepository pr;
	
	
	@GetMapping("/add")
	public ModelAndView add(Produto p) {
		
		ModelAndView mv = new ModelAndView("/formCad");
		mv.addObject("produto", p);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Produto p, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(p);
		}
		
		pr.save(p);
		
		return findAll();
	}
	
	@GetMapping("/edit/{codigo}")
	public ModelAndView edit(@PathVariable("codigo") Long codigo) {
		
		return add(pr.findByCodigo(codigo));
	}
	
	@GetMapping("/delete/{codigo}")
	public ModelAndView delete(@PathVariable("codigo") Long codigo) {
		
		pr.deleteById(codigo);
		
		return findAll();
	}
	
	@GetMapping("/")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("produtos", pr.findAll());
		
		return mv;
	}
}
