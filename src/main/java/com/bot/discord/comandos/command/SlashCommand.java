package com.bot.discord.comandos.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommand extends ListenerAdapter implements ICommand{
    @Override
    public void handle(CommandContext ctx) {
    }

    @Override
    public String getHelp() {
        return "Responde comandos slash\n" +
                "uso: /eae";
    }

    @Override
    public String getName() {
        return "slash";
    }
}
