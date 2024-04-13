import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.restaction.PermissionOverrideAction;

import java.awt.Color;
import java.util.List;

public class Bot extends ListenerAdapter {
    public static final Color color = new Color(254, 22, 95);
    public static JDA jda;
    public static final Long CEO_ROLE_ID = 1228405281424146452L;
    public static final String[] links = {
            "https://i.pinimg.com/originals/fb/fc/d3/fbfcd3a4aef4dcb1bb1ce6ea1f23830a.gif",
            "https://i.pinimg.com/originals/bd/0a/83/bd0a831c21dca4f865a6a8cc6ee66fab.gif",
            "https://i.pinimg.com/originals/ed/aa/7a/edaa7ab9b0abbb2e6e6d8a43bbbd3f65.gif",
            "https://i.pinimg.com/originals/d3/c7/6c/d3c76cd994b55a8121353ca95e292b9a.gif",
            "https://i.pinimg.com/originals/cc/77/c7/cc77c73075e343563d4b1736a7fb8fe9.gif",
            "https://i.pinimg.com/originals/31/e5/a3/31e5a32e8c72de66b996b6a72f416f16.gif",
            "https://i.pinimg.com/originals/71/88/d6/7188d6e75a8f5305f0413b815be3bad3.gif"
    };
    public static final String token = "MTIyNjYzMTE1NTA4NDk1NTY4OA.G8jyKW.a-AVciAQkxhAtS4pDQVK7bXDCsJmoRLFv0vo6w";
    private static final Long JS_ROLE_ID = 1227697647788232815L;
    private static final Long PY_ROLE_ID = 1227697828780966070L;
    private static final Long JAVA_ROLE_ID = 1227697425528127519L;
    private static final Long NODEJS_ROLE_ID = 1227697785709531166L;
    private static final Long GOLANG_ROLE_ID = 1227697682546425876L;
    private static final Long JS_EMOJI_ID = 1228299014441799801L;
    private static final Long PY_EMOJI_ID = 1228298993000513536L;
    private static final Long JAVA_EMOJI_ID = 1228298358985199688L;
    private static final Long GOLANG_EMOJI_ID = 1228302009614995506L;
    private static final Long SUPPORTER_ROLE_ID = 1226647989674840116L;
    private static final Long OPENED_TICKET_ROLE_ID = 1228395383659565206L;
    private static final Long NODEJS_EMOJI_ID = 1228298949006196766L;
    private static final Long MEMBER_ROLE_ID = 1228616085641760779L;

     static void sendMessageWithColor(String channelTitle, ChannelType type, String text, String title, String imageUrl) {
         MessageChannel channel = null;
         switch (type) {
             case NEWS:
                 channel = jda.getNewsChannelsByName(channelTitle, true).get(0);
                 break;
             case TEXT:
             case RULES:
                 channel = jda.getTextChannelsByName(channelTitle, true).get(0);
                 break;
         }
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(color);
        embedBuilder.setTitle(title);
        embedBuilder.setDescription(text);
        if (imageUrl != null) {
            embedBuilder.setImage(imageUrl);
        }
         MessageEmbed embed = embedBuilder.build();
         channel.sendMessageEmbeds(embed).queue();
    }


    /**
     * Код ниже используется для категорий с языками!
     */
    static void sendMessageWithColorAndButtons(String channelTitle, ChannelType channelType, String text, String title, String imageUrl, String buttonID, String label) {
        MessageChannel channel = null;
        Button makeTicket = Button.secondary(buttonID, Emoji.fromCustom("java", 1228298358985199688L, false));
        switch (channelType) {
            case NEWS:
                channel = jda.getNewsChannelsByName(channelTitle, true).get(0);
                break;
            case TEXT:
            case RULES:
                channel = jda.getTextChannelsByName(channelTitle, true).get(0);
                break;
        }
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(color);
        embedBuilder.setTitle(title);
        embedBuilder.setDescription(text);
        if (imageUrl != null) {
            embedBuilder.setImage(imageUrl);
        }
        MessageEmbed embed = embedBuilder.build();
        channel.sendMessageEmbeds(embed).setActionRow(makeTicket).queue();
    }

    static void sendMessageInUpdates(String title, String text) {
        String imageUrl = links[1];
        MessageChannel channel = jda.getNewsChannelsByName("updates",true).get(0);
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(color);
        embedBuilder.setTitle(title);
        embedBuilder.setDescription(text);
        if (imageUrl != null) {
            embedBuilder.setImage(imageUrl);
        }
        MessageEmbed embed = embedBuilder.build();
        channel.sendMessageEmbeds(embed).queue();
    }

    static void sendMessageInRoles(String title, String text, String imageUrl) {
        MessageChannel channel = jda.getTextChannelsByName("roles", true).get(0);
        Button java = Button.secondary("javaRole", Emoji.fromCustom("java", JAVA_EMOJI_ID, false));
        Button python = Button.secondary("pythonRole", Emoji.fromCustom("python", PY_EMOJI_ID, false));
        Button js = Button.secondary("jsRole", Emoji.fromCustom("js", JS_EMOJI_ID, false));
        Button nodeJS = Button.secondary("nodeJSRole", Emoji.fromCustom("nodeJS", NODEJS_EMOJI_ID, false));
        Button goLang = Button.secondary("golangRole", Emoji.fromCustom("golang",GOLANG_EMOJI_ID, false));

        EmbedBuilder embedBuilder = new EmbedBuilder();
        if (imageUrl != null) {
            embedBuilder.setImage(imageUrl);
        }
        embedBuilder.setColor(color);
        embedBuilder.setTitle(title);
        embedBuilder.setDescription(text);

        MessageEmbed embed = embedBuilder.build();
        channel.sendMessageEmbeds(embed).setActionRow(java, python, js, nodeJS, goLang).queue();
    }

    static void sendMessageInHowToUse(String title, String text, String imageUrl) {
        MessageChannel channel = jda.getTextChannelsByName("how-to-use", true).get(0);
        Button java = Button.secondary("createJavaTicket", Emoji.fromCustom("java", JAVA_EMOJI_ID, false));
        Button python = Button.secondary("createPyTicket", Emoji.fromCustom("python", PY_EMOJI_ID, false));
        Button js = Button.secondary("createJSTicket", Emoji.fromCustom("js", JS_EMOJI_ID, false));
        Button nodeJS = Button.secondary("createNodeJSTicket", Emoji.fromCustom("nodeJS", NODEJS_EMOJI_ID, false));
        Button goLang = Button.secondary("createGoLangTicket", Emoji.fromCustom("golang",GOLANG_EMOJI_ID, false));

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(color);
        embedBuilder.setTitle(title);
        embedBuilder.setDescription(text);
        if (imageUrl != null) {
            embedBuilder.setImage(imageUrl);
        }
        MessageEmbed embed = embedBuilder.build();
        channel.sendMessageEmbeds(embed).setActionRow(java, python, js, nodeJS, goLang).queue();
    }

    private static void sendMessageInTicket(long Id, String title, String text, String imageUrl) {
        TextChannel channel = jda.getTextChannelById(Id);
        Button closeTicket = Button.success("closeTicket", "Закрыть тикет");
        Button changeTopicTicket = Button.primary("changeTopicTicket", "Изменить название");

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(color);
        embedBuilder.setTitle(title);
        embedBuilder.setDescription(text);
        if (imageUrl != null) {
            embedBuilder.setImage(imageUrl);
        }
        MessageEmbed embed = embedBuilder.build();
        channel.sendMessageEmbeds(embed).setActionRow(closeTicket, changeTopicTicket).queue();
    }

    private static void editMessageTicket(Message message) {
        //TODO ТУТА!
        Button changeTopicTicket = Button.primary("changeTopicTicket", "Изменить название");

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(color);
        embedBuilder.setTitle("Инструкция");
        embedBuilder.setDescription(Messages.ticketMessageClosed);
        if (message.getEmbeds().get(0).getImage() != null) {
            embedBuilder.setImage(message.getEmbeds().get(0).getImage().getUrl());
        }
        MessageEmbed embed = embedBuilder.build();
        message.editMessageEmbeds(embed).setActionRow(changeTopicTicket).queue();
    }

    static void buttonInteractionHandler(ButtonInteractionEvent event) {
        if (event.getComponentId().endsWith("Role")) {
            roleHandler(event);
        }
        if (event.getComponentId().endsWith("Ticket")) {
            ticketsHandler(event);
        }
    }

    static void createTextChannel(ButtonInteractionEvent event, String categoryName) {
        Member member = event.getMember();
        String name = event.getMember().getEffectiveName();
        Guild guild = event.getGuild();
        Category category = guild.getCategoriesByName(categoryName, true).get(0);
        Role supporterRole = guild.getRoleById(SUPPORTER_ROLE_ID);
        Role javaRole = guild.getRoleById(JAVA_ROLE_ID);
        Role nodeJSRole = guild.getRoleById(NODEJS_ROLE_ID);
        Role pythonRole = guild.getRoleById(PY_ROLE_ID);
        Role jsRole = guild.getRoleById(JS_ROLE_ID);
        Role memberRole = guild.getRoleById(MEMBER_ROLE_ID);
        Role openedTicket = guild.getRoleById(OPENED_TICKET_ROLE_ID);
        Role botRole = guild.getRolesByName("Hexlet Supports", true).get(0);
        String textChannelName = name + "-ticket";

        category.createTextChannel(textChannelName)
                .addPermissionOverride(member, Permission.ALL_TEXT_PERMISSIONS | Permission.VIEW_CHANNEL.getRawValue(), Permission.MANAGE_CHANNEL.getRawValue())
                .addPermissionOverride(supporterRole, Permission.ALL_TEXT_PERMISSIONS | Permission.VIEW_CHANNEL.getRawValue(), Permission.MANAGE_CHANNEL.getRawValue())
                .addPermissionOverride(javaRole, 0, Permission.ALL_PERMISSIONS)
                .addPermissionOverride(nodeJSRole, 0, Permission.ALL_PERMISSIONS)
                .addPermissionOverride(pythonRole, 0, Permission.ALL_PERMISSIONS)
                .addPermissionOverride(jsRole, 0, Permission.ALL_PERMISSIONS)
                .addPermissionOverride(memberRole, 0, Permission.ALL_PERMISSIONS)
                .addPermissionOverride(botRole, Permission.ALL_PERMISSIONS, 0)
                .queue(channel -> {
                    event.reply("Тикет успешно создан! Перейдите в категорию " + categoryName).setEphemeral(true).queue();
                    sendMessageInTicket(channel.getIdLong(), "Инструкция",Messages.ticketMessageOpened, randomGif(links));
                });
        event.getGuild().addRoleToMember(member, openedTicket).queue();
    }

    static void roleHandler(ButtonInteractionEvent event) {
        Member member = event.getMember();
        List<Role> memberRoles = member.getRoles();
        Role role = null;
        Role roleForDelete = null;
        switch (event.getComponentId()) {
            case "jsRole":
                roleForDelete = hasRole(memberRoles, "javascript") ? event.getGuild().getRoleById(JS_ROLE_ID) : null;
                role = !hasRole(memberRoles, "javascript") ? event.getGuild().getRoleById(JS_ROLE_ID) : null;
                break;
            case "pythonRole":
                roleForDelete = hasRole(memberRoles, "python") ? event.getGuild().getRoleById(PY_ROLE_ID) : null;
                role = !hasRole(memberRoles, "python") ? event.getGuild().getRoleById(PY_ROLE_ID) : null;
                break;
            case "nodeJSRole":
                roleForDelete = hasRole(memberRoles, "nodejs") ? event.getGuild().getRoleById(NODEJS_ROLE_ID) : null;
                role = !hasRole(memberRoles, "nodejs") ? event.getGuild().getRoleById(NODEJS_ROLE_ID) : null;
                break;
            case "javaRole":
                roleForDelete = hasRole(memberRoles, "java") ? event.getGuild().getRoleById(JAVA_ROLE_ID) : null;
                role = !hasRole(memberRoles, "java") ? event.getGuild().getRoleById(JAVA_ROLE_ID) : null;
                break;
            case "golangRole":
                roleForDelete = hasRole(memberRoles, "go") ? event.getGuild().getRoleById(GOLANG_ROLE_ID) : null;
                role = !hasRole(memberRoles, "go") ? event.getGuild().getRoleById(GOLANG_ROLE_ID) : null;
                break;
        }
        if (role != null && roleForDelete == null) {
            event.getGuild().addRoleToMember(member, role).queue();
            event.reply("Роль " + role.getName() + " успешно выдана!").setEphemeral(true).queue();
        } else {
            event.getGuild().removeRoleFromMember(member, roleForDelete).queue();
            event.reply("Роль " + roleForDelete.getName() + " снята!").setEphemeral(true).queue();
        }
    }

    static void ticketsHandler(ButtonInteractionEvent event) {
        Member member = event.getMember();
        Role supporterRole = event.getGuild().getRoleById(SUPPORTER_ROLE_ID);
        Role openedTicket = event.getGuild().getRoleById(OPENED_TICKET_ROLE_ID);
        List<Role> memberRoles = member.getRoles();
        boolean isOpened = isOpenedTicket(memberRoles);
        TextChannel textChannel;
        switch (event.getComponentId()) {
            case "createJSTicket":
                if (!isOpened) {
                    if (hasRole(memberRoles, "javascript")) {
                        createTextChannel(event, "javascript");
                    } else {
                        event.reply("Для создания тикета необходима роль JavaScript!").setEphemeral(true).queue();
                    }
                    } else {
                        event.reply("У вас уже есть созданный тикет, перед созданием нового закройте старый!").setEphemeral(true).queue();
                    }
                    break;

            case "createPyTicket":
                if (!isOpened) {
                    if (hasRole(memberRoles, "python")) {
                        createTextChannel(event, "python");
                    } else {
                         event.reply("Для создания тикета необходима роль Python!").setEphemeral(true).queue();
                    }
                } else {
                     event.reply("У вас уже есть созданный тикет, перед созданием нового закройте старый!").setEphemeral(true).queue();
                }
                break;

            case "createJavaTicket":
                 if (!isOpened) {
                     if (hasRole(memberRoles, "java")) {
                         createTextChannel(event, "java");
                     } else {
                         event.reply("Для создания тикета необходима роль Java!").setEphemeral(true).queue();
                     }
                 } else {
                     event.reply("У вас уже есть созданный тикет, перед созданием нового закройте старый!").setEphemeral(true).queue();
                 }
                 break;

            case "createNodeJSTicket":
                 if (!isOpened) {
                     if (hasRole(memberRoles, "nodejs")) {
                         createTextChannel(event, "nodejs");
                     } else {
                        event.reply("Для создания тикета необходима роль NodeJS!").setEphemeral(true).queue();
                     }
                 } else {
                     event.reply("У вас уже есть созданный тикет, перед созданием нового закройте старый!").setEphemeral(true).queue();
                    }
                 break;

            case "createGoLangTicket":
                 if (!isOpened) {
                     if (hasRole(memberRoles, "go")) {
                         createTextChannel(event, "go");
                     } else {
                         event.reply("Для создания тикета необходима роль Go!").setEphemeral(true).queue();
                     }
                 } else {
                     event.reply("У вас уже есть созданный тикет, перед созданием нового закройте старый!").setEphemeral(true).queue();
                    }
                 break;

            case "closeTicket":
                 if (isOpened) {
                     event.reply("Тикет закрыт!").queue();
                     event.getGuild().removeRoleFromMember(member, openedTicket).queue();

                     textChannel = event.getMessage().getChannel().asTextChannel();
                     PermissionOverrideAction permissions = textChannel.upsertPermissionOverride(supporterRole);
                     permissions.deny(Permission.VIEW_CHANNEL)
                             .queue();

                     Message messageEmb = event.getMessage();
                     editMessageTicket(messageEmb);
                 }
                break;
            case "changeTopicTicket":
                 textChannel = event.getMessage().getChannel().asTextChannel();
                 TextChannel finalTextChannel = textChannel;
                 textChannel.getHistory().retrievePast(1).queue(messages -> {
                    if (!messages.get(0).getContentRaw().isEmpty()) {
                         String message = messages.get(0).getContentRaw().toLowerCase()
                                 .replaceAll("тема", "");
                         finalTextChannel.getManager().setName(message).queue();
                         event.reply("Название изменено!").setEphemeral(true).queue();
                    } else {
                        event.reply("Название не может быть пустым!").setEphemeral(true).queue();
                    }
                    });
                 break;
            }
    }
    static void guildJoinHandler(GuildMemberJoinEvent event) {
        Guild guild = event.getGuild();
        Member member = event.getMember();
        Role memberRole = guild.getRoleById(MEMBER_ROLE_ID);
            guild.addRoleToMember(member, memberRole).queue();

    }

    private static boolean isOpenedTicket(List<Role> roles) {
        for (Role role : roles) {
            if (role.getName().equalsIgnoreCase("opened")) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasRole(List<Role> roles, String compareTo) {
        for (Role role : roles) {
            if (role.getName().equalsIgnoreCase(compareTo)) {
                return true;
            }
        }
        return false;
    }

    private static String randomGif(String[] links) {
        return links[(int) Math.round(Math.random() * (links.length - 2) + 1)];
    }
}
