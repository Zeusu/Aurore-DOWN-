package net.aurore.core;

import javax.security.auth.login.LoginException;
import javax.servlet.annotation.WebListener;

import net.aurore.core.node.AuroreNode;
import net.aurore.core.node.AuroreNodeBuilder;
import net.aurore.event.AuroreListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.aurore.lolservice.AuroreLoLService;
import net.aurore.lolservice.AuroreLoLServiceImpl;

@WebListener
public class AuroreCore{
		
	private static final Game GAME = Game.streaming(Config.PREFIX + "help for commands", "https://twitch.tv/chaine%27");
	
	private static final String TOKEN = "";
	
	private AuroreNode node;

	public AuroreCore(){
			
		AuroreNodeBuilder builder = (AuroreNodeBuilder) new AuroreNodeBuilder(AccountType.BOT).setToken(TOKEN);
		builder.setStatus(OnlineStatus.ONLINE);
		builder.setGame(GAME);
		
		try {
			node = (AuroreNode) builder.buildBlocking();
		} catch (LoginException | InterruptedException e) {
			e.printStackTrace();
		}
		node.addEventListener(new AuroreListener(node));
		node.setLoLService(((AuroreLoLService) new AuroreLoLServiceImpl()));
		node.init();
		System.out.println("[Aurore] Loaded");
	}
	
}
