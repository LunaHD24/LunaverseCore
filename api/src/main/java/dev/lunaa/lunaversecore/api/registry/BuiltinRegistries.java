package dev.lunaa.lunaversecore.api.registry;

import dev.lunaa.lunaversecore.LunaverseApi;
import dev.lunaa.lunaversecore.api.attribute.StatType;
import dev.lunaa.lunaversecore.api.entity.CustomEntity;
import dev.lunaa.lunaversecore.api.item.base.CustomItemBase;

public class BuiltinRegistries {

    public static final Registry<CustomItemBase> ITEM = LunaverseApi.getRegistry();
    public static final Registry<StatType> STAT_TYPE = LunaverseApi.getRegistry();
    public static final Registry<CustomEntity> ENTITY = LunaverseApi.getRegistry();

}
