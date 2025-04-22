package dev.lunaa.lunaversecore.api;

import dev.lunaa.lunaversecore.LunaverseCore;
import org.reflections.Reflections;

import java.util.Set;

public class ApiInitializer {

    public static void initialize() {
        Reflections reflections = new Reflections("dev.lunaa.lunaversecore.api");
        Set<Class<? extends ApiService>> apiServices = reflections.getSubTypesOf(ApiService.class);

        for (Class<? extends ApiService> apiService : apiServices) {
            try {
                ApiService service = apiService.getDeclaredConstructor().newInstance();
                service.initialize();
            } catch (Exception e) {
                LunaverseCore.getInstance().getPluginLogger().severe("Failed to initialize API service: " + apiService.getName());
                e.printStackTrace();
            }
        }
    }

}
