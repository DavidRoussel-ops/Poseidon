package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CurvePointServiceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Autowired
    private CurvePointService curvePointService;

    @Test
    public void testGetCurvePoints() throws Exception {
        Iterable<CurvePoint> allCurvePoints = curvePointService.getCurvePoints();
        assertThat(allCurvePoints).isNotNull();
    }

    @Test
    public void testAddCurvePoint() throws Exception {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setTerm(10.0);
        curvePoint.setValue(20.0);
        curvePoint.setCurveId(1);
        curvePoint.setAsOfDate(timestamp);
        curvePoint.setCreationDate(timestamp);
        curvePointService.addCurvePoint(curvePoint);
        assertThat(curvePoint).isNotNull();
        assertNotNull(curvePoint.getCurveId());
        assertEquals(curvePoint.getCurveId(), 1, 1);
        assertNotNull(curvePoint.getAsOfDate());
        assertNotNull(curvePoint.getCreationDate());
    }

    @Test
    public void testGetCurvePointById() throws Exception {
        Iterable<CurvePoint> allCurvePoints = curvePointService.getCurvePoints();
        int lastId = 0;
        for (CurvePoint curvePoint : allCurvePoints) {
            lastId = curvePoint.getId();
        }
        Optional<CurvePoint> curvePointOptional = curvePointService.getCurvePointById(lastId);
        CurvePoint curvePoint = curvePointOptional.get();
        assertThat(curvePoint).isNotNull();
        assertEquals(lastId, curvePoint.getId());
    }

    @Test
    public void testDeleteCurvePointById() throws Exception {
        Iterable<CurvePoint> allCurvePoints = curvePointService.getCurvePoints();
        int lastId = 0;
        int counter = 0;
        for (CurvePoint curvePoint : allCurvePoints) {
            lastId = curvePoint.getId();
            counter ++;
        }
        counter --;
        Optional<CurvePoint> curvePointOptional = curvePointService.getCurvePointById(lastId);
        CurvePoint curvePoint = curvePointOptional.get();
        curvePointService.deleteCurvePointById(curvePoint.getId());
        assertThat(allCurvePoints).isNotNull();
        assertEquals(0, counter);
    }
}
