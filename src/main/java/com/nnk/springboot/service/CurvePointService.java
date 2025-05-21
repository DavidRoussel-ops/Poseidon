package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurvePointService {

    private static final Logger logger = LoggerFactory.getLogger(CurvePointService.class);

    @Autowired
    private CurvePointRepository curvePointRepository;

    /**
     * Méthode renvoyant tout les point de courbe
     * @return Iterable<CurvePoint>
     */
    public Iterable<CurvePoint> getCurvePoints() {
        return curvePointRepository.findAll();
    }

    /**
     * Méthode renvoyant un point de courbe par son id
     * @param id
     * @return Optional<CurvePoint>
     */
    public Optional<CurvePoint> getCurvePointById(Integer id) {
        return curvePointRepository.findById(id);
    }

    /**
     * Méthode d'ajout d'un point de courbe
     * @param curvePoint
     * @return CurvePoint
     */
    @Transactional
    public CurvePoint addCurvePoint(CurvePoint curvePoint) {
        logger.info("Ajout d'un point de courbe");
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Méthode de suppression d'un point de courbe
     * @param id
     */
    public void deleteCurvePointById(Integer id) {
        curvePointRepository.deleteById(id);
    }
}
