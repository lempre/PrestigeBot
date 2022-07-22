package com.lmpr.prestigebot

import com.lmpr.prestigebot.listeners.MessageListener
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent

object PrestigeBot {
    @JvmStatic
    fun main(args: Array<String>) {
        val builder = JDABuilder.createDefault(Token.ID)

        builder.setActivity(Activity.playing("Smigło jest kręcone"))
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
        builder.addEventListeners(MessageListener())

        builder.build()
    }
}