package blankin.music.util;

import static blankin.music.config.SheetMusicConfig.InstrumentMutePlayers;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class InstrumentMuteUtil {

  private static NamespacedKey muteKey;

  public static void init(JavaPlugin plugin) {
    muteKey = new NamespacedKey(plugin, "instrument_mute");
  }

  // 플레이어 데이터(PDC)에 저장된 차단 상태를 메모리로 불러온다.
  public static void loadMute(Player player) {
    if (player.getPersistentDataContainer().has(muteKey, PersistentDataType.BYTE)) {
      InstrumentMutePlayers.add(player.getUniqueId());
    }
  }

  // 차단 상태를 뒤집고, 차단되었으면 true 를 반환한다.
  public static boolean toggleMute(Player player) {
    var playerUUID = player.getUniqueId();
    var dataContainer = player.getPersistentDataContainer();

    if (InstrumentMutePlayers.remove(playerUUID)) {
      dataContainer.remove(muteKey);
      return false;
    }

    InstrumentMutePlayers.add(playerUUID);
    dataContainer.set(muteKey, PersistentDataType.BYTE, (byte) 1);
    return true;
  }

}
