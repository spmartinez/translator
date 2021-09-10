package com.sales.market.service;

import com.sales.market.model.Promotion;
import org.springframework.stereotype.Service;

@Service
public interface PromotionService extends GenericService<Promotion> {
    void sendBirthdayMessage(Long idPromotion);

}
