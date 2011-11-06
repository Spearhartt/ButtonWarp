package com.codisimus.plugins.buttonwarp;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import org.bukkit.block.Block;

/**
 * A Button is a Block location and a Map of Users with times attached to it
 * 
 * @author Codisimus
 */
public class Button {
    public String world;
    public int x;
    public int y;
    public int z;
    public boolean takeItems = ButtonWarp.defaultTakeItems;
    public int max = ButtonWarp.defaultMax;
    public HashMap users = new HashMap(); //A map of each Player that activates the button {PlayerName=TimeActivated}

    /**
     * Constructs a new Button with the given Block
     * 
     * @param block The given Block
     */
    public Button (Block block) {
        world = block.getWorld().getName();
        x = block.getX();
        y = block.getY();
        z = block.getZ();
    }
    
    /**
     * Constructs a new Button with the given Block Location data
     * 
     * @param world The name of the World
     * @param x The x-coordinate of the Block
     * @param y The y-coordinate of the Block
     * @param z The z-coordinate of the Block
     */
    public Button (String world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * Updates the Player's time value in the Map with the current time
     * The time is saved as an array with DAY, HOUR, MINUTE, SECOND
     * 
     * @param player The Player whose time is to be updated
     */
    public void setTime(String player) {
        int[] time = new int[5];
        Calendar calendar = Calendar.getInstance();
        time[0] = 1;
        time[1] = calendar.get(Calendar.DAY_OF_YEAR);
        time[2] = calendar.get(Calendar.HOUR_OF_DAY);
        time[3] = calendar.get(Calendar.MINUTE);
        time[4] = calendar.get(Calendar.SECOND);
        users.put(player, time);
    }
    
    /**
     * Retrieves the time for the given Player
     * 
     * @param player The Player whose time is requested
     * @return The time as an array of ints
     */
    public int[] getTime(String player) {
        return (int[])users.get(player);
    }

    /**
     * Returns true if the given Block has the same Location data as this Button
     * 
     * @param block The given Block
     * @return True if the Location data is the same
     */
    public boolean isBlock(Block block) {
        if(block.getX() != x)
            return false;

        if(block.getY() != y)
            return false;

        if(block.getZ() != z)
            return false;

        return block.getWorld().getName().equals(world);
    }

    @Override
    public String toString() {
        String string = world+"'"+x+"'"+y+"'"+z+"'"+takeItems+"'"+max+"{";

        Iterator itr = users.keySet().iterator();
        while (itr.hasNext()) {
            String key = (String)itr.next();
            int[] time = getTime(key);

            string = string.concat(key+"'"+time[0]+"@"+time[1]+"'"+time[2]+"'"+time[3]+"'"+time[4]);
            
            if (itr.hasNext())
                string = string.concat(", ");
        }

        return string.concat("}");
    }
}