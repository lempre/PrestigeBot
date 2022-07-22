package com.lmpr.prestigebot.listeners

import com.lmpr.prestigebot.Utils.sendFixMessage
import com.lmpr.prestigebot.data.Brain
import com.lmpr.prestigebot.data.Msg
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.util.*

class MessageListener : ListenerAdapter() {
    companion object {
        val lastmessage = HashMap<String, Msg>()
    }

    @Override
    override fun onMessageReceived(event: MessageReceivedEvent) {
        if(event.author.id == "797957464317624330") return

        val channel = event.channel

        val message = event.message
        val rawMessage = message.contentRaw
        val msgs = rawMessage.split(" ")

        if(msgs[0] == "<@797957464317624330>"){
            if(msgs.size == 1){
                channel.sendMessage("Masz jakiś problem???????").queue()
                return
            }
        }

        val lowerMessage = rawMessage.lowercase()

        when{
            lowerMessage.contains("jestem") -> {
                if (lowerMessage.contains("everyone") || lowerMessage.contains("here")) {
                    message.reply("Smacznej kawusi :)").queue()
                } else {
                    channel.sendFixMessage("Cześć "+rawMessage.substring(lowerMessage.indexOf("jestem")+7)+", jestem Yuumi!")
                }
            }

            rawMessage.contains("<@797957464317624330>") -> {
                channel.sendFixMessage(Brain.getDecision(lowerMessage.replace("<@797957464317624330>", "")))
                lastmessage.remove(event.channel.id)
            }

            else -> {
                if (!lastmessage.keys.contains(event.channel.id)) {
                    lastmessage[event.channel.id] = Msg(message.author, rawMessage)
                } else {
                    if (message.author != lastmessage[event.channel.id]!!.author) {
                        if (!rawMessage.contains("@") && rawMessage.length < 2001 && rawMessage != "") {
                            Brain.wordMap[lastmessage[event.channel.id]!!.text.lowercase()] = rawMessage
                        }

                        lastmessage[event.channel.id] = Msg(message.author, rawMessage)

                    } else {
                        lastmessage[event.channel.id]!!.text += " $rawMessage"
                    }
                }
            }
        }
    }
}