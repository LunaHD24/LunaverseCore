package dev.lunaa.lunaversecore.api.item;

import dev.lunaa.lunaversecore.api.attribute.StatType;
import dev.lunaa.lunaversecore.api.attribute.StatValue;
import dev.lunaa.lunaversecore.api.item.base.CustomItemBase;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCustomItem implements CustomItemBase {

    private final Map<StatType, StatValue> statsMap = new HashMap<>();

    @Override
    public Map<StatType, StatValue> getStats() {
        return statsMap;
    }

    @Override
    public void setStats(Map<StatType, StatValue> stats) {
        statsMap.clear();
        statsMap.putAll(stats);
    }

}
