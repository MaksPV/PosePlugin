package ru.armagidon.poseplugin.poses.swim;

import org.bukkit.entity.Player;

public interface ISwimAnimationHandler
{
    void play(Player target);
    void stop();
}