package com.bot.discord.comandos.command.music;

import com.bot.discord.comandos.command.CommandContext;
import com.bot.discord.comandos.command.ICommand;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final MessageChannelUnion channel = ctx.getChannel();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inAudioChannel()) {
            channel.sendMessage("Não estou em um canal de voz").queue();
            return;
        }

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        final AudioManager audioManager = ctx.getGuild().getAudioManager();
        final AudioChannel memberChannel = memberVoiceState.getChannel();

        audioManager.closeAudioConnection();
        channel.sendMessageFormat("Saindo de `\uD83D\uDD0A %s`", memberChannel.getName()).queue();
    }

    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String getHelp() {
        return "Faz o bot sair do canal de voz";
    }
}
