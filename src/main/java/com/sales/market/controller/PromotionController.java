package com.sales.market.controller;

import com.sales.market.dto.PromotionDTO;
import com.sales.market.model.Promotion;
import com.sales.market.service.GenericService;
import com.sales.market.service.PromotionService;
import controller.GenericController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromotionController extends GenericController<Promotion, PromotionDTO> {

    private PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping(value = "/promotion/birthday/{idPromotion}")
    public void getBirthday(@PathVariable("idPromotion") Long idPromotion) {
        promotionService.sendBirthdayMessage(idPromotion);
    }

    @Override
    protected GenericService getService() {
        return null;
    }
}

