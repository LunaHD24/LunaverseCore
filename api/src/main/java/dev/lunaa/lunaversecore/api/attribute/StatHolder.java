package dev.lunaa.lunaversecore.api.attribute;

import java.util.Optional;
import java.util.Set;

public interface StatHolder {

    default Optional<StatEntry> getStat(StatType type) {
        Set<StatEntry> stats = getStats();
        for (StatEntry entry : stats) {
            if (entry.type() == type) return Optional.of(entry);
        }
        return Optional.empty();
    }

    Set<StatEntry> getStats();

    void setStat(StatType type, StatEntry entry);

    void setStats(Set<StatEntry> entries);

    default boolean hasStat(StatType type) {
        return getStat(type).isPresent();
    }

}
