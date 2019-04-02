package fr.WarzouMc.JustSpawn.client.ParticlePackage;

import org.bukkit.entity.Player;

import fr.WarzouMc.JustSpawn.main;
import fr.WarzouMc.JustSpawn.client.ParticlePackage.Type.Types;

public class ParticleInstance {

	private static main main;
	
	public ParticleInstance(main main) {this.main = main;}

	public static void Instance(Player p, String path, int test, String pn) {
		
		if(main.isType(Types.HELIX_RIGHT_UP)) {

			main.getHelixP().creatHelixRU(p, path, test, pn);

		}else if(main.isType(Types.HELIX_RIGHT_DOWN)) {

			main.getHelixP().creatHelixRD(p, path, test, pn);

		}else if(main.isType(Types.HELIX_LEFT_UP)) {

			main.getHelixP().creatHelixLU(p, path, test, pn);

		}else if(main.isType(Types.HELIX_LEFT_DOWN)) {

			main.getHelixP().creatHelixLD(p, path, test, pn);

		}else if(main.isType(Types.BALL_UP)) {

			main.getBallP().creatBallU(p, path, test, pn);

		}else if(main.isType(Types.BALL_DOWN)) {

			main.getBallP().creatBallD(p, path, test, pn);

		}else if(main.isType(Types.BALL_LEFT)) {

			main.getBallP().creatBallL(p, path, test, pn);

		}else if(main.isType(Types.BALL_RIGHT)) {

			main.getBallP().creatBallR(p, path, test, pn);

		}else if(main.isType(Types.BALL_BACKWARD)) {

			main.getBallP().creatBallB(p, path, test, pn);

		}else if(main.isType(Types.BALL_FORWARD)) {

			main.getBallP().creatBallF(p, path, test, pn);

		}
		
	}

}
