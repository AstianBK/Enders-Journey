package mc.duzo.beyondtheend.mixin.common;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.server.PlayerAdvancements;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(PlayerAdvancements.class)
public interface AdvancementsProgressAccessor {
    @Accessor("advancements")
    Map<Advancement, AdvancementProgress> list();
}
