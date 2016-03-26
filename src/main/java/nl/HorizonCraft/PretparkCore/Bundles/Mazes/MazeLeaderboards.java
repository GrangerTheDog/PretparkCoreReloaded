/*
 * Copyright (c) 2015-2016 Tim Medema
 *
 * This plugin has no licence on it. But that DOESN'T mean you can use it.
 * See the COPYRIGHT.txt for in the root for more information.
 *
 * You are allowed to:
 * - Read the code, and use it for educational purposes.
 * - Ask me questions about how this plugin works and what some of the components do.
 *
 * You are NOT allowed to:
 * - Use it without my explicit permission.
 */

package nl.HorizonCraft.PretparkCore.Bundles.Mazes;

import nl.HorizonCraft.PretparkCore.Profiles.MysqlManager;
import nl.HorizonCraft.PretparkCore.Utilities.MiscUtils;
import org.bukkit.block.Sign;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Cooltimmetje on 1/16/2016 at 7:49 PM.
 */
public class MazeLeaderboards{

    public static int[] maze1Time = new int[]{0,0,0,0,0};
    public static String[] maze1Name = new String[]{"???","???","???","???","???"};
    public static int[] maze2Time = new int[]{0,0,0,0,0};
    public static String[] maze2Name = new String[]{"???","???","???","???","???"};

    public static void load(boolean force){
        MysqlManager.loadLeadMaze1();

        Sign one1 = MazeLeaderboardsEnum.MAZE_1_1.getSign();
        one1.setLine(1, MiscUtils.color("&l" + maze1Name[0]));
        one1.setLine(2, MiscUtils.color("&4&l" + MiscUtils.formatTime(maze1Time[0])));
        one1.update();

        Sign two1 = MazeLeaderboardsEnum.MAZE_1_2.getSign();
        two1.setLine(1, MiscUtils.color("&l" + maze1Name[1]));
        two1.setLine(2, MiscUtils.color("&4&l" + MiscUtils.formatTime(maze1Time[1])));
        two1.update();

        Sign three1 = MazeLeaderboardsEnum.MAZE_1_3.getSign();
        three1.setLine(1, MiscUtils.color("&l" + maze1Name[2]));
        three1.setLine(2, MiscUtils.color("&4&l" + MiscUtils.formatTime(maze1Time[2])));
        three1.update();

//        MysqlManager.loadLeadMaze2();

//        Sign one2 = MazeLeaderboardsEnum.MAZE_2_1.getSign();
//        one2.setLine(1, MiscUtils.color("&l" + maze2Name[0]));
//        one2.setLine(2, MiscUtils.color("&4&l" + MiscUtils.formatTime(maze2Time[0])));
//        one2.update();
//
//        Sign two2 = MazeLeaderboardsEnum.MAZE_2_2.getSign();
//        two2.setLine(1, MiscUtils.color("&l" + maze2Name[1]));
//        two2.setLine(2, MiscUtils.color("&4&l" + MiscUtils.formatTime(maze2Time[1])));
//        two2.update();
//
//        Sign three2 = MazeLeaderboardsEnum.MAZE_2_3.getSign();
//        three2.setLine(1, MiscUtils.color("&l" + maze2Name[2]));
//        three2.setLine(2, MiscUtils.color("&4&l" + MiscUtils.formatTime(maze2Time[2])));
//        three2.update();

        if(!force) {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, 30);

            Sign time1 = MazeLeaderboardsEnum.MAZE_1_TIME.getSign();
//            Sign time2 = MazeLeaderboardsEnum.MAZE_2_TIME.getSign();
            time1.setLine(3, MiscUtils.color("&4&l" + dateFormat.format(cal.getTime())));
//            time2.setLine(3, MiscUtils.color("&4&l" + dateFormat.format(cal.getTime())));
            time1.update();
//            time2.update();
        }
    }


}
