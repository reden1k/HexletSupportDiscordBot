import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws InterruptedException {
        JDA jda = JDABuilder.createDefault(Bot.token)
                .addEventListeners(new Main())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                .setActivity(Activity.competing("LeetCode!"))
                .setBulkDeleteSplittingEnabled(false)
                .build();

        jda.awaitReady();
        Bot.jda = jda;

        Guild guild = jda.getGuildsByName("Hexlet Supports", true).get(0);
        guild.updateCommands().addCommands(
                Commands.slash("hi", "say hi back")
        ).queue();

//        Bot.sendMessageWithColor("welcome", ChannelType.NEWS, Messages.welcome, "Добро пожаловать!", Bot.links[4]);
//        Bot.sendMessageWithColor("rules", ChannelType.RULES, Messages.rules, "Правила", "https://i.pinimg.com/564x/27/86/e8/2786e8afc91a348b470724fd30e2925f.jpg");
//        Bot.sendMessageInRoles("Получение роли", Messages.roles, "https://i.pinimg.com/originals/d3/dc/2c/d3dc2c6d1c1efe0782dd6d7ac322b1a3.gif");
//        Bot.sendMessageInHowToUse("Как создать тикет", Messages.howToUse, "https://i.pinimg.com/originals/d3/c7/6c/d3c76cd994b55a8121353ca95e292b9a.gif");
//        Bot.sendMessageInUpdates("Обновления", Messages.updatesMessage);
//        Bot.sendMessageWithColor("supports-rules", ChannelType.TEXT, Messages.supporterRules, "Правила для Supporters", Bot.links[5]);
    }
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) { // Метод для обработки callback'ов кнопок
        Bot.buttonInteractionHandler(event);
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("hi")) {
            event.reply("Hello").queue();
        }
    }
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Bot.guildJoinHandler(event);
    }
}
