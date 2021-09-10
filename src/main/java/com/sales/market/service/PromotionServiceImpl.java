package com.sales.market.service;

import com.sales.market.mail.EmailServiceImpl;
import com.sales.market.model.Client;
import com.sales.market.model.Promotion;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImpl extends GenericServiceImpl<Promotion> implements PromotionService {
    private final PromotionRepository repository;
    private final ClientService clientService;
    private final EmailServiceImpl emailService;

    @Value("${discount.value}")
    private String discount;

    public PromotionServiceImpl(PromotionRepository repository, ClientService clientService, EmailServiceImpl emailService) {
        this.repository = repository;
        this.clientService = clientService;
        this.emailService = emailService;
    }
    @Override
    protected GenericRepository<Promotion> getRepository() {
        return repository;
    }

    @Override
    public void sendBirthdayMessage(Long idPromotion) {
        List<Client> clientList = clientService.getBirthdayBoys();

        Promotion promotion = repository.getById(idPromotion);

        String message = promotion.getDescription();

        message.replace("<discountValue>",discount);

        String copyMessage ;

        for (Client client: clientList) {
            copyMessage = message;

            copyMessage.replace("<name>", client.getName());

            emailService.sendSimpleMessage(client.getEmail(), "Promotion", copyMessage);
        }
    }
}
