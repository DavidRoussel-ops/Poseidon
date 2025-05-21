package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RuleNameService {

    private static final Logger logger = LoggerFactory.getLogger(RatingService.class);

    @Autowired
    private RuleNameRepository ruleNameRepository;

    /**
     * Méthode renvoyant tout les noms de règle
     * @return Iterable<RuleName>
     */
    public Iterable<RuleName> getRuleNames() {
        return ruleNameRepository.findAll();
    }

    /**
     * Méthode renvoyant un nom de règle par son id
     * @param id
     * @return Optional<RuleName>
     */
    public Optional<RuleName> getRuleNameById(Integer id) {
        return ruleNameRepository.findById(id);
    }

    /**
     * Méthode d'ajout d'un nom de règle
     * @param ruleName
     * @return RuleName
     */
    @Transactional
    public RuleName addRuleName(RuleName ruleName) {
        logger.info("Ajout d'un nom de la règle");
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Méthode de suppression d'un nom de règle par son id
     * @param id
     */
    public void deleteRuleNameById(Integer id) {
        ruleNameRepository.deleteById(id);
    }
}
