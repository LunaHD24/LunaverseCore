package dev.lunaa.lunaversecore.api.item;

import dev.lunaa.lunaversecore.api.attribute.StatEntry;
import dev.lunaa.lunaversecore.api.item.base.CustomItemBase;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCustomItem implements CustomItemBase {

    private final Set<StatEntry> stats = new HashSet<>();

    @Override
    public Set<StatEntry> getStats() {
        return stats;
    }

    @Override
    public void setStats(Set<StatEntry> stats) {
        this.stats.clear();
        this.stats.addAll(stats);
    }

}
