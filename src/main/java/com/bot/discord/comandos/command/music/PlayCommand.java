package com.bot.discord.comandos.command.music;

import com.bot.discord.comandos.command.CommandContext;
import com.bot.discord.comandos.command.ICommand;
import lavaplayer.PlayerManager;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

import java.net.URI;
import java.net.URISyntaxException;

public class PlayCommand implements ICommand {
    @SuppressWarnings("ConstantConditions")
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = (TextChannel) ctx.getChannel();

        if (ctx.getArgs().isEmpty()) {
            channel.sendMessage("O uso correto é `!!play <youtube link>`").queue();
            return;
        }

        final Member self = ctx.getSelfMember();
        GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inAudioChannel()) {
            JoinCommand joinCommand = new JoinCommand();
            selfVoiceState = joinCommand.conectar(ctx, selfVoiceState);

            //channel.sendMessage("Preciso estar em um canal de voz para que isso funcione").queue();
            //return;
        }

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        if (!memberVoiceState.inAudioChannel()) {
            channel.sendMessage("Você precisa estar em um canal de voz para que este comando funcione").queue();
            return;
        }

        String link = String.join(" ", ctx.getArgs());

        if (!isUrl(link)) {
            link = "ytsearch:" + link;
        }

        PlayerManager.getInstance().loadAndPlay(channel, link);
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getHelp() {
        return "Tocar uma música\n" +
                "Uso: `!!play <youtube link>`";
    }

    private boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }
}
