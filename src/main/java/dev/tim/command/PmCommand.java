package dev.tim.command;

import dev.tim.Bot;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class PmCommand extends ListenerAdapter {

    private final Bot bot;

    public PmCommand(Bot bot){
        this.bot = bot;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("pm")){

            if(!event.getMember().hasPermission(Permission.KICK_MEMBERS)){
                event.reply("Je hebt geen permissie om deze commando te gebruiken!").setEphemeral(true).queue();
                return;
            }

            OptionMapping option = event.getOption("bericht");

            if(option == null){
                event.reply("Optie niet meegegeven!").setEphemeral(true).queue();
                return;
            }

            String message = option.getAsString();

            bot.getShardManager().getUserById(event.getMember().getId()).openPrivateChannel().complete().sendMessage(message).queue();

            event.reply("PM gestuurd!").setEphemeral(true).queue();
        }
    }
}
