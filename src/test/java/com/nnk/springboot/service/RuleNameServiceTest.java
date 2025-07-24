package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RuleNameServiceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    public void setUpMockMvc() { mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Autowired
    private RuleNameService ruleNameService;

    @Test
    public void testGetRuleNames() throws Exception {
        Iterable<RuleName> allRulesNames = ruleNameService.getRuleNames();
        assertThat(allRulesNames).isNotNull();
    }

    @Test
    public void testAddRuleName() throws Exception {
        RuleName ruleName = new RuleName();
        RuleName ruleNameToAdd = ruleNameService.addRuleName(ruleName);
        assertThat(ruleNameToAdd).isNotNull();
    }

    @Test
    public void testGetRuleNameById() throws Exception {
        Iterable<RuleName> allRuleNames = ruleNameService.getRuleNames();
        int lastId = 0;
        for (RuleName ruleName : allRuleNames) {
            lastId = ruleName.getId();
        }
        Optional<RuleName> ruleNameOptional = ruleNameService.getRuleNameById(lastId);
        RuleName ruleName = ruleNameOptional.get();
        assertThat(ruleName).isNotNull();
        Assertions.assertEquals(lastId, ruleName.getId());
    }

    @Test
    public void testDeleteRuleNameById() throws Exception {
        Iterable<RuleName> allRuleNames = ruleNameService.getRuleNames();
        int lastId = 0;
        int counter = 0;
        for (RuleName ruleName : allRuleNames) {
            lastId = ruleName.getId();
            counter ++;
        }
        counter --;
        Optional<RuleName> ruleNameOptional = ruleNameService.getRuleNameById(lastId);
        RuleName ruleName = ruleNameOptional.get();
        ruleNameService.deleteRuleNameById(ruleName.getId());
        assertThat(allRuleNames).isNotNull();
        Assertions.assertEquals(0, counter);
    }

}
