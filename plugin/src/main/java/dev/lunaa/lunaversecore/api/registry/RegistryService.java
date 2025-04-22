package dev.lunaa.lunaversecore.api.registry;

import dev.lunaa.lunaversecore.LunaverseCore;
import dev.lunaa.lunaversecore.api.ApiService;

public final class RegistryService implements ApiService {

    public void initialize() {
        RegistryProvider.setRegistry(LunaverseCore.registry);
    }

}
