package com.lmpr.prestigebot

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageChannel
import java.awt.Color

object Utils {
    fun MessageChannel.sendFixMessage(msg: String){
        this.sendTyping().queue()
        this.sendMessage(msg).queue()
    }

    fun MessageChannel.sendEmbed(msg: String, title: String, color: Color){
        val builder = EmbedBuilder()

        builder.setTitle(title)
        builder.setColor(color)
        builder.setDescription(msg)
        builder.setFooter("PrestigeBot - made by lmpR#9852")

        this.sendMessageEmbeds(builder.build()).queue()
    }
}