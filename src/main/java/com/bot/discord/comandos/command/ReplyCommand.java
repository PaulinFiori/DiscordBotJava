package com.bot.discord.comandos.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReplyCommand extends ListenerAdapter implements ICommand  {
    @Override
    public void handle(CommandContext ctx) {
        String[] command = ctx.getMessage().getContentRaw().split(" ");

        if("eae".equals(command[1].toString().toLowerCase())) ctx.getMessage().reply("Não eae não.").queue();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] command = event.getMessage().getContentRaw().split(" ");

        if("!eae".equals(command[0].toString().toLowerCase())) event.getMessage().reply("Não eae não.").queue();

        super.onMessageReceived(event);
    }

    @Override
    public String getHelp() {
        return "Responde mensagens\n" +
                "uso: !eae";
    }

    @Override
    public String getName() {
        return "reply";
    }
}
