package com.codisimus.plugins.buttonwarp.commands.commands;

import com.codisimus.plugins.buttonwarp.ButtonWarp;
import com.codisimus.plugins.buttonwarp.Warp;
import com.codisimus.plugins.buttonwarp.commands.CqCommand;
import com.codisimus.plugins.buttonwarp.menu.HelpCreateMenu;
import com.codisimus.plugins.buttonwarp.utils.Colorizer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Spearhartt on 1/30/2017.
 */
public class CommandMake implements CqCommand {
    @Override
    public boolean execute(CommandSender sender, ArrayList<String> args) {
        if(sender instanceof Player) {
            switch (args.size()) {
                case 2:
                    make((Player)sender, args.get(1), true);
                    return true;
                case 3:
                    if (args.get(2).equalsIgnoreCase("nowhere")) {
                        make((Player)sender, args.get(1), true);
                        return true;
                    }
                    break;
                default: break;
            }

            new HelpCreateMenu().ShowMenu((Player)sender);
            return true;
        }
        return false;
    }

    @Override
    public String getCommandLabel() {
        return "make";
    }

    private static void make(Player player, String name, boolean noWhere) {
        //Cancel if the Warp already exists
        if (ButtonWarp.findWarp(name) != null) {
            player.sendMessage(Colorizer.aColor + "A Warp named " + Colorizer.bColor + name + Colorizer.aColor + " already exists.");
            return;
        }

        if (noWhere) {
            //Create a Warp with a null Location
            ButtonWarp.addWarp(new Warp(name, null));
            player.sendMessage(Colorizer.aColor + "Warp " + Colorizer.bColor + name + Colorizer.aColor + " Made!");
        } else {
            //Create a Warp with the Player's Location
            ButtonWarp.addWarp(new Warp(name, player));
            player.sendMessage(Colorizer.aColor + "Warp " + Colorizer.bColor + name + Colorizer.aColor + " Made at current location!");
        }
    }
}
