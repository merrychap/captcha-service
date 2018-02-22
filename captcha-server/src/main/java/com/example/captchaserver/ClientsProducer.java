package com.example.captchaserver;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ClientsProducer {
    private HashMap<UUID, Client> clients;

    public ClientsProducer() {
        clients = new HashMap<>();
    }

    public Client createNewClient() {
        UUID publicUUID = generateUniqueUUID();
        Client generatedClient = new Client(publicUUID, UUID.randomUUID());

        clients.put(publicUUID, generatedClient);
        return generatedClient;
    }

    public Client getClient(String clientUUID) {
        return clients.get(UUID.fromString(clientUUID));
    }

    public Boolean isClientPresent(String clientUUID) {
        return getClient(clientUUID) != null;
    }

    private UUID generateUniqueUUID() {
        UUID someUUID;
        do {
            someUUID = UUID.randomUUID();
        } while (clients.containsKey(someUUID));
        return someUUID;
    }
}
