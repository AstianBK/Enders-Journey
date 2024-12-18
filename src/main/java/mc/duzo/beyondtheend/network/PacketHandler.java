package mc.duzo.beyondtheend.network;

import mc.duzo.beyondtheend.EndersJourney;
import mc.duzo.beyondtheend.network.message.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1.0";
    public static SimpleChannel MOD_CHANNEL;

    public static void registerMessages() {
        int index = 0;
        SimpleChannel channel= NetworkRegistry.ChannelBuilder.named(
                        new ResourceLocation(EndersJourney.MODID, "messages"))
                .networkProtocolVersion(()-> PROTOCOL_VERSION)
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        MOD_CHANNEL=channel;

        channel.registerMessage(index++, PacketLeavingDimension.class, PacketLeavingDimension::write,
                PacketLeavingDimension::new, PacketLeavingDimension::handle);

        channel.registerMessage(index++, PacketTower.class, PacketTower::write,
                PacketTower::new, PacketTower::handle);

        channel.registerMessage(index++, PacketSync.class, PacketSync::write,
                PacketSync::new, PacketSync::handle);

        channel.registerMessage(index++, PacketCloneSync.class, PacketCloneSync::write,
                PacketCloneSync::new, PacketCloneSync::handle);

        channel.registerMessage(index++, PacketUpdateChuck.class, PacketUpdateChuck::write,
                PacketUpdateChuck::new, PacketUpdateChuck::handle);


        channel.registerMessage(index++, PacketTravelDimension.class, PacketTravelDimension::write,
                PacketTravelDimension::new, PacketTravelDimension::handle);





    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        MOD_CHANNEL.send(PacketDistributor.PLAYER.with(() -> player),message);
    }

    public static <MSG> void sendToServer(MSG message) {
        MOD_CHANNEL.sendToServer(message);
    }

    public static <MSG> void sendToAllTracking(MSG message, LivingEntity entity) {
        MOD_CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), message);
    }
}
