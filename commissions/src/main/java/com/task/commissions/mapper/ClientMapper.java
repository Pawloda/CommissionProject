package com.task.commissions.mapper;

import com.task.commissions.domain.ClientEntity;
import com.task.commissions.type.ClientTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientMapper {

    public static ClientTO toClientTO(ClientEntity client) {
        if(Objects.equals(null, client)) {
            return null;
        }
        return ClientTO.builder()
                .id(client.getId())
                .build();
    }

    public static ClientEntity toClientEntity(ClientTO client) {
        if(Objects.equals(null, client)) {
            return null;
        }
        return ClientEntity.builder()
                .id(client.getId())
                .build();
    }

    public static List<ClientTO> map2TOs(List<ClientEntity> clients) {
        if(Objects.equals(null, clients)) {
            return null;
        }
        List<ClientTO> result = new ArrayList<>();
        for(ClientEntity client : clients) {
            if(!Objects.equals(null, client)) {
                result.add(toClientTO(client));
            }
        }
        return result;
    }

    public static List<ClientEntity> map2Entities(List<ClientTO> clients) {
        if(Objects.equals(null, clients)) {
            return null;
        }
        List<ClientEntity> result = new ArrayList<>();
        for(ClientTO client : clients) {
            if(!Objects.equals(null, client)) {
                result.add(toClientEntity(client));
            }
        }
        return result;
    }

}
