package com.lmpr.prestigebot.data

import kotlin.math.absoluteValue

object Brain {
    val wordMap: HashMap<String, String> = HashMap()

    init {
        wordMap[""] = "???"
    }

    fun getDecision(string: String) : String {
        if (!wordMap.containsKey(string)) {
            var highestSimilarity = -1.0
            var stringKey: String? = null

            for (key in wordMap.keys) {
                val similarity = key.getFullSimilarity(string)

                if (similarity > highestSimilarity) {
                    highestSimilarity = similarity
                    stringKey = key
                }
            }

            if (highestSimilarity > 90 && highestSimilarity < 100) {
                wordMap[string] = wordMap[stringKey]!!
            }

            return wordMap[stringKey]!!
        }

        return wordMap[string]!!
    }

    private fun String.getFullSimilarity(anotherString: String) : Double {
        val words1 = this.split(" ")
        val words2 = anotherString.split(" ")
        var similarity = 0.0

        if (words1 == words2) return 100.0

        for (word1 in words1) {
            val lenght1 = word1.length
            var highestSimilarity = 0.0

            if (lenght1 < 1) continue

            if (words2.contains(word1)) {
                similarity += 100/words1.size
            } else {
                for (word2 in words2) {
                    val lenght2 = word2.length

                    if (lenght2 < 1) continue

                    if ((lenght1 - lenght2).absoluteValue / (lenght1 + lenght2) * 100 < 33) {
                        val actualSimilarity = word1.toLowerCase().getWordSimilarity(word2.toLowerCase())

                        if (actualSimilarity > highestSimilarity) {
                            highestSimilarity = actualSimilarity
                        }
                    }
                }

                similarity += highestSimilarity / words1.size
            }
        }

        return similarity
    }

    private fun String.getWordSimilarity(anotherWord: String) : Double {
       var max = anotherWord.length

        if (this.length < anotherWord.length) {
            max = this.length
        }

        for (i in 0 until max) {
            if (this[i] != anotherWord[i]) {
                return (this.length/(this.length-i)-1)*100.0
            }
        }

        return 100.0
    }
}