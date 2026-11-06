package blankin.music.eventListener;

import blankin.music.util.InstrumentMuteUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinHandler implements Listener {

  @EventHandler
  private void onPlayerJoin(PlayerJoinEvent event) { //연주차단 상태 복원
    InstrumentMuteUtil.loadMute(event.getPlayer());
  }

}
