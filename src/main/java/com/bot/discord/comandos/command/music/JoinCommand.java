package com.bot.discord.comandos.command.music;

import com.bot.discord.comandos.command.CommandContext;
import com.bot.discord.comandos.command.ICommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.managers.AudioManager;

@SuppressWarnings("ConstantConditions")
public class JoinCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final MessageChannelUnion channel = ctx.getChannel();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if (selfVoiceState.inAudioChannel()) {
            channel.sendMessage("Já estou em um canal de voz").queue();
            return;
        }

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inAudioChannel()) {
            channel.sendMessage("Você precisa estar em um canal de voz para usar o comando").queue();
            return;
        }

        final AudioManager audioManager = ctx.getGuild().getAudioManager();
        final AudioChannel memberChannel = memberVoiceState.getChannel();

        audioManager.openAudioConnection(memberChannel);
        channel.sendMessageFormat("Conectando ao `\uD83D\uDD0A %s`", memberChannel.getName()).queue();
    }

    public GuildVoiceState conectar(CommandContext ctx, GuildVoiceState selfVoiceState) {
        final MessageChannelUnion channel = ctx.getChannel();

        if (selfVoiceState.inAudioChannel()) {
            channel.sendMessage("Já estou em um canal de voz").queue();
            return selfVoiceState;
        }

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inAudioChannel()) {
            channel.sendMessage("Você precisa estar em um canal de voz para usar o comando").queue();
            return selfVoiceState;
        }

        final AudioManager audioManager = ctx.getGuild().getAudioManager();
        final AudioChannel memberChannel = memberVoiceState.getChannel();

        audioManager.openAudioConnection(memberChannel);
        channel.sendMessageFormat("Conectando ao `\uD83D\uDD0A %s`", memberChannel.getName()).queue();

        return selfVoiceState;
    }

    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getHelp() {
        return "Faz o bot entrar no seu canal de voz";
    }
}
