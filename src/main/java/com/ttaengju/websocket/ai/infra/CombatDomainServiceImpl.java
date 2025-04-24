package com.ttaengju.websocket.ai.infra;

import com.ttaengju.websocket.ai.domain.Combat;
import com.ttaengju.websocket.ai.domain.CombatDomainService;
import org.springframework.stereotype.Service;

@Service
public class CombatDomainServiceImpl implements CombatDomainService {

    @Override
    public Combat findCombatById(String id) {

        return Combat.builder()
                .id(id)
                .job("SUPPORT")
                .action("HILLER")
                .command("USE HELL")
                .build();

    }
}
